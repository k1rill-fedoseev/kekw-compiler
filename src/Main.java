import interpreter.Interpreter;
import interpreter.Scope;
import lexems.ElementsList;
import lexems.IElement;
import parser.Parser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ElementsList ast = Parser.makeAST();
        if (ast == null) {
            System.err.println("Syntax parsing failed");
            System.exit(1);
        }
        StringBuilder res = new StringBuilder("ElementsList[\n");
        for (IElement elem: ast) {
            res.append("  ").append(elem).append(",\n\n");
        }
        res.append("]");
        System.out.println(res.toString());
        Interpreter interpreter = new Interpreter(new Scope());

        for (IElement elem: ast) {
            System.out.println(interpreter.execute(elem));
        }
    }
}
