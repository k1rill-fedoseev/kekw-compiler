package interpreter;

import lexems.*;
import lexems.builtin.*;

import java.util.List;

public class Interpreter {
    private final SymbolTable globalScope;

    public Interpreter() {
        globalScope = new SymbolTable();
        globalScope.define(new Plus());
        globalScope.define(new Minus());
        globalScope.define(new Times());
        globalScope.define(new Divide());

        globalScope.define(new Head());
        globalScope.define(new Tail());
        globalScope.define(new Cons());

        globalScope.define(new Greater());
        globalScope.define(new Less());

        globalScope.define(new Isnull());
    }

    public IElement execute(IElement elem) {
        return execute(elem, globalScope);
    }

    public IElement execute(IElement elem, SymbolTable scope) {
        if (elem instanceof ElementsList){
            ElementsList list = ((ElementsList) elem).clone();
            if (list.isEmpty()) return list;
            // Evaluate each element of the list
            for (int i = 0; i < list.size(); i++) {
                list.set(i, execute(list.get(i), scope));
            }
            IElement first = list.getFirst();
            if (first instanceof Func) {
                Func f = (Func) first;
                List<IElement> argValues;
                if (checkNumberOfArguments(f, list)) {
                    argValues = list.subList(1, 1 + f.getArgs().size());
                    // Builtin function
                    if (f instanceof IBuiltin) {
                        return ((IBuiltin) f).execute(argValues);
                    }
                    // User-defined function
                    else {
                        // Create function local scope
                        // Define current scope as a parent
                        SymbolTable localScope = new SymbolTable(scope);

                        // Fill local scope with provided values
                        List<Atom> argNames = f.getArgs();
                        for (int i = 0; i < argNames.size(); i++) {
                            localScope.define(argNames.get(i).getV(), argValues.get(i));
                        }

                        // Execute function
                        return execute(f.getV(), localScope);
                    }
                }
            } else {
                return elem;
            }
        } else if (elem instanceof Setq){
            Setq sq = (Setq) elem;
            scope.define(sq.getName(), execute(sq.getV()));
        } else if (elem instanceof Atom) {
            return scope.lookup(((Atom) elem).v);
        } else if (elem instanceof Func) {
            scope.define((Func) elem);
        } else if (elem instanceof Cond) {
            Cond c = (Cond) elem;
            // Evaluate condition result
            IElement condition =  execute(c.getC(), scope);
            if (condition instanceof BooleanLiteral) {
                if (((BooleanLiteral) condition).getV())
                    return execute(c.getV(), scope);
                else
                    return execute(c.getE(), scope);
            }
        } else {
            return elem;
        }
        return null;
    }

    private boolean checkNumberOfArguments(Func f, ElementsList list){
        // list contains f as a first element
        if (list.size() - 1 != f.getArgs().size()) {
            // number of arguments does not match
            // handle error
            return false;
        } else {
            return true;
        }
    }

}
