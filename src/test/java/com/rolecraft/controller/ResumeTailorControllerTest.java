// package com.rolecraft.controller;

// import java.util.Set;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.rolecraft.controller.dto.ResumeTailorRequest;
// import com.rolecraft.model.JobDescription;



// @SpringBootTest
// @AutoConfigureMockMvc
// class ResumeTailorControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @Autowired
//     private ObjectMapper objectMapper;

//     @Test
//     void shouldReturn200WhenTailoringResume() throws Exception {

//         ResumeTailorRequest request = new ResumeTailorRequest();
//         request.setTitle("Backend Developer");
//         request.setSummary("Java Spring Boot developer with 3 years experience");
//         request.setSkills(Set.of("Java", "Spring Boot", "SQL"));
//         request.setExperienceBullets(Set.of("Built REST APIs", "Optimized DB queries"));
//         JobDescription jobDescription = new JobDescription();
//         jobDescription.setRequiredSkills(Set.of("Java", "Spring Boot"));
//         request.setJobDescription(jobDescription);
        

//         mockMvc.perform(post("/api/resume/tailor")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(request)))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.success").value(true))
//                 .andExpect(jsonPath("$.data.title").exists())
//                 .andExpect(jsonPath("$.data.matchPercentage").exists())
//                 .andExpect(jsonPath("$.data.atsScore").exists());
//     }
// }
