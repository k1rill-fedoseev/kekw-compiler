package lexems;

import java.util.List;

public class Tail extends Func {
    public Tail() {
        super(
                new Atom("tail"),
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
            ElementsList list = (ElementsList) f;
            return new ElementsList(list.subList(1, list.size()));
        } else {
            return null;
        }

    }
}
