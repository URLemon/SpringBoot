package boot.controller;

import boot.model.Role;
import boot.model.User;
import boot.service.RoleService;
import boot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "")
    public String adminPage(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping(value="/add")
    public String addUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "user-info";
    }

    @PostMapping(value="/saveUser")
    public String saveUser(@ModelAttribute("user") User user){
        if(user.getId() == null) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getRoleByName("ROLE_USER"));
            user.setRoles(roles);
            userService.saveUser(user);
        } else {
            userService.updateUser(user);
        }
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String deleteUSer(@RequestParam("id") int id){
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam("id") int id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-info";
    }
}
