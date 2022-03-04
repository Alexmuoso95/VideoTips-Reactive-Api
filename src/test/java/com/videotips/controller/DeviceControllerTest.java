package com.videotips.controller;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.videotips.entity.Device;

import lombok.var;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebClient
@ActiveProfiles("test")
class DeviceControllerTest {
	
	private static final String DEVICE_CREATE_URL = "/v1/device/create";
	
	@Autowired
	WebTestClient webTestClient;
	
	@BeforeEach
	void doBefore() {
	Device device = new Device("1","tv","123-456-789");
	}
	@AfterEach
	void doAfter() {
	}

	@Test
	void addDivce_NotNull_Test() {
	//GIVEN
	Device device = new Device("1","tv","123-456-789");
		
	//WHEN
	webTestClient.post().uri(DEVICE_CREATE_URL).bodyValue(device)
				 .exchange()
				 .expectStatus().isCreated()
				 .expectBody()
				 .consumeWith( deviceExchangeResult -> {
					 var saveDevice =  deviceExchangeResult.getResponseBody();
					 assert saveDevice!=null;
				 });
	//THEN 
	}

//	@Test
//	void deleteMovieInfoById() {
//	    var id = "abc";
//
//	    webTestClient
//	            .delete()
//	            .uri(MOVIES_INFO_URL + "/{id}", id)
//	            .exchange()
//	            .expectStatus()
//	            .isNoContent();
//	}
}
