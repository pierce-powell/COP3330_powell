import java.lang.annotation.Documented;

public abstract class Shape3D extends Shape2D{

    protected double thirdDimension;

    public Shape3D(double firstDimension, double secondDimension,
                   double thirdDimension) {
        super(firstDimension, secondDimension);
        this.thirdDimension = thirdDimension;
    }

    public Shape3D(double firstDimension, double secondDimension){
        super(firstDimension, secondDimension);
    }

    public Shape3D(double firstDimension){
        super(firstDimension);
    }

    @Override
    public abstract double getArea();

    @Override
    public abstract String getName();

    public abstract double getVolume();

}
