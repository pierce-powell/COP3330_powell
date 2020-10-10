import static java.lang.Math.PI;

public class Circle extends Shape2D {

    private String name;
    public Circle(double firstDimesnion) {
        super(firstDimesnion);
        this.name = "circle";
    }

    @Override
    public double getArea(){
    return (PI * (firstDimension * firstDimension));
    }

    @Override
    public String getName(){
        return name;
    }
}
