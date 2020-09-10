package lexems.builtin;

import lexems.*;

import java.util.List;
import java.util.zip.InflaterInputStream;

public class Not extends Func implements IBuiltin {
    public Not() {
        super(
                new Atom("not"),
                List.of(new Atom("n1")),
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

        Integer obj = 9;
        switch (obj) {
            case Integer i -> String.format("int %d", i);
            case Byte b    -> String.format("byte %d", b);
            case Long l    -> String.format("long %d", l);
            case Double d  -> String.format("double %f", d);
            case String s  -> String.format("String %s", s);
            default        -> String.format("Object %s", obj);
        };
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
