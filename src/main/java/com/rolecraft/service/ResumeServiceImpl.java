package com.rolecraft.service;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rolecraft.model.Resume;
import com.rolecraft.parser.ResumeParser;

@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeParser resumeParser;

    public ResumeServiceImpl(ResumeParser resumeParser) {
        this.resumeParser = resumeParser;
    }

    @Override
    public Resume parseResume(MultipartFile file) {
        return resumeParser.parse(file);
    }
}