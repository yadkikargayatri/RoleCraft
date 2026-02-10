package com.rolecraft.controller.dto;

import jakarta.validation.constraints.NotBlank;

public class ResumeRequest {

    @NotBlank(message = "Resume content cannot be blank")
    private String resumeText;

    @NotBlank(message = "Job description cannot be blank")
    private String jobDescription;

    // Constructors
    public ResumeRequest() {}

    public ResumeRequest(String resumeText, String jobDescription) {
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

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}
