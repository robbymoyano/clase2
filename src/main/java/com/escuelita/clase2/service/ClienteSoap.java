package com.escuelita.clase2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tempuri.Add;
import org.tempuri.AddResponse;
import org.tempuri.Calculator;
import org.tempuri.CalculatorSoap;

import com.escuelita.clase2.config.Config;

@Service
public class ClienteSoap {
	private static final Logger log = LoggerFactory.getLogger(ClienteSoap.class);
	
	@Autowired
	Config config;
	
	public void callSoap() {
		log.info("Inicio SOAP");
		
		CalculatorSoap port = config.getSoapClient();
		
		Add suma = new Add();
		suma.setIntA(40);
		suma.setIntB(71);
		try {
			AddResponse resultado = port.add(suma);
			log.info("{}", resultado.getAddResult());
		}
		catch(Exception e) {
			log.error("Error al consumir soap: {}", e.getMessage());
		}
		
		
	}
}
