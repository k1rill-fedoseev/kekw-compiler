package lexems;

public class BadToken implements IElement {
    private final String v;

    public BadToken(String v) {
        this.v = v;
    }

    public Object evaluate() {
        return v;
    }

    @Override
    public String toString() {
        return "'" + v + "'";
    }
}
