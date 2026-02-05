package com.rolecraft.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.rolecraft.model.SkillMatchResult;
import com.rolecraft.service.SkillMatchService;

@Service
public class SkillMatchServiceImpl implements SkillMatchService {

    @Override
    public SkillMatchResult matchSkills(
            Set<String> requiredSkills,
            Set<String> resumeSkills,
            Set<String> preferredSkills // optional, can be empty
    ) {
        SkillMatchResult result = new SkillMatchResult();

        // Matched skills: intersection of required and resume skills
        Set<String> matched = new HashSet<>(resumeSkills);
        matched.retainAll(requiredSkills);
        result.setMatchedSkills(matched);

        // Missing skills: required skills not in resume
        Set<String> missing = new HashSet<>(requiredSkills);
        missing.removeAll(resumeSkills);
        result.setMissingSkills(missing);

        // Extra skills: resume skills not in required
        Set<String> extra = new HashSet<>(resumeSkills);
        extra.removeAll(requiredSkills);
        result.setExtraSkills(extra);

        // Optional: matched preferred skills
        Set<String> matchedPreferred = new HashSet<>(resumeSkills);
        matchedPreferred.retainAll(preferredSkills);
        result.setMatchedPreferredSkills(matchedPreferred);

        // Calculate match percentage
        int totalRequired = requiredSkills.size();
        double percentage = totalRequired == 0 ? 100.0
                : ((double) matched.size() / totalRequired) * 100.0;
        result.setMatchPercentage(Math.round(percentage * 100.0) / 100.0);

        return result;
    }
}
