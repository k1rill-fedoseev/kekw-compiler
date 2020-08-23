package lexems;

public class Identifier implements IElement {
    private final String v;

    public Identifier(String v) {
        this.v = v;
    }

    public Object evaluate() {
        return v;
    }

    @Override
    public String toString() {
        return "Identifier{" +
                "v='" + v + '\'' +
                '}';
    }
}
