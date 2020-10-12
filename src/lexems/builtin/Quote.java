package lexems.builtin;

import exceptions.InvalidNumberOfArgumentsException;
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

    public IElement execute(List<IElement> argValues) throws InvalidNumberOfArgumentsException {
        if (argValues.size() != 1){
            throw new InvalidNumberOfArgumentsException(argValues.size(), 1);
        }

        return argValues.get(0);
    }
}
