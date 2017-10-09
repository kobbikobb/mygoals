package com.kobbikobb.mygoals;

import org.joda.time.LocalDate;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

public class ModelMapperFactory {

    private ModelMapperFactory() {

    }

    public static ModelMapper create() {
        ModelMapper modelMapper = new ModelMapper();
        Converter<String, LocalDate> toStringDate = new AbstractConverter<String, LocalDate>() {
            @Override
            protected LocalDate convert(String source) {
                return LocalDate.parse(source);
            }
        };
        modelMapper.addConverter(toStringDate);
        return modelMapper;
    }

}
