package lexems.builtin;

import exceptions.InterpreterException;
import exceptions.InvalidArgumentTypesException;
import exceptions.InvalidNumberOfArgumentsException;
import lexems.*;

import java.util.List;

public class Cons extends Func implements IBuiltin {
    public Cons() {
        super(
                new Atom("cons"),
                List.of(new Atom("n1"), new Atom("n2")),
                new ElementsList()
        );
    }

    public IElement execute(List<IElement> argValues) throws InterpreterException  {
        if (argValues.size() != 2){
            throw new InvalidNumberOfArgumentsException(argValues.size(), 2);
        }

        IElement f = argValues.get(0);
        IElement s = argValues.get(1);

        if (s instanceof ElementsList) {
            ElementsList list = (ElementsList) s;
            list.addFirst(f);
            return list;
        } else {
            throw new InvalidArgumentTypesException();
        }

    }
}
