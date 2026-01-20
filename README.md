# RoleCraft
AI based resume powering system

Problem Statement

    Given:
        1. A job link or job description
        2. A base resume
        3. Automatically generate a tailored, ATS-optimized resume aligned to the job requirements, without fabricating experience.

Core Features (MVP → Advanced)

    MVP (Must-Have)
    Job Description Ingestion
    Paste job description OR
    Provide job link → scrape description
    Resume Parsing
    Upload resume (PDF/DOCX)
    Convert into structured data (sections, bullets, skills)
    JD–Resume Matching

Identify:

    Required skills
    Preferred skills
    Keywords

Role expectations

	Tailored Resume Generation
	Re-order bullets
	Rewrite bullets using JD terminology
	Highlight relevant experience
	De-emphasize unrelated content
	Preserve truthfulness
	Export
	Download tailored resume as PDF or DOCX


High-Level Architecture
Client (Web UI)
   |
   v
Resume Tailoring API (Spring Boot)
   |
   +-- Resume Parser Service
   |
   +-- JD Ingestion Service
   |
   +-- Skill & Keyword Extractor
   |
   +-- Matching & Scoring Engine
   |
   +-- LLM Resume Rewrite Service
   |
   +-- Export Service (PDF/DOCX)

# Spring Boot Application

This project is now a Spring Boot application. To run the application, use the following command:

```bash
mvn spring-boot:run
```

## Features
- AI-based resume generation
- ATS optimization

## Getting Started
1. Clone the repository.
2. Navigate to the project directory.
3. Run the application using Maven.

## Requirements
- Java 17 or higher
- Maven
