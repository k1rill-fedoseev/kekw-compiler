package lexems;

public class RealLiteral implements IElement {
    public final double v;

    public RealLiteral(double v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return String.valueOf(v);
    }
}
