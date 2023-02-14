package util;

public final class Funcs {

    private Funcs() {}

    // sin(x+1)
    public static double ft(double x) {
        return Math.sin(x + 1);
    }

    // x^3 - 3.5x^2 + 0.5x + 5
    public static double f1(double x) {
        return x * x * x - 3.5 * x * x + 0.5 * x + 5;
    }

    // 5(0.5x-lnx)^2-x+3
    public static double f2(double x) {
        double v = 0.5 * x - Math.log(x);
        return 5 * v * v - x + 3;
    }

    // (x-1/x)(2x-x^2)
    public static double f3(double x) {
        return (x - 1 / x) * (2 * x - x * x);
    }

    // x^3+x^2-3x
    public static double f4(double x) {
        return x * x * x + x * x - 3 * x;
    }

    // xe^x-(1+e^x)cosx
    public static double f5(double x) {
        return x * Math.exp(x) - (1 + Math.exp(x)) * Math.cos(x);
    }

    // 2x^4+x^3-2x^2-sin(x^2)
    public static double f6(double x) {
        return 2 * x * x * x * x + x * x * x - 2 * x * x - Math.sin(x * x);
    }

    // 0.5*sin - log(cosx)
    public static double f7(double x) {
        return 0.5 * Math.sin(x) - Math.log(Math.cos(x));
    }

    // x^2+ln(x+5)-1
    public static double f8(double x) {
        return x * x + Math.log(x + 5) - 1;
    }

    // 1/(2+cos(x^(5/6)+1/2)) - 1
    public static double f9(double x) {
        return 1 / (2 + Math.cos(Math.pow(x, 5.0 / 6.0) + 0.5)) - 1;
    }

    // x^e-x-1/2
    public static double f10(double x) {
        return Math.pow(x, Math.E) - x - 0.5;
    }

    // x+cosx/ln(x)
    public static double f11(double x) {
        return x + Math.cos(x) / Math.log(x);
    }
}
