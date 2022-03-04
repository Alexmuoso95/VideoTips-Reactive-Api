package com.videotips.service;

import org.junit.jupiter.api.Test;

import com.videotips.reactive.streams.FluxAndMonoGeneratorService;

import reactor.test.StepVerifier;


public class FluxAndMonoGeneratorServiceTest {

	FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();
	
	@Test
	public void namesFlux_Test() {
		//Test next info 
		StepVerifier.create(fluxAndMonoGeneratorService.namesFlux()).expectNext("alex");
		//Test on complete
		StepVerifier.create(fluxAndMonoGeneratorService.namesFlux()).expectNext("alex","ben","chloe").verifyComplete();
		//Test counter
		StepVerifier.create(fluxAndMonoGeneratorService.namesFlux()).expectNextCount(3).verifyComplete();
	}
	
	@Test
	public void namesMono_Test() {
		StepVerifier.create(fluxAndMonoGeneratorService.namesMono()).expectNextCount(1).verifyComplete();	
	}
	
}
