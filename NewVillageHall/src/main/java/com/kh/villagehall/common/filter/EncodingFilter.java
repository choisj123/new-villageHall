package com.kh.villagehall.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter(filterName="encodingFilter", urlPatterns="/*")
public class EncodingFilter extends HttpFilter implements Filter{
	

	public void init(FilterConfig fConfig) {
		System.out.println("문자 인코딩 필터 초기화");
	}

	public void destroy() {
		System.out.println("문자 인코딩 필터 파괴");
		
	}
	

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		
		// 모든 요청의 문자 인코딩 UTF-8로 설정
		request.setCharacterEncoding("UTF-8");
		
		// 모든 응답의 문자 인코딩 UTF-8로 설정
		response.setCharacterEncoding("UTF-8");
		
		 // ------------------------------------
		
		// application scope로 최상위 경로를 얻어올 수 있는 값 세팅
		
		// application 내장 객체 얻어오기
		ServletContext application = request.getServletContext();
		
		// 최상위 주소 얻어오기
		String contextPath = ((HttpServletRequest)request).getContextPath();
		
		// 세팅
		application.setAttribute("contextPath", contextPath);
		
		// 연결된 다음 필터 수행(없으면 Servlet 수행)
		chain.doFilter(request, response);
		
	}

}
