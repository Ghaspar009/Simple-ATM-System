package atmApp;

import atmApp.domain.BankAccount;
import atmApp.domain.User;
import atmApp.repositories.BankAccountRepository;
import atmApp.repositories.UserRepository;
import atmApp.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
@Scope("singleton")
public class Starter implements CommandLineRunner {

    @Autowired
    BankAccountRepository bankRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BankAccountService service;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        User user = new User("Kacper", passwordEncoder.encode("1234"), "USER");
        userRepository.save(user);
        BankAccount account = new BankAccount(user, BigDecimal.valueOf(100.00));
        user.setAccount(account);
        bankRepository.save(account);
        User user2 = new User("Stefan", passwordEncoder.encode("4321"), "USER");
        userRepository.save(user2);
        BankAccount account2 = new BankAccount(user, BigDecimal.valueOf(250.01));
        account2.setTransferable(true);
        user2.setAccount(account2);
        bankRepository.save(account2);
    }
}
