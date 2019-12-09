package Gui;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Login extends JPanel {
//Apoya Ait  sanki başarılı bir şekilde giriş yapılmış gibi devam ediyorum

    JFrame jf = null;
    final int jframeX = 250;
    final int jframeY = 100;
    final int jframeWidth = 850;
    final int jframeHeight = 550;
    JLabel username = null;
    JLabel password = null;
    JButton signIn = null;

    public Login() {
        getJf().setVisible(true);

        this.setBackground(Color.BLUE);

        getJf().add(this);

        MainGui mg = new MainGui(this);
    }

    public JFrame getJf() {
        if (jf == null) {
            jf = new JFrame();
            jf.setBounds(jframeX, jframeY, jframeWidth, jframeHeight);
            //jf.setLayout(null);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        return jf;
    }

    public void setJf(JFrame jf) {
        this.jf = jf;
    }

}
