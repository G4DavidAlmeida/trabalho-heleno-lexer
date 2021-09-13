package window;

import javax.swing.*;

import window.components.ExecButton;
import window.components.OpenFileButton;


public class MainWindow extends JFrame {
    private JScrollPane labelErrorBox;
    private JScrollPane tableBox;
    private JTextArea ta;
    private OpenFileButton openFileButton;
    private ExecButton execButton;

    public MainWindow () {
        // configurando caixa de texto
        this.ta = new JTextArea(800, 800);
        JScrollPane taBox = new JScrollPane(this.ta);
        taBox.setBounds(0, 40, 400, 400);

        // criando botões de ação
        this.openFileButton = new OpenFileButton(this.ta);
        this.execButton = new ExecButton(this.ta, this);

        JLabel resultsLabel = new JLabel("Resultados");
        resultsLabel.setBounds(580, 0, 400, 40);

        // adicionando eles ao frame
        this.add(this.openFileButton);
        this.add(this.execButton);
        this.add(resultsLabel);
        this.add(taBox);

        // ao fechar janela, encerrar programa
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void changeTable (String[][] data, String[] columns) {
        if (this.tableBox != null)
            this.remove(this.tableBox);

        JTable table = new JTable(data, columns);
        JScrollPane tableScroll = new JScrollPane(table);

        // set possition
        tableScroll.setBounds(450, 40, 350, 350);

        this.add(tableScroll);
        this.tableBox = tableScroll;
    }

    public void changeLabelError (String text) {
        if (this.labelErrorBox != null)
            this.remove(this.labelErrorBox);

        if (text != null) {
            JLabel label = new JLabel(text);
            JScrollPane erroScroll = new JScrollPane(label);
            label.setBounds(450, 400, 350, 50);
            erroScroll.setBounds(459, 399, 352, 52);

            this.add(erroScroll);
            this.labelErrorBox = erroScroll;
        } else {
            if (this.labelErrorBox != null)
                this.remove(this.labelErrorBox);
        }
    }
}
