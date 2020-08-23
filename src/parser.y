%language "Java"

%define api.parser.class {Parser}
%define api.value.type {lexems.IElement}
%define api.parser.public
%define api.push-pull push

%define parse.error custom
%define parse.trace

%locations

%locations

%code imports {
import java.io.IOException;
}

%code {
    private static lexems.ElementsList ast;
    public static lexems.IElement makeAST() throws IOException {
        ast = new lexems.ElementsList();
        MyLexer l = new MyLexer(System.in);
        Parser p = new Parser(l);
        int status;
        do {
            int token = l.getToken();
            lexems.IElement lval = l.getValue();
            Parser.Location yyloc = l.getLocation();
            status = p.push_parse(token, lval, yyloc);
        } while (status == Parser.YYPUSH_MORE);
        if (status != Parser.YYACCEPT) {
            return null;
        }
        return ast;
    }
}

%token <lexems.IntegerLiteral> INTEGER
%token <lexems.RealLiteral>    REAL
%token <lexems.BooleanLiteral> BOOLEAN
%token <lexems.Identifier>     IDENTIFIER

%type <lexems.ElementsList> program
%type <lexems.IElement>     element
%type <lexems.ElementsList> list
%type <lexems.ElementsList> list_elements
%type <lexems.IElement>     literal

%%
program:
  %empty { }
| list_elements { ast = $1; }
;

element:
  IDENTIFIER
| literal
| list
| '\'' element { $$ = new lexems.ElementsList(new lexems.Identifier("quote"), $2); }
| error ')' { return YYERROR; }
;

literal:
  INTEGER
| REAL
| BOOLEAN
;

single_separator: ' ' | '\n' | '\t';
separator: single_separator | single_separator separator;
optional_separator: %empty | separator;

list:
  '(' optional_separator ')'                                  { $$ = new lexems.ElementsList(); }
| '(' optional_separator list_elements optional_separator ')' { $$ = $3; }
;

list_elements:
   element                        { $$ = new lexems.ElementsList($1); }
| list_elements separator element { $1.add($3); $$ = $1; }
;
%%
