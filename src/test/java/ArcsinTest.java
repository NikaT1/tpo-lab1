import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tpo.task_1.Arcsin_calc.calc_arcsin;

public class ArcsinTest {
    @ParameterizedTest(name = "{index} - arcsin({0})")
    @ValueSource(doubles = {-1, -0.93, -0.84, -0.65, -0.5, -0.34, 0, 0.28, 0.5, 0.67, 0.84, 0.9, 1})
    public void testArcsinCalcFunctionNormalValues(double number) {
        assertEquals(Math.asin(number), calc_arcsin(number, 85), 0.1);
    }

    @ParameterizedTest(name = "{index} - arcsin({0})")
    @ValueSource(doubles = {-5, 5})
    public void testArcsinCalcFunctionIncorrectValues(double number) {
        assertEquals(Double.NaN, calc_arcsin(number, 80), 0.1);
    }

    @ParameterizedTest(name = "{index} - arcsin({0})")
    @ValueSource(ints = {86, 90, 1000})
    public void testArcsinCalcFunctionStepMoreThan85(int count) {
        assertEquals(Double.POSITIVE_INFINITY, calc_arcsin(0.5, count), 0.1);
    }

    @ParameterizedTest(name = "{index} - arcsin({0}) = -arcsin(-{0})")
    @ValueSource(doubles = {0.34, 0.64})
    public void testArcsinCalcFunctionOddness(double number) {
        assertEquals(-calc_arcsin(number, 80), calc_arcsin(-number, 80), 0.0001);
    }

    @Test
    @DisplayName("testArcsinCalcFunctionOddnessRandomValues: arcsin(x) = -arcsin(-x)")
    public void testArcsinCalcFunctionOddnessRandomValues() {
        double number = Math.random() * 2 - 1;
        assertEquals(-calc_arcsin(number, 80), calc_arcsin(-number, 80), 0.0001);
    }
}

