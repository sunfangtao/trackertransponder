package com.aioute.trans.coder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * 对设备数据信息的编码操作 TODO
 */
public class ServerEncoder implements ProtocolEncoder {

	public void dispose(IoSession session) throws Exception {

	}

	public void encode(IoSession session, Object data, ProtocolEncoderOutput out) throws Exception {
		try {
			String msg = (String) data;
			msg = msg.replace(" ", "");
			IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
			int length = msg.length() / 2;
			for (int i = 0; i < length; i++) {
				buf.put((byte) ((int) Integer.valueOf(msg.substring(i * 2, i * 2 + 2), 16)));
			}
			buf.flip();
			out.write(buf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
