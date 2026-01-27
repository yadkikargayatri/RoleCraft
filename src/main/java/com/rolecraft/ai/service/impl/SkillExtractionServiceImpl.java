package com.rolecraft.ai.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rolecraft.ai.client.LLMClient;
import com.rolecraft.ai.prompt.SkillExtractionPrompt;
import com.rolecraft.ai.service.SkillExtractionService;

@Service
public class SkillExtractionServiceImpl implements SkillExtractionService {
    
    private final LLMClient llmClient;

    public SkillExtractionServiceImpl(LLMClient llmClient) {
        this.llmClient = llmClient;
    }

    @Override
    public List<String> extractSkills(String jobDescriptionText) {
        String prompt = SkillExtractionPrompt.buildPrompt(jobDescriptionText);
        String response = llmClient.complete(prompt);
        return Arrays.stream(response.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();
    }
}