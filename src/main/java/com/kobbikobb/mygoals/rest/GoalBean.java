package com.kobbikobb.mygoals.rest;

public class GoalBean {

    private String id;
    private String description;
    private String createdDate;
    private String targetDate;

    private GoalBean[] subGoals;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }

    public GoalBean[] getSubGoals() {
        return subGoals;
    }

    public void setSubGoals(GoalBean[] subGoals) {
        this.subGoals = subGoals;
    }
}
