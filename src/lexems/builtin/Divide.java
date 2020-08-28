package lexems.builtin;

import lexems.*;

import java.util.List;

public class Divide extends Func implements IBuiltin {
    public Divide() {
        super(
                new Atom("divide"),
                List.of(new Atom("n1"), new Atom("n2")),
                new ElementsList()
        );
    }

    public IElement execute(List<IElement> argValues) {
        if (argValues.size() != 2){
            // error
            return null;
        }

        IElement f = argValues.get(0);
        IElement s = argValues.get(1);

        if (f instanceof IntegerLiteral && s instanceof IntegerLiteral) {
            return new RealLiteral(((double) ((IntegerLiteral) f).v) / ((IntegerLiteral) s).v);
        }
        else if (f instanceof IntegerLiteral && s instanceof RealLiteral) {
            return new RealLiteral(((IntegerLiteral) f).v / ((RealLiteral) s).v);
        }
        else if (f instanceof RealLiteral && s instanceof IntegerLiteral) {
            return new RealLiteral(((RealLiteral) f).v / ((IntegerLiteral) s).v);
        }
        else if (f instanceof RealLiteral && s instanceof RealLiteral) {
            return new RealLiteral(((RealLiteral) f).v / ((RealLiteral) s).v);
        } else {
            //error
            return null;
        }

    }
}
