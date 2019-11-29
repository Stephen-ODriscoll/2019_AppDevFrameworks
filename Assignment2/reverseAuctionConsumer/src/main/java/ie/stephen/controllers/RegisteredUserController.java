package ie.stephen.controllers;

import ie.stephen.model.RegisteredUser;
import ie.stephen.services.RegisteredUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class RegisteredUserController {

    @Autowired
    RegisteredUserService registeredUserService;

    @GetMapping(value= {"/register"})
    public String register(Model model)
    {
        model.addAttribute("registeredUser", new RegisteredUser());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid RegisteredUser user, BindingResult binding, RedirectAttributes redirectAttributes)
    {
        if (binding.hasErrors())
            return "register";

        user = registeredUserService.save(user);

        if (user != null )
            return "redirect:login";
        else {
            // starts again with empty form (new object)
            redirectAttributes.addFlashAttribute("duplicate", true);
            return "redirect:register";
        }
    }

    @GetMapping(value= {"/login"})
    public String login()
    {
        return "login";
    }

    @GetMapping("/users")
    public String users(Model model){
        List<RegisteredUser> registeredUsers = registeredUserService.getAllRegisteredUsers();

        if (registeredUsers.size() == 0) {
            return "notfounderror";
        }
        model.addAttribute("registeredUsers", registeredUsers);
        return "users";
    }
}
