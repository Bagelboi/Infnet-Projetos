package org.daniel.tp3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients(basePackages = {"org.daniel.tp3.client"})
@SpringBootApplication
//@ComponentScan("org.daniel.tp3")
public class Application {

    public static final String POKEAPI_KEY = "102be30b-dbb3-48cf-a2c5-c983fd5db8cf";
    public static final double CARD_SIZE = 0.08;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}