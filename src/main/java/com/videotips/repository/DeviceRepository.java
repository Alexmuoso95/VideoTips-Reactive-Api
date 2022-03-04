package com.videotips.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.videotips.entity.Device;

import reactor.core.publisher.Mono;

@Repository
public interface DeviceRepository extends ReactiveMongoRepository<Device,String>{
	Mono<Device> findByModel(String model);
}
