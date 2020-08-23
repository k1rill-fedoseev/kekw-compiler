import lexems.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.regex.Pattern;

class MyLexer implements Parser.Lexer {
    StreamTokenizer st;

    Position start = new Position(1, 0);
    Position end = new Position(1, 0);

    PositionReader reader;

    private final Pattern integerPattern = Pattern.compile("[+\\-]?[0-9]+");
    private final Pattern realPattern = Pattern.compile("[+\\-]?[0-9]*\\.[0-9]+");
    private final Pattern boolPattern = Pattern.compile("(true|false)");
    private final Pattern identifierPattern = Pattern.compile("[a-zA-Z_][a-zA-Z_0-9]*");

    public MyLexer(InputStream is) {
        reader = new PositionReader(new InputStreamReader(is));
        st = new StreamTokenizer(reader);
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
        st.commentChar('#');
        st.ordinaryChar('(');
        st.ordinaryChar(')');
        st.ordinaryChar('\'');
    }


    public void yyerror(Parser.Location loc, String s){
        System.err.println(String.format("%s at line %d, column %d", s, loc.begin.line, loc.begin.column));
    }

    public Position getStartPos() {
        return new Position(start);
    }

    public Position getEndPos() {
        return new Position(end);
    }

    public Parser.Location getLocation() {
       return new Parser.Location(new Position(start), new Position(end));
    }

    IElement yylval;

    public IElement getLVal() {
        return yylval;
    }

    public int yylex() throws IOException {
        start.set(reader.getPosition());
        int ttype = st.nextToken();
        end.set(reader.getPosition());

        switch (ttype) {
            case StreamTokenizer.TT_EOL:
                end.line += 1;
                end.column = 0;
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
                    yylval = new BooleanLiteral(Boolean.parseBoolean(v));
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
