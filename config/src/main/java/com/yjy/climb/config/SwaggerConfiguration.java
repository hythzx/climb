package com.yjy.climb.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

	/**
	 * 自定义 OpenAPI，控制swagger界面可以输入访问令牌并在全局生效
	 * <a href="https://github.com/springdoc/springdoc-openapi/issues/696">Github issue</a>
	 * @return OpenAPI
	 */
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components()
						.addSecuritySchemes("访问令牌", new SecurityScheme()
								.type(SecurityScheme.Type.HTTP)
								.scheme("bearer").bearerFormat("JWT")))
				.addSecurityItem(new SecurityRequirement()
						.addList("访问令牌"));
	}
}
