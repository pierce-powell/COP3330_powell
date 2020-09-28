import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BodyMassIndexTest {

    @Test
    void testCalcBMIScoreToOneDecimal() {
        BodyMassIndex bmi = new BodyMassIndex(60, 120);
        assertEquals(bmi.getBMIScore(), 23.4);
    }
    @Test
    void test2CalcBMIScoreToOneDecimal() {
        BodyMassIndex bmi = new BodyMassIndex(120, 60);
        assertEquals(bmi.getBMIScore(), 2.9);
    }

    @Test
    void testCalcBMICategory1() {
        BodyMassIndex bmi = new BodyMassIndex(120, 60);
        assertEquals(bmi.getBMICateg(), 1);
    }

    @Test
    void testCalcBMICategory2() {
        BodyMassIndex bmi = new BodyMassIndex(42, 60);
        assertEquals(bmi.getBMICateg(), 2);
    }

    @Test
    void testCalcBMICategory3() {
        BodyMassIndex bmi = new BodyMassIndex(120, 600);
        assertEquals(bmi.getBMICateg(), 3);
    }

    @Test
    void testCalcBMICategory4() {
        BodyMassIndex bmi = new BodyMassIndex(12, 600);
        assertEquals(bmi.getBMICateg(), 4);
    }

}