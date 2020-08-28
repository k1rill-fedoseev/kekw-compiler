package interpreter;

import lexems.*;
import lexems.builtin.*;

import java.util.List;

public class Interpreter {
    private final SymbolTable st;

    public Interpreter(SymbolTable globalScope) {
        st = globalScope;
        st.define(new Plus());
        st.define(new Minus());
        st.define(new Times());
        st.define(new Divide());

        st.define(new Head());
        st.define(new Tail());
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
                    if (f instanceof IBuiltin) {
                        if (checkNumberOfArguments(f, list)) {
                            args = list.subList(1, 1 + f.getArgs().size());
                            for (int i = 0; i < args.size(); i++) {
                                args.set(i, execute(args.get(i)));
                            }
                            return ((IBuiltin) f).execute(args);
                        }
                    }
                }
            } else {
                return elem;
            }
        } else if (elem instanceof Setq){
            st.define((Setq) elem);
        } else if (elem instanceof Atom) {
            return st.lookup(((Atom) elem).v);
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
