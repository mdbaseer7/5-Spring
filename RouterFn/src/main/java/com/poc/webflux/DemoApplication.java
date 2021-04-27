package com.poc.webflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.poc.webflux.handler.CustomerHandler;


// lombok installation
// identify sts location
// this class was in demo package unable to autowire handler
// change port in app.config to 9090
// pom. plugin section   excluded in jar via plugin 
// dependency set to optional as true that means compile take but for the jar its optional, even for other dependent of this jar
// in dependency tree you can see

// 
@Configuration
@SpringBootApplication
public class DemoApplication {

	@Autowired
	private CustomerHandler handler;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	// @Autowired
	// private CustomerStreamHandler streamHandler;

	@Bean
	public RouterFunction<ServerResponse> routerFunction() {
		return RouterFunctions.route().GET("/router/customers", handler::loadCustomers)
				// .GET("/router/customers/stream",streamHandler::getCustomers)
				.GET("/router/customer/{input}", handler::findCustomer)
				.POST("/router/customer/save", handler::saveCustomer).build();

	}

}
