package lexems;

public class BooleanLiteral implements IElement {
    public final boolean v;

    public BooleanLiteral(boolean v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "BooleanLiteral{" +
                "v=" + v +
                '}';
    }
}
