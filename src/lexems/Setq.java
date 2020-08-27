package lexems;

public class Setq implements IElement {
    private final Atom id;
    private final IElement v;

    public Setq(Atom id, IElement v) {
        this.id = id;
        this.v = v;
    }

    @Override
    public String toString() {
        return "Setq{" +
                "id=" + id.v +
                ", v=" + v +
                '}';
    }

    public String getName() {
        return id.v;
    }

    public IElement getV() {
        return v;
    }
}
