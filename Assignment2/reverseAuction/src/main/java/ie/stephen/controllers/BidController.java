package ie.stephen.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class BidController {

    @GetMapping(value= {"/bid/{jobId}"})
    public String bid(@PathVariable("jobId") int jobId)
    {
        return "bid";
    }
}
