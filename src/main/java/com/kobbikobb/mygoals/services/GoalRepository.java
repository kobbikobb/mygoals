package com.kobbikobb.mygoals.services;

import com.google.common.collect.Lists;
import com.kobbikobb.mygoals.model.Goal;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.UUID;

public class GoalRepository {

    public List<Goal> getAll() {
        Goal goal1 = new Goal();
        goal1.setDescription("Learn how to relax with my children");
        goal1.setId(UUID.randomUUID());
        goal1.setCreatedDate(DateTime.now());
        goal1.setTargetDate(LocalDate.now());

        Goal goal2 = new Goal();
        goal2.setId(UUID.randomUUID());

        goal2.setDescription("Contribute to open source at least 2 times per week");

        return Lists.newArrayList(goal1, goal2);
    }
}
