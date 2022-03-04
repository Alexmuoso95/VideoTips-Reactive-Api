package com.videotips.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.videotips.entity.Device;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
@AutoConfigureWireMock(port=8081)
@TestPropertySource(
	properties = {
			"restClient.url=http://localhost:8080/v1/device/get/1"
	}
)
public class wireMockTest {
	
	@Autowired
	WebTestClient webTestClient;
	
	@Test
	void retrieveMovieById() {
		//given
		//STUBS
		webTestClient.get().uri("/v1/device/get/{id}",1).exchange().expectStatus().isOk().expectBody(Device.class);
		//when
		//then
	}

}
