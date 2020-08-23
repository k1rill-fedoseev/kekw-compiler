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
%token <Atom>           ATOM

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
%type <ElementsList>     commands
%type <IElement>         element
%type <Atom>             identifier
%type <IElement>         literal
%type <ElementsList>     list
%type <ElementsList>     list_elements
%type <IElement>         special_form
%type <LinkedList<Atom>> list_of_atoms
%type <LinkedList<Atom>> atoms_sequence


%%
program:
  optional_separator commands optional_separator { ast = $2; }
;

commands:
  element                    { $$ = new ElementsList($1); }
| commands separator element { $$ = $1; $1.add($3); }
;

element:
  identifier
| literal
| list
| '\'' element { $$ = new Quote($2); }
;

identifier: ATOM;

literal: INTEGER | REAL | BOOLEAN;

single_separator: ' ' | '\n' | '\t';
separator: single_separator | single_separator separator;
optional_separator: %empty | separator;

list:
  '(' optional_separator ')'                                  { $$ = new ElementsList(); }
| '(' optional_separator list_elements optional_separator ')' { $$ = $3; }
| '(' optional_separator special_form ')'                     { $$ = $3; }
;

list_elements:
  element                         { $$ = new ElementsList($1); }
| list_elements separator element { $1.add($3); $$ = $1; }
;

special_form:
  QUOTE separator element optional_separator                                             { $$ = new Quote($3); }
| SETQ separator identifier separator element optional_separator                         { $$ = new Setq($3, $5); }
| FUNC separator identifier separator list_of_atoms separator element optional_separator { $$ = new Func($3, $5, $7); }
| LAMBDA separator list_of_atoms separator element optional_separator                    { $$ = new Lambda($3, $5); }
| PROG separator list_of_atoms separator element optional_separator                      { $$ = new Prog($3, $5); }
| COND separator element separator element optional_separator                            { $$ = new Cond($3, $5); }
| COND separator element separator element separator element optional_separator          { $$ = new Cond($3, $5, $7); }
| WHILE separator element separator element optional_separator                           { $$ = new While($3, $5); }
| RETURN separator element optional_separator                                            { $$ = new Return($3); }
| BREAK optional_separator                                                               { $$ = new Break(); }
;

list_of_atoms:
  '(' optional_separator ')'                                   { $$ = new LinkedList<Atom>(); }
| '(' optional_separator atoms_sequence optional_separator ')' { $$ = $3; }
;

atoms_sequence:
  identifier                          { LinkedList<Atom> list = new LinkedList<Atom>(); list.add($1); $$ = list; }
| atoms_sequence separator identifier { $$ = $1; $1.add($3); }
;


%%
