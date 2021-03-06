package lexems.builtin;

import exceptions.InvalidNumberOfArgumentsException;
import lexems.*;

import java.util.List;

public class Print extends Func implements IBuiltin {
    public Print() {
        super(
                new Atom("print"),
                List.of(new Atom("s")),
                new ElementsList()
        );
    }

    public IElement execute(List<IElement> argValues) throws InvalidNumberOfArgumentsException {
        for (IElement e: argValues) {
            if (e instanceof StringLiteral) {
                System.out.print(((StringLiteral) e).v + " ");
            } else if (e instanceof IntegerLiteral) {
                System.out.print(((IntegerLiteral) e).v + " ");
            } else if (e instanceof RealLiteral) {
                System.out.print(((RealLiteral) e).v + " ");
            } else if (e instanceof BooleanLiteral) {
                System.out.print(((BooleanLiteral) e).v + " ");
            } else {
                System.out.print(e + " ");
            }
        }
        return null;
    }
}
