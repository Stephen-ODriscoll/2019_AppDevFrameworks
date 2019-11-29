package ie.stephen.config;

import ie.stephen.model.Job;
import ie.stephen.services.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Slf4j
@Component
public class ScheduleConfig {

    @Autowired
    JobService jobService;

    // At 12 o clock every day
    @Scheduled(cron = "0 00 12 * * *")
    public void closeJobs() {
        List<Job> jobs = jobService.getAllJobs();

        for (Job job : jobs) {
            Period period = Period.between(job.getDate(), LocalDate.now());

            // 20 days old it's set to closed (not open)
            if (period.getDays() > 20 && job.getOpen()) {
                job.setOpen(false);
            }
        }
    }
}
