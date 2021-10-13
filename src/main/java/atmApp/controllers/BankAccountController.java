package atmApp.controllers;

import atmApp.domain.User;
import atmApp.repositories.UserRepository;
import atmApp.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BankAccountController {

    @Autowired
    BankAccountService service;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/account")
    public String accountView() {
        return "account";
    }

    @GetMapping("/")
    public String homeView() {
        return "redirect:/account";
    }

    @GetMapping("/account/transfer")
    public String transferMoney(@RequestParam("id") Long id, Model model) {

        List<User> transferableUsers = userRepository.findAll().stream().filter(u -> u.getAccount().isTransferable()).collect(Collectors.toList());
        User user = userRepository.findById(id).get();
        if(transferableUsers.contains(user))
            transferableUsers.remove(user);

        userRepository.findById(id).ifPresent(o -> model.addAttribute("user", o));
        model.addAttribute("usersList", transferableUsers);

        return "transfer";
    }

    @GetMapping("/account/deposit")
    public String depositForm(@RequestParam("id") Long id, Model model) {

        userRepository.findById(id).ifPresent(o -> model.addAttribute("user", o));

        return "deposit";
    }

    @PostMapping("/account/addMoney")
    public String AddMoneyToBankAccount(@RequestParam(name = "money") BigDecimal money, User user, BindingResult result) {

        service.addMoneyToBankAccount(money, user.getAccount());

        return "redirect:/account";
    }

    @PostMapping("/account/subtractMoney")
    public String subtractMoneyFromBankAccount(@RequestParam(name = "money") BigDecimal money, User user, BindingResult result) {

        service.subtractMoneyFromBankAccount(money, user.getAccount());

        return "redirect:/account";
    }

    @PostMapping("/account/transferMoney")
    public String transferMoneyToAnotherAccount(@RequestParam(name = "userToTransferTo") String userToTransferTo, @RequestParam(name = "money") BigDecimal money, User user, BindingResult result) {

        User transferUser = userRepository.findByUsername(userToTransferTo);
        if(transferUser.getAccount().isTransferable()) {
            service.addMoneyToBankAccount(money, transferUser.getAccount());
            service.subtractMoneyFromBankAccount(money, user.getAccount());
        }
        return "redirect:/account";
    }

    @GetMapping("/account/withdraw")
    public String withdrawMoney(@RequestParam("id") Long id, Model model) {
        userRepository.findById(id).ifPresent(o -> model.addAttribute("user", o));

        return "withdraw";
    }

    @GetMapping("/account/balance")
    public String showBalance(@RequestParam("id") Long id, Model model) {

        userRepository.findById(id).ifPresent(o -> model.addAttribute("user", o));

        return "balance";
    }

    @GetMapping("/account/prepaid")
    public String doPrepaid(@RequestParam("id") Long id, Model model) {

        userRepository.findById(id).ifPresent(o -> model.addAttribute("user", o));

        return "prepaid";
    }


}
