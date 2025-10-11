package geometry3d;
import geometry2d.Figure;
import exceptions.InvalidDimensionException;
import exceptions.NullFigureException;

public class Cylinder {
    public Figure base; // Основание
    double height;

    public Cylinder(Figure base, double height) {
        if (base == null) {
            throw new NullFigureException("Base figure cannot be null");
        }
        if (height <= 0) {
            throw new InvalidDimensionException("Height must be positive: " + height);
        }
        this.base = base;
        this.height = height;
    }

    public double volume() {
        return base.area() * height;
    }

    public String toString() {
        return String.format("Cylinder{base=%s, height=%.2f, volume=%.2f}", base, height, volume());
    }
}
