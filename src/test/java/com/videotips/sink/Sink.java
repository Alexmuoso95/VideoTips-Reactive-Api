package com.videotips.sink;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Sink {

	@Test
	void sink() {
		//publisher
		Sinks.Many<Integer> replySink = Sinks.many().replay().all();
		
		//subscriber
		Flux<Integer> integerFlux1 = replySink.asFlux();
		integerFlux1.subscribe(i -> {
			System.out.println("Subscribe 1 : " + i );
		});
		Flux<Integer> integerFlux2 = replySink.asFlux();
		integerFlux2.subscribe(i -> {
			System.out.println("Subscribe 2 : " + i );
		});
		
		replySink.emitNext(1,Sinks.EmitFailureHandler.FAIL_FAST);
		replySink.emitNext(2,Sinks.EmitFailureHandler.FAIL_FAST);
		replySink.tryEmitNext(3);
	}
	@Test
	void sink_multicast() { 
		//publisher
		//unicast only allow one suscriber
		Sinks.Many<Integer> replySink = Sinks.many().multicast().onBackpressureBuffer();
		
		//subscriber
		Flux<Integer> integerFlux1 = replySink.asFlux();
		integerFlux1.subscribe(i -> {
			System.out.println("Subscribe 1 : " + i );
		});
		//subscriber
		Flux<Integer> integerFlux2 = replySink.asFlux();
		integerFlux1.subscribe(i -> {
			System.out.println("Subscribe 2 : " + i );
		});
		replySink.emitNext(1,Sinks.EmitFailureHandler.FAIL_FAST);
		replySink.emitNext(2,Sinks.EmitFailureHandler.FAIL_FAST);
		replySink.tryEmitNext(3);
	}
}
