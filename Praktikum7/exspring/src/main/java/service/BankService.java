package service;

import model.Money;

public interface BankService {

    void deposit(Money money, String toAccount);

    void withdraw(Money money, String fromAccount);


}