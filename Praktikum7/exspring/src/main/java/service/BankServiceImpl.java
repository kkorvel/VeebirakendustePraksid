package service;

import model.Money;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService {

    @Override
    public void deposit(Money money, String toAccount) {
        System.out.println("depositing ...");
    }

    @Override
    public void withdraw(Money money, String fromAccount) {
        System.out.println("withdrawing ...");
    }


}
