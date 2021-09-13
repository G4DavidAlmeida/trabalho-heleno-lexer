package window.components;

import lexer.MyLexer;
import window.MainWindow;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class ExecButton extends JButton implements ActionListener {
    private JTextArea code;
    private MainWindow window;

    public ExecButton (JTextArea code, MainWindow window) {
        super("Analisar");
        this.window = window;
        this.code = code;
        this.addActionListener(this);
        this.setBounds(200, 0, 200, 40);
    }

    public void actionPerformed(ActionEvent e) {
        String code_text = this.code.getText();
        StringReader str = new StringReader(code_text);
        String[][] tokens = MyLexer.analyze(str).toArray(String[][]::new);
        String[] columns = new String[] {"lexema", "token"};
        this.window.changeTable(tokens, columns);
        
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i][0] == "Erro léxico") {
                this.window.changeLabelError(tokens[i][1]);
                break;
            }

            if (tokens[i][0] == "Erro léxico" && i - 1 == tokens.length) {
                this.window.changeLabelError(null);
            }
        }
    }
}
