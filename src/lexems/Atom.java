package lexems;

public class Atom implements IElement {
    private final String[] keywords = {
            "quote", "setq", "func", "lambda", "prog", "cond", "while", "return", "break"
    };

    public final String v;

    public Atom(String v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "Atom{" +
                "v='" + v + '\'' +
                '}';
    }
}
