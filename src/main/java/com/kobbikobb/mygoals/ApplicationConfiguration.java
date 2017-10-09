package com.kobbikobb.mygoals;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.kobbikobb.mygoals.services"})
public class ApplicationConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return ModelMapperFactory.create();
    }
}
