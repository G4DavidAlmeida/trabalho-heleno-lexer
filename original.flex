package lexer;
%%
%class AnalisadorLexico
%type Token
%line
%column
EspacoOuNovaLinha = " "|\n|\r\n|\t
PalavraReservada = "int"|"float"|"if"|"while"|"for"|"boolean"|"string"|"char"|"main"|"return"
Digito = \d
Digitos = [-+]?{Digito}*\.?{Digito}+([eE][-+]?{Digito}+)?
Operador = "+" | "-" | "*" | "/" | "%" | "=" | "==" | ">" | "<" | "!=" | ">=" | "<="
Caractere = [a-zA-Z_]
Identificador = {Caractere}[{Caractere}{Digito}]*
Simbolo = "(" | ")" | "[" | "]" | "{" | "}" | ";" | ","
Invalido = {Digitos}{Caractere}+
QualquerCaractereExcetoNovaLinha = [^\r\t\r]
ComentarioFimDeLinha = "//" {QualquerCaractereExcetoNovaLinha}*
ComentarioVariasLInhas = "/*"[^*]*"*/"
Comentario = {ComentarioVariasLInhas}|{ComentarioFimDeLinha}
Texto = \".*\"
%%
<YYINITIAL> {Texto} {return new Token(yytext(), Tipo.STRING);}
<YYINITIAL> {Comentario} {/* não fazer nada */}
<YYINITIAL> {Invalido} {throw new Error("Identificador invalido: \"" + yytext() + "\"");}
<YYINITIAL> {PalavraReservada} {return new Token(yytext(), Tipo.PALAVRA_RESERVADA);}
<YYINITIAL> {Simbolo} {return new Token(yytext(),Tipo.SIMBOLO);}
<YYINITIAL> {Identificador} {return new Token(yytext(),Tipo.IDENTIFICADOR);}
<YYINITIAL> {Operador} {return new Token(yytext(),Tipo.OPERADOR);}
<YYINITIAL> {Digitos} {return new Token(yytext(),Tipo.CONSTANTE);}
<YYINITIAL> {EspacoOuNovaLinha} {/*não fazer nada*/}
<YYINITIAL> [^] {throw new Error("IllegalExpression \"" + yytext() + "\"" + "\nLinha: " + yyline + " Coluna: " + yycolumn );}