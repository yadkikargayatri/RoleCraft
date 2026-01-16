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

    private static final List<String> SKILL_KEYWORDS = Arrays.asList(
            "Java", "Spring", "Spring Boot", "AWS", "Docker", "Kubernetes",
            "React", "SQL", "PostgreSQL", "Microservices", "REST", "CI/CD"
    );

    public JobDescription parse(String jdText) {
        String normalized = jdText.replaceAll("\\r?\\n", " ");

        JobDescription jd = new JobDescription();
        jd.setTitle(extractTitle(jdText));
        jd.setRequiredSkills(extractRequiredSkills(normalized));
        jd.setPreferredSkills(extractPreferredSkills(normalized));
        jd.setResponsibilities(extractResponsibilities(jdText));
        jd.setKeywords(extractKeywords(normalized));

        return jd;
    }

    private String extractTitle(String text) {
        String[] lines = text.split("\\r?\\n");
        return lines.length > 0 ? lines[0].trim() : "Unknown Title";
    }

    private List<String> extractRequiredSkills(String text) {
        List<String> required = new ArrayList<>();

        for (String skill : SKILL_KEYWORDS) {
            if (text.toLowerCase().contains(skill.toLowerCase())
                    && text.toLowerCase().contains("required")) {
                required.add(skill);
            }
        }
        return required;
    }

    private List<String> extractPreferredSkills(String text) {
        List<String> preferred = new ArrayList<>();

        for (String skill : SKILL_KEYWORDS) {
            if (text.toLowerCase().contains(skill.toLowerCase())
                    && (text.toLowerCase().contains("preferred")
                    || text.toLowerCase().contains("nice to have"))) {
                preferred.add(skill);
            }
        }
        return preferred;
    }

    private List<String> extractResponsibilities(String text) {
        List<String> responsibilities = new ArrayList<>();
        String[] lines = text.split("\\r?\\n");

        for (String line : lines) {
            if (line.trim().startsWith("-") || line.trim().startsWith("•")) {
                responsibilities.add(line.replaceAll("[-•]", "").trim());
            }
        }
        return responsibilities;
    }

    private List<String> extractKeywords(String text) {
        Set<String> keywords = new LinkedHashSet<>();

        for (String skill : SKILL_KEYWORDS) {
            if (text.toLowerCase().contains(skill.toLowerCase())) {
                keywords.add(skill);
            }
        }
        return new ArrayList<>(keywords);
    }
}
