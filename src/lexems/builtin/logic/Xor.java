package lexems.builtin.logic;

import exceptions.InterpreterException;
import exceptions.InvalidArgumentTypesException;
import exceptions.InvalidNumberOfArgumentsException;
import lexems.*;
import lexems.builtin.IBuiltin;

import java.util.List;

public class Xor extends Func implements IBuiltin {
    public Xor() {
        super(
                new Atom("xor"),
                List.of(new Atom("n1"), new Atom("n2")),
                new ElementsList()
        );
    }

    public lexems.IElement execute(List<IElement> argValues) throws InterpreterException {
        if (argValues.size() != 2){
            throw new InvalidNumberOfArgumentsException(argValues.size(), 2);
        }

        IElement f = argValues.get(0);
        IElement s = argValues.get(1);

        if (f instanceof BooleanLiteral && s instanceof BooleanLiteral) {
            return new BooleanLiteral(((BooleanLiteral) f).v && !((BooleanLiteral) s).v);
        } else {
            throw new InvalidArgumentTypesException();
        }
    }
}
