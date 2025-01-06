package com.share;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableFeignClients
@SpringBootApplication
public class DailySharePriceApplication {

	@Bean
	public Docket configureSwagger() {
		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.ant("/**"))
				.apis(RequestHandlerSelectors.basePackage("com.share.controller")).build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Daily Share Price Api",
				"Daily Share Price with Authenticating User and check for validity of token", "1.0", "path/to/terms",
				new Contact("Team 3", "http://www.pod2cts.com", "Shreya.Agrawal@cognizant.com"), "API License",
				"http://www.pd3cts.com", Collections.emptyList());
	}

	public static void main(String[] args) {
		SpringApplication.run(DailySharePriceApplication.class, args);
	}

}
