package com.rolecraft.controller.dto;

import java.util.List;

public class SkillMatchRequest {


    private List<String> requiredSkills;
    private List<String> resumeSkills;

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public List<String> getResumeSkills() {
        return resumeSkills;
    }

    public void setResumeSkills(List<String> resumeSkills) {
        this.resumeSkills = resumeSkills;
    }
}