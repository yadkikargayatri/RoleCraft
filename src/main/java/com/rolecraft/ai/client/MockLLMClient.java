package com.rolecraft.ai.client;

import org.springframework.stereotype.Component;

@Component

public class MockLLMClient implements LLMClient {
    
    @Override
    public String complete(String prompt) {
        return """
        • Add AWS and cloud deployment experience
        • Highlight Spring Boot microservices work
        • Quantify performance improvements
        • Align project descriptions with job requirements
        """;
    }
}
    
