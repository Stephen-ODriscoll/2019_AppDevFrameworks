package ie.stephen.controllers;

import ie.stephen.forms.JobForm;
import ie.stephen.model.Bid;
import ie.stephen.model.Job;
import ie.stephen.services.BidService;
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
    BidService bidService;

    @Autowired
    RegisteredUserService registeredUserService;

    @GetMapping("/viewJobs")
    public String viewJobs(Model model)
    {
        List<Job> jobs = jobService.getAllJobs();
        List<Bid> winningBids = bidService.getWinningBids(jobs);
        model.addAttribute("jobs", jobs);
        model.addAttribute("winningBids", winningBids);
        return "viewJobs";
    }

    @GetMapping(value= {"/createJob"})
    public String createJob(Model model)
    {
        model.addAttribute("jobForm", new JobForm());
        return "createJob";
    }

    @PostMapping("/createJob")
    public String register(@Valid JobForm jobForm, BindingResult binding, RedirectAttributes redirectAttributes, Principal user)
    {
        if (binding.hasErrors())
            return "createJob";

        Job job = jobService.save(new Job(jobForm.getName(), jobForm.getDescription(), registeredUserService.findByEmail(user.getName())));
        if (job != null )
            return "redirect:viewJobs";
        else {
            // starts again with empty form (new object)
            redirectAttributes.addFlashAttribute("error", true);
            return "redirect:createJob";
        }
    }
}
