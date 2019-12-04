package ie.stephen.controllers;

import ie.stephen.forms.BidForm;
import ie.stephen.model.Bid;
import ie.stephen.model.Job;
import ie.stephen.model.RegisteredUser;
import ie.stephen.services.BidService;
import ie.stephen.services.JobService;
import ie.stephen.services.RegisteredUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
public class BidController {
    @Autowired
    BidService bidService;

    @Autowired
    JobService jobService;

    @Autowired
    RegisteredUserService registeredUserService;

    @GetMapping(value= {"/bid/{jobId}"})
    public String bid(@PathVariable("jobId") int jobId, Model model)
    {
        model.addAttribute("bidForm", new BidForm());
        return "bid";
    }

    @PostMapping("/bid/{jobId}")
    public String bid(@PathVariable("jobId") int jobId, @Valid BidForm bidForm, BindingResult binding, RedirectAttributes redirectAttributes, Principal user)
    {
        if (binding.hasErrors())
            return "bid/" + jobId;

        double amount = bidForm.getAmount();
        Job job = jobService.getJob(jobId);
        RegisteredUser bidder = registeredUserService.findByEmail(user.getName());
        if (job == null || !bidService.isValid(amount, job, bidder)) {
            redirectAttributes.addFlashAttribute("error", true);
            return "redirect:" + jobId;
        }

        bidService.save(new Bid(amount, job, bidder));
        return "redirect:/viewJobs";
    }

    @GetMapping(value= {"/bidHistory/{jobId}"})
    public String bidHistory(@PathVariable("jobId") int jobId, Model model)
    {
        Job job = jobService.getJob(jobId);
        List<Bid> bids =  bidService.getBids(job);
        model.addAttribute("job", job);
        model.addAttribute("bids", bids);
        return "bidHistory";
    }
}
