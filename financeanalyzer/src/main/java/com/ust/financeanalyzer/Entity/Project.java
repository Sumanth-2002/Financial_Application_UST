
package com.ust.financeanalyzer.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "project")
public class Project {
    @Id
    private String projectid;
    private String projectname;
    private Double budget;
    private String budgetduration;
    private  int teamSize;
    public Project() {
    }
    public Project(String projectid, String projectname, Double budget, String budgetduration,int teamSize) {
        this.projectid = projectid;
        this.projectname = projectname;
        this.budget = budget;
        this.budgetduration = budgetduration;
        this.teamSize=teamSize;
    }
    public String getProjectid() {
        return projectid;
    }
    public void setProjectid(String projectid) {
        this.projectid = projectid;
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
