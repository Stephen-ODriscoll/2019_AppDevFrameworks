package ie.stephen.controllers;

import ie.stephen.forms.RegisterForm;
import ie.stephen.model.RegisteredUser;
import ie.stephen.model.Role;
import ie.stephen.services.RegisteredUserService;
import ie.stephen.services.RoleService;
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

@Slf4j
@Controller
public class RegisteredUserController {

    @Autowired
    RegisteredUserService registeredUserService;

    @Autowired
    RoleService roleService;

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

        if (registeredUserService.existsByEmail(registerForm.getEmail())) {
            redirectAttributes.addFlashAttribute("error", true);
            return "redirect:register";
        }

        RegisteredUser user = registeredUserService.save(new RegisteredUser(registerForm.getEmail(), registerForm.getName(),
                registerForm.getPhoneNo(), encoder.encode(registerForm.getPassword()),true));

        roleService.save(new Role(user, "ROLE_REGISTERED"));
        return "redirect:login";
    }

    @GetMapping(value= {"/login"})
    public String login() { return "login"; }

    @GetMapping(value="/logout")
    public String register()
    {
        return "index";
    }
}
