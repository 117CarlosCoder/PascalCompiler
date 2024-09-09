package com.compiladores.AnalizadorLexico;

import com.compiladores.AnalizadorSintactico.sym;
import java_cup.runtime.*;
import java.util.LinkedList;
import com.compiladores.Excepciones.Errores;

%%

%{
    public LinkedList<Errores> listaErrores = new LinkedList<>();

    StringBuffer string = new StringBuffer();

    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

%init{
    yyline = 1;
    yycolumn = 0;
    listaErrores = new LinkedList<>();
%init}


%class Lexico
%public
%char
%cup
%unicode
%caseless
%line
%column
//%debug
%ignorecase

TerminadorLinea = \r|\n|\r\n
EntradaCaracter = [^\r\n]
EspacioBlanco = {TerminadorLinea} | [ \t\f]
ComentarioMultiple   = "(*" [^*] ~"*)" | "(*" "*"+ ")"
ComentarioLinea     = "{" {EntradaCaracter}* {TerminadorLinea}? "}"
ContenidoComentario       = ( [^*] | \*+ [^/*] )*
Comentario = {ComentarioMultiple} | {ComentarioLinea}

Entero = [0-9]+
Decimal = [0-9]+[.][0-9]+
Id = [a-zA-Z_][a-zA-Z0-9_-]*

%state STRING
%state CHAR

%%

<YYINITIAL> "var" { return new Symbol(sym.VAR, yyline,yycolumn, yytext()); }
<YYINITIAL> "const" { return new Symbol(sym.CONST, yyline,yycolumn, yytext()); }
<YYINITIAL> "true" { return new Symbol(sym.TRUE, yyline,yycolumn, yytext()); }
<YYINITIAL> "false" { return new Symbol(sym.FALSE, yyline,yycolumn, yytext()); }
<YYINITIAL> "integer" { return new Symbol(sym.INT, yyline,yycolumn, yytext()); }
<YYINITIAL> "real" { return new Symbol(sym.REAL, yyline,yycolumn, yytext()); }
<YYINITIAL> "boolean" { return new Symbol(sym.BOOL, yyline,yycolumn, yytext()); }
<YYINITIAL> "char" { return new Symbol(sym.CHAR, yyline,yycolumn, yytext()); }
<YYINITIAL> "string" { return new Symbol(sym.CADENA, yyline,yycolumn, yytext()); }
<YYINITIAL> "type" { return new Symbol(sym.TYPE, yyline,yycolumn, yytext()); }
<YYINITIAL> "of" { return new Symbol(sym.OF, yyline,yycolumn, yytext()); }
<YYINITIAL> "if" { return new Symbol(sym.IF, yyline,yycolumn, yytext()); }
<YYINITIAL> "case" { return new Symbol(sym.CASE, yyline,yycolumn, yytext()); }
<YYINITIAL> "while" { return new Symbol(sym.WHILE, yyline,yycolumn, yytext()); }
<YYINITIAL> "for" { return new Symbol(sym.FOR, yyline,yycolumn, yytext()); }
<YYINITIAL> "do" { return new Symbol(sym.DO, yyline,yycolumn, yytext()); }
<YYINITIAL> "else" { return new Symbol(sym.ELSE, yyline,yycolumn, yytext()); }
<YYINITIAL> "break" { return new Symbol(sym.BREAK, yyline,yycolumn, yytext()); }
<YYINITIAL> "continue" { return new Symbol(sym.CONTINUE, yyline,yycolumn, yytext()); }
<YYINITIAL> "return" { return new Symbol(sym.RETURN, yyline,yycolumn, yytext()); }
<YYINITIAL> "writeln" { return new Symbol(sym.IMPRIMIR, yyline,yycolumn, yytext()); }
<YYINITIAL> "readln" { return new Symbol(sym.LEER, yyline,yycolumn, yytext()); }
<YYINITIAL> "begin" { return new Symbol(sym.BEGIN, yyline,yycolumn, yytext()); }
<YYINITIAL> "end" { return new Symbol(sym.END, yyline,yycolumn, yytext()); }
<YYINITIAL> "packed" { return new Symbol(sym.PACKED, yyline,yycolumn, yytext()); }
<YYINITIAL> "program" { return new Symbol(sym.PROGRAM, yyline,yycolumn, yytext()); }
<YYINITIAL> "record" { return new Symbol(sym.RECORD, yyline,yycolumn, yytext()); }
<YYINITIAL> "array" { return new Symbol(sym.ARRAY, yyline,yycolumn, yytext()); }
<YYINITIAL> "then" { return new Symbol(sym.THEN, yyline,yycolumn, yytext()); }
<YYINITIAL> "to" { return new Symbol(sym.TO, yyline,yycolumn, yytext()); }
<YYINITIAL> "repeat" { return new Symbol(sym.REPEAT, yyline,yycolumn, yytext()); }
<YYINITIAL> "until" { return new Symbol(sym.UNTIL, yyline,yycolumn, yytext()); }

<YYINITIAL>{

"+" { return new Symbol(sym.SUMA, yyline,yycolumn, yytext()); }
"-" { return new Symbol(sym.RESTA, yyline,yycolumn, yytext()); }
"_" { return new Symbol(sym.DEFAULT, yyline,yycolumn, yytext()); }
"div" { return new Symbol(sym.DIV, yyline,yycolumn, yytext()); }
"*" { return new Symbol(sym.MULTI, yyline,yycolumn, yytext()); }
"**" { return new Symbol(sym.POTENCIA, yyline,yycolumn, yytext()); }
"mod" { return new Symbol(sym.MOD, yyline,yycolumn, yytext()); }
"=" { return new Symbol(sym.IGUAL, yyline,yycolumn, yytext()); }
"==" { return new Symbol(sym.IGUALIGUAL, yyline,yycolumn, yytext()); }
"<>" { return new Symbol(sym.DIFIGUAL, yyline,yycolumn, yytext()); }
"<" { return new Symbol(sym.MENORQ, yyline,yycolumn, yytext()); }
"<=" { return new Symbol(sym.MENORIGQ, yyline,yycolumn, yytext()); }
">" { return new Symbol(sym.MAYORQ, yyline,yycolumn, yytext()); }
">=" { return new Symbol(sym.MAYORIGQ, yyline,yycolumn, yytext()); }
"||" { return new Symbol(sym.OR, yyline,yycolumn, yytext()); }
"&&" { return new Symbol(sym.AND, yyline,yycolumn, yytext()); }
"^" { return new Symbol(sym.XOR, yyline,yycolumn, yytext()); }
"!" { return new Symbol(sym.NOT, yyline,yycolumn, yytext()); }
"++" { return new Symbol(sym.INCRE, yyline,yycolumn, yytext()); }
"--" { return new Symbol(sym.DECRE, yyline,yycolumn, yytext()); }
";" { return new Symbol(sym.CIERRE, yyline,yycolumn, yytext()); }
":" { return new Symbol(sym.DOBP, yyline,yycolumn, yytext()); }
"," { return new Symbol(sym.COMA, yyline,yycolumn, yytext()); }
"." { return new Symbol(sym.PUNTO, yyline,yycolumn, yytext()); }
".." { return new Symbol(sym.RANGO, yyline,yycolumn, yytext()); }
"..." { return new Symbol(sym.SUBRANGO, yyline,yycolumn, yytext()); }
"(" { return new Symbol(sym.PARA, yyline,yycolumn, yytext());}
")" { return new Symbol(sym.PARC, yyline,yycolumn, yytext());}
"{" { return new Symbol(sym.LLAVEA, yyline,yycolumn, yytext());}
"}" { return new Symbol(sym.LLAVEC, yyline,yycolumn, yytext());}
"[" { return new Symbol(sym.CORA, yyline,yycolumn, yytext());}
"]" { return new Symbol(sym.CORC, yyline,yycolumn, yytext());}


{Entero} { return new Symbol(sym.ENTERO, yyline,yycolumn, yytext()); }
{Decimal} { return new Symbol(sym.DECIMAL, yyline,yycolumn, yytext()); }
{Id} { return new Symbol(sym.ID, yyline,yycolumn, yytext()); }



\" {string.setLength(0); yybegin(STRING);}
\'  {string.setLength(0); yybegin(CHAR); }



{Comentario}  {}
{EspacioBlanco} {}



}


<STRING> {
  \"        { yybegin(YYINITIAL); return new Symbol(sym.STRING_LITERAL, string.toString()); }
  [^\r\n\"\\]+   { string.append( yytext() ); }
  \n          { string.append(yytext()); }

  \\t          { string.append("\t"); }
  \\n          { string.append("\n"); }
  \\r          { string.append('\r'); }
  \\\"         { string.append("\""); }
  \\\'         { string.append("\'"); }
  "\\\\"          { string.append("\\"); }
}

<CHAR> {
  \'       { yybegin(YYINITIAL); return new Symbol(sym.CHAR_LITERAL, string.toString()); }
  [^\r\n\'\\]+   { string.append( yytext() ); }
  \n          { string.append(yytext()); }

  \\t          { string.append("\t"); }
  \\n          { string.append("\n"); }
  \\r          { string.append('\r'); }
  \\\"         { string.append("\""); }
  \\\'         { string.append("\'"); }
  "\\\\"           { string.append("\\"); }
}


<YYINITIAL> .  {

    listaErrores.add(new Errores("LEXICO","El caracter "+ yytext()+" NO pertenece al lenguaje", yyline, yycolumn));

    System.err.println("Error léxico: Carácter no válido '" + yytext() + "' en la línea " + yyline + ", columna " + yycolumn);
}
