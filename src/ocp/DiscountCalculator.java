package ocp;

/**
 * Подсказка:
 * Каждый раз, когда появляется новый тип пользователя (например, student или employee),
 * придётся лезть в код метода и добавлять новое условие if.
 * Вспомните задание из предыдущих лабораторных с фигурами.
 */
public class DiscountCalculator {
    public double calculateDiscount(String userType, double price) {
        return switch (userType) {
            case "regular" -> price * 0.05;
            case "vip" -> price * 0.1;
            case "super_vip" -> price * 0.2;
            default -> 0.0;
        };
    }
}
