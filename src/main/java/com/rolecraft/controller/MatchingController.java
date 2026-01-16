package com.rolecraft.controller;

import com.rolecraft.model.SkillMatchResult;
import com.rolecraft.service.SkillMatchingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match")
public class MatchingController {

    private final SkillMatchingService skillMatchingService;

    public MatchingController(SkillMatchingService skillMatchingService) {
        this.skillMatchingService = skillMatchingService;
    }

    @PostMapping("/skills")
    public SkillMatchResult matchSkills(
            @RequestParam List<String> resumeSkills,
            @RequestParam List<String> jobSkills) {

        return skillMatchingService.matchSkills(resumeSkills, jobSkills);
    }
}
