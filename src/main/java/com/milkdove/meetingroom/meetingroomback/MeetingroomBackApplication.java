package com.milkdove.meetingroom.meetingroomback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.milkdove.meetingroom.meetingroomback.mapper")
@SpringBootApplication

public class MeetingroomBackApplication {

    public static void main(String[] args) {

        SpringApplication.run(MeetingroomBackApplication.class, args);
    }

}
