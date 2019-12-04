package ie.stephen.services;

import ie.stephen.model.Job;

import java.util.List;

public interface JobService {

    Job save(Job job);
    Job getJob(int id);
    List<Job> getAllJobs();
    List<Job> getAllActiveJobs();
}
