package parser;

import lexems.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.regex.Pattern;

class MyLexer implements Parser.Lexer {
    private final Pattern INTEGER_PATTERN = Pattern.compile("[+\\-]?[0-9]+");
    private final Pattern REAL_PATTERN = Pattern.compile("[+\\-]?[0-9]*\\.[0-9]+");
    private final Pattern BOOL_PATTERN = Pattern.compile("(true|false)");
    private final Pattern IDENTIFIER_PATTERN = Pattern.compile("[a-zA-Z_][a-zA-Z_0-9]*");

    StreamTokenizer st;
    PositionReader reader;

    private IElement lastToken;
    Position lastTokenStart = new Position(1, 0);
    Position lastTokenEnd = new Position(1, 0);

    public MyLexer(InputStream is) {
        reader = new PositionReader(new InputStreamReader(is));
        st = new StreamTokenizer(reader);
        st.resetSyntax();
        st.whitespaceChars('\n', '\n');
        st.whitespaceChars('\t', '\t');
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

    /**
     * Build and emit a syntax error message.
     */
    public void reportSyntaxError(Parser.Context ctx) {
        yyerror(ctx.getLocation(), "syntax error");
        final int TOKENMAX = 10;
        Parser.SymbolKind[] arg = new Parser.SymbolKind[TOKENMAX];
        int n = ctx.getExpectedTokens(arg, TOKENMAX);
        for (int i = 0; i < n; ++i) {
            System.err.print((i == 0 ? ": expected " : " or ") + arg[i].getName());
        }
        Parser.SymbolKind lookahead = ctx.getToken();
        if (lookahead != null && lookahead != Parser.SymbolKind.S_YYUNDEF) {
            System.err.println(" before " + lookahead.getName());
        } else {
            System.err.println(", got invalid token " + getValue() + " instead");
        }
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
        return new Parser.Location(new Position(lastTokenStart), new Position(lastTokenEnd));
    }

    public IElement getValue() {
        return lastToken;
    }

    public int getToken() throws IOException {
        lastTokenStart.set(reader.getPosition());
        int ttype = st.nextToken();
        lastTokenEnd.set(reader.getPosition());
        switch (ttype) {
            case StreamTokenizer.TT_EOF:
                return YYEOF;
            case StreamTokenizer.TT_WORD:
                String v = st.sval;
                lastTokenEnd.set(reader.getPreviousPosition());
                if (INTEGER_PATTERN.matcher(v).matches()) {
                    lastToken = new IntegerLiteral(Integer.parseInt(v));
                    return INTEGER;
                }
                if (REAL_PATTERN.matcher(v).matches()) {
                    lastToken = new RealLiteral(Float.parseFloat(v));
                    return REAL;
                }
                if (BOOL_PATTERN.matcher(v).matches()) {
                    lastToken = new BooleanLiteral(Boolean.parseBoolean(v));
                    return BOOLEAN;
                }
                switch (v.toLowerCase()) {
                    case "quote":
                        return QUOTE;
                    case "setq":
                        return SETQ;
                    case "func":
                        return FUNC;
                    case "lambda":
                        return LAMBDA;
                    case "prog":
                        return PROG;
                    case "cond":
                        return COND;
                    case "while":
                        return WHILE;
                    case "return":
                        return RETURN;
                    case "break":
                        return BREAK;
                }
                if (IDENTIFIER_PATTERN.matcher(v).matches()) {
                    lastToken = new Atom(v);
                    return ATOM;
                }
                lastToken = new BadToken(v);
                return YYUNDEF;
            case '(':
                return LPAREN;
            case ')':
                return RPAREN;
            case '\'':
                return QUOTE_SYMBOL;
            default:
                lastToken = new BadToken(String.valueOf(ttype));
                return YYUNDEF;
        }
    }
}
