package com.videotips.service;

import com.videotips.entity.Device;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DeviceService {

	public Mono<Device> addDevice(Device device); 
	public Flux<Device> getDevices(); 
	public Mono<Device> getDevice(String id,String model); 
	public Mono<Device> updateDevice(Device device,String id); 
	public Mono<Void> deleteDevice(String id); 
	public Mono<Void> deleteDevices(); 


}
