package ie.stephen;

import ie.stephen.model.Bid;
import ie.stephen.model.Job;
import ie.stephen.model.RegisteredUser;
import ie.stephen.model.Role;
import ie.stephen.services.BidService;
import ie.stephen.services.JobService;
import ie.stephen.services.RegisteredUserService;
import ie.stephen.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    RegisteredUserService registeredUserService;
    @Autowired
    JobService jobService;
    @Autowired
    BidService bidService;
    @Autowired
    RoleService roleService;
    @Autowired
    PasswordEncoder encoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        RegisteredUser user = registeredUserService.save(new RegisteredUser("admin@admin.com","admin",
                "000-0000000", encoder.encode("admin"), true));

        roleService.save(new Role(user, "Registered"));

        Job job = jobService.save(new Job("jname","jdesc", user));
        Job job1 = jobService.save(new Job("jname1","jdesc1", user));
        Job job2 = jobService.save(new Job("jname2","jdesc2", user));

        Bid bid1 = bidService.save(new Bid(100, job1, user));
    }
}
