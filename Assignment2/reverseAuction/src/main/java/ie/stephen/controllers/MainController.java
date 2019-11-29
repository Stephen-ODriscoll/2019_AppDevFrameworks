package ie.stephen.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Slf4j
@Controller
public class MainController {

    @GetMapping(value= {"/", "/index"})
    public String index()
    {
        return "index";
    }

    @GetMapping(value= {"/error"})
    public String error() { return "error"; }
}
