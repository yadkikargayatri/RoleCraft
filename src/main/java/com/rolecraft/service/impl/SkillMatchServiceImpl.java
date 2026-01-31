package com.rolecraft.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
            Set<String> preferredSkills
    ) {
        Set<String> matched = new HashSet<>(resumeSkills);
        matched.retainAll(requiredSkills);

        List<String> missing = new ArrayList<>(requiredSkills);
        missing.removeAll(matched);

        double matchPercentage = 0.0;
        if (!requiredSkills.isEmpty()) {
            matchPercentage = ((double) matched.size() / requiredSkills.size()) * 100;
        }

        SkillMatchResult result = new SkillMatchResult();
        result.setMatchedSkills(new ArrayList<>(matched));
        result.setMissingSkills(missing);
        result.setMatchPercentage(matchPercentage);

        return result;
    }
}
