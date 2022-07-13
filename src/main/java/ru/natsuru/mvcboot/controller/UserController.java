package ru.natsuru.mvcboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.natsuru.mvcboot.service.UserService;
import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String getRootPage() {
        return "/pages/index";
    }
    @GetMapping("/user")
    public String getOwnPage(Principal principal, Model model) {
        model.addAttribute("owner", userService.getUserObjectByLogin(principal.getName()));
        return "/pages/user";
    }
}
