package com.chen.nots_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ProgrammeNotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProgrammeNotesApplication.class, args);
    }

}
