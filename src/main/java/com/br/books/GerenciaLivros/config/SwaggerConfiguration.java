package com.br.books.GerenciaLivros.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

public class SwaggerConfiguration {

	public OpenAPI customOpenAPI() {
		return new OpenAPI().components(new Components())
				.info(new Info().title("Swagger").description("Documentação do sistema de gestão de livros"));
	}
	
	
}
