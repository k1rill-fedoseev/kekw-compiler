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
        for (IElement elem : ast) {
            res.append("  ").append(elem).append(",\n\n");
        }
        res.append("]");
        // System.out.println(res.toString());
        Interpreter interpreter = new Interpreter();

        int i = 0;
        for (IElement elem : ast) {
            try {
                interpreter.stackTrace.add(new StackTraceElement("", "main", "", Parser.lines.get(i++)));
                IElement executed = interpreter.execute(elem);
                if (executed != null) {
                    System.out.println(executed);
                }
                interpreter.stackTrace.pop();
            } catch (InterpreterException e) {
                e.setStackTrace(interpreter.getStackTrace());
                throw e;
            }
        }
    }
}
