package com.escuelita.clase2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

	@Value("${client.rest.timeout}")
	int timeout;
	
	public RestTemplate getClientRest() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(timeout);
		factory.setReadTimeout(timeout);
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(factory);
		return restTemplate;
		
	}
}
