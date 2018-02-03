package com.aioute.trans.coder;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * 设备通信数据的编码工厂 TODO
 */
public class ServerCodeFactory implements ProtocolCodecFactory {

	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return new ServerDecoder();
	}

	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return new ServerEncoder();
	}

}
