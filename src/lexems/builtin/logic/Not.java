package lexems.builtin.logic;

import exceptions.InterpreterException;
import exceptions.InvalidArgumentTypesException;
import exceptions.InvalidNumberOfArgumentsException;
import lexems.*;
import lexems.builtin.IBuiltin;

import java.util.List;

public class Not extends Func implements IBuiltin {
    public Not() {
        super(
                new Atom("not"),
                List.of(new Atom("n1")),
                new ElementsList()
        );
    }

    public lexems.IElement execute(List<IElement> argValues) throws InterpreterException {
        if (argValues.size() != 1){
            throw new InvalidNumberOfArgumentsException(argValues.size(), 1);
        }

        IElement f = argValues.get(0);

        if (f instanceof BooleanLiteral) {
            return new BooleanLiteral(!((BooleanLiteral) f).v);
        } else {
            throw new InvalidArgumentTypesException();
        }
    }
}
