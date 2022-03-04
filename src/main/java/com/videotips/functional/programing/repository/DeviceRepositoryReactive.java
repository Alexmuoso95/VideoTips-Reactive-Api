package com.videotips.functional.programing.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.videotips.entity.Device;

public interface DeviceRepositoryReactive extends ReactiveMongoRepository<Device,String>{

}
