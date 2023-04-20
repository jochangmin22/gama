package com.category.common;

import java.io.File;

import org.apache.commons.lang.RandomStringUtils;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class Common {

	private Common() {
		throw new IllegalStateException("Utility class");
	}

	public static final String SITE = "https://adulti12.com/";

	public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Whale/1.0.40.10 Safari/537.36";

	public static final String REFERER = "https://www.naver.com/";

	public static final String ACCEPT_LANGUAGE = "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4";

	public static final int TIME_OUT = 60000;

	public static final String EMPTY = "";


	public static boolean isOrEquals(Object source, String[] target) {
		if (source == null)
			return false;
		boolean result = false;
		for (int i = 0; i < target.length; i++) {
			result = source.equals(Common.nvl(target[i]));
			if (result)
				break;
		}
		return result;
	}

	/**
	 * @param target
	 * @return
	 */
	public static boolean isOrEquals(Object source, Object... target) {
		if (source == null)
			return false;
		boolean result = false;
		for (int i = 0; i < target.length; i++) {
			result = source.equals(Common.nvl(target[i]));
			if (result)
				break;
		}
		return result;
	}

	/**
	 * @param target
	 * @return
	 */
	public static boolean isEquals(Object source, Object target) {
		return ((source == null) ? false : (target == null) ? true : source.equals(target));
	}

	/**
	 * @param target
	 * @return
	 */
	public static boolean isNotEquals(Object source, Object target) {
		return ((source == null) ? true : (target == null) ? false : !source.equals(target));
	}

	/**
	 * Java String isEmpty This Java String isEmpty shows how to check whether the
	 * given string is empty or not using isEmpty method of Java String class.
	 *
	 * @param target
	 * @return
	 */
	public static boolean isEmpty(Object target) {
		return nvl(target).isEmpty();
	}

	/**
	 * Java String isEmpty This Java String isEmpty shows how to check whether the
	 * given string is empty or not using isEmpty method of Java String class.
	 *
	 * @param target
	 * @return
	 */
	public static boolean isNotEmpty(Object target) {
		return !isEmpty(target);
	}

	public static String nvl(Object target) {
		return nvl(target, Common.EMPTY);
	}

	public static String nvl(Object target, String defaultStr) {
		String result = defaultStr;
		if (target != null) {
			if (!String.valueOf(target).toLowerCase().equals("null")) return String.valueOf(target);
		}
		return result;
	}
	
	/**
	 * Null to Empty String
	 *
	 * @param target
	 * @return
	 */
	public static int nvz(Object target) {
		return nvz(target, 0);
	}

	/**
	 * Null to Empty String
	 *
	 * @param target
	 * @return
	 */
	public static int nvz(Object target, int defaultNum) {
		int result = defaultNum;
		if (target != null) {
			if (!String.valueOf(target).toLowerCase().equals("null") && !String.valueOf(target).equals("")) {
				try {
					return Integer.parseInt(String.valueOf(target));
				} catch (Exception e) {
					return defaultNum;
				}
			}
		}
		return result;
	}


	public static boolean mkdirs(final String path) {
		boolean flag = false;
		try {
			File _file = new File(path);
			if (!_file.isDirectory()) flag = _file.mkdirs();
			else flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static String removeInvalidName(String fileName) {
		String[] invalidName = { "\\\\", "/", ":", "[*]", "[?]", "\"", "<", ">", "[|]" };
		for (int i = 0; i < invalidName.length; i++) {
			fileName = fileName.replaceAll(invalidName[i], ".");
		}
		return fileName;
	}

	public static String random() {
		StringBuilder b = new StringBuilder(RandomStringUtils.randomAlphanumeric(16).toUpperCase());
		for (int i = b.length() - 1; i > 0; i--) {
			if (i % 4 == 0) {
				b.insert(i, "-");
			}
		}
		return b.toString();
	}
	
	public static JSONObject toJSONObject(Object obj) {
		if (Common.isEmpty(obj))
			return new JSONObject();

		JSON json = JSONSerializer.toJSON(obj);
		if (json instanceof JSONObject)
			return (JSONObject) json;

		return new JSONObject();
	}
	
	public static JSONArray toJSONArray(Object obj) {
		if (Common.isEmpty(obj))
			return new JSONArray();

		JSON json = JSONSerializer.toJSON(obj);
		if (json instanceof JSONArray)
			return (JSONArray) json;

		return new JSONArray();
	}
}
