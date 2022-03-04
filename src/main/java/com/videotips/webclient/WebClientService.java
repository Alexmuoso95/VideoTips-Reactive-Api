package com.videotips.webclient;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.videotips.entity.Device;
import com.videotips.model.clients.Client;

import lombok.var;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

//NON BLOCKING 
@Service
public class WebClientService {

	@Autowired
	private WebClient webClient;
	
	@Value("${restClient.url}")
	public String url ;
	
	public Mono<Device> getDeviceById() {
		
		//propagate the error from the external service.
		var retrySpec =Retry.fixedDelay(3,Duration.ofSeconds(1)).onRetryExhaustedThrow((retryBackoffSpec,retrySignal) -> Exceptions.propagate(retrySignal.failure()));
		return webClient.get()
						.uri(url)
						.retrieve()
						.onStatus(HttpStatus::is4xxClientError, clientResponse -> {
							if(clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
								return	Mono.error(new Exception(url + "is not available, please try later"));
								}
							return clientResponse.bodyToMono(String.class)
												.flatMap(response -> Mono.error(new Exception()));
							})
						.onStatus(HttpStatus::is5xxServerError, clientResponse -> {
							if(clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
								return	Mono.error(new Exception(url + "Internal Server Error"));
								}
							return clientResponse.bodyToMono(String.class)
												.flatMap(response -> Mono.error(new Exception()));
							})
						.bodyToMono(Device.class)
						//.retry(3)
						.retryWhen(retrySpec)
						.log(); 
	}
	
	public static final String URL = "http://localhost:8081/v1/clients/all";

	public Flux<Client> getClients() {
		return webClient.get()
						.uri(URL)
						.retrieve()
						.onStatus(HttpStatus::is4xxClientError, clientResponse -> {
							if(clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
								return	Mono.error(new Exception(url + "is not available, please try later"));
								}
							return clientResponse.bodyToMono(String.class)
												.flatMap(response -> Mono.error(new Exception()));
							})
						.onStatus(HttpStatus::is5xxServerError, clientResponse -> {
							if(clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
								return	Mono.error(new Exception(url + "Internal Server Error"));
								}
							return clientResponse.bodyToMono(String.class)
												.flatMap(response -> Mono.error(new Exception()));
							})
						.bodyToFlux(Client.class)
						.retry(3)
						.log(); 
	}
}
