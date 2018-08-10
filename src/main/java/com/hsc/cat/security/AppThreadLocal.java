package com.hsc.cat.security;

import java.util.Map;

public class AppThreadLocal {
	public static final ThreadLocal<Map<String, Object>> userThreadLocal = new ThreadLocal<Map<String, Object>>();

	public static void set(Map<String, Object> user) {
		userThreadLocal.set(user);
	}

	public static void unset() {
		userThreadLocal.remove();
	}

	public static Map<String, Object> get() {
		return userThreadLocal.get();
	}
}
