package lexems;

public class IntegerLiteral implements IElement {
    public final int v;

    public IntegerLiteral(int v) {
        this.v = v;
    }

    public Object evaluate() { return v; }

    @Override
    public String toString() {
        return "IntegerLiteral{" +
                "v=" + v +
                '}';
    }
}
