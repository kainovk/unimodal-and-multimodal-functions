package method;

import util.Funcs;

import java.util.Scanner;
import java.util.function.DoubleUnaryOperator;

public class GoldenSectionMethod {
    private double a;
    private double b;
    private final double eps;
    private final boolean isMin;
    private int count;

    public GoldenSectionMethod(double a, double b, double eps, boolean isMin) {
        this.a = a;
        this.b = b;
        this.eps = eps;
        this.isMin = isMin;
        this.count = 0;
    }

    public Result run(DoubleUnaryOperator f) {
        int sgn = isMin ? 1 : -1;
        double gr = (Math.sqrt(5) + 1) / 2;
        double c = b - (b - a) / gr;
        double d = a + (b - a) / gr;
        while (Math.abs(b - a) > eps) {
            double leftResult = f.applyAsDouble(c);
            double rightResult = f.applyAsDouble(d);
            if (sgn * leftResult < sgn * rightResult) {
                b = d;
            } else {
                a = c;
            }
            c = b - (b - a) / gr;
            d = a + (b - a) / gr;
            count++;
        }
        double x = (b + a) / 2;
        return new Result(x, f.applyAsDouble(x), count);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Левая граница: ");
        double a = sc.nextDouble();
        System.out.print("Правая граница: ");
        double b = sc.nextDouble();
        System.out.print("Параметр эпсилон: ");
        double eps = sc.nextDouble();
        System.out.println("Поиск минимума?");
        boolean isMin = sc.nextBoolean();

        System.out.println("Метод золотого сечения");
        Result result = new GoldenSectionMethod(a, b, eps, isMin).run(Funcs::f1);
        System.out.println(result.toString());
    }
}
