package com.ust.financeanalyzer.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "project")
public class Project {
    @Id
    @JsonProperty("projectId")
    private String projectId;
    private String projectname;
    private Double budget;
    private String budgetduration;
    private int teamSize;

    public Project() {
    }

    public Project(String projectId, String projectname, Double budget, String budgetduration, int teamSize) {
        this.projectId = projectId;
        this.projectname = projectname;
        this.budget = budget;
        this.budgetduration = budgetduration;
        this.teamSize = teamSize;
    }

    public String getProjectid() {
        return projectId;
    }

    public void setProjectid(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getBudgetduration() {
        return budgetduration;
    }

    public void setBudgetduration(String budgetduration) {
        this.budgetduration = budgetduration;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }
}
