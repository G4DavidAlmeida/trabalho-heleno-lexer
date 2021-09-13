package lexer;

import java.io.*;
import java.util.ArrayList;

public class MyLexer {
    public static ArrayList<String[]> analyze (Reader in) {
        AnalisadorLexico lexer = new AnalisadorLexico(in);
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] tmp;
        Token t;

        try {
            do {
                t = lexer.yylex();
    
                if (t != null) {
                    tmp = new String[2];
                    tmp[0] = t.getLexema();
                    tmp[1] = t.getTipo().toString();
                    list.add(tmp);
                }

            } while(t != null);
        } catch (java.io.IOException e) {
            tmp = new String[2];
            tmp[0] = "Erro de I/O.";
            tmp[1] = e.getMessage();
            list.add(tmp);
        } catch (java.lang.Error e) {
            tmp = new String[2];
            tmp[0] = "Erro léxico";
            tmp[1] = e.getMessage();
            list.add(tmp);
        }

        return list;
    }
    public static void main(String[] args){
        try {
            FileReader file = new FileReader("script.js");
            BufferedReader br = new BufferedReader(file);
            AnalisadorLexico lexer = new AnalisadorLexico(br);

            Token t;

            do {
                t = lexer.yylex();

                if (t != null) {
                    System.out.println("TOKEN: <" + t.getLexema() + "," + t.getTipo() + ">");
                }
            } while(t != null);

            System.out.println("Análise léxica terminada");

        } catch (java.io.FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.\n\n" + e.getMessage());
        } catch (java.io.IOException e) {
            System.out.println("Erro de I/O.\n\n"  + e.getMessage()); 
        }
    }
}