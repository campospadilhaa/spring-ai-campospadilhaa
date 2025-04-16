package br.com.campospadilhaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	// configurações para permsisão de Cors
	// aplicações React e Angular, antes de efetuar uma requisição, requisição realizada primeiramente uma chamada do tipo 'options' para idenificar quais são os verbos disponíveis na API

	@Bean
	public WebMvcConfigurer mvcConfigurer() {

		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry corsRegistry) {

				// permitido acesso a todos os endpoint's
				corsRegistry.addMapping("/**")

					// permitido cors nestes endpoins
					.allowedOrigins("http://localhost:8080/", "http://localhost:3000/")

					// quais os verbos são permitidos
					.allowedMethods("GET", "POST", "PUT", "DELETE", "PATH", "OPTIONS")

					// permitidos todos os headers
					.allowedHeaders("*")

					.allowCredentials(true);
			}
		};
	}
}