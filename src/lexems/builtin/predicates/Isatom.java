package lexems.builtin.predicates;

import lexems.*;
import lexems.builtin.IBuiltin;

import java.util.List;

public class Isatom extends Func implements IBuiltin {
    public Isatom() {
        super(
                new Atom("isatom"),
                List.of(new Atom("n1")),
                new ElementsList()
        );
    }

    public IElement execute(List<IElement> argValues) {
        if (argValues.size() != 1){
            // error
            return null;
        }

        IElement f = argValues.get(0);

        if (f instanceof Atom)
            return new BooleanLiteral(true);
        else
            return new BooleanLiteral(false);
    }
}
