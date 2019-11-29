package ie.stephen.controllers;

import ie.stephen.model.Bid;
import ie.stephen.model.Job;
import ie.stephen.services.BidService;
import ie.stephen.services.JobService;
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

	@PreAuthorize("hasRole('Registered')")
	@GetMapping("/jobs")
	public List<Job> myRestJobs() { return jobService.getAllJobs(); }

	@PreAuthorize("hasRole('Registered')")
	@GetMapping("/job/{jobId}")
	public Job myRestJob(@PathVariable("jobId") int jobId)
	{
		return jobService.findJob(jobId);
	}

	@PreAuthorize("hasRole('Registered')")
	@GetMapping("/bids{jobId}")
	public List<Bid> myRestJobBids(@PathVariable("jobId") int jobId)
	{
		return jobService.findJob(jobId).getBids();
	}
}
