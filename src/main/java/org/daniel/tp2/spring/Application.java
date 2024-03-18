package org.daniel.tp2.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.daniel.tp2")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}