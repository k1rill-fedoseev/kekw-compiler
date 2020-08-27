package interpreter;

import lexems.IElement;

public class Interpreter {
    private final SymbolTable st;

    public Interpreter(SymbolTable globalScope) {
        this.st = globalScope;
    }

    public Object execute(IElement command) {
        return null;
    }
}
