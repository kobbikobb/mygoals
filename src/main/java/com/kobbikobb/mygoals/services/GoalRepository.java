package com.kobbikobb.mygoals.services;

import com.kobbikobb.mygoals.model.Goal;

import java.util.List;

public interface GoalRepository {

     Goal create(Goal goal);

     List<Goal> getAll();
}
