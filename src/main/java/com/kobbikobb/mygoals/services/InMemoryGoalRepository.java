package com.kobbikobb.mygoals.services;

import com.google.common.collect.Lists;
import com.kobbikobb.mygoals.model.Goal;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InMemoryGoalRepository implements GoalRepository {

    // NOTE: Not thread safe
    private List<Goal> goals = Lists.newArrayList();

    public Goal create(Goal goal) {
        goals.add(goal);
        return goal;
    }

    public List<Goal> getAll() {
        return goals;
    }
}
