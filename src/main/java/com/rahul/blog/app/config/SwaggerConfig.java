package com.rahul.blog.app.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	public static final String AUTHRIZATION_HEAD="Authorization";
	
	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHRIZATION_HEAD, "header");
	}
	
	private List<SecurityContext> contexts(){
		return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
	}
	
	
	private List<SecurityReference> sf(){
		
	AuthorizationScope scope=new AuthorizationScope("global", "accessEverything");
		
		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] {scope}));
		
	}
	
    @Bean
    Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo()).securityContexts(contexts())
        		.securitySchemes(Arrays.asList(apiKey()))
        		.select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();

    }

	private ApiInfo getInfo() {
		// TODO Auto-generated method stub
		return new ApiInfo("Blogging Application : Backend Course", "This Project Developed By Rahul", "1.0", "Terms of Service", new Contact("Rahul", null, "Rahul@dev.com"), "Licence Of API", "API Lincese URL",new ArrayList<>());
	}
}
