package ie.stephen.controllers;

import ie.stephen.model.Job;
import ie.stephen.services.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class JobController {

    @Autowired
    JobService jobService;

    @GetMapping("/viewJobs")
    public String viewJobs(Model model)
    {
        List<Job> jobs = jobService.getAllJobs();
        model.addAttribute("jobs", jobs);
        return "viewJobs";
    }

    @GetMapping(value= {"/createJob"})
    public String createJob()
    {
        return "createJob";
    }
}
