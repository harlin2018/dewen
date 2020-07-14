package com.dewen.project.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 响应JSON数据格式封装
 * 
 * @author harlin
 *
 */
public class ResultJSON {
	
	// 请求处理是否成功
	private Boolean succeed;
	// 响应的消息
	private String message;
	// 响应码
	private Integer code;
	// 响应的具体数据
	private Map<String,Object> data;
	
	public ResultJSON(){
		data = new HashMap<String, Object>();
	}
	
	/**
	 * 添加数据到data中
	 * 
	 * @param key
	 * @param val
	 */
	public void addData(String key,Object val){
		data.put(key, val);
	}
	
	/**
	 * 添加分页信息到data中
	 * 
	 * @param page
	 */
	public <T> void addPageData(Page<T> page){
		if (page == null) {
			return;
		}
		JSONObject pageObj = new JSONObject();
		pageObj.put("pageNo", page.getPageNo());
		pageObj.put("pageSize", page.getPageSize());
		pageObj.put("totalCount", page.getTotalCount());
		pageObj.put("totalPages", page.getTotalPages());
		pageObj.put("hasNext", page.isHasNext());
		pageObj.put("hasPre", page.isHasPre());
		data.put("page", pageObj);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public Boolean getSucceed() {
		return succeed;
	}

	public void setSucceed(Boolean succeed) {
		this.succeed = succeed;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * 设置消息
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 设置消息和成功状态
	 * 
	 * @param message
	 * @param succeed
	 */
	public void setMessage(String message, Boolean succeed) {
		this.message = message;
		this.succeed = succeed;
		this.code = 0;
	}
	
	/**
	 * 设置消息和成功状态和响应码
	 * 
	 * @param message
	 * @param succeed
	 * @param code
	 */
	public void setMessage(String message, Boolean succeed, int code) {
		this.message = message;
		this.succeed = succeed;
		this.code = code;
	}

}