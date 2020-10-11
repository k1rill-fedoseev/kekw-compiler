package lexems.builtin.predicates;

import lexems.*;
import lexems.builtin.IBuiltin;

import java.util.List;

public class Isnull extends Func implements IBuiltin {
    public Isnull() {
        super(
                new Atom("isnull"),
                List.of(new Atom("n1")),
                new ElementsList()
        );
    }

    public IElement execute(List<IElement> argValues) {
        if (argValues.size() != 1){
            // error
            return null;
        }

        IElement f = argValues.get(0);

//        System.out.println(f);

        if (f instanceof ElementsList) {
            return new BooleanLiteral(((ElementsList) f).isEmpty());
        } else {
            return null;
        }

    }
}
