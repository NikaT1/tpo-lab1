package tpo.task_1;

public class Arcsin_calc {
    public static double calc_arcsin(double x, int count) {
        if (x < -1 || x > 1) {
            return Double.NaN;
        }
        double arcsin_result = x;
        double numerator = 1;
        double denominator = 1;
        for (int i = 0; i < count; i++) {
            x *= x * x;
            numerator *= (2 * i + 1) * (2 * i + 2);
            denominator *= 4 * (i + 1) * (i + 1);
            if (numerator == Double.POSITIVE_INFINITY || denominator == Double.POSITIVE_INFINITY || x == Double.POSITIVE_INFINITY) {
                return Double.POSITIVE_INFINITY;
            }
            arcsin_result += numerator / (denominator * (2 * i + 3)) * x;
        }

        return arcsin_result;
    }
}
