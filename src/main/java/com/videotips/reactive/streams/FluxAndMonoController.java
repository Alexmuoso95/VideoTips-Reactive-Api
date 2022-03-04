package com.videotips.reactive.streams;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class FluxAndMonoController {

	@GetMapping("/flux")
	public Flux<Integer> flux(){
		return Flux.just(1,2,3).log();
	}
	@GetMapping("/mono")
	public Mono<Integer> mono(){
		return Mono.just(1).log();
	}
	@GetMapping(value = "/stream", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Long> stream(){
		return Flux.interval(Duration.ofSeconds(3)).log();
	}
}