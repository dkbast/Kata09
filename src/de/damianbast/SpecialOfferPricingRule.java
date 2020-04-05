package de.damianbast;

public class SpecialOfferPricingRule extends PricingRule {


    public int getBundlePrice() {
        return bundlePrice;
    }

    public int getBundleSize() {
        return bundleSize;
    }

    private final int bundlePrice;
    private final int bundleSize;

    SpecialOfferPricingRule() throws RuleException {
        throw new RuleException("You need to specify the price of the item");
    }

    /**
     * Special offer bundle pricing rule
     * @param price standard price for non discounted items
     * @param bundleSize how many items are in the special offer bundle
     * @param bundlePrice for the complete bundle
     */
    SpecialOfferPricingRule(int price, int bundleSize, int bundlePrice){
        super(price);
        this.bundleSize =bundleSize;
        this.bundlePrice =bundlePrice;
    }

    @Override
    public int total(){
        int result = 0;
        int numberOfBundles = getAmount() / getBundleSize();
        int singleItems= getAmount() % getBundleSize();
        result += numberOfBundles * getBundlePrice();
        result += singleItems * getPrice();
        return result;
    }

}
