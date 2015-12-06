package br.com.fornax.corporativo.status.config.web;

import java.io.IOException;
import java.util.Properties;

import javax.inject.Inject;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles2.TilesConfigurer;

@PropertySources({ @PropertySource("classpath:/mail/mail-config.properties") })

@EnableScheduling
@Configuration
@EnableWebMvc
@ComponentScan(value = { "br.com.fornax.corporativo.status" })
public class WebSpringConfig extends WebMvcConfigurerAdapter {

	@Inject
	private Environment env;
	
	@Bean
	JavaMailSenderImpl javaMail() {
		JavaMailSenderImpl mail = new JavaMailSenderImpl();
		mail.setUsername(env.getProperty("br.com.fornax.corporativo.mail.username"));
		mail.setPassword(env.getProperty("br.com.fornax.corporativo.mail.password"));
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", env.getProperty("br.com.fornax.corporativo.mail.protocol"));
		properties.setProperty("mail.smtp.auth", env.getProperty("br.com.fornax.corporativo.mail.auth"));
		properties.setProperty("mail.smtp.starttls.enable", env.getProperty("br.com.fornax.corporativo.mail.starttls"));
		properties.setProperty("mail.smtp.debug", env.getProperty("br.com.fornax.corporativo.mail.debug"));
		properties.setProperty("mail.smtp.host", env.getProperty("br.com.fornax.corporativo.mail.host"));
		properties.setProperty("mail.smtp.port", env.getProperty("br.com.fornax.corporativo.mail.port"));
		properties.setProperty("mail.smtp.ssl.trust", env.getProperty("br.com.fornax.corporativo.mail.ssl"));
		mail.setJavaMailProperties(properties);
		return mail;
	}
		
	@Bean
	public VelocityEngine velocityEngine() throws VelocityException, IOException {
		VelocityEngineFactoryBean velocityFactory = new VelocityEngineFactoryBean();
		velocityFactory.setResourceLoaderPath("/mail");
		return velocityFactory.createVelocityEngine();
	}
	
	@Override
	// Carrega as configurações default do Spring
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	// Resolve as requisições de chamadas de páginas jsp, xhtml, html...
	// Utilizando tiles
	public ViewResolver configureViewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver
				.setViewClass(org.springframework.web.servlet.view.tiles2.TilesView.class);
		return viewResolver;
	}

	@Bean
	// Inicializa a configuração do tiles para a criação de templates
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions("/tiles/tiles-config.xml");
		return tilesConfigurer;
	}
}