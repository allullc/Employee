package com.example.commerce;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


// TODO: Agregar un Seeder
@SpringBootApplication
public class EmpleadoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmpleadoApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}