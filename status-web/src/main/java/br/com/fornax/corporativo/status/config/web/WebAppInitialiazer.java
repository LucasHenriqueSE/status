package br.com.fornax.corporativo.status.config.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitialiazer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		
		// Le as configurações do Spring que foram feitas via classe e anotações
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(WebSpringConfig.class);

		// Le as anotações contidas na classe do Spring
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(WebSpringConfig.class);

		// Faz com que o sistema entenda as requisições a partir da "/"
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
				"dispatcher", new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}

}