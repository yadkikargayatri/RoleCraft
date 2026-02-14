package com.rolecraft.controller.dto;

import com.rolecraft.model.Resume;
import com.rolecraft.model.SkillMatchResult;

public class ResumeTailorResponse {

    private Resume tailoredResume;
    private SkillMatchResult skillMatchResult;
    private String aiSuggestions;

    public Resume getTailoredResume() {
        return tailoredResume;
    }

    public void setTailoredResume(Resume tailoredResume) {
        this.tailoredResume = tailoredResume;
    }

    public SkillMatchResult getSkillMatchResult() {
        return skillMatchResult;
    }

    public void setSkillMatchResult(SkillMatchResult skillMatchResult) {
        this.skillMatchResult = skillMatchResult;
    }

    public String getAiSuggestions() {
        return aiSuggestions;
    }    
    
    public void setAiSuggestions(String aiSuggestions) {
        this.aiSuggestions = aiSuggestions;
    }

}

