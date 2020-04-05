package de.damianbast;

import java.util.function.Function;

public class PricingRule {


    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    private int price = 0;
    private int amount = 0;


    PricingRule() throws RuleException {
    throw new RuleException("You need to specify the price of the item");
    }

    /**
     * Standard pricing rule
     * @param price
     */
    PricingRule(int price){
        this.price = price;
    }

    public void add(){
        amount++;
    }

    public int total(){
        return price * amount;
    }


}
