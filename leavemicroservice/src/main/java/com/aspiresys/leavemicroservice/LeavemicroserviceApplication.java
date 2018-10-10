package com.aspiresys.leavemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

// TODO: Auto-generated Javadoc
/**
 * The Class LeavemicroserviceApplication.
 */
@EnableSwagger2
@SpringBootApplication
@EnableDiscoveryClient
public class LeavemicroserviceApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(LeavemicroserviceApplication.class, args);
	}
}
