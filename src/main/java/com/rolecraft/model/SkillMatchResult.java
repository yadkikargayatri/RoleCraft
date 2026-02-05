package com.rolecraft.model;

import java.util.Set;

public class SkillMatchResult {

    private Set<String> matchedSkills;
    private Set<String> missingSkills;
    private Set<String> extraSkills;
    private Set<String> matchedPreferredSkills;
    private double matchPercentage;

    // Getters & Setters
    public Set<String> getMatchedSkills() { return matchedSkills; }
    public void setMatchedSkills(Set<String> matchedSkills) { this.matchedSkills = matchedSkills; }

    public Set<String> getMissingSkills() { return missingSkills; }
    public void setMissingSkills(Set<String> missingSkills) { this.missingSkills = missingSkills; }

    public Set<String> getExtraSkills() { return extraSkills; }
    public void setExtraSkills(Set<String> extraSkills) { this.extraSkills = extraSkills; }

    public Set<String> getMatchedPreferredSkills() { return matchedPreferredSkills; }
    public void setMatchedPreferredSkills(Set<String> matchedPreferredSkills) { this.matchedPreferredSkills = matchedPreferredSkills; }

    public double getMatchPercentage() { return matchPercentage; }
    public void setMatchPercentage(double matchPercentage) { this.matchPercentage = matchPercentage; }
}
