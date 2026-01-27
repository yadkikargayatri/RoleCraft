package com.rolecraft.ai.service;

import java.util.List;

public interface SkillExtractionService {
    List<String> extractSkills(String jobDescriptionText); 
}
