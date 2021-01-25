package com.juxi.lingshibang.common.utils;


import org.apache.http.HttpStatus;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author hongqiang.chai
 * 返回数据通用类
 */
public class Result extends TreeMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public Result() {
		put("code", 0);
		put("msg", "获取数据成功");
	}

	public Result(Throwable e) {
		super();
		put("code", HttpStatus.SC_INTERNAL_SERVER_ERROR);
		put("msg", e.getMessage());

	}
	
	public static Result error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}
	
	public static Result error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}
	
	public static Result error(int code, String msg) {
		Result r = new Result();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static Result error(Throwable e) {
		Result r = new Result();
		r.put("code", HttpStatus.SC_INTERNAL_SERVER_ERROR);
		r.put("msg", e.getMessage());
		return r;
	}

	public static Result ok(String msg) {
		Result r = new Result();
		r.put("msg", msg);
		return r;
	}
	
	public static Result ok(Map<String, Object> map) {
		Result r = new Result();
		r.putAll(map);
		return r;
	}
	
	public static Result ok() {
		return new Result();
	}

	@Override
	public Result put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
