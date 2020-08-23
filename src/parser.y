%language "Java"

%define api.parser.class {Parser}
%define api.value.type {lexems.IElement}
%define api.parser.public

%define parse.error verbose

%code imports {
import java.io.IOException;
}

%code {
    private static lexems.ElementsList ast;
    public static lexems.IElement makeAST() throws IOException {
        ast = new lexems.ElementsList();
        Lexer l = new MyLexer(System.in);
        Parser p = new Parser(l);
        if (!p.parse()) {
            System.exit(1);
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
| program element { ast.add($2); }
;

element:
  IDENTIFIER
| literal
| list
| '\'' element { $$ = new lexems.ElementsList(new lexems.Identifier("quote"), $2); }
;

literal:
  INTEGER
| REAL
| BOOLEAN
;

list:
  '(' list_elements ')' { $$ = $2; }
list_elements:
  %empty                { $$ = new lexems.ElementsList(); }
| list_elements element { $1.add($2); $$ = $1; }
;
%%
