package com.rolecraft.ai.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rolecraft.ai.client.LLMClient;
import com.rolecraft.ai.service.SkillExtractionService;

@Service
public class SkillExtractionServiceImpl implements SkillExtractionService {

    private final LLMClient llmClient;

    public SkillExtractionServiceImpl(LLMClient llmClient) {
        this.llmClient = llmClient;
    }

    @Override
    public List<String> extractFromResume(String resumeText) {
        String prompt = """
            Extract technical skills from the following resume text.
            Return a comma-separated list of skills only.

            Resume:
            %s
            """.formatted(resumeText);

        return callLLM(prompt);
    }

    @Override
    public List<String> extractFromJobDescription(String jobDescriptionText) {
        String prompt = """
            Extract required technical skills from the following job description.
            Return a comma-separated list of skills only.

            Job Description:
            %s
            """.formatted(jobDescriptionText);

        return callLLM(prompt);
    }

    private List<String> callLLM(String prompt) {  // callLLM() splits the comma-separated respons, trims and filters emmpty strings and removes duplicates
        String response = llmClient.complete(prompt);

        return Arrays.stream(response.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .distinct()
                .collect(Collectors.toList());
    }
}
