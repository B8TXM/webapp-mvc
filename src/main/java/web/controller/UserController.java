package web.controller;



import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String showUsers(Model model, @RequestParam(required = false) Long editId) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users/list";
    }

    @GetMapping("/users/view")
    public String viewUser(@RequestParam Long id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        return "users/view";

    }

    @GetMapping("/user/create")
    public String showCreationForm(Model model) {
        model.addAttribute("user", new User());
        return "users/create";
    }

    @PostMapping("/user/create")
    public String addUser(
            @RequestParam String name,
            @RequestParam(required = false) String email,
            @RequestParam Integer age) {

        userService.save(name, email, age);

        return "redirect:/users";
    }

    @GetMapping("/user/edit")
    public String showEditingForm(@RequestParam Long id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        return "users/edit";
    }

    @PostMapping("/user/update")
    public String updateUser(
            @RequestParam Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Integer age) {

        userService.update(id, name, email, age);

        return "redirect:/users/view?id=" + id;

    }

    @PostMapping("/user/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}

