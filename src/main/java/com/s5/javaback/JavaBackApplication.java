package com.s5.javaback;

import com.mercadopago.MercadoPagoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class JavaBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaBackApplication.class, args);
		//MercadoPagoConfig.setIntegratorId("8258518285413967");


	}

}
