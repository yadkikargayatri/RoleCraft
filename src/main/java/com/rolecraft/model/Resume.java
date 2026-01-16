package com.rolecraft.model;
import java.util.List;

public class Resume {

    private String title;
    private String summary;
    private List<String> skills;
    private List<String> experienceBullets;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public List<String> getExperienceBullets() {
        return experienceBullets;
    }
    public void setExperienceBullets(List<String> experienceBullets) {
        this.experienceBullets = experienceBullets;
    }   
}
