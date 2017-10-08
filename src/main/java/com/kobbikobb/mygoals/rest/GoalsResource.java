package com.kobbikobb.mygoals.rest;

import com.google.common.collect.Lists;
import com.kobbikobb.mygoals.services.GoalRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

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

    @GET
    public List<GoalBean> getAllGoals() {

        GoalRepository goalRepository = new GoalRepository();

        ModelMapper modelMapper = new ModelMapper();
        java.lang.reflect.Type targetListType = new TypeToken<List<GoalBean>>() {}.getType();
        return modelMapper.map(goalRepository.getAll(), targetListType);
    }
}
