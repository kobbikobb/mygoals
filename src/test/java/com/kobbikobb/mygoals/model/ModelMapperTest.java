package com.kobbikobb.mygoals.model;

import com.kobbikobb.mygoals.ModelMapperFactory;
import com.kobbikobb.mygoals.rest.CreateGoalBean;
import com.kobbikobb.mygoals.rest.GoalBean;
import org.hamcrest.Matchers;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import java.util.UUID;

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

    @Test
    public void shouldMapFromGoalToBean() {
        UUID id = UUID.randomUUID();
        Goal goal = new Goal();
        goal.setId(id);
        goal.setDescription("The description");
        goal.setCreatedDate(DateTime.parse("2009-01-31"));
        goal.setTargetDate(LocalDate.parse("2009-02-02"));

        GoalBean result = modelMapper.map(goal, GoalBean.class);

        assertThat(result.getId(), Matchers.is(id.toString()));
        assertThat(result.getDescription(), Matchers.is("The description"));
        assertTrue(result.getCreatedDate().equals("2009-01-31T00:00:00.000Z"));
        assertTrue(result.getTargetDate().equals("2009-02-02"));
    }
}
