package ie.stephen.controllers;

import ie.stephen.model.Bid;
import ie.stephen.model.Job;
import ie.stephen.model.RegisteredUser;
import ie.stephen.services.BidService;
import ie.stephen.services.JobService;
import ie.stephen.services.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController    // All returned data is converted to Json format.
@RequestMapping("/api")	// All endpoints start with localhost:8080/api
public class JobRestController {
	@Autowired
	JobService jobService;

	@Autowired
	BidService bidService;

	@Autowired
	RegisteredUserService registeredUserService;

	@PreAuthorize("hasRole('Registered')")
	@GetMapping("/activeJobs")
	public List<Job> myRestJobs() { return jobService.getAllActiveJobs(); }

	@PreAuthorize("hasRole('Registered')")
	@GetMapping("/bids{userEmail}")
	public List<Bid> myRestJobBids(@PathVariable("userEmail") String userEmail)
	{
		RegisteredUser registeredUser = registeredUserService.findByEmail(userEmail);
		return bidService.findBids(registeredUser);
	}
}
