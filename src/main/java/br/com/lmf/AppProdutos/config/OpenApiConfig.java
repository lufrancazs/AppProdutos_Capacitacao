package br.com.lmf.AppProdutos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI()
				.components(
						new Components().addSecuritySchemes("bearerAuth",
								new SecurityScheme().type(SecurityScheme.Type.HTTP)
								.scheme("bearer").bearerFormat("JWT")
								)
						)
				.info(
						new Info()
						.title("App de Cadastro de Produtos e Estoque")
						.description("Este app faz cadastro e controle de produtos, além do estoque")
						.contact(new Contact()
								.name("Lucas Martins")
								.email("lucasfranca_zs@hotmail.com")
								.url("http://localhost:8080")).version("Versão 0.0.1 SNAPSHOT")
						)
				.addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
	}

}
