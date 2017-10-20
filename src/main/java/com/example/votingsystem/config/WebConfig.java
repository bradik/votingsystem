package com.example.votingsystem.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.validation.ConstraintViolationProblemModule;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	public WebConfig() {
		super();
	}

	@Override
	public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		converters.add(new MappingJackson2HttpMessageConverter());
	}


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("**/*.css", "**/*.js", "**/*.map", "**/*.html")
				.addResourceLocations("classpath:static/","classpath:META-INF/resources/")
				.setCachePeriod(0);
	}


	@Bean
	public ObjectMapper objectMapper() {
		//problem-spring-web
		return new ObjectMapper()
				.registerModule(new ProblemModule())
				.registerModule(new ConstraintViolationProblemModule());
	}
}