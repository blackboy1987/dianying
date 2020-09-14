
package com.bootx.common;

import com.bootx.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * 消息
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
public class Result {

	@JsonView({BaseEntity.ViewView.class, BaseEntity.ListView.class, BaseEntity.EditView.class, BaseEntity.BaseView.class})
	private Integer code;

	@JsonView({BaseEntity.ViewView.class, BaseEntity.ListView.class, BaseEntity.EditView.class, BaseEntity.BaseView.class})
	private String msg;

	@JsonView({BaseEntity.ViewView.class, BaseEntity.ListView.class, BaseEntity.EditView.class, BaseEntity.BaseView.class})
	private Object data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 构造方法
	 */
	public Result() {
	}

	public Result(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public static Result success(String msg, Object data) {
		return new Result(0, msg, data);
	}

	public static Result success(Object data) {
		return new Result(0, "成功", data);
	}

	public static Result error(String msg) {
		return new Result(-1, msg, null);
	}

	public static Result error(Integer code,String msg) {
		return new Result(code, msg, null);
	}

}