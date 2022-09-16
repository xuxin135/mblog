package com.xuxin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MblogSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(MblogSystemApplication.class,args);
    }
}
