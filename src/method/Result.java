package method;

public class Result {
    private final double x;
    private final double fx;
    private final int count;

    public Result(double x, double fx, int count) {
        this.x = x;
        this.fx = fx;
        this.count = count;
    }

    public double getFx() {
        return fx;
    }

    @Override
    public String toString() {
        return String.format("Найден приближенный экстремум: x = %.3f, f(x) = %.3f за %d итераций",
                x, fx, count);
    }
}
