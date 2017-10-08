package com.kobbikobb.mygoals.rest;

import com.google.common.collect.Lists;

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

        GoalBean goal1 = new GoalBean();
        goal1.setDescription("Learn how to relax with my children");

        GoalBean goal2 = new GoalBean();
        goal2.setDescription("Contribute to open source at least 2 times per week");

        return Lists.newArrayList(goal1, goal2);
    }
}
