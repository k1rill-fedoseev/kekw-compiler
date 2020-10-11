package lexems.builtin.comp;

import lexems.*;
import lexems.builtin.IBuiltin;

import java.util.List;

public class Less extends Func implements IBuiltin {
    public Less() {
        super(
                new Atom("less"),
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
            return new BooleanLiteral(((IntegerLiteral) f).v < ((IntegerLiteral) s).v);
        }
        else if (f instanceof IntegerLiteral && s instanceof RealLiteral) {
            return new BooleanLiteral(((IntegerLiteral) f).v < ((RealLiteral) s).v);
        }
        else if (f instanceof RealLiteral && s instanceof IntegerLiteral) {
            return new BooleanLiteral(((RealLiteral) f).v < ((IntegerLiteral) s).v);
        }
        else if (f instanceof RealLiteral && s instanceof RealLiteral) {
            return new BooleanLiteral(((RealLiteral) f).v < ((RealLiteral) s).v);
        } else {
            //error
            return null;
        }
    }
}
