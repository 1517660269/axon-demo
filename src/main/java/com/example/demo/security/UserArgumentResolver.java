package com.example.demo.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.example.demo.query.user.model.User;
import com.example.demo.query.user.repository.UserRepository;
import com.example.demo.utils.TokenUtil;

@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

	@Autowired
	private UserRepository userRepository;

	private static final String SCHEMA = "Bearer ";

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(CurrentUserId.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		String header = request.getHeader("Authorization");
		User user = this.userRepository.findByUsername(TokenUtil.getAccountByJwt(header.substring(SCHEMA.length())));
		return user.getId();
	}
}
