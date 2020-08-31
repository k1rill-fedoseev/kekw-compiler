%language "Java"

%define api.package parser
%define api.parser.class {Parser}
%define api.value.type {Object}
%define api.parser.public
%define api.push-pull push

%define parse.error custom
%define parse.trace

%locations

%code imports {
import java.io.IOException;
import java.util.LinkedList;
import lexems.*;
}

%code {
    private static ElementsList ast;
    public static ElementsList makeAST() throws IOException {
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
%token <StringLiteral>  STRING
%token <Atom>           ATOM

%token LPAREN       "("
%token RPAREN       ")"
%token QUOTE_SYMBOL "'"

%token <Quote>          QUOTE
%token <Setq>           SETQ
%token <Func>           FUNC
%token <Lambda>         LAMBDA
%token <Prog>           PROG
%token <Cond>           COND
%token <While>          WHILE
%token <Return>         RETURN
%token <Break>          BREAK

%type <ElementsList>     program
%type <IElement>         element
%type <Atom>             identifier
%type <IElement>         literal
%type <IElement>         list
%type <ElementsList>     list_elements
%type <IElement>         special_form
%type <LinkedList<Atom>> list_of_atoms
%type <LinkedList<Atom>> atoms_sequence


%%
program:
  %empty          { ast = new ElementsList();}
| program element { ast.add($2); }
;

element:
  identifier  { $$ = $1; }
| literal     { $$ = $1; }
| list        { $$ = $1; }
| "'" element { $$ = new Quote($2); }
;

identifier: ATOM;
literal:
  INTEGER { $$ = $1; }
| REAL    { $$ = $1; }
| BOOLEAN { $$ = $1; }
| STRING  { $$ = $1; }
;

list:
  "(" list_elements ")" { $$ = $2; }
| "(" special_form ")"  { $$ = $2; }
;

list_elements:
  %empty                { $$ = new ElementsList(); }
| list_elements element { $$ = $1; $1.add($2); }
;

special_form:
  QUOTE element                         { $$ = new Quote($2); }
| SETQ identifier element               { $$ = new Setq($2, $3); }
| FUNC identifier list_of_atoms element { $$ = new Func($2, $3, $4); }
| LAMBDA list_of_atoms element          { $$ = new Lambda($2, $3); }
| PROG list_of_atoms element            { $$ = new Prog($2, $3); }
| COND element element                  { $$ = new Cond($2, $3); }
| COND element element element          { $$ = new Cond($2, $3, $4); }
| WHILE element element                 { $$ = new While($2, $3); }
| RETURN element                        { $$ = new Return($2); }
| BREAK                                 { $$ = new Break(); }
;

list_of_atoms: "(" atoms_sequence ")" { $$ = $2; };

atoms_sequence:
  %empty                    { $$ = new LinkedList<Atom>(); }
| atoms_sequence identifier { $$ = $1; $1.add($2); }
;
%%
