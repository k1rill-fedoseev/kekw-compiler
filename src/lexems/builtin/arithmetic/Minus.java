package lexems.builtin.arithmetic;

import exceptions.InterpreterException;
import exceptions.InvalidArgumentTypesException;
import exceptions.InvalidNumberOfArgumentsException;
import lexems.*;
import lexems.builtin.IBuiltin;

import java.util.List;

public class Minus extends Func implements IBuiltin {
    public Minus() {
        super(
                new Atom("minus"),
                List.of(new Atom("n1"), new Atom("n2")),
                new ElementsList()
        );
    }

    public IElement execute(List<IElement> argValues) throws InterpreterException {
        if (argValues.size() != 2){
            throw new InvalidNumberOfArgumentsException(argValues.size(), 2);
        }

        IElement f = argValues.get(0);
        IElement s = argValues.get(1);

        if (f instanceof IntegerLiteral && s instanceof IntegerLiteral) {
            return new IntegerLiteral(((IntegerLiteral) f).v - ((IntegerLiteral) s).v);
        }
        else if (f instanceof IntegerLiteral && s instanceof RealLiteral) {
            return new RealLiteral(((IntegerLiteral) f).v - ((RealLiteral) s).v);
        }
        else if (f instanceof RealLiteral && s instanceof IntegerLiteral) {
            return new RealLiteral(((RealLiteral) f).v - ((IntegerLiteral) s).v);
        }
        else if (f instanceof RealLiteral && s instanceof RealLiteral) {
            return new RealLiteral(((RealLiteral) f).v - ((RealLiteral) s).v);
        } else {
            throw new InvalidArgumentTypesException();
        }
    }
}
