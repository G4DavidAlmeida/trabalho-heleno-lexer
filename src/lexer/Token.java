package lexer;

public class Token {
    private String lexema;
    private Tipo tipo;

    public Token (String lexema, Tipo tipo) {
       this.setLexema(lexema);
       this.setTipo(tipo); 
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }
}
