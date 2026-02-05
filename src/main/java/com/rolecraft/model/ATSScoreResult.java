package com.rolecraft.model;

public class ATSScoreResult {
    private double requreSkillScore;
    private double preferredSkillScore;
    private double coverageScore;
    private double finalScore;

    public double getRequreSkillScore() {
        return requreSkillScore;
    }
    public void setRequiredSkillScore(double requreSkillScore) {
        this.requreSkillScore = requreSkillScore;
    }
    public double getPreferredSkillScore() {
        return preferredSkillScore;
    }
    public void setPreferredSkillScore(double preferredSkillScore) {
        this.preferredSkillScore = preferredSkillScore;
    }
    public double getCoverageScore() {
        return coverageScore;
    }
    public void setCoverageScore(double coverageScore) {
        this.coverageScore = coverageScore;
    }       
    public double getFinalScore() {
        return finalScore;
    }
    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }

}
