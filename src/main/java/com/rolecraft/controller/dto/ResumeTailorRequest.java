package com.rolecraft.controller.dto;

import com.rolecraft.model.JobDescription;
import com.rolecraft.model.Resume;
import com.rolecraft.model.SkillMatchResult;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class ResumeTailorRequest {

    @NotNull(message = "Resume cannot be null")
    @Valid
    private Resume resume;
    @NotNull(message = "Job description cannot be null")
    @Valid
    private JobDescription jobDescription;
    private SkillMatchResult skillMatchResult;

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
