package org.daniel.ddd2_tp2;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class Ddd2Tp2Application {

	public static void main(String[] args) {
		SpringApplication.run(Ddd2Tp2Application.class, args);
	}

}
