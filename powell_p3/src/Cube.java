public class Cube extends Shape3D {

    private String name;

    public Cube(double firstDimension) {
        super(firstDimension);
        this.name = "cube";
    }

    @Override
    public double getArea(){
        return 6 * (firstDimension * firstDimension);
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public double getVolume() {
        return (firstDimension * firstDimension * firstDimension);
    }
}
