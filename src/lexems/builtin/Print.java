package lexems.builtin;

import lexems.*;

import java.util.List;

public class Print extends Func implements IBuiltin{
    public Print() {
        super(
                new Atom("print"),
                List.of(new Atom("s")),
                new ElementsList()
        );
    }

    public IElement execute(List<IElement> argValues) {
        if (argValues.size() != 1){
            // error
            return null;
        }

        IElement s = argValues.get(0);

        return s;
    }
}
