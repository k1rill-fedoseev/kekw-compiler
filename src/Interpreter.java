import lexems.IElement;

import java.io.IOException;

public class Interpreter {
    public static void main(String[] args) throws IOException {
        IElement ast = Parser.makeAST();
        System.out.println(ast);
    }
}
