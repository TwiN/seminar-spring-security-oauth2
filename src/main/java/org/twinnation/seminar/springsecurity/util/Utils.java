package org.twinnation.seminar.springsecurity.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;


public class Utils {
	
	/**
	 * Prevents instantiation
	 */
	private Utils() {}
	
	
	public static String jsonReply(Object... o) {
		Map<Object, Object> msg = new HashMap<>();
		if (!((o.length & 1) == 1)) {
			for (int i = 1; i < o.length; i += 2) {
				msg.put(o[i - 1], o[i]);
			}
		} else {
			System.out.println("BAD NUMBER OF ARGUMENT INSERTED. MUST BE EVEN. TODO: EXCEPTION INSTEAD OF SYSOUT");
		}
		return new Gson().toJson(msg);
	}
	
	
	public static boolean checkIfJsonReplyHasError(String jsonReply) {
		return Boolean.parseBoolean(new Gson().fromJson(jsonReply, JsonObject.class).get("ERROR").toString());
	}
	
}
