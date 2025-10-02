package com.artemyakkonen.aston_spring_boot;

import net.datafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


import java.util.Locale;
import java.util.Random;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Faker getFaker() {
        var seed = 123;
        var rand = new Random(seed);
        return new Faker(Locale.US, rand);
    }
}
