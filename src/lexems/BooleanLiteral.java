package lexems;

public class BooleanLiteral implements IElement {
    private final boolean v;

    public BooleanLiteral(boolean v) {
        this.v = v;
    }

    public Object evaluate() { return v; }

    @Override
    public String toString() {
        return "BooleanLiteral{" +
                "v=" + v +
                '}';
    }
}
