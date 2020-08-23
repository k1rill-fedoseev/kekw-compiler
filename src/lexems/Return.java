package lexems;

public class Return implements IElement {
    private final IElement v;

    public Return(IElement v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "Return{" +
                "v=" + v +
                '}';
    }
}
