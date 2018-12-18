package io.digitalstate.camunda.mainms;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class MicroservicePrimary{
    public static void main(String... args) {
        SpringApplication.run(MicroservicePrimary.class, args);
    }
}

