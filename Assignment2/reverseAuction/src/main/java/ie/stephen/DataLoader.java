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

        RegisteredUser user1 = registeredUserService.save(new RegisteredUser("test@gmail.com","Tester",
                "123-1234567", encoder.encode("test"), true));

        RegisteredUser user2 = registeredUserService.save(new RegisteredUser("disabled@gmail.com","Disabled",
                "010-1010101", encoder.encode("disabled"), false));

        roleService.save(new Role(user, "ROLE_REGISTERED"));
        roleService.save(new Role(user1, "ROLE_REGISTERED"));

        Job job = jobService.save(
                new Job("Install new toilet","My toilet is after exploding bois. Gonna need a plum-mer to install a new one", user));
        Job job1 = jobService.save(
                new Job("TV on the wall","I bought one of those new tv yokes. Can someone help me glue it to my wall?", user));
        Job job2 = jobService.save(
                new Job("Help Me","I could use some help. (No, I won't be more specific)", user1));

        Job job3 = new Job("Paint my shack","I need a good, cheap, bald painter to paint my shack blue. Is there a painter here?", user);
        job3.setOpen(false);
        jobService.save(job3);

        Bid bid1 = bidService.save(new Bid(100, job1, user1));
        Bid bid2 = bidService.save(new Bid(50, job2, user));
    }
}
