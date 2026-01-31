package com.rolecraft.service;

import java.util.Set;

import com.rolecraft.model.SkillMatchResult;

public interface SkillMatchService {

    /**
     * Matches required skills against resume skills and optional preferred skills.
     *
     * @param requiredSkills The skills required by the job.
     * @param resumeSkills The skills extracted from the resume.
     * @param preferredSkills Optional preferred skills to match.
     * @return SkillMatchResult containing matched skills, missing skills, and match percentage.
     */
    SkillMatchResult matchSkills(
            Set<String> requiredSkills,
            Set<String> resumeSkills,
            Set<String> preferredSkills
    );
}
