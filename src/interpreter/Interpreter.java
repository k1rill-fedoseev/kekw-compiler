package interpreter;

import lexems.IElement;

public class Interpreter {
    private final Scope scope;

    public Interpreter(Scope parentScope) {
        this.scope = parentScope;
    }

    public Object execute(IElement command) {
        return null;
    }
}
