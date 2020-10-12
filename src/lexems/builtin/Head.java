package lexems.builtin;

import exceptions.InterpreterException;
import exceptions.InvalidArgumentTypesException;
import exceptions.InvalidNumberOfArgumentsException;
import lexems.Atom;
import lexems.ElementsList;
import lexems.Func;
import lexems.IElement;

import java.util.List;

public class Head extends Func implements IBuiltin {
    public Head() {
        super(
                new Atom("head"),
                List.of(new Atom("list")),
                new ElementsList()
        );
    }

    public IElement execute(List<IElement> argValues) throws InterpreterException {
        if (argValues.size() != 1){
            throw new InvalidNumberOfArgumentsException(argValues.size(), 1);
        }

        IElement f = argValues.get(0);

        if (f instanceof ElementsList) {
            return ((ElementsList) f).getFirst();
        } else {
            throw new InvalidArgumentTypesException();
        }

    }
}
