package com.rolecraft.controller.dto;

import com.rolecraft.model.JobDescription;
import com.rolecraft.model.Resume;
import com.rolecraft.model.SkillMatchResult;

public class ResumeTailorRequest {

    private Resume resume;
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
