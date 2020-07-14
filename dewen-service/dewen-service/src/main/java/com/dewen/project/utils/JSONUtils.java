package com.dewen.project.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * JSON操作
 * 
 * @author harlin
 *
 */
public class JSONUtils {

	public static Long getLong(JSONObject obj, final String key, final Long defaultValue) {
		if (obj.containsKey(key)) {
			return obj.getLong(key);
		} else {
			return defaultValue;
		}
	}

	public static Long getLong(JSONObject obj, final String key) {
		return getLong(obj, key, null);
	}
	
	/**
	 * 获取JSON对象中指定Key的值 - JSONDouble
	 * 
	 * @param obj
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Double getJSONDouble(JSONObject obj, final String key, final Double defaultValue) {
		if (obj.containsKey(key)) {
			return obj.getDouble(key);
		} else {
			return defaultValue;
		}
	}
	
	public static Double getJSONDouble(JSONObject obj, final String key) {
		return getJSONDouble(obj, key, null);
	}
	
	/**
	 * 获取JSON对象中指定Key的值 - JSONObject
	 * 
	 * @param obj
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static JSONObject getJSONObject(JSONObject obj, final String key, final JSONObject defaultValue) {
		if (obj.containsKey(key)) {
			return obj.getJSONObject(key);
		} else {
			return defaultValue;
		}
	}
	
	public static JSONObject getJSONObject(JSONObject obj, final String key) {
		return getJSONObject(obj, key, null);
	}
	
	/**
	 * 获取JSON对象中指定Key的值 - Boolean类型
	 * 
	 * @param obj
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Boolean getBoolean(JSONObject obj, final String key, final Boolean defaultValue) {
		if (obj.containsKey(key)) {
			return obj.getBoolean(key);
		} else {
			return defaultValue;
		}
	}
	
	public static Boolean getBoolean(JSONObject obj, final String key) {
		return getBoolean(obj, key, null);
	}
	
	/**
	 * 获取JSON对象中指定Key的值 - Integer类型
	 * 
	 * @param obj
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Integer getInteger(JSONObject obj, final String key, final Integer defaultValue) {
		if (obj.containsKey(key)) {
			return obj.getInteger(key);
		} else {
			return defaultValue;
		}
	}
	
	public static Integer getInteger(JSONObject obj, final String key) {
		return getInteger(obj, key, null);
	}
	
	/**
	 * 获取JSON对象中指定Key的值 - String类型
	 * 
	 * @param obj
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getString(JSONObject obj, final String key, final String defaultValue) {
		if (obj.containsKey(key)) {
			return obj.getString(key);
		} else {
			return defaultValue;
		}
	}

	public static JSONArray getJSONArray(JSONObject obj, final String key) {
		return getJSONArray(obj, key, null);
	}
	
	public static JSONArray getJSONArray(JSONObject obj, final String key, final JSONArray defaultValue) {
		if (obj.containsKey(key)) {
			return obj.getJSONArray(key);
		} else {
			return defaultValue;
		}
	}
	
	public static String getString(JSONObject obj, final String key) {
		return getString(obj, key, null);
	}

	/**
	 * 将响应的JSON对象写到response中
	 * 
	 * @param result
	 * @param response
	 */
	public static void outResultJSON(ResultJSON result, HttpServletResponse response) {
		if (result == null)
			return;
		try {
			String jsonString = JSON.toJSONString(result);
			wirteAndEnd(jsonString, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将对象以UTF-8的格式写到response中
	 * 
	 * @param ob
	 * @param response
	 * @throws IOException
	 */
	public static void wirteAndEnd(Object ob, HttpServletResponse response) throws IOException {
		wirteAndEnd(ob, "UTF-8", response);
	}

	/**
	 * 向response中写指定编码格式的对象
	 * 
	 * @param ob
	 * @param charset
	 * @param response
	 * @throws IOException
	 */
	public static void wirteAndEnd(Object ob, String charset, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=" + charset);
		PrintWriter out = response.getWriter();
		writer(ob, out);
		out.flush();
		out.close();
	}

	/**
	 * 将对象写到out中
	 * 
	 * @param ob
	 * @param out
	 * @throws IOException
	 */
	public static void writer(Object ob, PrintWriter out) throws IOException {
		out.print(ob);
	}
	
	/**
	 * 将一个Map对象转为json字符串
	 * 
	 * @param jsonMap
	 * @return
	 */
	public static String getJSONString(final Map<String, Object> jsonMap) {
		return JSONObject.toJSONString(jsonMap, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
	}
	
}