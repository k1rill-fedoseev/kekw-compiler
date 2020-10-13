package lexems;

public class StringLiteral implements IElement {
    public final String v;

    public StringLiteral(String v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return v;
    }
}
