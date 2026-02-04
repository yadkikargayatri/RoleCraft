package com.rolecraft.parser;

import java.io.InputStream;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.rolecraft.ai.service.SkillExtractionService;
import com.rolecraft.model.Resume;

@Component
public class ResumeParser {

    private final Tika tika = new Tika();
    private final SkillExtractionService skillExtractionService;

    public ResumeParser(SkillExtractionService skillExtractionService) {
        this.skillExtractionService = skillExtractionService;
    }

    public Resume parse(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {

            String content = tika.parseToString(is);
            content = content.replaceAll("\\r?\\n+", "\n");

            Resume resume = new Resume();
            resume.setTitle(extractTitle(content));
            resume.setSummary(extractSummary(content));

            // ✅ Use AI to extract skills
            List<String> skills = skillExtractionService.extractFromResume(content);
            resume.setSkills(new LinkedHashSet<>(skills));

            // Experience bullets (basic placeholder)
            resume.setExperienceBullets(new LinkedHashSet<>(extractExperience(content)));

            return resume;

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse resume", e);
        }
    }

    private String extractTitle(String text) {
        if (text == null || text.isEmpty()) return "Software Engineer";

        String[] lines = text.split("\\R");
        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.matches("^[A-Z ]{5,40}$") && trimmed.contains("ENGINEER")) {
                return capitalizeWords(trimmed.toLowerCase());
            }
            if (trimmed.matches("(?i).*(software engineer|backend engineer|java developer).*")) {
                return capitalizeWords(trimmed);
            }
        }
        return "Software Engineer";
    }

    private String capitalizeWords(String input) {
        String[] words = input.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word.isEmpty()) continue;
            sb.append(Character.toUpperCase(word.charAt(0)))
              .append(word.substring(1))
              .append(" ");
        }
        return sb.toString().trim();
    }

    private String extractSummary(String content) {
        Pattern pattern = Pattern.compile(
            "(?i)(SUMMARY|Professional Summary)\\s*[:\\-]?\\s*(.*?)(?=\\s+[A-Z]{2,})"
        );
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) return matcher.group(2).trim();
        return "";
    }

    private List<String> extractExperience(String text) {
        // Could later leverage AI for bullet points
        return List.of();
    }
}
