package com.bootx.interceptor;

import com.bootx.common.Result;
import com.bootx.entity.App;
import com.bootx.service.AppService;
import com.bootx.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Interceptor - 跨域拦截器
 * 
 * @author blackboy
 * @version 1.0
 */
public class ValidateInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private AppService appService;

	/**
	 * 全局拦截器，用判断请求是否是合法请求
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(request.getRequestURI().startsWith("/init")){
			return true;
		}
		App app = appService.get(request);
		if(app==null){
			Map<String, Object> data = new HashMap<>();
			data.put("data", Result.error("非法调用123"));
			response.setStatus(999);
			response.setContentType("text/html; charset=UTF-8");
			JsonUtils.writeValue(response.getWriter(), data);
			return false;
		}
		return true;
	}
}