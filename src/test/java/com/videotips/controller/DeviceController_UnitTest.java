package com.videotips.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.videotips.controller.definition.DeviceController;
import com.videotips.service.DeviceService;


@WebFluxTest(controllers = DeviceController.class)
@AutoConfigureWebTestClient
public class DeviceController_UnitTest {

	private static final String DEVICE_CREATE_URL = "/v1/device/create";

	@Autowired
	WebTestClient webTestClient;

	@MockBean
	private DeviceService service;
	
	@BeforeEach
	void doBefore() {
	}

	@AfterEach
	void doAfter() {
	}

	@Test
	void addDivce_NotNull_Test() {
		//GIVE
		//WHEN
		//THEN
	}

}
