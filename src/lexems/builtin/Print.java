package lexems.builtin;

import lexems.*;

import java.util.List;

public class Print extends Func implements IBuiltin {
    public Print() {
        super(
                new Atom("print"),
                List.of(new Atom("s")),
                new ElementsList()
        );
    }

    public IElement execute(List<IElement> argValues) {
        if (argValues.size() != 1) {
            // error
            return null;
        }

        IElement s = argValues.get(0);
        if (s instanceof StringLiteral) {
            System.out.println(((StringLiteral) s).v);
            return s;
        } else if (s instanceof IntegerLiteral) {
            System.out.println(((IntegerLiteral) s).v);
            return s;
        } else if (s instanceof RealLiteral) {
            System.out.println(((RealLiteral) s).v);
            return s;
        } else if (s instanceof BooleanLiteral) {
            System.out.println(((BooleanLiteral) s).v);
            return s;
        } else {
            System.out.println(s);
            return s;
        }
    }
}
