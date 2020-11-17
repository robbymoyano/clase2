package com.escuelita.clase2.config;

import javax.xml.ws.BindingProvider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.tempuri.Calculator;
import org.tempuri.CalculatorSoap;

import com.sun.xml.ws.client.BindingProviderProperties;

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

	@Bean
	public CalculatorSoap getSoapClient() {
		String endpoint = "http://www.dneonline.com/calculator.asmx?wsdl";
		int timeOut = 6000;

		int requestTimeout = timeOut;
		int connectTimeout = timeOut;
		
		Calculator servicio = new Calculator();
		CalculatorSoap port = servicio.getCalculatorSoap();

		// Configuración para el redireccionamiento
		BindingProvider bindingProvider = (BindingProvider) port;
		bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);

		// Configuración para el timeout
		bindingProvider.getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, requestTimeout);
		bindingProvider.getRequestContext().put(BindingProviderProperties.CONNECT_TIMEOUT, connectTimeout);
		
		return port;

	}
}
