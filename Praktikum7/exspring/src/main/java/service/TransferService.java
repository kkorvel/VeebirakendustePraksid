package service;

import lombok.Getter;
import lombok.Setter;
import model.Money;

import javax.annotation.Resource;

@Getter
@Setter
public class TransferService {

    @Resource
    private BankService bankService;

    public void transfer(Money money, String fromAccount, String toAccount) {
        bankService.withdraw(money, fromAccount);

        bankService.deposit(money, toAccount);
    }
}