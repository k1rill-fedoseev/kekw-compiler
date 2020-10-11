package lexems.builtin;

import lexems.*;

import java.util.List;

public class Quote extends Func implements IBuiltin {
    public Quote() {
        super(
                new Atom("quote"),
                List.of(new Atom("n1")),
                new ElementsList()
        );
    }

    public IElement execute(List<IElement> argValues) {
        if (argValues.size() != 1){
            // error
            return null;
        }

        return argValues.get(0);
    }
}
