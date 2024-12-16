package com.example.kidsstore_tz;

import com.example.kidsstore_tz.domain.User;
import com.example.kidsstore_tz.repository.ProductRepository;
import com.example.kidsstore_tz.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class KidsstoreTzApplication {

    public static void main(String[] args) {
        SpringApplication.run(KidsstoreTzApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository, ProductRepository productRepository,
                                               PasswordEncoder passwordEncoder) {
        return args -> {
            User user = new User("admin@gmail.com", passwordEncoder.encode("admin"), "Admin");
            userRepository.save(user);
        };
    }

}
