package ocp;

public class SuperVipDiscountStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(double price) {
        return price * 0.2;
    }
}
