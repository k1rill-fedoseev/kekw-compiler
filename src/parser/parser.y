%language "Java"

%define api.package parser
%define api.parser.class {Parser}
%define api.value.type {IElement}
%define api.parser.public
%define api.push-pull push

%define parse.error custom
%define parse.trace

%locations

%code imports {
import java.io.IOException;
import lexems.*;
}

%code {
    private static ElementsList ast;
    public static ElementsList makeAST() throws IOException {
        ast = new ElementsList();
        MyLexer l = new MyLexer(System.in);
        Parser p = new parser.Parser(l);
        int status;
        do {
            int token = l.getToken();
            IElement lval = l.getValue();
            Parser.Location yyloc = l.getLocation();
            status = p.push_parse(token, lval, yyloc);
        } while (status == Parser.YYPUSH_MORE);
        if (status != Parser.YYACCEPT) {
            return null;
        }
        return ast;
    }
}

%token <IntegerLiteral> INTEGER
%token <RealLiteral>    REAL
%token <BooleanLiteral> BOOLEAN
%token <Identifier>     IDENTIFIER

%type <ElementsList> program
%type <IElement>     element
%type <ElementsList> list
%type <ElementsList> list_elements
%type <IElement>     literal

%%
program:
  optional_separator { }
| optional_separator list_elements optional_separator { ast = $2; }
;

element:
  IDENTIFIER
| literal
| list
| '\'' element { $$ = new ElementsList(new Identifier("quote"), $2); }
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
  '(' optional_separator ')'                                  { $$ = new ElementsList(); }
| '(' optional_separator list_elements optional_separator ')' { $$ = $3; }
;

list_elements:
  element                         { $$ = new ElementsList($1); }
| list_elements separator element { $1.add($3); $$ = $1; }
;
%%
