import lexems.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.regex.Pattern;

class MyLexer implements Parser.Lexer {
    private final Pattern integerPattern = Pattern.compile("[+\\-]?[0-9]+");
    private final Pattern realPattern = Pattern.compile("[+\\-]?[0-9]*\\.[0-9]+");
    private final Pattern boolPattern = Pattern.compile("(true|false)");
    private final Pattern identifierPattern = Pattern.compile("[a-zA-Z_][a-zA-Z_0-9]*");

    StreamTokenizer st;
    PositionReader reader;

    private IElement yylval;
    Position start = new Position(1, 0);
    Position end = new Position(1, 0);

    public MyLexer(InputStream is) {
        reader = new PositionReader(new InputStreamReader(is));
        st = new StreamTokenizer(reader);
        st.resetSyntax();
        st.eolIsSignificant(true);
        st.ordinaryChars('\n', '\n');
        st.ordinaryChars('\t', '\t');
        st.ordinaryChars(' ', ' ');
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

    /**
     * Build and emit a syntax error message.
     */
    public void reportSyntaxError(Parser.Context ctx) {
        System.err.print(ctx.getLocation() + ": syntax error");
        final int TOKENMAX = 10;
        Parser.SymbolKind[] arg = new Parser.SymbolKind[TOKENMAX];
        int n = ctx.getExpectedTokens(arg, TOKENMAX);
        for (int i = 0; i < n; ++i) {
            System.err.print((i == 0 ? ": expected " : " or ") + arg[i].getName());
        }
        Parser.SymbolKind lookahead = ctx.getToken();
        if (lookahead != null && lookahead.getCode() != YYUNDEF ) {
            System.err.print(" before " + lookahead.getName());
        }
        System.err.println();
    }

    /**
     * Emit an error referring to the given location in a user-defined way.
     *
     * @param loc The location of the element to which the
     *            error message is related.
     * @param msg The string for the error message.
     */
    public void yyerror(Parser.Location loc, String msg) {
        if (loc == null) {
            System.err.println(msg);
        } else {
            System.err.println(loc + ": " + msg);
        }
    }

    public Parser.Location getLocation() {
        return new Parser.Location(new Position(start), new Position(end));
    }

    public IElement getValue() {
        return yylval;
    }

    public int getToken() throws IOException {
        start.set(reader.getPosition());
        int ttype = st.nextToken();
        end.set(reader.getPosition());
        switch (ttype) {
            case StreamTokenizer.TT_EOF:
                return YYEOF;
            case StreamTokenizer.TT_WORD:
                String v = st.sval;
                end.set(reader.getPreviousPosition());
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
                yylval = new BadToken(v);
                return YYUNDEF;
            case '\'':
            case '(':
            case ')':
            case '\n':
            case ' ':
            case '\t':
                return ttype;
            default:
                yylval = new BadToken(st.sval);
                return YYUNDEF;
        }
    }
}
