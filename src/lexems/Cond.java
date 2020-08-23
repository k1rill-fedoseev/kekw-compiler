package lexems;

public class Cond implements IElement {
    private final IElement c;
    private final IElement v;
    private final IElement e;

    public Cond(IElement c, IElement v) {
        this.c = c;
        this.v = v;
        this.e = null;
    }

    public Cond(IElement c, IElement v, IElement e) {
        this.c = c;
        this.v = v;
        this.e = e;
    }

    @Override
    public String toString() {
        return "Cond{" +
                "c=" + c +
                ", v=" + v +
                ", e=" + e +
                '}';
    }
}
