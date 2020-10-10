import java.sql.SQLOutput;

public class Square extends Shape2D {
    private String name;

    public Square(double firstDimension){
        super(firstDimension);
        this.name = "square";
    }

    @Override
    public double getArea() {
        return firstDimension * firstDimension;
    }

    @Override
    public String getName() {
        return name;
    }
}
