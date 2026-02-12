package com.rolecraft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rolecraft.model.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

}
