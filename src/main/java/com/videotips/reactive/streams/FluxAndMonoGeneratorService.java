package com.videotips.reactive.streams;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxAndMonoGeneratorService {
	
	//This method will be used to create names streams to get names
	public Flux<String> namesFlux() {
		return Flux.fromIterable(List.of("alex","ben","chloe"));
				    //.log(); //Flux used to be used to return data from DataBase or External Services
	}
	
	//This method will be used to create names streams to get names
	public Mono<String> namesMono() {
		return Mono.just("alex")
					.map(s -> s = s.toUpperCase())
					.log(); //Mono used to be used to return data from DataBase or External Services Only one OBJECT.
	}

	//Transform MONO TO FLUX
	public Flux<String> transforMonoToFlux() {
		return Mono.just("ALEX")
					.flatMapMany(s -> splitString(s))
					.log();
	}
	//Function Function Interface
	public Flux<String> transform() {
		Function<Flux<String>,Flux<String>> doSomeTransformation = someFlux -> someFlux.flatMap(s -> splitString(s));
		return  Flux.just("ALEX").transform(doSomeTransformation);
			
	}
	public Flux<String> splitString(String s){
		String [] arrayString = s.split("");
		
		return Flux.fromArray(arrayString).delayElements(Duration.ofMillis(10));
	}
	
	
	public static void main(String[] args) {
		//Create Instance of Class  , Subscribes client into Flux to have access to the elements and consume them.
		FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();
		fluxAndMonoGeneratorService.transform().subscribe();
	} 
}
