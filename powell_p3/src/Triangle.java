public class Triangle extends Shape2D {

    private String name;

    public Triangle(double firstDimension, double secondDimension) {
        super(firstDimension, secondDimension);
        this.name = "triangle";
    }

    @Override
    public double getArea(){
        return (firstDimension * secondDimension) / 2;
    }

    @Override
    public String getName(){
        return name;
    }
}
