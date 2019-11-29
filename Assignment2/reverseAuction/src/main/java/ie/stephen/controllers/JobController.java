package ie.stephen.controllers;

import ie.stephen.forms.JobForm;
import ie.stephen.model.Job;
import ie.stephen.model.RegisteredUser;
import ie.stephen.services.JobService;
import ie.stephen.services.RegisteredUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
public class JobController {

    @Autowired
    JobService jobService;

    @Autowired
    RegisteredUserService registeredUserService;

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

    @PostMapping("/createJob")
    public String register(@Valid JobForm jobForm, BindingResult binding, RedirectAttributes redirectAttributes, Principal user)
    {
        if (binding.hasErrors())
            return "createJob";

        Job job = new Job(jobForm.getName(), jobForm.getDescription(), registeredUserService.findByEmail(user.getName()));
        job = jobService.save(job);

        if (job != null )
            return "redirect:viewJobs";
        else {
            // starts again with empty form (new object)
            redirectAttributes.addFlashAttribute("duplicate", true);
            return "redirect:createJob";
        }
    }
}