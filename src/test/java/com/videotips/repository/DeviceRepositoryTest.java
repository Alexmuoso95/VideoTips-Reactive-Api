package com.videotips.repository;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

import com.videotips.entity.Device;

import lombok.var;
import reactor.test.StepVerifier;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@ActiveProfiles("test")
class DeviceRepositoryTest {

	@Autowired
	DeviceRepository deviceRepository;
	
	@BeforeEach
	public void setUp() {
		var devicesDummy = List.of(new Device("1","TV","12345"),new Device("1","TV","12345"),new Device("1","TV","12345"));
		deviceRepository.saveAll(devicesDummy).blockLast();
	}
	
	@AfterEach
	public void tearDown() {
		deviceRepository.deleteAll().block();
	}
	
	@Test
	public void test_findAll() {
		//given
		//when
		var devices = deviceRepository.findAll();
		//then
		StepVerifier.create(devices).expectNextCount(1).verifyComplete();
	}
}
