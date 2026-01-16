package com.rolecraft.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rolecraft.model.JobDescription;
import com.rolecraft.model.Resume;
import com.rolecraft.model.SkillMatchResult;
import com.rolecraft.model.TailoredResume;
import com.rolecraft.service.ResumeTailorService;

@RestController
@RequestMapping("/api/resume")
public class ResumeTailorController {

    private final ResumeTailorService resumeTailorService;

    public ResumeTailorController(ResumeTailorService resumeTailorService) {
        this.resumeTailorService = resumeTailorService;
    }

    @PostMapping("/tailor")
    public TailoredResume tailorResume(
            @RequestBody TailorRequest request) {
        return resumeTailorService.tailorResume(request.getResume(), request.getJobDescription(), request.getMatchResult());
    }
}

// Helper class for request
class TailorRequest {
    private Resume resume;
    private JobDescription jobDescription;
    private SkillMatchResult matchResult;

    public Resume getResume() { return resume; }
    public void setResume(Resume resume) { this.resume = resume; }

    public JobDescription getJobDescription() { return jobDescription; }
    public void setJobDescription(JobDescription jobDescription) { this.jobDescription = jobDescription; }

    public SkillMatchResult getMatchResult() { return matchResult; }
    public void setMatchResult(SkillMatchResult matchResult) { this.matchResult = matchResult; }
}
