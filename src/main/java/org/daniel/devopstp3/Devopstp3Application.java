package org.daniel.devopstp3;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
public class Devopstp3Application {

	public static void main(String[] args) {
		SpringApplication.run(Devopstp3Application.class, args);
	}

}
