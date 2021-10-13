package atmApp.domain;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity(name = "bankAccount")
@Table(name = "BANK_ACCOUNT")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, scale = 2)
    private BigDecimal balance = BigDecimal.valueOf(0);

    @OneToOne
    private User user;

    private boolean transferable = false;

    public BankAccount(User user, BigDecimal balance) {

        this.user = user;
        this.balance = balance;
    }

    protected BankAccount() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isTransferable() {
        return transferable;
    }

    public void setTransferable(boolean transferable) {
        this.transferable = transferable;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return this.user.toString();
    }

}
