package com.kobbikobb.mygoals.model;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.UUID;

public class Goal {

    private UUID id;
    private String description;
    private DateTime createdDate;
    private LocalDate targetDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }
}
