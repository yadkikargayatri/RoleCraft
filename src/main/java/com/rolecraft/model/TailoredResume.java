package com.rolecraft.model;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TailoredResume {

    private String title;
    private String summary;
    private Set<String> matchedSkills = new LinkedHashSet<>();
    private Set<String> experienceBullets = new LinkedHashSet<>();
    private List<String> aiSuggestions;
    private double matchPercentage;

    public TailoredResume() {}

    // ---------- Getters & Setters ----------
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Set<String> getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(Set<String> matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public Set<String> getExperienceBullets() {
        return experienceBullets;
    }

    public void setExperienceBullets(Set<String> experienceBullets) {
        this.experienceBullets = experienceBullets;
    }

    public double getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }
    public List<String> getAiSuggestions() {
        return aiSuggestions;
    }
    public void setAiSuggestions(List<String> aiSuggestions) {
        this.aiSuggestions = aiSuggestions;
    }
}
