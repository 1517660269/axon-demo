package com.example.demo.security;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.exception.TokenException;
import com.example.demo.query.user.model.User;
import com.example.demo.query.user.repository.UserRepository;
import com.example.demo.utils.TokenUtil;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
			return true;
		}

		// 如果不是映射到方法直接通过
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		//检查是否有 PassToken 注释，有则跳过认证
		if (method.isAnnotationPresent(PassToken.class)) {
			PassToken passToken = method.getAnnotation(PassToken.class);
			if (passToken.required()) {
				return true;
			}
		}

		if (method.isAnnotationPresent(UserLoginToken.class)) {
			UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
			if (userLoginToken.required()) {
				String token = request.getHeader("Authorization");
				if (token == null)
					throw new TokenException("token 认证失败");

				User user = this.userRepository.findUserByUsernameOrMobile(TokenUtil.getAccountByJwt(token));
				if (user == null)
					throw new TokenException("token 认证失败");

				if (TokenUtil.verify(token))
					return true;
			}
		}

		throw new TokenException("token 认证失败");
	}
}
