package lexems.builtin;

import exceptions.InvalidNumberOfArgumentsException;
import interpreter.SymbolTable;
import lexems.Atom;
import lexems.ElementsList;
import lexems.Func;
import lexems.IElement;

import java.util.List;

public class Eval extends Func implements IBuiltin {
    public Eval() {
        super(
                new Atom("eval"),
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
