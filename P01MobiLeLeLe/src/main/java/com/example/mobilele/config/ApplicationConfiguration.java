package com.example.mobilele.config;

import com.example.mobilele.util.MyValidator;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {


    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public MyValidator validator(){
        return new MyValidator();
    }
}
