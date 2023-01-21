package app.controller;

import app.model.User;
import app.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("users", usersService.getAll());
        return "users/all";
    }

    @GetMapping("/{id}")
    public String showUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersService.getById(id));
        return "users/user";
    }

    @PostMapping("/create")
    public String newUser(Model model) {
        model.addAttribute("person", new User());
        return "/users/create";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        usersService.save(user);
        return "redirect:/users";
    }
}
