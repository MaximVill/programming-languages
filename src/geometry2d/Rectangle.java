package geometry2d;
import exceptions.InvalidDimensionException;

public class Rectangle {
    double width;
    double height;

    public Rectangle(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new InvalidDimensionException("Width and height must be positive");
        }
        this.width = width;
        this.height = height;
    }

    public double area(){
        return width * height;
    }

    public double perimeter() {
        return 2 * (width * height);
    }

    public String toString(){
        return String.format("Rectangle width and height = ", width, height);
    }
}
