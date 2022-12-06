package com.face.boot;

import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.faces.webapp.FacesServlet;

@SpringBootApplication
public class BootApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BootApplication.class, Initializer.class);
	}

	@Bean
	public OkHttpClient httpClient() {
		return new OkHttpClient();
	}

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		FacesServlet servlet = new FacesServlet();
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servlet, "*.xhtml");
		servletRegistrationBean.setName("Faces Servlet");
		return servletRegistrationBean;
	}
}
