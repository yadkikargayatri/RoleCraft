package com.rolecraft.model;

import java.util.List;

public class TailoredResume {

    private String summary;
    private List<String> skills;
    private List<String> experienceBullets;
    private double matchPercentage; // optional, can be used in API responses

    // Getters and setters
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }

    public List<String> getExperienceBullets() { return experienceBullets; }
    public void setExperienceBullets(List<String> experienceBullets) { this.experienceBullets = experienceBullets; }

    public double getMatchPercentage() { return matchPercentage; }
    public void setMatchPercentage(double matchPercentage) { this.matchPercentage = matchPercentage; }
}
