package lexems;

import java.util.List;

public class Prog implements IElement {
    private final List<Atom> args;
    private final IElement v;

    public Prog(List<Atom> args, IElement v) {
        this.args = args;
        this.v = v;
    }

    @Override
    public String toString() {
        return "Prog{" +
                "args=" + args +
                ", v=" + v +
                '}';
    }
}
