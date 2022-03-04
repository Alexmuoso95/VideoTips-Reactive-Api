package com.videotips.functional.programing.handler;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.videotips.entity.Device;
import com.videotips.functional.programing.repository.DeviceRepositoryReactive;

import reactor.core.publisher.Mono;

@Component
public class DeviceServiceHandler {
	
	private DeviceRepositoryReactive deviceRepository;
	
	public DeviceServiceHandler(DeviceRepositoryReactive repo) {
		this.deviceRepository = repo;
	}

	public Mono<ServerResponse> addDevice (ServerRequest request){
		return request.bodyToMono(Device.class)
						.flatMap(deviceRepository::save)
						.flatMap(ServerResponse.status(HttpStatus.CREATED)::bodyValue);
	}
}
