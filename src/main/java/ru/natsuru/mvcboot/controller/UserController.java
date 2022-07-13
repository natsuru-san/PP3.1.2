package ru.natsuru.mvcboot.controller;

import ru.natsuru.mvcboot.model.Role;
import ru.natsuru.mvcboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.natsuru.mvcboot.service.RoleService;
import ru.natsuru.mvcboot.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public String getAllUsers(Model model) {
        model.addAttribute("listUsers", userService.getListUsers());
        return "/pages/users.html";
    }

    @GetMapping("/new")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "/pages/new.html";
    }
    @PostMapping("/create")
    @Transactional
    public String creatingNewUser(@ModelAttribute User user, @RequestParam("role") int role) {
        Set<Role> updatedRoles = new HashSet<>();
        updatedRoles.add(roleService.getAllRoles().get(role - 1));
        user.setRoles(updatedRoles);
        userService.addUser(user);
        return "redirect:/";
    }

    //Цей метод контролера треба використовувати через кнопку в шаблоні html, інакше помилка #405
    @DeleteMapping("/delete/{id}")
    @Transactional
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    @Transactional
    public String updateUser(@PathVariable long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "/pages/single_user.html";
    }

    @PatchMapping("/change")
    @Transactional
    public String changeUser(@ModelAttribute User user, @RequestParam("role") int role) {
        Set<Role> updatedRoles = new HashSet<>();
        updatedRoles.add(roleService.getAllRoles().get(role - 1));
        user.setRoles(updatedRoles);
        userService.changeUser(user);
        return "redirect:/";
    }
}
