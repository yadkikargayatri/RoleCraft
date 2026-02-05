package com.rolecraft.service.impl;

import org.springframework.stereotype.Service;

import com.rolecraft.model.ATSScoreResult;
import com.rolecraft.model.JobDescription;
import com.rolecraft.model.SkillMatchResult;
import com.rolecraft.service.ATSScoringService;

@Service
public class ATSScoringServiceImpl implements ATSScoringService {

    @Override
    public ATSScoreResult calculateScore(SkillMatchResult skillMatchResult, JobDescription jd) {
        ATSScoreResult result = new ATSScoreResult();

        int requiredTotal = jd.getRequiredSkills().size();
        int preferredTotal = jd.getPreferredSkills().size();

        int requiredMatched = skillMatchResult.getMatchedSkills().size();
        int preferredMatched = skillMatchResult.getMatchedSkills().size();

        double requiredScore =
                requiredTotal == 0 ? 100 :
                ((double) requiredMatched / requiredTotal) * 60;

        double preferredScore =
                preferredTotal == 0 ? 100 :
                ((double) preferredMatched / preferredTotal) * 25;

        double coverageScore =
                ((double) (requiredMatched + preferredMatched) /
                (requiredTotal + preferredTotal)) * 15;
        
        result.setRequiredSkillScore(requiredScore);
        result.setPreferredSkillScore(preferredScore);
        result.setCoverageScore(coverageScore);
        result.setFinalScore(
                requiredScore + preferredScore + coverageScore
        );

        return result;
    }
}
