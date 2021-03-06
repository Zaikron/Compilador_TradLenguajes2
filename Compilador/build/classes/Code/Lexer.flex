
package Code;
import compilerTools.Token;

%%
%class Lexer
%type Token
%line
%column
%{
    private Token token(String lexeme, String lexicalComp, int line, int column){
        return new Token(lexeme, lexicalComp, line+1, column+1);
    }
%}
/* Variables básicas de comentarios y espacios */
TerminadorDeLinea = \r|\n|\r\n
EntradaDeCaracter = [^\r\n]
EspacioEnBlanco = {TerminadorDeLinea} | [ \t\f]
ComentarioTradicional = "/*" [^*] ~"*/" | "/*" "*"+ "/"
FinDeLineaComentario = "//" {EntradaDeCaracter}* {TerminadorDeLinea}?
ContenidoComentario = ( [^*] | \*+ [^/*] )*
ComentarioDeDocumentacion = "/**" {ContenidoComentario} "*"+ "/"

/* Comentario */
Comentario = {ComentarioTradicional} | {FinDeLineaComentario} | {ComentarioDeDocumentacion}

/* Identificador */
Letra = [A-Za-zÑñ_ÁÉÍÓÚáéíóúÜü]
Digito = [0-9]
Identificador = {Letra}({Letra}|{Digito})*

/* Número */
Numero = 0 | [1-9][0-9]*
%%

{Comentario}|{EspacioEnBlanco} { /*Ignorar*/ }

        /* ASM */
    /* Palabra reservada ASM */
    ( asm ) {return token(yytext(), "ASM", yyline, yycolumn);}

    /* Registros 16 bits*/
    ( "ax" | "bx" | "cx" | "dx" ) {return token(yytext(), "REG_16", yyline, yycolumn);}

    /* Registros 8 bits*/
    ( "ah" | "al" | "bh" | "bl" | "ch" | "cl" | "dh" | "dl" ) {return token(yytext(), "REG_8", yyline, yycolumn);}

    /* Palabra reservada MOV */
    ( mov ) {return token(yytext(), "MOV", yyline, yycolumn);}

    /* Palabra reservada ADD */
    ( add ) {return token(yytext(), "ADD", yyline, yycolumn);}

    /* Palabra reservada SUB */
    ( sub ) {return token(yytext(), "SUB", yyline, yycolumn);}

    /* Palabra reservada MUL */
    ( mul ) {return token(yytext(), "MUL", yyline, yycolumn);}

    /* Palabra reservada DIV */
    ( div ) {return token(yytext(), "DIV", yyline, yycolumn);}

    /* Palabra reservada potencia */
    ( pow ) {return token(yytext(), "POW", yyline, yycolumn);}

    /* Palabra reservada seno */
    ( sen ) {return token(yytext(), "SEN", yyline, yycolumn);}

    /* Palabra reservada coseno */
    ( cos ) {return token(yytext(), "COS", yyline, yycolumn);}

    /* Palabra reservada tangente */
    ( tan ) {return token(yytext(), "TAN", yyline, yycolumn);}


/* Tipo de dato int */
( int ) {return token(yytext(), "INT", yyline, yycolumn);}

/* Tipo de dato int */
( float ) {return token(yytext(), "FLOAT", yyline, yycolumn);}

/* Tipo de dato String */
( char ) {return token(yytext(), "CHAR", yyline, yycolumn);}

/* Tipo de dato String */
( string ) {return token(yytext(), "CADENA", yyline, yycolumn);}

/* Comillas */
\" {return token(yytext(), "COMILLAS", yyline, yycolumn);}

/* Comillas Simples */
\' {return token(yytext(), "C_SIMPLE", yyline, yycolumn);}

/* Palabra reservada print */
( print ) {return token(yytext(), "PRINT", yyline, yycolumn);}

/* Palabra reservada If */
( if ) {return token(yytext(), "IF", yyline, yycolumn);}

/* Palabra reservada Else */
( else ) {return token(yytext(), "ELSE", yyline, yycolumn);}

/* Palabra reservada Do */
( do ) {return token(yytext(), "DO", yyline, yycolumn);}

/* Palabra reservada While */
( while ) {return token(yytext(), "WHILE", yyline, yycolumn);}

/* Palabra reservada For */
( for ) {return token(yytext(), "FOR", yyline, yycolumn);}

/* Palabra reservada Return */
( return ) {return token(yytext(), "RETURN", yyline, yycolumn);}

/* Palabra reservada import */
( import ) {return token(yytext(), "IMPORT", yyline, yycolumn);}

/* Palabra reservada Void */
( void ) {return token(yytext(), "VOID", yyline, yycolumn);}

/* Coma */
( "," ) {return token(yytext(), "COMA", yyline, yycolumn);}

/* Operador Igual */
( "=" ) {return token(yytext(), "ASIGNACION", yyline, yycolumn);}

/* Operador Suma */
/*( "+" ) {return token(yytext(), "SUMA", yyline, yycolumn);}

/* Operador Resta */
( "-" ) {return token(yytext(), "RESTA", yyline, yycolumn);}

/* Operador Multiplicacion */
( "*" ) {return token(yytext(), "MULTIPLICACION", yyline, yycolumn);}

/* Operador Division */
( "/" ) {return token(yytext(), "DIVISION", yyline, yycolumn);}*/

/* Operadores Aritmeticos*/
( "+" | "-" | "*" | "/" ) {return token(yytext(), "OP_ARIT", yyline, yycolumn);}

/* Operadores logicos */
( "&&" | "||" | "&" | "|" ) {return token(yytext(), "OP_LOGICO", yyline, yycolumn);}

/* Operadores negacion */
( "!" ) {return token(yytext(), "NEG", yyline, yycolumn);}

/*Operadores Relacionales */
( ">" | "<" | "==" | "!=" | ">=" | "<=" | "<<" | ">>" ) {return token(yytext(), "OP_RELACIONAL", yyline, yycolumn);}

/* Operadores Atribucion */
( "+=" | "-="  | "*=" | "/=" | "%=" ) {return token(yytext(), "OP_ATRIBUCION", yyline, yycolumn);}

/* Operadores Incremento y decremento */
( "++" | "--" ) {return token(yytext(), "OP_INCREMENTO", yyline, yycolumn);}

/*Operadores Booleanos*/
(true | false)      {return token(yytext(), "OP_BOOL", yyline, yycolumn);}

/* Parentesis de apertura */
( "(" ) {return token(yytext(), "PARENTESIS_A", yyline, yycolumn);}

/* Parentesis de cierre */
( ")" ) {return token(yytext(), "PARENTESIS_C", yyline, yycolumn);}

/* Llave de apertura */
( "{" ) {return token(yytext(), "LLAVE_A", yyline, yycolumn);}

/* Llave de cierre */
( "}" ) {return token(yytext(), "LLAVE_C", yyline, yycolumn);}

/* Corchete de apertura */
( "[" ) {return token(yytext(), "CORCHETE_A", yyline, yycolumn);}

/* Corchete de cierre */
( "]" ) {return token(yytext(), "CORCHETE_C", yyline, yycolumn);}

/* Marcador de inicio de algoritmo */
( "main" ) {return token(yytext(), "MAIN", yyline, yycolumn);}

/* Punto y coma */
( ";" ) {return token(yytext(), "PUNTO_COMA", yyline, yycolumn);}

/* Simbolo para funciones */
\# {return token(yytext(), "GATO", yyline, yycolumn);}

/* Simbolo para variable en sentencia */
\$ {return token(yytext(), "SIMB_SENT", yyline, yycolumn);}

/* Identificador */ 
{Identificador} {return token(yytext(), "IDENTIFICADOR", yyline, yycolumn);}

/* Numero */
{Numero} {return token(yytext(), "NUMERO", yyline, yycolumn);}

/* Error de analisis */
. { return token(yytext(), "ERROR", yyline, yycolumn); }

