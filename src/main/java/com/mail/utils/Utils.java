package com.mail.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;

public class Utils {
	
	public static String[] toArray(String str) {
		String[] array = str.split(", ");		
		return array;
	}
	
	public static String listToString(List<String> list) {
		if (list.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (String string : list) {
			if (first) {
				first = false;
			} else {
				sb.append(",");
			}
			sb.append(string);
		}
		return sb.toString();
	}
	
	public static List<String> toList(String string) {
		final String[] array = string.split(",");
		final List<String> list = new ArrayList<String>();
		if (array != null) {
			for (String keString : array) {
				list.add(keString.trim());
			}
		}
		return list;
	}
	
	public static String toJoiner(String string) {
		List<String> list = Utils.toList(string);
		return Joiner.on(",").join(list);
	}

}
