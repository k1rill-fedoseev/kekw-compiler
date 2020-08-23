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
        System.out.println(ast);
        Interpreter interpreter = new Interpreter(new Scope());

        for (IElement elem: ast) {
            System.out.println(interpreter.execute(elem));
        }
    }
}
