package interpreter;

import java.util.HashMap;
import lexems.*;

public class SymbolTable {
    private final HashMap<String, IElement> table;

    public SymbolTable() {
        table = new HashMap<>();

    }

    // TODO: check existance of an identifier
    public void define(Func f) {
        table.put(f.getName(), f);
    }

    public void define(Setq s) {
        table.put(s.getName(), s);
    }

    public IElement lookup(String name) {
        return table.get(name);
    }
}