package lexems;

public class IntegerLiteral implements IElement {
    public final int v;

    public IntegerLiteral(int v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return String.valueOf(v);
    }
}
