package com.ust.financeanalyzer.dto;



public class Projectdto {
    private String projectid;
    private String projectname;
    private Double budget;
    private String budgetduration;
    private  int teamSize;
    private  Double expenditure;
    private Double income;

    public Projectdto() {
    }

    public Projectdto(String projectid, String projectname, Double budget, String budgetduration, int teamSize, Double expenditure, Double income) {
        this.projectid = projectid;
        this.projectname = projectname;
        this.budget = budget;
        this.budgetduration = budgetduration;
        this.teamSize = teamSize;
        this.expenditure = expenditure;
        this.income = income;
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

    public Double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(Double expenditure) {
        this.expenditure = expenditure;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

}
