package lexems.builtin;

import exceptions.InterpreterException;
import exceptions.InvalidArgumentTypesException;
import exceptions.InvalidNumberOfArgumentsException;
import lexems.Atom;
import lexems.ElementsList;
import lexems.Func;
import lexems.IElement;

import java.util.List;

public class Tail extends Func implements IBuiltin {
    public Tail() {
        super(
                new Atom("tail"),
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
            ElementsList list = (ElementsList) f;
            return new ElementsList(list.subList(1, list.size()));
        } else {
            throw new InvalidArgumentTypesException();
        }

    }
}
