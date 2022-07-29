package ru.natsuru.mvcboot.controller;

import ru.natsuru.mvcboot.model.Role;
import ru.natsuru.mvcboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.natsuru.mvcboot.service.RoleService;
import ru.natsuru.mvcboot.service.UserService;
import java.util.HashSet;
import java.util.Set;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/admin/list")
    public String getAllUsers(Model model) {
        model.addAttribute("listUsers", userService.getListUsers());
        return "/pages/users.html";
    }
    @GetMapping("/admin")
    public String redirectToAdminListOfUsers() {
        return "redirect:/admin/list";
    }

    @GetMapping("/admin/new")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "/pages/new.html";
    }
    @PostMapping("/admin/create")
    public String creatingNewUser(@ModelAttribute User user, @RequestParam("role") int role) {
        Set<Role> updatedRoles = new HashSet<>();
        updatedRoles.add(roleService.getAllRoles().get(role - 1));
        user.setRoles(updatedRoles);
        userService.addUser(user);
        return "redirect:/admin";
    }

    //Цей метод контролера треба використовувати через кнопку в шаблоні html, інакше помилка #405
    @DeleteMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/update/{id}")
    public String updateUser(@PathVariable long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "/pages/single_user.html";
    }

    @PatchMapping("/admin/change")
    public String changeUser(@ModelAttribute User user, @RequestParam("role") int role) {
        Set<Role> updatedRoles = new HashSet<>();
        updatedRoles.add(roleService.getAllRoles().get(role - 1));
        user.setRoles(updatedRoles);
        userService.changeUser(user);
        return "redirect:/admin";
    }
}
