public class BodyMassIndex {

    //Will need setters for these too
    private double height;
    private double weight;
    private double BMIScore;
    private int BMICateg;

    public BodyMassIndex (double height, double weight){
        this.height = height;
        this.weight = weight;
        BMIScore = calcBMIScoreToOneDecimal(height, weight);
        BMICateg = calcBMICategory(BMIScore);
    }

    public double calcBMIScoreToOneDecimal(double height, double weight){
        double answer = -1;
        answer = 703 * weight / (height * height);
        answer = (double) Math.round(answer * 10) / 10;
        return answer;
    }

    //Calculate BMI score
    public int calcBMICategory(double BMIScore){
        if (BMIScore < 18.5)
            return 1; //UnderWeight ID
        else if (BMIScore >= 18.5 && BMIScore < 25)
            return 2; //NormalWeight ID
        else if (BMIScore >= 25 && BMIScore < 30)
            return 3; //Overweight ID
        else if (BMIScore >= 30)
            return 4; //Obese ID
        else return -1; //Error ID
    }

    public double getBMIScore(){
        return BMIScore;
    }

    public int getBMICateg() {
        return BMICateg;
    }
}
