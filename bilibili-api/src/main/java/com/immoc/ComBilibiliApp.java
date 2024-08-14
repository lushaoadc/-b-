package com.immoc;

import com.immoc.bilibili.service.websocket.WebSocketService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ComBilibiliApp {
    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(ComBilibiliApp.class, args);
        WebSocketService.setApplicationContext(app);
    }
}
