package com.rolecraft.service;

import com.rolecraft.model.ATSScoreResult;
import com.rolecraft.model.JobDescription;
import com.rolecraft.model.SkillMatchResult;

public interface ATSScoringService {
    ATSScoreResult calculateScore(SkillMatchResult skillMatchResult, JobDescription jd);
}
