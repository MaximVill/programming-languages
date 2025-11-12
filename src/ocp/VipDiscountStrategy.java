package ocp;

public class VipDiscountStrategy implements  DiscountStrategy {
    @Override
    public double applyDiscount(double price) {
        return price * 0.1;
    }
}
