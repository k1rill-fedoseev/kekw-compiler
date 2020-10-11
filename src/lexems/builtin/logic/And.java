package lexems.builtin.logic;

import lexems.*;
import lexems.builtin.IBuiltin;

import java.util.List;

public class And extends Func implements IBuiltin {
    public And() {
        super(
                new Atom("and"),
                List.of(new Atom("n1"), new Atom("n2")),
                new ElementsList()
        );
    }

    public lexems.IElement execute(List<IElement> argValues) {
        if (argValues.size() != 2){
            // error
            return null;
        }

        IElement f = argValues.get(0);
        IElement s = argValues.get(1);

        if (f instanceof BooleanLiteral && s instanceof BooleanLiteral) {
            return new BooleanLiteral(((BooleanLiteral) f).v && ((BooleanLiteral) s).v);
        } else {
            //error
            return null;
        }
    }
}
