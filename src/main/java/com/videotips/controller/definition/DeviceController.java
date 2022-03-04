package com.videotips.controller.definition;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.videotips.entity.Device;
import com.videotips.model.clients.Client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/v1/videotips")
public interface DeviceController {

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/clients")
	public Flux<Client> getClients();
	//----------------------------------------------------------------
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, path = "/add")
	public Mono<Device> addDevice(@RequestBody Device device);

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/getAll")
	public Flux<Device> getDevices();

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/get/{id}")
	public Mono<Device> getDevice(@PathVariable @Validated String id,@RequestParam(name = "mode", required = false) String model);

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/getDummy")
	public Mono<Device> getDevice();

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, path = "/update/{id}")
	public Mono<ResponseEntity<Device>> updateDevice(@RequestBody @Valid Device device, @PathVariable String id);

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
	public Mono<Void> deleteDivce(@PathVariable String id);

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.DELETE, path = "/delete")
	public Mono<Void> deleteAll();

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_NDJSON_VALUE)
	public Flux<Device> sse();
}
