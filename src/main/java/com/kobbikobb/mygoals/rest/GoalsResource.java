package com.kobbikobb.mygoals.rest;

import com.kobbikobb.mygoals.services.GoalRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
        ModelMapper modelMapper = new ModelMapper();
        java.lang.reflect.Type targetListType = new TypeToken<List<GoalBean>>() {}.getType();
        return modelMapper.map(goalRepository.getAll(), targetListType);
    }
}
