package com.click.firstjob.Job;

import java.util.List;

public interface JobService {

    List<Job> findAll();
    void createJob(Job job);

    Job getById(long id);

    boolean deleteById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
