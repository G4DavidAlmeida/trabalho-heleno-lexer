package window.components;

import java.awt.event.*;
import javax.swing.*;
import java.io.*;


public class OpenFileButton extends JButton implements ActionListener {
    private JTextArea textArea;

    public OpenFileButton (JTextArea ta) {
        super("Selecione o arquivo");
        this.textArea = ta;
        this.setBounds(0, 0, 200, 40);
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser(".");
        int i = fc.showOpenDialog(this);

        if (i == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            String filepath = f.getPath();

            try {
                BufferedReader br = new BufferedReader(new FileReader(filepath));
                String s1 = "", s2 = "";
                while ((s1 = br.readLine()) != null) {
                    s2 += s1 + "\n";
                }
                textArea.setText(s2);
                br.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
