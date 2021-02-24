package com.bootx.interceptor;

import com.bootx.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interceptor - 跨域拦截器
 * 
 * @author blackboy
 * @version 1.0
 */
public class ValidateInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private AppService appService;


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println(request.getRequestURI());
		if(request.getRequestURI().contains("/es") || request.getRequestURI().contains("/cache")){
			return true;
		}

		/*String appCode = request.getParameter("appCode");
		String appSecret = request.getParameter("appSecret");

		App app = appService.findByAppCode(appCode);
		if(app==null||!StringUtils.equals(appSecret,app.getToken())){
			Map<String, Object> data = new HashMap<>();
			data.put("data", Result.error("非法调用"));
			response.setStatus(999);
			response.setContentType("text/html; charset=UTF-8");
			JsonUtils.writeValue(response.getWriter(), data);
			return false;
		}*/
		return true;
	}
}