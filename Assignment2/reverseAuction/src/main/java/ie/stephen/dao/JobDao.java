package ie.stephen.dao;

import ie.stephen.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobDao  extends JpaRepository<Job, Integer> {

    Job findByJobId(int jobId);
}
