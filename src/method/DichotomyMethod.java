package method;

import util.Funcs;

import java.util.Scanner;
import java.util.function.DoubleUnaryOperator;

public class DichotomyMethod {
    private double a;
    private double b;
    private final double eps;
    private final boolean isMin;
    private int count;

    public DichotomyMethod(double a, double b, double eps, boolean isMin) {
        this.a = a;
        this.b = b;
        this.eps = eps;
        this.isMin = isMin;
        this.count = 0;
    }

    public Result run(DoubleUnaryOperator f) {
        int sgn = isMin ? 1 : -1;
        double x;
        while (b - a >= eps) {
            x = (a + b) / 2;
            double leftResult = f.applyAsDouble(x - eps);
            double rightResult = f.applyAsDouble(x + eps);
            if (sgn * leftResult < sgn * rightResult) {
                b = x;
            } else {
                a = x;
            }
            count++;
        }
        x = (a + b) / 2;
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

        System.out.println("Метод дихотомии");
        Result result = new DichotomyMethod(a, b, eps, isMin).run(Funcs::f1);
        System.out.println(result.toString());
    }
}
