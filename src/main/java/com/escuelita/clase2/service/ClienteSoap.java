package com.escuelita.clase2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tempuri.Add;
import org.tempuri.AddResponse;
import org.tempuri.Calculator;
import org.tempuri.CalculatorSoap;

@Service
public class ClienteSoap {
	private static final Logger log = LoggerFactory.getLogger(ClienteSoap.class);
	
	public void callSoap() {
		log.info("Inicio SOAP");
		
		Calculator calculadora = new Calculator();
		CalculatorSoap port = calculadora.getCalculatorSoap();
		
		Add suma = new Add();
		suma.setIntA(40);
		suma.setIntB(71);
		
		AddResponse resultado = port.add(suma);
		log.info("{}", resultado.getAddResult());
		
	}
}
