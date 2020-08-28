package lexems.builtin;

import lexems.Atom;
import lexems.ElementsList;
import lexems.Func;
import lexems.IElement;

import java.util.List;

public class Head extends Func implements IBuiltin {
    public Head() {
        super(
                new Atom("head"),
                List.of(new Atom("list")),
                new ElementsList()
        );
    }

    public IElement execute(List<IElement> argValues) {
        if (argValues.size() != 1){
            // error
            return null;
        }

        IElement f = argValues.get(0);

        if (f instanceof ElementsList) {
            return ((ElementsList) f).getFirst();
        } else {
            return null;
        }

    }
}
