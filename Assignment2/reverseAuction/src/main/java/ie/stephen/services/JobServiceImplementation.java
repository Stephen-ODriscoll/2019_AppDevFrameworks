package ie.stephen.services;

import ie.stephen.dao.JobDao;
import ie.stephen.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImplementation implements JobService {

    @Autowired
    JobDao jobDAO;

    @Override
    public Job save(Job job) {
        return jobDAO.save(job);
    }

    @Override
    public Job getJob(int id) {
        return jobDAO.findByJobId(id);
    }

    @Override
    public List<Job> getAllActiveJobs() {
        List<Job> activeJobs = new ArrayList<>();
        for (Job job : jobDAO.findAll()) {
            if (job.getOpen()) {
                activeJobs.add(job);
            }
        }
        return activeJobs;
    }

    @Override
    public List<Job> getAllJobs() {
        return jobDAO.findAll();
    }
}
