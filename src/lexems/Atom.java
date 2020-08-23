package lexems;

public class Atom implements IElement {
    private final String[] keywords = {
            "quote", "setq", "func", "lambda", "prog", "cond", "while", "return", "break"
    };
    private final String[] predefined_functions = {
            "plus", "minus", "times", "divide", "head", "tail", "cons",
            "equal", "nonequal", "less", "lesseq", "greater", "greatereq",
            "isint", "isreal", "isbool", "isnull", "isatom", "islist",
            "and", "or", "xor", "not",
            "eval"
    };
    public final String v;

    public Atom(String v) {
        this.v = v;
    }

    public boolean isKeyword() {
        for(String s: keywords) {
            if (s.equals(v)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Identifier{" +
                "v='" + v + '\'' +
                '}';
    }
}
