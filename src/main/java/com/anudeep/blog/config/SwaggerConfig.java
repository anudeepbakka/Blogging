package com.anudeep.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {
	public static final String AuthorizationHeader="Authorization";
	private ApiKey apikey(){
		return new ApiKey("JWT",AuthorizationHeader,"header");
	}



	private List<SecurityContext> securityContext(){
		return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
	}

	private List<SecurityReference> sf(){
		AuthorizationScope scope = new AuthorizationScope("global","Access Everything");
	return Arrays.asList(new SecurityReference("JWT",new AuthorizationScope[] {scope}));
	}

	@Bean
	public Docket api() {



		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getInfo())
				.securityContexts(securityContext())
				.securitySchemes(Arrays.asList(apikey()))
				.select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo getInfo() {
		
		return new ApiInfo("Blogging Application : Backend Course","This Project is developed by Anudeep","1.0","Terms of Service",new Contact("Anudeep","bakkaanudeep.com","bakkaanudeep12@gmail.com"),"Liscenese","UrL", Collections.emptyList());

	}
}
