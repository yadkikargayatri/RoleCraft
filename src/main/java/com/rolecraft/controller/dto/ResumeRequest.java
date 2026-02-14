package com.rolecraft.controller.dto;

import com.rolecraft.model.JobDescription;

import jakarta.validation.constraints.NotBlank;

public class ResumeRequest {

    @NotBlank(message = "Resume content cannot be blank")
    private String resumeText;

    @NotBlank(message = "Job description cannot be blank")
    private JobDescription jobDescription;

    // Constructors
    public ResumeRequest() {}

    public ResumeRequest(String resumeText, JobDescription jobDescription) {
        this.resumeText = resumeText;
        this.jobDescription = jobDescription;
    }

    // Getters & Setters
    public String getResumeText() {
        return resumeText;
    }

    public void setResumeText(String resumeText) {
        this.resumeText = resumeText;
    }

    public JobDescription getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(JobDescription jobDescription) {
        this.jobDescription = jobDescription;
    }
}
