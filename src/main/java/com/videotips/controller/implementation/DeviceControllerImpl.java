package com.videotips.controller.implementation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.videotips.controller.definition.DeviceController;
import com.videotips.entity.Device;
import com.videotips.model.clients.Client;
import com.videotips.service.DeviceService;
import com.videotips.webclient.WebClientService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Slf4j
@RestController
public class DeviceControllerImpl implements DeviceController {
	@Autowired
	DeviceService deviceService;
	@Autowired
	WebClientService webClientService;

	Sinks.Many<Device> sinkService = Sinks.many().replay().all();

	@Override
	public Flux<Client> getClients() {
		return webClientService.getClients().log();
	}
	//------------------------------------------------------------------------------
	public Mono<Device> addDevice (Device device ) {
		return deviceService.addDevice(device).log();
		// sink return deviceService.addDevice(device).doOnNext(save ->
		// sinkService.tryEmitNext(new Device("10","name","model"))).log();
	}

	public Flux<Device> getDevices() {
		return deviceService.getDevices();
	}

	public Mono<Device> getDevice(@PathVariable @Validated String id,
			@RequestParam(name = "mode", required = false) String model) {
		return deviceService.getDevice(id, model);
	}

	public Mono<Device> getDevice() {
		return webClientService.getDeviceById();
	}

	public Mono<ResponseEntity<Device>> updateDevice(@RequestBody @Valid Device device, @PathVariable String id) {
		return deviceService.updateDevice(device, id).map(ResponseEntity::ok);
	}

	public Mono<Void> deleteDivce(@PathVariable String id) {
		return deviceService.deleteDevice(id);
	}

	public Mono<Void> deleteAll() {
		return deviceService.deleteDevices();
	}

	public Flux<Device> sse() {
		log.info(":: INFO :: SINK ");
		return sinkService.asFlux().log();
	}
}
