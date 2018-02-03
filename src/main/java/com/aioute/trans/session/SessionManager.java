package com.aioute.trans.session;

import org.apache.mina.core.session.IoSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {

	public static final String OPERATING = "operating";

	public static Map<String, IoSession> sessionMap = new ConcurrentHashMap<String, IoSession>();

	public static void addSession(String key, IoSession session) {
		sessionMap.put(key, session);
	}

	public static void removeSession(String key) {
		sessionMap.remove(key);
	}

	public static IoSession getSession(String key) {
		return sessionMap.get(key);
	}
}
