package de.damianbast;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


public class Main {

    /**
     * In production, this is where the rules would be fetched the configuration
     * @return a set of pricing rules
     */
    public static Map<String, PricingRule> getRules(){
        Map<String, PricingRule> result = new HashMap<>();
        result.put("A", new SpecialOfferPricingRule(50,3,130));
        result.put("B", new SpecialOfferPricingRule(30,2,45));
        result.put("C", new PricingRule(20));
        result.put("D", new PricingRule(15));
        return result;
    };

    public void main(String[] args) {
        test_totals();
        test_incremental();
    }

    public int price(String goods){
        CheckOut co = new CheckOut(getRules());
        goods.chars().forEach(item -> {
            try {
                co.scan(String.valueOf((char) item));
            } catch (ScanException e) {
                e.printStackTrace();
            }
        });
        return co.total();
    }

    @Test
    public void test_totals() {
        assertEquals(0, price(""));
        assertEquals(50, price("A"));
        assertEquals(80, price("AB"));
        assertEquals(115, price("CDBA"));

        assertEquals(100, price("AA"));
        assertEquals(130, price("AAA"));
        assertEquals(180, price("AAAA"));
        assertEquals(230, price("AAAAA"));
        assertEquals(260, price("AAAAAA"));

        assertEquals(160, price("AAAB"));
        assertEquals(175, price("AAABB"));
        assertEquals(190, price("AAABBD"));
        assertEquals(190, price("DABABA"));
    }

    @Test
    public void test_incremental() {
        CheckOut co = new CheckOut(getRules());
        assertEquals(0, co.total());
        try {
            co.scan("A");
        } catch (ScanException e) {
            e.printStackTrace();
        }
        assertEquals(50, co.total());
        try {
            co.scan("B");
        } catch (ScanException e) {
            e.printStackTrace();
        }
        assertEquals(80, co.total());
        try {
            co.scan("A");
        } catch (ScanException e) {
            e.printStackTrace();
        }
        assertEquals(130, co.total());
        try {
            co.scan("A");
        } catch (ScanException e) {
            e.printStackTrace();
        }
        assertEquals(160, co.total());
        try {
            co.scan("B");
        } catch (ScanException e) {
            e.printStackTrace();
        }
        assertEquals(175, co.total());
    }
}
