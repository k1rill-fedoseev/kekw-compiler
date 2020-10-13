package lexems;

public class Quote implements IElement {
    private final IElement v;

    public Quote(IElement v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "v=" + v +
                '}';
    }

    public IElement getV() {
        return v;
    }
}
