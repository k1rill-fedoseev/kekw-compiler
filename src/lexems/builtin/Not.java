package lexems.builtin;

import lexems.*;

import java.util.List;

public class Not extends Func implements IBuiltin {
    public Not() {
        super(
                new Atom("not"),
                List.of(new Atom("n1")),
                new ElementsList()
        );
    }

    public lexems.IElement execute(List<IElement> argValues) {
        if (argValues.size() != 1){
            // error
            return null;
        }

        IElement f = argValues.get(0);

        if (f instanceof BooleanLiteral) {
            return new BooleanLiteral(!(((BooleanLiteral) f).v));
        } else {
            //error
            return null;
        }
    }
}
