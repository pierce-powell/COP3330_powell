import static java.lang.Math.PI;

public class Sphere extends Shape3D {

    private String name;

    public Sphere(double firstDimension) {
        super(firstDimension);
        this.name = "sphere";
    }

    @Override
    public double getArea() {
        return (4*PI*(firstDimension * firstDimension));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getVolume() {
        return ((4*PI*(firstDimension * firstDimension * firstDimension))/3);
    }


}
