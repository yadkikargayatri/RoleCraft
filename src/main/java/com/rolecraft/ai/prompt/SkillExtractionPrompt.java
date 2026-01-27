package com.rolecraft.ai.prompt;

public class SkillExtractionPrompt {

    public static String buildPrompt(String jobDescription) {
        return """
        You are an expert technical recruiter.

        Extract a concise list of technical and professional skills 
        required for the following job description.

        Return the result as a comma-separated list.

        Job Description:
        %s
        """.formatted(jobDescription);
    }
}

