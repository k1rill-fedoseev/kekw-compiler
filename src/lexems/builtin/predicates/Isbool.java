package lexems.builtin.predicates;

import exceptions.InvalidNumberOfArgumentsException;
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

    public IElement execute(List<IElement> argValues) throws InvalidNumberOfArgumentsException {
        if (argValues.size() != 1){
            throw new InvalidNumberOfArgumentsException(argValues.size(), 1);
        }

        IElement f = argValues.get(0);

        return new BooleanLiteral(f instanceof BooleanLiteral);
    }
}
