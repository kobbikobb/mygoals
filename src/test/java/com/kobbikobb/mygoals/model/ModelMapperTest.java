package com.kobbikobb.mygoals.model;

import com.kobbikobb.mygoals.ModelMapperFactory;
import com.kobbikobb.mygoals.rest.CreateGoalBean;
import org.hamcrest.Matchers;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ModelMapperTest {

    private ModelMapper modelMapper;

    @Before
    public void setUp() {
        modelMapper = ModelMapperFactory.create();
    }

    @Test
    public void shouldMapFromCreateGoalBean() {
        CreateGoalBean createGoalBean = new CreateGoalBean();
        createGoalBean.setDescription("The description");
        createGoalBean.setTargetDate("2009-01-31");

        Goal result = modelMapper.map(createGoalBean, Goal.class);

        assertThat(result.getDescription(), Matchers.is("The description"));
        assertTrue(result.getTargetDate().equals(LocalDate.parse("2009-01-31")));
    }
}
