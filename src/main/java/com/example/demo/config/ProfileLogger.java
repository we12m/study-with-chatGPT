package com.example.demo.config;

import org.springframework.core.env.Environment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileLogger implements CommandLineRunner {
    private final Environment env;

    public ProfileLogger(Environment env) {
        this.env = env;
    }

    @Override
    public void run(String... args) {
        System.out.println("info.app.name: " +  env.getProperty("info.app.name"));
    }
}
