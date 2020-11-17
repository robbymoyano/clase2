package com.escuelita.clase2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.escuelita.clase2.service.ClienteService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestClient {

	@Autowired
	ClienteService cliente;
	
	@Test
	public void testearCliente() {
		System.out.println("hola mundo");
		cliente.callApiGorest();
	}
}
