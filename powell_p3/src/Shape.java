public abstract class Shape {
    protected double firstDimension;

    public Shape(double firstDimension) {
        this.firstDimension = firstDimension;
    }

    public abstract double getArea();

    public abstract String getName();
}
