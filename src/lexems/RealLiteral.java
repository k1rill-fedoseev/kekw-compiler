package lexems;

public class RealLiteral implements IElement {
    private final double v;

    public RealLiteral(double v) {
        this.v = v;
    }

    public Object evaluate() {
        return v;
    }

    @Override
    public String toString() {
        return "RealLiteral{" +
                "v=" + v +
                '}';
    }
}
