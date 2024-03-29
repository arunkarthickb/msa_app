package com.aspiresys.leavemicroservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import static springfox.documentation.builders.PathSelectors.regex;

// TODO: Auto-generated Javadoc
/**
 * Class SwaggerConfig has the configuration required for swagger to run
 */
@Configuration
public class SwaggerConfig {
	
	/**
	 * Product api.
	 *
	 * @return the docket
	 */
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.aspiresys.leavemicroservice.controller"))
				.paths(regex("/employee.*")).build();
	}
	
}