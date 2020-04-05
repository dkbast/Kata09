package de.damianbast;

import org.junit.platform.commons.util.StringUtils;

import java.util.Map;
import java.util.stream.Collectors;

public class CheckOut implements iCheckOut {
    Map<String, PricingRule> pricingRules;

    public CheckOut(Map<String, PricingRule> rules) {
        this.pricingRules = rules;
    }

    @Override
    public void scan(String item) throws ScanException {
        if(StringUtils.isNotBlank(item) && pricingRules.containsKey(item)) {
            pricingRules.get(item).add();
        } else {
            throw new ScanException("SKU not valid, please scan again");
        }
    }

    @Override
    public int total() {
        return pricingRules.values().stream().collect(Collectors.summingInt(PricingRule::total));
    }
}
