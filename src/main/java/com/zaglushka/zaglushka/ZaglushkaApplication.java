package com.zaglushka.zaglushka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZaglushkaApplication {
	public static void main(String[] args) throws Exception {
		Class.forName("org.postgresql.Driver");
		SpringApplication.run(ZaglushkaApplication.class, args);
	}
}
