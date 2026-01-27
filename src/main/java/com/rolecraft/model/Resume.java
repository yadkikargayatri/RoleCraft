package com.rolecraft.model;
import java.util.Set;

public class Resume {

    private String title;
    private String summary;
    private Set<String> skills;
    private Set<String> experienceBullets;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Set<String> getSkills() {
        return skills;
    }
    
    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }

    public String getTitle() {
       return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<String> getExperienceBullets() {
        return experienceBullets;
    }
    public void setExperienceBullets(Set<String> experienceBullets) {
        this.experienceBullets = experienceBullets;
    }   
}
