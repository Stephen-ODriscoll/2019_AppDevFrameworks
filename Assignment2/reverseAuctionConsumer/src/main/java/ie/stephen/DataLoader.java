package ie.stephen;

import ie.stephen.model.Bid;
import ie.stephen.model.Job;
import ie.stephen.model.RegisteredUser;
import ie.stephen.services.BidService;
import ie.stephen.services.JobService;
import ie.stephen.services.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    RegisteredUserService registeredUserService;
    @Autowired
    JobService jobService;
    @Autowired
    BidService bidService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        RegisteredUser user = registeredUserService.save(new RegisteredUser("ll","llls", "111-1111111"));
        System.out.println(registeredUserService.getAllRegisteredUsers().size());

        Job job = jobService.save(new Job("jname","jdesc", user));
        Job job1 = jobService.save(new Job("jname1","jdesc1", user));
        Job job2 = jobService.save(new Job("jname2","jdesc2", user));

        Bid bid1 = bidService.save(new Bid(100, job1, user));
    }
}
