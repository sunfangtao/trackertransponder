package com.aioute.trans.coder;

import com.aioute.trans.model.TracketPacket;
import com.aioute.trans.util.Util;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备数据信息的解码操作 TODO
 */
public class ServerDecoder extends CumulativeProtocolDecoder {

    // 包头 设备号 命令字 消息体 包尾
    // 1 字节 12 字节 4 字节 N 字节（N 不大于 1K） 1 字节
    // 每一个完整的数据帧都必须包含包头标志、设备号、命令字、消息体和包尾标志
    // 以 0x28H （即字符“（”作为开始标志符），以0x29H（即字符“）”作为结束标志符

    private static final Logger log = Logger.getLogger(ServerDecoder.class);

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {

        session.getConfig().setReadBufferSize(1024);

        if (in.remaining() <= 0) {
            return false;
        }

        int value = in.get() & 0xFF;// 读取1字节
        if (value == 0x28) {// 包开始
            if (in.remaining() >= 17) {
                // 数据包最短为18个字节,已经读取了1个
                List<Integer> values = new ArrayList<Integer>();
                values.add(0x28);

                TracketPacket tracketPacket = new TracketPacket();

                while (in.hasRemaining()) {
                    value = in.get() & 0xFF;// 读取1字节
                    values.add(value);
                    if (value == 0x29) {// 包结束
                        log.info("解析新数据：" + Util.getPacketString(values));
                        String success = tracketPacket.parse(Util.getPacketString(values));
                        if (success == null) {
                            out.write(tracketPacket);
                        } else {
                            log.info(success);
                            // 解析出现问题，关闭连接
                            session.close(true);
                        }
                        if (in.remaining() > 0) {// 如果读取内容后还粘了包，就让父类再重读一次，进行下一次解析
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
                session.close(true);
            } else {
                // 不够一个包的长度，清空
                while (in.hasRemaining()) {
                    in.get();
                }
                session.close(true);
            }
        } else {
            // 第一个字节出错，清空
            while (in.hasRemaining()) {
                in.get();
            }
            session.close(true);
        }
        return false;// 处理成功，让父类进行接收下个包
    }
}
