package com.rolecraft.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class JobDescription {

    private String title;
    private Set<String> requiredSkills =new LinkedHashSet<>(); // was incorrectly List<String>

    private Set<String> preferredSkills;
    private List<String> responsibilities;
    private List<String> keywords = new ArrayList<>(); // initialized to avoid null pointer
    private String rawText;
    private List<String> skills;
   
    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<String> extractedSkills) {
        this.requiredSkills = new LinkedHashSet<>(extractedSkills);
    }

    public Set<String> getPreferredSkills() {
        return preferredSkills;
    }

    public void setPreferredSkills(List<String> preferredSkills) {
        this.preferredSkills = new LinkedHashSet<>(preferredSkills);
    }

    public List<String> getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(List<String> responsibilities) {
        this.responsibilities = responsibilities;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
