package lexems.builtin.predicates;

import exceptions.InvalidNumberOfArgumentsException;
import lexems.*;
import lexems.builtin.IBuiltin;

import java.util.List;

public class Isnull extends Func implements IBuiltin {
    public Isnull() {
        super(
                new Atom("isnull"),
                List.of(new Atom("n1")),
                new ElementsList()
        );
    }

    public IElement execute(List<IElement> argValues) throws InvalidNumberOfArgumentsException {
        if (argValues.size() != 1){
            throw new InvalidNumberOfArgumentsException(argValues.size(), 1);
        }

        IElement f = argValues.get(0);

        if (f instanceof ElementsList)
            return new BooleanLiteral(((ElementsList) f).isEmpty());
        else
            return new BooleanLiteral(false);
    }
}
