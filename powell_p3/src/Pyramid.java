import static java.lang.Math.sqrt;

public class Pyramid extends Shape3D{

    private String name;

    public Pyramid(double firstDimension, double secondDimension,
                   double thirdDimension){
        super(firstDimension, secondDimension, thirdDimension);

        this.name = "pyramid";

    }

    @Override
    public double getArea() {
        double temp = -1;

        temp = firstDimension * secondDimension;
        temp += firstDimension * sqrt(((secondDimension / 2)*(secondDimension / 2)) + thirdDimension * thirdDimension);
        temp += secondDimension * sqrt(((firstDimension/2)*(firstDimension/2)) + thirdDimension * thirdDimension);

        return temp;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getVolume() {
        return ((firstDimension * secondDimension * thirdDimension) / 3);
    }

}
