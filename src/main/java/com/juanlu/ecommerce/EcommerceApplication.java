package com.juanlu.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
            System.out.println("Arrancando la app...");
            SpringApplication.run(EcommerceApplication.class, args);
            System.out.println("App arrancada");
	}

}
