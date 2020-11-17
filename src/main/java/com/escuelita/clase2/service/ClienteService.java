package com.escuelita.clase2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.escuelita.clase2.model.Response;
import com.escuelita.clase2.util.Utilidades;

@Service
public class ClienteService {

	private final Logger log = LoggerFactory.getLogger(ClienteService.class);

	
	public void callApiGorest() {
		RestTemplate template = new RestTemplate();
		String url = "https://gorest.co.in/public-api/categories";
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		
		HttpEntity entity = new HttpEntity(headers);
		
		ResponseEntity<Response> salida = template.exchange(url, HttpMethod.GET, entity, Response.class);
		
		log.info("{}", Utilidades.serializarObjeto(salida.getBody()));
		log.info("{}", salida.getStatusCode());
		
	}
}
