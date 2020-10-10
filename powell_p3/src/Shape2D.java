public abstract class Shape2D extends Shape {

    protected double secondDimension;

    public Shape2D(double firstDimension, double secondDimension) {
        super(firstDimension);
        this.secondDimension = secondDimension;
    }

    public Shape2D(double firstDimension) {
        super(firstDimension);
    }

    @Override
    public abstract double getArea();

    @Override
    public abstract String getName();
}
