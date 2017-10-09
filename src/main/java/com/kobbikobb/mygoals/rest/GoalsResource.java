package com.kobbikobb.mygoals.rest;

import com.kobbikobb.mygoals.model.Goal;
import com.kobbikobb.mygoals.services.GoalRepository;
import com.kobbikobb.mygoals.services.InMemoryGoalRepository;
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

    private GoalRepository goalRepository;

    @Autowired
    public GoalsResource(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @GET
    public List<GoalBean> getAllGoals() {
        //TODO: Inject
        ModelMapper modelMapper = new ModelMapper();
        java.lang.reflect.Type targetListType = new TypeToken<List<GoalBean>>() {}.getType();
        return modelMapper.map(goalRepository.getAll(), targetListType);
    }

    @POST
    public GoalBean createGoal(CreateGoalBean createGoalBean) {

        ModelMapper modelMapper = new ModelMapper();
        Goal goal = modelMapper.map(createGoalBean, Goal.class);

        return modelMapper.map(goalRepository.create(goal), GoalBean.class);
    }
}
