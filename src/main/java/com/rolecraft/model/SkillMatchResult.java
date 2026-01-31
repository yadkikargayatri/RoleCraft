package com.rolecraft.model;

import java.util.List;

public class SkillMatchResult {

    private List<String> matchedSkills;
    private List<String> missingSkills;
    private double matchPercentage;

    public SkillMatchResult() {
    }

    public SkillMatchResult(List<String> matchedSkills, List<String> missingSkills, double matchPercentage) 
    {
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
        this.matchPercentage = matchPercentage;
    }

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(List<String> matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }

    public double getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }
}
