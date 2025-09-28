package geometry2d;
import exceptions.InvalidDimensionException;

public class Circle implements Figure {
    double radius;

    public Circle(double radius){
        if (radius <= 0) {
            throw new InvalidDimensionException("Radius must be positive");
        }
        this.radius = radius;
    }

    public double area(){
        return Math.PI * (radius* radius);
    }

    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    public String toString() {
        return String.format("Circle{radius=%.2f}", radius);
    }
}
