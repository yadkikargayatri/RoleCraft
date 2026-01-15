package com.rolecraft.model;
import java.util.List;

public class Resume {

    private String summary;
    private List<String> skills;

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
}
