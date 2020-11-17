package com.escuelita.clase2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.escuelita.clase2.config.Config;
import com.escuelita.clase2.model.Data;
import com.escuelita.clase2.model.Response;
import com.escuelita.clase2.util.Utilidades;

@Service
public class ClienteService {

	private final Logger log = LoggerFactory.getLogger(ClienteService.class);

	@Autowired
	private Config config;
	
	@Value("${client.rest.url}")
	private String url;
	
	public void callApiGorest() {
		RestTemplate template = config.getClientRest();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");

		HttpEntity entity = new HttpEntity(headers);
		
		try {
			ResponseEntity<Response> salida = template.exchange(url, HttpMethod.GET, entity, Response.class);
			log.info("{}", Utilidades.serializarObjeto(salida.getBody()));
			log.info("{}", salida.getStatusCode());
			
			for(Data data : salida.getBody().getData()) {
				log.info("{}", data.getName());
				log.info("{}", data.getDescription());
			}
		} 
		
		catch(HttpStatusCodeException e) {
			log.warn("Status Code: {}", e.getStatusCode());
			log.warn("Body: {}", e.getResponseBodyAsString());
		}
		catch (RestClientException e) {

			log.error("{}", e.getMessage());
		}

	}
}
