package method;

import util.Funcs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.DoubleUnaryOperator;

public class PiecewiseLinearApproximationMethod {
    private final double a;
    private final double b;
    private final double eps;
    private final boolean isMin;
    private final int m;
    private final Map<Double, Double> values;

    public PiecewiseLinearApproximationMethod(double a, double b, double eps, boolean isMin, int m) {
        this.a = a;
        this.b = b;
        this.eps = eps;
        this.isMin = isMin;
        this.m = m;
        this.values = new HashMap<>();
    }

    public Result run(DoubleUnaryOperator f) {
        int n = 5;
        List<Interval> suspectIntervals = new ArrayList<>();

        int maxSuspectIntervalCount = Integer.MIN_VALUE;
        int stopCount = 0;
        while (stopCount != m) {
            double[] points = updatePointsAndCacheValues(n, f);
            suspectIntervals = findSuspectIntervals(points);
            System.out.println("n = " + n + ", suspectIntervals = " + suspectIntervals);
            int suspectIntervalCount = suspectIntervals.size();

            if (suspectIntervalCount == maxSuspectIntervalCount) {
                stopCount++;
            } else {
                stopCount = 0;
            }
            maxSuspectIntervalCount = Math.max(maxSuspectIntervalCount, suspectIntervalCount);
            n = 2 * n - 1;
        }

        return suspectIntervals.stream()
                .map(interval -> new GoldenSectionMethod(interval.a, interval.b, eps, isMin).run(f))
                .min(Comparator.comparingDouble(Result::getFx))
                .orElse(null);
    }

    private List<Interval> findSuspectIntervals(double[] points) {
        Map<Double, Interval> rightBoundToInterval = new HashMap<>();

        for (int i = 0; i < points.length - 1; i++) {
            double x1 = points[i];
            double x2 = points[i + 1];
            double y1 = values.get(x1);
            double y2 = values.get(x2);

            if (y1 <= y2) {
                if (rightBoundToInterval.containsKey(x1)) {
                    Interval connectedInterval = rightBoundToInterval.get(x1);
                    connectedInterval.b = x2;
                    rightBoundToInterval.remove(x1);
                    rightBoundToInterval.put(x2, connectedInterval);
                } else {
                    Interval interval = new Interval(x1, x2);
                    rightBoundToInterval.put(x2, interval);
                }
            }
        }
        return new ArrayList<>(rightBoundToInterval.values());
    }

    private double[] updatePointsAndCacheValues(int n, DoubleUnaryOperator f) {
        double[] result = new double[n];
        double h = (b - a) / (n - 1);
        for (int i = 0; i < n; i++) {
            double x = a + i * h;
            result[i] = x;
            values.computeIfAbsent(x, f::applyAsDouble);
        }
        return result;
    }

    private static class Interval {
        double a;
        double b;

        public Interval(double a, double b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "[" + a + ", " + b + "]";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Левая граница: ");
        double a = sc.nextDouble();
        System.out.print("Правая граница: ");
        double b = sc.nextDouble();
        System.out.print("Параметр эпсилон: ");
        double eps = sc.nextDouble();
        System.out.println("Параметр m: ");
        int m = sc.nextInt();

        System.out.println("Метод кусочно-линейной аппроксимации (с применением метода золотого сечения) - поиск минимума");
        Result result = new PiecewiseLinearApproximationMethod(a, b, eps, true, m).run(Funcs::f6);
        System.out.println(result.toString());
    }
}
