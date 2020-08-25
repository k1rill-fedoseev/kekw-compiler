package parser;

/**
 * A class defining a point in the input.
 */
public class Position {
    public int line;
    public int column;

    public Position() {
        this(1, 1);
    }

    public Position(int l, int t) {
        line = l;
        column = t;
    }

    public Position(Position p) {
        line = p.line;
        column = p.column;
    }

    public void set(Position p) {
        line = p.line;
        column = p.column;
    }

    public boolean equals(Position l) {
        return l.line == line && l.column == column;
    }

    public String toString() {
        return "L" + line + "." + column;
    }
}
