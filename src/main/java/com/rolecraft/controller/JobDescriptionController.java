package com.rolecraft.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rolecraft.model.JobDescription;
import com.rolecraft.model.JobDescriptionRequest;
import com.rolecraft.service.JobDescriptionService;


// @RequestMapping("/api/jobs")
@RestController
@RequestMapping("/api/job-description")
public class JobDescriptionController {

    private final JobDescriptionService jobService;
 
    public JobDescriptionController(JobDescriptionService jobDescriptionService) {
        this.jobService = jobDescriptionService;
    }

    @PostMapping("/parse")
    public JobDescription parseJobDescription(@RequestBody JobDescriptionRequest request) {
        return jobService.parseDescription(request.getJobDescription());
    }
}

