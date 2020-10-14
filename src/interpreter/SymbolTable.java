package interpreter;

import java.util.HashMap;
import java.util.Objects;

import lexems.*;

public class SymbolTable {
    private final SymbolTable parent;
    private final HashMap<String, IElement> table;
    private boolean isTransient = false;

    public SymbolTable() {
        parent = null;
        table = new HashMap<>();
    }

    public SymbolTable(SymbolTable p) {
        parent = p;
        table = new HashMap<>();
    }

    public SymbolTable(SymbolTable p, boolean isTransient) {
        this(p);
        this.isTransient = isTransient;
    }

    public void define(Func f) {
        table.put(f.getName(), f);
    }

    public void define(String s, IElement e) {
        table.put(s, e);
    }

    public void defineLookup(String s, IElement e) {
        SymbolTable cur = this;
        while (cur != null && !cur.table.containsKey(s) && cur.isTransient) {
            cur = cur.parent;
        }
        Objects.requireNonNullElse(cur, this).define(s, e);
    }

    public IElement lookup(String name) {
        IElement e = table.get(name);
        if (e == null && parent != null && isTransient){
            e = parent.lookup(name);
        }
        return e;
    }

    public boolean contains(String name) {
        return this.table.containsKey(name);
    }

    public String toString() {
        return "SymbolTable{" +
                "table=" + table +
                '}';
    }
}