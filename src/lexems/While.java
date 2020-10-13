package lexems;

public class While implements IElement {
    private final IElement c;
    private final IElement v;

    public While(IElement c, IElement v) {
        this.c = c;
        this.v = v;
    }

    @Override
    public String toString() {
        return "While{" +
                "c=" + c +
                ", v=" + v +
                '}';
    }

    public IElement getC() {
        return c;
    }

    public IElement getV() {
        return v;
    }
}
