package com.kobbikobb.mygoals.model;

import com.kobbikobb.mygoals.rest.CreateGoalBean;
import org.hamcrest.Matchers;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ModelMapperTest {

    @Test
    public void shouldMapFromCreateGoalBean() {
        CreateGoalBean createGoalBean = new CreateGoalBean();
        createGoalBean.setDescription("The description");
        createGoalBean.setTargetDate("2009-01-31");

        ModelMapper modelMapper = new ModelMapper();
        Converter<String, LocalDate> toStringDate = new AbstractConverter<String, LocalDate>() {
            @Override
            protected LocalDate convert(String source) {
                return LocalDate.parse(source);
            }
        };
        modelMapper.addConverter(toStringDate);
        Goal result = modelMapper.map(createGoalBean, Goal.class);

        assertThat(result.getDescription(), Matchers.is("The description"));
        assertTrue(result.getTargetDate().equals(LocalDate.parse("2009-01-31")));
    }
}