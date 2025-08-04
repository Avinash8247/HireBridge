package com.microservices.jobms.job;

import com.microservices.jobms.job.dto.jobDto;

import java.util.List;

public interface JobService {
    List<jobDto> findAll();
    void createJob(Job job);

   jobDto findbyid(Long id);

    boolean deletebyid(Long id);

    boolean update(Long id, Job updatejob);
}
