package com.rolecraft.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rolecraft.model.Resume;
import com.rolecraft.model.SkillMatchResult;
import com.rolecraft.service.SkillMatchService;

@RestController
@RequestMapping("/api/match")
public class MatchingController {

    private final SkillMatchService skillMatchService;

    public MatchingController(SkillMatchService skillMatchService) {
        this.skillMatchService = skillMatchService;
    }

    @PostMapping
    public ResponseEntity<SkillMatchResult> matchSkills(
            @RequestBody Resume resume,
            @RequestParam Set<String> requiredSkills) {

        SkillMatchResult result =
                skillMatchService.match(requiredSkills, resume);

        return ResponseEntity.ok(result);
    }
}
