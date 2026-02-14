package com.rolecraft.controller.dto;

import java.util.Set;

import com.rolecraft.model.JobDescription;
import com.rolecraft.model.Resume;
import com.rolecraft.model.SkillMatchResult;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


public class ResumeTailorRequest {

    @Schema(description = "Resume details")
    @NotNull(message = "Resume cannot be null")
    @Valid
    private Resume resume;

    private String title;
    private String summary;

    private Set<String> skills;
    private Set<String> experienceBullets;

    @Schema(description = "Job description details")
    @NotNull(message = "Job description cannot be null")
    @Valid
    private JobDescription jobDescription;

    private SkillMatchResult skillMatchResult;

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public Set<String> getSkills() {
        return skills;
    } 
    
    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }
    
    public Set<String> getExperienceBullets() {
        return experienceBullets;
    }

    public void setExperienceBullets(Set<String> experienceBullets) {
        this.experienceBullets = experienceBullets;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public JobDescription getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(JobDescription jobDescription) {
        this.jobDescription = jobDescription;
    }

    public SkillMatchResult getSkillMatchResult() {
        return skillMatchResult;
    }

    public void setSkillMatchResult(SkillMatchResult skillMatchResult) {
        this.skillMatchResult = skillMatchResult;
    }
}
