package lexems.builtin.predicates;

import exceptions.InvalidNumberOfArgumentsException;
import lexems.*;
import lexems.builtin.IBuiltin;

import java.util.List;

public class Isint extends Func implements IBuiltin {
    public Isint() {
        super(
                new Atom("isint"),
                List.of(new Atom("n1")),
                new ElementsList()
        );
    }

    public IElement execute(List<IElement> argValues) throws InvalidNumberOfArgumentsException {
        if (argValues.size() != 1){
            throw new InvalidNumberOfArgumentsException(argValues.size(), 1);
        }

        IElement f = argValues.get(0);

        if (f instanceof IntegerLiteral)
            return new BooleanLiteral(true);
        else
            return new BooleanLiteral(false);
    }
}
