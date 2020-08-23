import lexems.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.regex.Pattern;

class MyLexer implements Parser.Lexer {
    StreamTokenizer st;

    private final Pattern integerPattern = Pattern.compile("[+\\-]?[0-9]+");
    private final Pattern realPattern = Pattern.compile("[+\\-]?[0-9]*\\.[0-9]+");
    private final Pattern boolPattern = Pattern.compile("(true|false)");
    private final Pattern identifierPattern = Pattern.compile("[a-zA-Z_][a-zA-Z_0-9]*");

    public MyLexer(InputStream is) {
        st = new StreamTokenizer(new InputStreamReader(is));
        st.resetSyntax();
        st.whitespaceChars('\t', '\t');
        st.whitespaceChars('\n', '\n');
        st.whitespaceChars(' ', ' ');
        st.wordChars('0', '9');
        st.wordChars('a', 'z');
        st.wordChars('A', 'Z');
        st.wordChars('-', '-');
        st.wordChars('+', '+');
        st.wordChars('_', '_');
        st.wordChars('.', '.');
        st.ordinaryChar('(');
        st.ordinaryChar(')');
        st.ordinaryChar('\'');
    }

    public void yyerror(String s) {
        System.err.println(s);
    }

    IElement yylval;

    public IElement getLVal() {
        return yylval;
    }

    public int yylex() throws IOException {
        int ttype = st.nextToken();
        switch (ttype) {
            case StreamTokenizer.TT_EOF:
                return YYEOF;
            case StreamTokenizer.TT_WORD:
                String v = st.sval;
                if (integerPattern.matcher(v).matches()) {
                    yylval = new IntegerLiteral(Integer.parseInt(v));
                    return INTEGER;
                }
                if (realPattern.matcher(v).matches()) {
                    yylval = new RealLiteral(Float.parseFloat(v));
                    return REAL;
                }
                if (boolPattern.matcher(v).matches()) {
                    yylval = new BoolLiteral(Boolean.parseBoolean(v));
                    return BOOLEAN;
                }
                if (identifierPattern.matcher(v).matches()) {
                    yylval = new Identifier(v);
                    return IDENTIFIER;
                }
                return YYUNDEF;
            case '\'':
            case '(':
            case ')':
                return ttype;
            default:
                return YYUNDEF;
        }
    }
}
