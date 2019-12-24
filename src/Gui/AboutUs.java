package Gui;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AboutUs {

    JFrame jf;
    JPanel jp;

    public AboutUs(MainGui mg) {
        setJf(mg.getJf());

    }

    public void changeBackgroundColorOfPanel() {
        Thread t1 = new Thread() {
            public void run() {
                int firstColor = 0;
                int secondColor = 1;
                int thirdColor = 2;

                //getJp().getBackground().ge; //new Color(255, 255, 255)
            }
        };
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
            jp.setLayout(null);
        }
        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

}
