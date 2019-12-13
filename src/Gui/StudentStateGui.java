/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.sun.imageio.plugins.jpeg.JPEG;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Ahmet Emin SAĞLIK
 */
public class StudentStateGui {

    JFrame jf;
    JPanel jp;

    public StudentStateGui(MainGui mg) {
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
            jp.setBackground(new Color(250, 150, 0));
        }
        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

}
