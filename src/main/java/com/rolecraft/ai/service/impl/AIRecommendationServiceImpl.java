package com.rolecraft.ai.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rolecraft.ai.client.LLMClient;
import com.rolecraft.ai.service.AIRecommendationService;
import com.rolecraft.model.JobDescription;
import com.rolecraft.model.Resume;
import com.rolecraft.model.SkillMatchResult;

@Service
public class AIRecommendationServiceImpl implements AIRecommendationService {

    private final LLMClient llmClient;

    public AIRecommendationServiceImpl(LLMClient llmClient) {
        this.llmClient = llmClient;
    }

    @Override
    public List<String> suggestImprovements(
            Resume resume,
            JobDescription jd,
            SkillMatchResult skillMatchResult
    ) {
        String prompt = """
            You are an AI assistant for resume optimization.
            A job description and a candidate's resume are provided.
            Identify improvements to make the resume match the job description better.
            Focus on:
            - Missing required skills
            - Missing preferred skills
            - Experience alignment
            - General suggestions to highlight relevant points
            Provide the suggestions as a bullet-point list.

            Job Description Required Skills: %s
            Job Description Preferred Skills: %s
            Resume Skills: %s
            Resume Experience Summary: %s
            Matched Skills: %s
            Missing Skills: %s
            """.formatted(
                jd.getRequiredSkills(),
                jd.getPreferredSkills(),
                resume.getSkills(),
                resume.getSummary(),
                skillMatchResult.getMatchedSkills(),
                skillMatchResult.getMissingSkills()
            );

        String response = llmClient.complete(prompt);

        if(response == null || response.isBlank()) {
            return List.of("No AI suggestions available.");
        }

        return response.lines()
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .toList();
    }
}

