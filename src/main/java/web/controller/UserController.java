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

        User user = new User();
        user.setName(name.trim());
        user.setEmail(email != null ? email.trim() : null);
        user.setAge(age);
        userService.save(user);

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

        User existingUser = userService.findById(id);
        if (existingUser != null) {
            if (name != null && !name.trim().isEmpty()) {
                existingUser.setName(name.trim());
            }
            if (email != null && !email.trim().isEmpty()) {
                existingUser.setEmail(email.trim());
            }
            if (age != null) {
                existingUser.setAge(age);
            }
            userService.update(existingUser);
        }

        return "redirect:/users/view?id=" + existingUser.getId();

    }

    @PostMapping("/user/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}

