package com.dewen.project.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * 项目全局基类，抽一些公共方法在这里面
 * 
 * @author harlin
 */
public class BaseUtils {
	
	/**
	 * 获取一个UUID字符串
	 * 
	 * @return
	 */
	public static String getUUID() {
		return NumberUtils.getUUID();
	}
	
	/**
	 * 上传单个文件到指定目录，并返回文件相对路径
	 * 
	 * @param multipartFile
	 * @param folder
	 * @return
	 * @throws IOException
	 */
	public String uploadFile(MultipartFile multipartFile, final String folder, HttpServletRequest request) throws IOException {
		return FileUtils.uploadFile(multipartFile, folder, request);
	}

	public Double getJSONDouble(JSONObject obj, final String key) {
		return JSONUtils.getJSONDouble(obj, key);
	}
	
	public JSONObject getJSONObject(JSONObject obj, final String key) {
		return JSONUtils.getJSONObject(obj, key);
	}
	
	public Boolean getJSONBoolean(JSONObject obj, final String key) {
		return JSONUtils.getBoolean(obj, key);
	}

	public Boolean getJSONBoolean(JSONObject obj, final String key, final Boolean defaultValue) {
		return JSONUtils.getBoolean(obj, key, defaultValue);
	}
	
	public JSONArray getJSONArray(JSONObject obj, final String key) {
		return JSONUtils.getJSONArray(obj, key);
	}
	
	public Integer getJSONInteger(JSONObject obj, final String key) {
		return getJSONInteger(obj, key, null);
	}
	
	public Integer getJSONInteger(JSONObject obj, final String key, final Integer defaultValue) {
		return JSONUtils.getInteger(obj, key, defaultValue);
	}
	
	public String getJSONString(JSONObject obj, final String key) {
		return getJSONString(obj, key, null);
	}
	
	public String getJSONString(JSONObject obj, final String key, final String defaultValue) {
		return JSONUtils.getString(obj, key, defaultValue);
	}

	public Long getJSONLong(JSONObject obj, final String key) {
		return getJSONLong(obj, key, null);
	}

	public Long getJSONLong(JSONObject obj, final String key, final Long defaultValue) {
		return JSONUtils.getLong(obj, key, defaultValue);
	}
	
	public Date getDate() {
		return new Date();
	}
	
	public Date getDate(final String date) {
		return TimeUtils.getDate(date);
	}
	
}