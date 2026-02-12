package com.rolecraft.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rolecraft.controller.dto.ApiResponse;
import com.rolecraft.controller.dto.ResumeTailorRequest;
import com.rolecraft.model.TailoredResume;
import com.rolecraft.service.ResumeTailorService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/resume")
public class ResumeTailorController {

    private final ResumeTailorService resumeTailorService;

    private static final Logger logger =
        LoggerFactory.getLogger(ResumeTailorController.class);

    public ResumeTailorController(ResumeTailorService resumeTailorService) {
        this.resumeTailorService = resumeTailorService;
    }

    @PostMapping("/tailor")
    public ResponseEntity<ApiResponse<TailoredResume>> tailorResume(
            @Valid @RequestBody ResumeTailorRequest request) {

        logger.info("Received resume tailoring request for job title: {}",
            request.getJobDescription().getTitle());

        TailoredResume tailoredResume =
                resumeTailorService.tailorResume(
                        request.getResume(),
                        request.getJobDescription()
                        
                );
        logger.info("Resume tailoring completed. Match: {}%, ATS: {}%",
            tailoredResume.getMatchPercentage(),
            tailoredResume.getAtsScore());

        return ResponseEntity.ok(ApiResponse.success(tailoredResume));
    }
}
