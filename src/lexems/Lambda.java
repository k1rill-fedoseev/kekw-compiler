package lexems;

import java.util.List;

public class Lambda implements IElement {
    private final List<Atom> args;
    private final IElement v;

    public Lambda(List<Atom> args, IElement v) {
        this.args = args;
        this.v = v;
    }

    public List<Atom> getArgs() { return args; }
    public IElement getV() { return v; }
    public String getName() { return "Anonymous"; }

    @Override
    public String toString() {
        return "Lambda{" +
                "args=" + args +
                ", v=" + v +
                '}';
    }
}
