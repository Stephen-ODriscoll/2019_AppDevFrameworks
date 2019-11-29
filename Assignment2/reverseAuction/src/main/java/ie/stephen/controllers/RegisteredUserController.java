package ie.stephen.controllers;

import ie.stephen.forms.LoginForm;
import ie.stephen.forms.RegisterForm;
import ie.stephen.model.RegisteredUser;
import ie.stephen.model.Role;
import ie.stephen.services.RegisteredUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class RegisteredUserController {

    @Autowired
    RegisteredUserService registeredUserService;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping(value= {"/register"})
    public String register(Model model)
    {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid RegisterForm registerForm, BindingResult binding, RedirectAttributes redirectAttributes)
    {
        if (binding.hasErrors())
            return "register";


        Role role = new Role(registerForm.getEmail(), "Authorized");
        RegisteredUser user = new RegisteredUser(registerForm.getName(), registerForm.getEmail(),
                registerForm.getPhoneNo(), encoder.encode(registerForm.getPassword()),true,  role);

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
    public String login() { return "login"; }

    @GetMapping(value="/logout")
    public String register()
    {
        return "index";
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
