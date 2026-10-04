package com.Beehive.beekeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BeekeeperApplication {

    public static void main(String[] args) {
        System.out.println("hi");
        SpringApplication.run(BeekeeperApplication.class, args);
    }

}
