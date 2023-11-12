package com.polarbookshop.catalogservice02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CatalogService02Application {

	public static void main(String[] args) {
		SpringApplication.run(CatalogService02Application.class, args);
	}

}
