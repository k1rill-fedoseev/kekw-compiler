package lexems.builtin.predicates;

import lexems.*;
import lexems.builtin.IBuiltin;

import java.util.List;

public class Isreal extends Func implements IBuiltin {
    public Isreal() {
        super(
                new Atom("isreal"),
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

        if (f instanceof RealLiteral)
            return new BooleanLiteral(true);
        else
            return new BooleanLiteral(false);
    }
}