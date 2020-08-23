package lexems;

import java.util.List;

public class Func implements IElement {
    private final Atom id;
    private final List<Atom> args;
    private final IElement v;

    public Func(Atom id, List<Atom> args, IElement v) {
        this.id = id;
        this.args = args;
        this.v = v;
    }

    @Override
    public String toString() {
        return "Func{" +
                "id=" + id +
                ", args=" + args +
                ", v=" + v +
                '}';
    }
}
