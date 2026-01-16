package com.rolecraft.service.impl;

import com.rolecraft.model.SkillMatchResult;
import com.rolecraft.service.SkillMatchingService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SkillMatchingServiceImpl implements SkillMatchingService {

    @Override
    public SkillMatchResult matchSkills(List<String> resumeSkills, List<String> jobSkills) {

        Set<String> resumeSet = normalize(resumeSkills);
        Set<String> jobSet = normalize(jobSkills);

        List<String> matched = new ArrayList<>();
        List<String> missing = new ArrayList<>();

        for (String skill : jobSet) {
            if (resumeSet.contains(skill)) {
                matched.add(skill);
            } else {
                missing.add(skill);
            }
        }

        double matchPercentage =
                jobSet.isEmpty() ? 0 : ((double) matched.size() / jobSet.size()) * 100;

        SkillMatchResult result = new SkillMatchResult();
        result.setMatchedSkills(matched);
        result.setMissingSkills(missing);
        result.setMatchPercentage(Math.round(matchPercentage * 100.0) / 100.0);

        return result;
    }

    private Set<String> normalize(List<String> skills) {
        Set<String> normalized = new HashSet<>();
        if (skills == null) return normalized;

        for (String skill : skills) {
            normalized.add(skill.toLowerCase().trim());
        }
        return normalized;
    }
}
