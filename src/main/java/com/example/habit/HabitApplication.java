package com.example.habit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.habit.mapper")
public class HabitApplication {

    public static void main(String[] args) {
        SpringApplication.run(HabitApplication.class, args);
    }

}
