package com.videotips.functional.programing.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.videotips.functional.programing.handler.DeviceServiceHandler;

@Configuration
public class DeviceRouter {
	@Bean
	public RouterFunction<ServerResponse> deviceRoute(DeviceServiceHandler deviceServiceHandler){
		return RouterFunctions.route()
								.GET("/v1/hello", (request -> ServerResponse.ok().bodyValue("SUCCESS")))
								.POST("/v1/hello", (request -> deviceServiceHandler.addDevice(request)))
								.build();
	}
}
