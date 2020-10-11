import exceptions.InterpreterException;
import interpreter.Interpreter;
import lexems.ElementsList;
import lexems.IElement;
import parser.Parser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterpreterException {
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
        // System.out.println(res.toString());
        Interpreter interpreter = new Interpreter();

        for (IElement elem: ast) {
            try {
                System.out.println(interpreter.execute(elem));
            } catch (InterpreterException e) {
                e.setStackTrace(interpreter.getStackTrace());
                throw e;
            }
        }
    }
}
