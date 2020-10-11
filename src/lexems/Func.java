package lexems;

import java.util.List;

public class Func extends Lambda implements IElement {
    private final Atom id;

    public Func(Atom id, List<Atom> args, IElement v) {
        super(args, v);
        this.id = id;
    }

    @Override
    public String toString() {
        return "Func{" +
                "id=" + id +
                ", args=" + getArgs() +
                ", v=" + getV() +
                '}';
    }

    public String getName() { return id.v; }
}
