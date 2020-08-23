package lexems;

import java.util.List;

public class Lambda implements IElement {
    private final List<Atom> args;
    private final IElement v;

    public Lambda(List<Atom> args, IElement v) {
        this.args = args;
        this.v = v;
    }

    @Override
    public String toString() {
        return "Lambda{" +
                "args=" + args +
                ", v=" + v +
                '}';
    }
}
