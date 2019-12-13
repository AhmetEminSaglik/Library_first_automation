package Gui;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BookReturnGui {

    JFrame jf;
    JPanel jp;
    JLabel lblBarcodeNo;
    JLabel lblStudentNo;
    JLabel lblBookName;
    JLabel lblAuthorName;
    JLabel lblStudentName;
    JLabel lblResult;

    JTextField txtBarcodeNo;
    JTextField txtStudentNo;
    JTextField txtBookName;
    JTextField txtAuthorName;
    JTextField txtStudentName;
    JTextField txtResult;
    JButton btnComeBack;
    final int lblWidth=150;
    final int lblHeight=40;
    

    public BookReturnGui(MainGui mg) {
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
            jp.setBackground(new Color(152, 57, 65));
            jp.setBounds(getJf().getBounds());
        }
        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

    public JLabel getLblBarcodeNo() {
        if (lblBarcodeNo == null) {
            lblBarcodeNo = new JLabel();

        }
        return lblBarcodeNo;
    }

    public void setLblBarcodeNo(JLabel lblBarcodeNo) {
        this.lblBarcodeNo = lblBarcodeNo;
    }

    public JLabel getLblStudentNo() {
        return lblStudentNo;
    }

    public void setLblStudentNo(JLabel lblStudentNo) {
        this.lblStudentNo = lblStudentNo;
    }

    public JLabel getLblBookName() {
        return lblBookName;
    }

    public void setLblBookName(JLabel lblBookName) {
        this.lblBookName = lblBookName;
    }

    public JLabel getLblAuthorName() {
        return lblAuthorName;
    }

    public void setLblAuthorName(JLabel lblAuthorName) {
        this.lblAuthorName = lblAuthorName;
    }

    public JLabel getLblStudentName() {
        return lblStudentName;
    }

    public void setLblStudentName(JLabel lblStudentName) {
        this.lblStudentName = lblStudentName;
    }

    public JLabel getLblResult() {
        return lblResult;
    }

    public void setLblResult(JLabel lblResult) {
        this.lblResult = lblResult;
    }

    public JTextField getTxtBarcodeNo() {
        return txtBarcodeNo;
    }

    public void setTxtBarcodeNo(JTextField txtBarcodeNo) {
        this.txtBarcodeNo = txtBarcodeNo;
    }

    public JTextField getTxtStudentNo() {
        return txtStudentNo;
    }

    public void setTxtStudentNo(JTextField txtStudentNo) {
        this.txtStudentNo = txtStudentNo;
    }

    public JTextField getTxtBookName() {
        return txtBookName;
    }

    public void setTxtBookName(JTextField txtBookName) {
        this.txtBookName = txtBookName;
    }

    public JTextField getTxtAuthorName() {
        return txtAuthorName;
    }

    public void setTxtAuthorName(JTextField txtAuthorName) {
        this.txtAuthorName = txtAuthorName;
    }

    public JTextField getTxtStudentName() {
        return txtStudentName;
    }

    public void setTxtStudentName(JTextField txtStudentName) {
        this.txtStudentName = txtStudentName;
    }

    public JTextField getTxtResult() {
        return txtResult;
    }

    public void setTxtResult(JTextField txtResult) {
        this.txtResult = txtResult;
    }

    public JButton getBtnComeBack() {
        return btnComeBack;
    }

    public void setBtnComeBack(JButton btnComeBack) {
        this.btnComeBack = btnComeBack;
    }

}
