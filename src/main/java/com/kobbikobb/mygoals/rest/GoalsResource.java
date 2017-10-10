package com.kobbikobb.mygoals.rest;

import com.kobbikobb.mygoals.model.Goal;
import com.kobbikobb.mygoals.services.GoalRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("goals")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GoalsResource {

    private ModelMapper modelMapper;
    private GoalRepository goalRepository;

    @Autowired
    public GoalsResource(GoalRepository goalRepository, ModelMapper modelMapper) {
        this.goalRepository = goalRepository;
        this.modelMapper = modelMapper;
    }

    @GET
    public List<GoalBean> getAllGoals() {
        java.lang.reflect.Type targetListType = new TypeToken<List<GoalBean>>() {}.getType();
        return modelMapper.map(goalRepository.getAll(), targetListType);
    }

    @POST
    public GoalBean createGoal(CreateGoalBean createGoalBean) {
        Goal goal = modelMapper.map(createGoalBean, Goal.class);
        return modelMapper.map(goalRepository.create(goal), GoalBean.class);
    }
}
