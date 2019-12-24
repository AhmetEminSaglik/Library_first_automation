/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Ahmet Emin SAĞLIK
 */
public class StudentUpdateGui extends JPanel {

    JFrame jf;
//getLblToBeChangedBarcodeNo
    JLabel lblno;
    JLabel lblNewNo;
    JLabel lblNewName;
    JLabel lblNewSurname;
    JLabel lblNewEmail;
    JLabel lblResult;

    JTextField txtno;
    JTextField txtNewNo;
    JTextField txtNewName;
    JTextField txtNewSurname;
    JTextField txtNewEmail;
    JTextField txtResult;

    JButton btnComeBack;
    JButton btnUpdate;
    JButton btnDelete;

    final int leftSpace = 50;
    final int lblWidth = 500;
    final int lblHeight = 20;

    int lblPushUnderCounter = 0;
    final int heightpaintY = 125;

    final int lblTopSpace = heightpaintY + 30;
    final int txtTopSpace = heightpaintY + lblHeight + 40;
    final int txtLeftSpace = 50;
    final int txtWidth = 350;
    final int txtHeight = 40;

    int txtPushUnderCounter = 0;

    final int lblPushSpaceRight = lblWidth + 100;
    final int lblPushSpaceUnder = lblHeight + 50;
    final int txtPushSpaceUnder = txtHeight + lblHeight + 30;

    Font lblFont = new Font("monospaced", Font.BOLD, 17);
    Font txtFont = new Font("", Font.BOLD, 15);

    public StudentUpdateGui(MainGui mg) {
        this.jf = mg.getJf();
        this.setBounds(getJf().getBounds());
        this.setBackground(new Color(75, 153, 23));
        this.setLayout(null);

        setJf(mg.getJf());
        mg.getJp().setVisible(false);
        getJf().add(this);

        this.add(getLblno());
        this.add(getLblNewNo());
        this.add(getLblNewName());
        this.add(getLblNewSurname());
        this.add(getLblNewEmail());
        this.add(getLblResult());
        this.add(getTxtno());
        this.add(getTxtNewNo());
        this.add(getTxtNewName());
        this.add(getTxtNewSurname());
        this.add(getTxtNewEmail());
        this.add(getTxtResult());
        this.add(getBtnComeBack());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        g.setColor(Color.orange);
        g.fillRect(0, heightpaintY, 850, 10);
    }

    public JFrame getJf() {
        return jf;
    }

    public void setJf(JFrame jf) {
        this.jf = jf;
    }

    public JLabel getLblno() {
        if (lblno == null) {
            lblno = new JLabel("Öğrenci Numarası");
            lblno.setBounds(leftSpace, 15, lblWidth, lblHeight);
            lblno.setForeground(Color.white);
            lblno.setFont(lblFont);
        }
        return lblno;
    }

    public void setLblno(JLabel lblno) {
        this.lblno = lblno;
    }

    public JLabel getLblNewNo() {
        if (lblNewNo == null) {
            lblNewNo = new JLabel("Öğrenci Numarası");
            lblNewNo.setBounds(leftSpace, lblTopSpace + (lblHeight + txtHeight) * lblPushUnderCounter, lblWidth, lblHeight);
            lblNewNo.setForeground(Color.WHITE);
            lblNewNo.setFont(lblFont);
            lblPushUnderCounter++;
        }
        return lblNewNo;
    }

    public void setLblNewNo(JLabel lblNewNo) {
        this.lblNewNo = lblNewNo;
    }

    public JLabel getLblNewName() {

        if (lblNewName == null) {
            lblNewName = new JLabel("Öğrenci Adı");
            lblNewName.setBounds(leftSpace, lblTopSpace + (lblHeight + txtHeight + 30) * lblPushUnderCounter, lblWidth, lblHeight);
            lblNewName.setForeground(Color.WHITE);
            lblNewName.setFont(lblFont);

        }
        return lblNewName;
    }

    public void setLblNewName(JLabel lblNewName) {
        this.lblNewName = lblNewName;
    }

    public JLabel getLblNewSurname() {
        if (lblNewSurname == null) {
            lblNewSurname = new JLabel("Öğrenci Soyadı");
            lblNewSurname.setBounds(leftSpace + txtWidth + 50, lblTopSpace + (lblHeight + txtHeight + 30) * lblPushUnderCounter, lblWidth, lblHeight);
            lblNewSurname.setForeground(Color.WHITE);
            lblNewSurname.setFont(lblFont);
            lblPushUnderCounter++;
        }
        return lblNewSurname;
    }

    public void setLblNewSurname(JLabel lblNewSurname) {
        this.lblNewSurname = lblNewSurname;
    }

    public JLabel getLblNewEmail() {
        if (lblNewEmail == null) {
            lblNewEmail = new JLabel("Öğrenci Email");
            lblNewEmail.setBounds(leftSpace, lblTopSpace + (lblHeight + txtHeight + 30) * lblPushUnderCounter, lblWidth, lblHeight);
            lblNewEmail.setForeground(Color.WHITE);
            lblNewEmail.setFont(lblFont);
            lblPushUnderCounter++;
        }

        return lblNewEmail;
    }

    public void setLblNewEmail(JLabel lblNewEmail) {
        this.lblNewEmail = lblNewEmail;
    }

    public JLabel getLblResult() {
        if (lblResult == null) {
            lblResult = new JLabel("Sonuç");
            lblResult.setBounds(leftSpace + lblWidth + 42, 15, lblWidth, lblHeight);
            lblResult.setForeground(Color.WHITE);
            lblResult.setFont(lblFont);
        }
        return lblResult;
    }

    public void setLblResult(JLabel lblResult) {
        this.lblResult = lblResult;
    }

    public JTextField getTxtno() {
        if (txtno == null) {
            txtno = new JTextField();
            txtno.setBounds(leftSpace, 50, txtWidth, txtHeight);
            txtno.setFont(txtFont);

        }

        return txtno;
    }

    public void setTxtno(JTextField txtno) {
        this.txtno = txtno;
    }

    public JTextField getTxtNewNo() {
        if (txtNewNo == null) {
            txtNewNo = new JTextField("yeni no");
            txtNewNo.setBounds(leftSpace, txtTopSpace + (txtPushSpaceUnder * txtPushUnderCounter), txtWidth, txtHeight);
            txtNewNo.setFont(txtFont);
            txtPushUnderCounter++;
        }
        return txtNewNo;
    }

    public void setTxtNewNo(JTextField txtNewNo) {
        this.txtNewNo = txtNewNo;
    }

    public JTextField getTxtNewName() {
        if (txtNewName == null) {
            txtNewName = new JTextField("yeni ad");
            txtNewName.setBounds(leftSpace, txtTopSpace + (txtPushSpaceUnder * txtPushUnderCounter), txtWidth, txtHeight);
            txtNewName.setFont(txtFont);

        }
        return txtNewName;
    }

    public void setTxtNewName(JTextField txtNewName) {
        this.txtNewName = txtNewName;
    }

    public JTextField getTxtNewSurname() {

        if (txtNewSurname == null) {
            txtNewSurname = new JTextField("Öğrenci Soyadı");
            txtNewSurname.setBounds(leftSpace + txtWidth + 50, txtTopSpace + txtPushSpaceUnder * txtPushUnderCounter, txtWidth, txtHeight);
            txtNewSurname.setForeground(Color.BLACK);
            txtNewSurname.setFont(lblFont);
            txtPushUnderCounter++;
        }
        return txtNewSurname;
    }

    public void setTxtNewSurname(JTextField txtNewSurname) {
        this.txtNewSurname = txtNewSurname;
    }

    public JTextField getTxtNewEmail() {
        if (txtNewEmail == null) {
            txtNewEmail = new JTextField("yeni Email");
            txtNewEmail.setBounds(leftSpace, txtTopSpace + txtPushSpaceUnder * txtPushUnderCounter, txtWidth, txtHeight);
            txtNewEmail.setFont(txtFont);

        }
        return txtNewEmail;
    }

    public void setTxtNewEmail(JTextField txtNewEmail) {
        this.txtNewEmail = txtNewEmail;
    }

    public JTextField getTxtResult() {
        if (txtResult == null) {
            txtResult = new JTextField("Sonuç");
            //txtResult.setBounds(leftSpace, txtTopSpace + (txtPushSpaceUnder * txtPushUnderCounter), txtWidth, txtHeight);
            txtResult.setBounds(txtWidth + leftSpace + 50, 50, txtWidth, txtHeight);
            txtResult.setFont(txtFont);
            txtResult.setEditable(false);

        }
        return txtResult;
    }

    public void setTxtResult(JTextField txtResult) {
        this.txtResult = txtResult;
    }

    public JButton getBtnComeBack() {
        if (btnComeBack == null) {
            btnComeBack = new JButton("Geri dön");
            btnComeBack.setBounds(leftSpace, 450, 100, 30);
        }
        return btnComeBack;
    }

    public void setBtnComeBack(JButton btnComeBack) {
        this.btnComeBack = btnComeBack;
    }

    public JButton getBtnUpdate() {
        return btnUpdate;
    }

    public void setBtnUpdate(JButton btnUpdate) {
        this.btnUpdate = btnUpdate;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(JButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public int getLblPushUnderCounter() {
        return lblPushUnderCounter;
    }

    public void setLblPushUnderCounter(int lblPushUnderCounter) {
        this.lblPushUnderCounter = lblPushUnderCounter;
    }

}
