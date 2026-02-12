package com.rolecraft.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rolecraft.controller.dto.ApiResponseWrapper;
import com.rolecraft.controller.dto.ResumeTailorRequest;
import com.rolecraft.model.TailoredResume;
import com.rolecraft.service.ResumeTailorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(name = "Resume Tailoring API", description = "APIs for resume skill matching and AI-powered tailoring")
@RestController
@RequestMapping("/api/resume")
public class ResumeTailorController {

    private final ResumeTailorService resumeTailorService;

    private static final Logger logger =
        LoggerFactory.getLogger(ResumeTailorController.class);

    public ResumeTailorController(ResumeTailorService resumeTailorService) {
        this.resumeTailorService = resumeTailorService;
    }

    @Operation(
        summary = "Tailor resume for a job",
        description = "Matches resume skills with job description and generates ATS score and AI recommendations")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Resume tailored successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping("/tailor")
    public ResponseEntity<ApiResponseWrapper<TailoredResume>> tailorResume(
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

        return ResponseEntity.ok(ApiResponseWrapper.success(tailoredResume));
    }
}
