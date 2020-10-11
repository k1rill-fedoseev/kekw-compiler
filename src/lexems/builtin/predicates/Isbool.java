package lexems.builtin.predicates;

import lexems.*;
import lexems.builtin.IBuiltin;

import java.util.List;

public class Isbool extends Func implements IBuiltin {
    public Isbool() {
        super(
                new Atom("isbool"),
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

        if (f instanceof BooleanLiteral)
            return new BooleanLiteral(true);
        else
            return new BooleanLiteral(false);
    }
}
