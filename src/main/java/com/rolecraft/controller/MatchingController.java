package com.rolecraft.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rolecraft.controller.dto.SkillMatchRequest;
import com.rolecraft.model.SkillMatchResult;
import com.rolecraft.service.SkillMatchService;

@RestController
@RequestMapping("/api/match")
public class MatchingController {

    private final SkillMatchService skillMatchService;

    public MatchingController(SkillMatchService skillMatchService) {
        this.skillMatchService = skillMatchService;
    }

    @PostMapping("/skills")
    public ResponseEntity<SkillMatchResult> matchSkills(
            @RequestBody SkillMatchRequest request
    ) {

        Set<String> requiredSkills = new HashSet<>(request.getRequiredSkills());

        Set<String> resumeSkills = new HashSet<>(request.getResumeSkills());

        SkillMatchResult result = skillMatchService.matchSkills(requiredSkills, resumeSkills, new HashSet<>());

        return ResponseEntity.ok(result);
    }
}
