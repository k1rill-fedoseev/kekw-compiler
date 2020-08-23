package lexems;

public class BoolLiteral implements IElement {
    private final boolean v;

    public BoolLiteral(boolean v) {
        this.v = v;
    }

    public Object evaluate() {
        return v;
    }

    @Override
    public String toString() {
        return "BoolLiteral{" +
                "v=" + v +
                '}';
    }
}
