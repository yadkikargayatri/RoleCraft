package com.rolecraft.service;

import org.springframework.web.multipart.MultipartFile;

import com.rolecraft.model.Resume;

public interface ResumeService {
    Resume parseResume(MultipartFile file);
}