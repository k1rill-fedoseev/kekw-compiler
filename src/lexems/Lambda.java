package lexems;

import java.util.List;

public class Lambda implements IElement {
    private final List<Atom> args;
    private final IElement v;
    private final int line;

    public Lambda(List<Atom> args, IElement v, int line) {
        this.args = args;
        this.v = v;
        this.line = line;
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

    public int getLine() {
        return this.line;
    }
}
