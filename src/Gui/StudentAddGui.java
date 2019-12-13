package Gui;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StudentAddGui {

    JFrame jf;
    JPanel jp;

    public StudentAddGui(MainGui mg) {
        setJf(mg.getJf());
        mg.getJp().setVisible(false);
        getJf().add(getJp());

    }

    public JFrame getJf() {
        if (jf == null) {
            jf = new JFrame();

        }
        return jf;
    }

    public void setJf(JFrame jf) {
        this.jf = jf;
    }

    public JPanel getJp() {
        if (jp == null) {
            jp = new JPanel();
            jp.setBounds(getJf().getBounds());
            jp.setBackground(new Color(11, 11, 11));
        }
        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

}
