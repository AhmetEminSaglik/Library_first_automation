/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Ahmet Emin SAĞLIK
 */
public class StudentUpdateGui {

    JFrame jf;
    JPanel jp;

    public StudentUpdateGui(MainGui mg) {
        setJf(mg.getJf());
        mg.getJp().setVisible(false);
        getJf().add(getJp());
    }

    public JFrame getJf() {
        return jf;
    }

    public void setJf(JFrame jf) {
        this.jf = jf;
    }

    public JPanel getJp() {
        if (jp == null) {
            jp = new JPanel();
            jp.setBounds(getJf().getBounds());
            jp.setBackground(new Color(15, 150, 250));
        }
        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

}
