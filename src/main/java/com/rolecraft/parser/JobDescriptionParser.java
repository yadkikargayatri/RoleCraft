package com.rolecraft.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.rolecraft.model.JobDescription;
@Component
public class JobDescriptionParser {

    // Predefined skill list
    private static final List<String> SKILL_KEYWORDS = Arrays.asList(
            "Java", "Spring", "Spring Boot", "AWS", "Docker", "Kubernetes",
            "React", "SQL", "PostgreSQL", "Microservices", "REST", "CI/CD"
    );

    public JobDescription parse(String jdText) {
        JobDescription jd = new JobDescription();

        // Normalize text for parsing
        String normalizedText = jdText.replaceAll("\\r?\\n", "\n");

        // Raw text
        jd.setRawText(jdText);

        // Title
        jd.setTitle(extractTitle(jdText));

        // Responsibilities
        jd.setResponsibilities(extractResponsibilities(jdText));

        // Required Skills
        jd.setRequiredSkills(extractRequiredSkills(normalizedText));

        // Preferred Skills
        jd.setPreferredSkills(extractPreferredSkills(normalizedText));

        // Keywords (skills + important terms)
        jd.setKeywords(extractKeywords(normalizedText));

        // Combined skill set
        jd.setSkills(extractSkills(normalizedText));

        return jd;
    }

    private String extractTitle(String text) {
        String[] lines = text.split("\\r?\\n");
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                return line.trim();
            }
        }
        return "Unknown Title";
    }

    private List<String> extractResponsibilities(String text) {
        List<String> responsibilities = new ArrayList<>();
        String[] lines = text.split("\\r?\\n");
        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("-") || line.startsWith("•")) {
                responsibilities.add(line.replaceAll("[-•]", "").trim());
            }
        }
        return responsibilities;
    }

    private List<String> extractRequiredSkills(String text) {
        List<String> required = new ArrayList<>();
        String lower = text.toLowerCase();
        for (String skill : SKILL_KEYWORDS) {
            if (lower.contains(skill.toLowerCase()) && lower.contains("required")) {
                required.add(skill);
            }
        }
        return required;
    }

    private List<String> extractPreferredSkills(String text) {
        List<String> preferred = new ArrayList<>();
        String lower = text.toLowerCase();
        for (String skill : SKILL_KEYWORDS) {
            if (lower.contains(skill.toLowerCase())
                    && (lower.contains("preferred") || lower.contains("nice to have"))) {
                preferred.add(skill);
            }
        }
        return preferred;
    }

    private List<String> extractSkills(String text) {
        Set<String> skills = new LinkedHashSet<>();
        String lower = text.toLowerCase();
        for (String skill : SKILL_KEYWORDS) {
            if (lower.contains(skill.toLowerCase())) {
                skills.add(skill);
            }
        }
        return new ArrayList<>(skills);
    }

    private List<String> extractKeywords(String text) {
        Set<String> keywords = new LinkedHashSet<>(extractSkills(text));
        // Add other important terms manually or via simple heuristics
        if (text.toLowerCase().contains("backend")) keywords.add("backend");
        if (text.toLowerCase().contains("frontend")) keywords.add("frontend");
        if (text.toLowerCase().contains("microservices")) keywords.add("microservices");
        if (text.toLowerCase().contains("rest")) keywords.add("REST");
        if (text.toLowerCase().contains("scalable")) keywords.add("scalable");
        return new ArrayList<>(keywords);
    }
}
