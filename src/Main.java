import geometry2d.*;
import geometry3d.Cylinder;

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(3.0);
        Rectangle rectangle = new Rectangle(2.5, 5.9);
        Cylinder cylinder1 = new Cylinder(circle, 4.2);
        Cylinder cylinder2 = new Cylinder(rectangle, 5.2);

        System.out.println(circle);
        System.out.println(rectangle);
        System.out.println(cylinder1);
        System.out.println(cylinder2);
    }
}