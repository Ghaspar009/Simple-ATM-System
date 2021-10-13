package atmApp.controllers;

import atmApp.domain.User;
import atmApp.repositories.UserRepository;
import atmApp.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    BankAccountService service;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);

        return "login";
    }
}
