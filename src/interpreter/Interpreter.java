package interpreter;

import exceptions.InterpreterException;
import lexems.*;
import lexems.builtin.*;
import lexems.builtin.Quote;
import lexems.builtin.arithmetic.*;
import lexems.builtin.comp.*;
import lexems.builtin.logic.*;
import lexems.builtin.predicates.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Interpreter {
    private final SymbolTable globalScope;

    public final Stack<StackTraceElement> stackTrace;

    public Interpreter() {
        stackTrace = new Stack<>();

        globalScope = new SymbolTable();
        globalScope.define(new Plus());
        globalScope.define(new Minus());
        globalScope.define(new Times());
        globalScope.define(new Divide());

        globalScope.define(new Head());
        globalScope.define(new Tail());
        globalScope.define(new Cons());

        // Comp
        globalScope.define(new Greater());
        globalScope.define(new Greatereq());
        globalScope.define(new Less());
        globalScope.define(new Lesseq());
        globalScope.define(new Equal());
        globalScope.define(new Nonequal());

        //Logic
        globalScope.define(new Not());
        globalScope.define(new And());
        globalScope.define(new Or());
        globalScope.define(new Xor());

        // Predicates
        globalScope.define(new Isint());
        globalScope.define(new Isreal());
        globalScope.define(new Isbool());
        globalScope.define(new Isnull());
        globalScope.define(new Isatom());
        globalScope.define(new Islist());

        globalScope.define(new Quote());

        globalScope.define(new Print());
    }

    public StackTraceElement[] getStackTrace() {
        StackTraceElement[] res = new StackTraceElement[this.stackTrace.size()];
        return this.stackTrace.toArray(res);
    }

    public IElement execute(IElement elem) throws InterpreterException {
        return execute(elem, globalScope);
    }

    public IElement execute(IElement elem, SymbolTable scope) throws InterpreterException {
        if (elem instanceof ElementsList) {
            ElementsList list = ((ElementsList) elem).clone();
            if (list.isEmpty()) return list;
            // Evaluate each element of the list
            list.set(0, execute(list.getFirst(), scope));
            IElement first = list.getFirst();
            if (first instanceof Lambda) {
                stackTrace.add(new StackTraceElement("", ((Lambda) first).getName(), "", ((Lambda) first).getLine()));
            }
            for (int i = 1; i < list.size(); i++) {
                list.set(i, execute(list.get(i), scope));
            }
            if (first instanceof Lambda) {
                Lambda f = (Lambda) first;

                list.removeFirst();
                // Builtin function
                if (f instanceof IBuiltin) {
                    IElement result = ((IBuiltin) f).execute(list);
                    stackTrace.pop();
                    return result;
                } else {
                    // User-defined function

                    // Create function local scope
                    // Define current scope as a parent
                    SymbolTable localScope = new SymbolTable(scope);

                    // Fill local scope with provided values
                    List<Atom> argNames = f.getArgs();
                    for (int i = 0; i < argNames.size(); i++) {
                        localScope.define(argNames.get(i).getV(), list.get(i));
                    }

                    // Execute function
                    IElement result = execute(f.getV(), localScope);
                    stackTrace.pop();
                    return result;
                }
            } else {
                return elem;
            }
        } else if (elem instanceof Setq) {
            Setq sq = (Setq) elem;
            scope.define(sq.getName(), execute(sq.getV()));
        } else if (elem instanceof Atom) {
            return scope.lookup(((Atom) elem).v);
        } else if (elem instanceof Func) {
            scope.define((Func) elem);
        } else if (elem instanceof Cond) {
            Cond c = (Cond) elem;
            // Evaluate condition result
            IElement condition = execute(c.getC(), scope);
            if (condition instanceof BooleanLiteral) {
                if (((BooleanLiteral) condition).v)
                    return execute(c.getV(), scope);
                else
                    return execute(c.getE(), scope);
            }
        } else {
            return elem;
        }
        return null;
    }
}
