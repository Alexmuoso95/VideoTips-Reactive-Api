package com.videotips.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videotips.entity.Device;
import com.videotips.repository.DeviceRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	DeviceRepository deviceRepository;
	
	@Override
	public Mono<Device> addDevice(Device device) {
		log.info(":: INFO :: Device Service ADD device");
		return deviceRepository.save(device);
	}
	@Override
	public Flux<Device> getDevices() {
		log.info(":: INFO :: Device Service GET devices");
		return deviceRepository.findAll();
	}
	@Override
	public Mono<Device> getDevice(String id,String model) {
		log.info(":: INFO :: Device Service GET device with id : {} ",id);
		if(model != null && !model.isBlank()) {
			log.info(":: INFO :: Device Service GET device by Model with id : {} ",id);
			return deviceRepository.findByModel(model);
		}else {
			log.info(":: INFO :: Device Service GET device by Id with id : {} ",id);
			return deviceRepository.findById(id);
		}
	}
	@Override
	public Mono<Device> updateDevice(Device device, String id) {
		log.info(":: INFO :: Device Service UPDATE device with id : {}",id);
		return deviceRepository.findById(id).flatMap(deviceUpdate -> {
			deviceUpdate.setId(device.getId());
			deviceUpdate.setModel(device.getModel());
			deviceUpdate.setName(device.getName());
			return deviceRepository.save(deviceUpdate);
		});
	}
	@Override
	public Mono<Void> deleteDevice(String id) {
		log.info(":: INFO :: Device Service DELETE device with  id : {} ", id);
		return deviceRepository.deleteById(id);
	}
	@Override
	public Mono<Void> deleteDevices() {
		log.info(":: INFO :: Device Service DELETE All");
		return deviceRepository.deleteAll();
	}
}
