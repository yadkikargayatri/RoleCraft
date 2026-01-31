package com.rolecraft.ai.client;

import org.springframework.stereotype.Component;

@Component

public class MockLLMClient implements LLMClient {
    
    @Override
    public String complete(String prompt) {
        return "Java, Spring Boot, REST APIs, SQL, AWS";
    }
}
    
