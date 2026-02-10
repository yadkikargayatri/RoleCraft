package com.rolecraft.model;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Resume {

    @NotBlank(message = "Resume title cannot be empty")
    private String title;

    @NotBlank(message = "Resume summary cannot be empty")
    @Size(max = 500, message = "Resume summary must not exceed 500 characters")
    private String summary;

    @NotEmpty(message = "Resume skills must not be empty")
    private Set<String> skills;

    @NotEmpty(message = "Resume experience bullets must not be empty")
    private Set<@NotBlank String> experienceBullets;

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
