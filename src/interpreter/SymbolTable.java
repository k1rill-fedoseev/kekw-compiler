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
        table.put(s.getName(), s.getV());
    }

    public void define(String s, IElement e) {
        table.put(s, e);
    }

    public IElement lookup(String name) {
        return table.get(name);
    }
}