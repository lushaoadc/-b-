package com.immoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ComBilibiliApp {
    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(ComBilibiliApp.class, args);
    }
}
