package com.rolecraft.parser;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rolecraft.ai.service.SkillExtractionService;
import com.rolecraft.model.JobDescription;

@Component
public class JobDescriptionParser {

    private final SkillExtractionService skillExtractionService;

    public JobDescriptionParser(SkillExtractionService skillExtractionService) {
        this.skillExtractionService = skillExtractionService;
    }

    public JobDescription parse(String jdText) {
        JobDescription jd = new JobDescription();
       // String normalizedText = jdText.replaceAll("\\r?\\n", "\n");

        jd.setRawText(jdText);
        jd.setTitle(extractTitle(jdText));
        jd.setResponsibilities(extractResponsibilities(jdText));

        // ✅ Use AI for required and preferred skills
       List<String> extractedSkills = extractSkills(jdText);
       
        jd.setRequiredSkills(extractedSkills);
        jd.setPreferredSkills(extractedSkills);
        jd.setSkills(extractedSkills);
       // jd.setKeywords(extractKeywords(jdText, extractedSkills));

        return jd;
    }

    private List<String> extractSkills(String text) {
        return skillExtractionService.extractFromJobDescription(text);
    }
  

    private String extractTitle(String text) {
        String[] lines = text.split("\\r?\\n");
        for (String line : lines) {
            if (!line.trim().isEmpty()) return line.trim();
        }
        return "Unknown Title";
    }

    private List<String> extractResponsibilities(String text) {
        List<String> responsibilities = new java.util.ArrayList<>();
        String[] lines = text.split("\\r?\\n");
        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("-") || line.startsWith("•")) {
                responsibilities.add(line.replaceAll("[-•]", "").trim());
            }
        }
        return responsibilities;
    }

    // private List<String> extractKeywords(String text, List<String> skills) {
    //     Set<String> keywords = new LinkedHashSet<>(skills);
    //     // Optional: add AI-based important terms here later
    //     if (text.toLowerCase().contains("backend")) keywords.add("backend");
    //     if (text.toLowerCase().contains("frontend")) keywords.add("frontend");
    //     if (text.toLowerCase().contains("microservices")) keywords.add("microservices");
    //     if (text.toLowerCase().contains("rest")) keywords.add("REST");
    //     if (text.toLowerCase().contains("scalable")) keywords.add("scalable");
    //     return List.copyOf(keywords);
    // }
}
