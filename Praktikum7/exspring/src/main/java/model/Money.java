package model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Money {

    private int amount;
    private String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

}
