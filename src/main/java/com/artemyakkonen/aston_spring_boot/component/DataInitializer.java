package com.artemyakkonen.aston_spring_boot.component;

import com.artemyakkonen.aston_spring_boot.model.User;
import com.artemyakkonen.aston_spring_boot.repository.UserRepository;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Select;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final Faker faker;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 20; i++) {
            var user = Instancio.of(User.class)
                    .ignore(Select.field(User::getId))
                    .supply(Select.field(User::getName), () -> faker.name().name())
                    .supply(Select.field(User::getEmail), () -> faker.internet().emailAddress())
                    .supply(Select.field(User::getAge), () -> faker.number().numberBetween(18,100))
                    .ignore(Select.field(User::getCreatedAt))
                    .ignore(Select.field(User::getUpdatedAt))
                    .create();

            userRepository.save(user);
        }
    }
}