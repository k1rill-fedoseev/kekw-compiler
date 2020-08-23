package interpreter;

import lexems.*;
import parser.Parser;

import java.io.IOException;

public class Interpreter {
    public static void main(String[] args) throws IOException {
        ElementsList ast = Parser.makeAST();
        if (ast == null) {
            System.err.println("Syntax parsing failed");
            System.exit(1);
        }
        System.out.println(ast);
    }
}
