package ie.stephen.services;

import ie.stephen.model.Job;

import java.util.List;

public interface JobService {

    Job save(Job job);
    List<Job> getAllJobs();
}