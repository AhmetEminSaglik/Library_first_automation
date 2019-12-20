/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Ahmet Emin SAĞLIK
 */
public class BookSearch_ListGui {

    JFrame jf;
    JPanel jp;
    JLabel lblBookName;
    JLabel lblAuthorName;
    JLabel lblBarcodeNo;
    JTable table;

    JTextField txtBookName;
    JTextField txtAuthorName;
    JTextField txtBarcodeNo;
    JButton btnSearch;
    JButton btnComeBack;

    final int lblTopSpace = 10;
    final int leftSpace = 20;
    final int txtWidth = 250;
    final int txtHeight = 30;
    final int lblWidth = 150;
    final int lblHeight = 15;
    final int pushRightSpace = txtWidth + 50;
    final int txtTopSpace = lblTopSpace + lblHeight + 10;
    int pushRightCounter = 0;

    Font font_lbl = new Font("monospaced", Font.BOLD, 15);
    Font font_txt = new Font("monospaced", Font.BOLD, 15);

    public BookSearch_ListGui(MainGui mg) {
        setJf(mg.getJf());
        mg.getJp().setVisible(false);
        getJf().add(getJp());
        getJp().add(getLblBookName());
        getJp().add(getLblAuthorName());
        getJp().add(getLblBarcodeNo());
        getJp().add(getTxtBookName());
        getJp().add(getTxtAuthorName());
        getJp().add(getTxtBarcodeNo());
        /*  JFrame jf;
    JPanel jp;
    JLabel lblBookName;
    JLabel lblAuthorName;
    JLabel lblBarcodeNo;
    JTable table;

    JTextField txtBookName;
    JTextField txtAuthorName;
    JTextField txtBarcodeNo;
    JButton btnSearch;
    JButton btnComeBack;
         */
 /*
          f = new JFrame();
        String data[][] = {{"101", "Amit", "670000"},
        {"102", "Jai", "780000"},
        {"101", "Sachin", "700000"}};
        String column[] = {"ID", "NAME", "SALARY"};

        JTable jt = new JTable(data, column);
        jt.setBounds(30, 40, 200, 300);
        // jt.setEnabled(false);
        //  jt.setFocusable(false);
        //jt.setCellSelectionEnabled(true);
        //  jt.setShowVerticalLines(true);
        JScrollPane sp = new JScrollPane(jt);
        jt.setDefaultEditor(Object.class, null);// jtable içindeki değerleri değiştirmeyi engeller

        f.add(sp);
        f.setSize(300, 400);
        f.setVisible(true);*/
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
            jp.setBackground(new Color(100, 0, 100));
            jp.setLayout(null);
        }

        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

    /*
    final int lblTopSpace = 10;
    final int leftSpace = 20;
    final int txtWidth = 125;
    final int txtHeight = 50;
    final int lblWidth = 100;
    final int lblHeight = 40;
    final int pushRightSpace = txtWidth + 30;
    final int txtYBegginnerPoint = lblTopSpace + lblHeight + 10;
    final int pushRightCounter = 0;
     */
    public JLabel getLblBookName() {
        if (lblBookName == null) {
            lblBookName = new JLabel("Kitap Adı");
            lblBookName.setBounds(leftSpace + (pushRightSpace * pushRightCounter), lblTopSpace, lblWidth, lblHeight);
            lblBookName.setFont(font_lbl);
            lblBookName.setForeground(Color.WHITE);
            pushRightCounter++;
        }
        return lblBookName;
    }

    public void setLblBookName(JLabel lblBookName) {
        this.lblBookName = lblBookName;
    }

    public JLabel getLblAuthorName() {
        if (lblAuthorName == null) {
            lblAuthorName = new JLabel("Yazar Adı");
            lblAuthorName.setBounds(leftSpace + (pushRightSpace * pushRightCounter), lblTopSpace, lblWidth, lblHeight);
            lblAuthorName.setFont(font_lbl);
            lblAuthorName.setForeground(Color.WHITE);
            pushRightCounter++;
        }
        return lblAuthorName;
    }

    public void setLblAuthorName(JLabel lblAuthorName) {
        this.lblAuthorName = lblAuthorName;
    }

    public JLabel getLblBarcodeNo() {
        if (lblBarcodeNo == null) {
            lblBarcodeNo = new JLabel("Barkod No");
            lblBarcodeNo.setBounds(leftSpace + (pushRightSpace * pushRightCounter), lblTopSpace, lblWidth, lblHeight);
            lblBarcodeNo.setFont(font_lbl);
            lblBarcodeNo.setForeground(Color.WHITE);
            pushRightCounter++;
        }
        return lblBarcodeNo;
    }

    public void setLblBarcodeNo(JLabel lblBarcodeNo) {
        this.lblBarcodeNo = lblBarcodeNo;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JTextField getTxtBookName() {
        if (txtBookName == null) {
            pushRightCounter = 0;
            txtBookName = new JTextField("Kitap Adı");
            txtBookName.setBounds(leftSpace + (pushRightSpace * pushRightCounter), txtTopSpace, txtWidth, txtHeight);
            txtBookName.setFont(font_txt);
            pushRightCounter++;
        }

        return txtBookName;
    }

    public void setTxtBookName(JTextField txtBookName) {
        this.txtBookName = txtBookName;
    }

    public JTextField getTxtAuthorName() {
        if (txtAuthorName == null) {
            txtAuthorName = new JTextField("Yazar Adı");
            txtAuthorName.setBounds(leftSpace + (pushRightSpace * pushRightCounter), txtTopSpace, txtWidth, txtHeight);
            txtAuthorName.setFont(font_txt);
            pushRightCounter++;
        }

        return txtAuthorName;
    }

    public void setTxtAuthorName(JTextField txtAuthorName) {
        this.txtAuthorName = txtAuthorName;
    }

    public JTextField getTxtBarcodeNo() {
        if (txtBarcodeNo == null) {
            txtBarcodeNo = new JTextField("Barkod No");
            txtBarcodeNo.setBounds(leftSpace + (pushRightSpace * pushRightCounter), txtTopSpace, txtWidth, txtHeight);
            txtBarcodeNo.setFont(font_txt);
            pushRightCounter++;
        }

        return txtBarcodeNo;
    }

    public void setTxtBarcodeNo(JTextField txtBarcodeNo) {
        this.txtBarcodeNo = txtBarcodeNo;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public void setBtnSearch(JButton btnSearch) {
        this.btnSearch = btnSearch;
    }

    public JButton getBtnComeBack() {
        return btnComeBack;
    }

    public void setBtnComeBack(JButton btnComeBack) {
        this.btnComeBack = btnComeBack;
    }

}
