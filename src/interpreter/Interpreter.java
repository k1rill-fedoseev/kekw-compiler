package interpreter;

import lexems.*;

import java.util.List;

public class Interpreter {
    private final SymbolTable st;

    public Interpreter(SymbolTable globalScope) {
        this.st = globalScope;
        st.define(new Plus());
    }

    public IElement execute(IElement elem) {
        if (elem instanceof ElementsList){
            ElementsList list = (ElementsList) elem;
            IElement first = list.getFirst();
            if (first instanceof Atom) {
                Atom id = (Atom) first;
                IElement lookupVal = st.lookup(id.v);
                if (lookupVal instanceof Func) {
                    Func f = (Func) lookupVal;
                    List<IElement> args;
                    if (checkNumberOfArguments(f, list)){
                        args = list.subList(1, 1 + f.getArgs().size());
                        for (int i = 0; i < args.size(); i++) {
                            args.set(i, execute(args.get(i)));
                        }
                        return f.execute(args);
                    }
                }
            }
        } else {
            return elem;
        }
        return null;
    }

    private boolean checkNumberOfArguments(Func f, ElementsList list){
        // list contains f as a first element
        if (list.size() - 1 < f.getArgs().size()) {
            // not enough args
            // handle error
            return false;
        } else {
            return true;
        }
    }

}
