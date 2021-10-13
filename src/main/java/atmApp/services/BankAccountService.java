package atmApp.services;

import atmApp.domain.BankAccount;
import atmApp.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

@Service
public class BankAccountService {

    @Autowired
    BankAccountRepository repository;

    public ArrayList<BankAccount> getAllBankAccounts() {
        return new ArrayList<>(repository.findAll());
    }

    public void addMoneyToBankAccount(BigDecimal money, BankAccount bankAccount) {

        System.out.println(money);
        BigDecimal balance = bankAccount.getBalance();
        BigDecimal total = money.add(balance).setScale(2, RoundingMode.HALF_EVEN);
        bankAccount.setBalance(total);
        repository.save(bankAccount);
    }

    public void subtractMoneyFromBankAccount(BigDecimal money, BankAccount account) {

        BigDecimal balance = account.getBalance();
        BigDecimal total = balance.subtract(money).setScale(2, RoundingMode.HALF_EVEN);
        System.out.println(total);
        if(total.compareTo(BigDecimal.ZERO) >= 0) {
            account.setBalance(total);
            repository.save(account);
        }
    }
}
