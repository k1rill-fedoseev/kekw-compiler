package interpreter;

import java.util.HashMap;
import lexems.*;

public class SymbolTable {
    private final SymbolTable parent;
    private final HashMap<String, IElement> table;

    public SymbolTable() {
        parent = null;
        table = new HashMap<>();
    }

    public SymbolTable(SymbolTable p) {
        parent = p;
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
        IElement e = table.get(name);
        if (e == null && parent != null){
            e = parent.lookup(name);
        }
        return e;
    }

    public String toString() {
        return "SymbolTable{" +
                "table=" + table +
                '}';
    }
}