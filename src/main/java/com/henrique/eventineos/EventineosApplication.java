package com.henrique.eventineos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.henrique.eventineos.controller"})
@EntityScan(basePackages = { "com.henrique.eventineos.model" })
public class EventineosApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventineosApplication.class, args);
    }

}
