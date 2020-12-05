package com.spring.codeblog;

import com.spring.codeblog.utils.SecurityFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CodeblogApplication {

	private static final String URL_PATTERN = "/v1/*";

	public static void main(String[] args) {
		SpringApplication.run(CodeblogApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<SecurityFilter> jwtFilter() {
		final FilterRegistrationBean<SecurityFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new SecurityFilter());
		registrationBean.addUrlPatterns(URL_PATTERN);
		return registrationBean;
	}

}
