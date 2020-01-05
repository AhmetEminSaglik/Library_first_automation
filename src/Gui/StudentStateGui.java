package Gui;

import Logic.ActionStudent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ToolTipManager;

public class StudentStateGui extends JPanel {

    JFrame jf;
    JLabel lblStudentNo;
    JLabel lblDept;
    JLabel lblLendingDayNumber1; //ödünç verilen gün sayısı
    JLabel lblLendingDayNumber2;
    JLabel lblLendingDayNumber3;

    JTextField txtStudentNo;
    JTextField txtDept;
    JTextField txtBookBarcodeNo1; //ödünç verilen gün sayısı
    JTextField txtBookBarcodeNo2;
    JTextField txtBookBarcodeNo3;
    JTextField txtBookName1;
    JTextField txtBookName2;
    JTextField txtBookName3;
    JButton btnComeBack;

    final int lblWidth = 150;
    final int lblHeight = 30;

    final int txtBarcodeWidth = 250;
    final int txtBookNameWidth = 700;
    final int txtHeight = 40;

    final int txtPushUnder = txtHeight + 10;

    final int lblPushUnder = txtHeight + 10;
    final int LeftSpace = 50;
    int pushUnderlblCounter = 0;
    int pushUndertxtCounter = 0;
    final int topSpace = 130;
    final int topSpaceBook = topSpace + txtHeight + 5;
    Font lblFont = new Font("monospaced", Font.BOLD, 17);
    Font txtFont = new Font("", Font.BOLD, 15);
    MainGui mg;
    ActionStudent action = new ActionStudent(this);
    ToolTipManager ttm = ToolTipManager.sharedInstance();

    public StudentStateGui(MainGui mg) {
        setMg(mg);
        getMg().getJf().setTitle("ÖĞRENCİ DURUMU");
        this.setBounds(getJf().getBounds());
        this.setBackground(new Color(0, 0, 0));
        this.setLayout(null);

        setJf(mg.getJf());
        mg.getJp().setVisible(false);
        getJf().add(this);
        this.add(getLblStudentNo());
        this.add(getLblDept());
        this.add(getLblLendingDayNumber1());
        this.add(getLblLendingDayNumber2());
        this.add(getLblLendingDayNumber3());
        this.add(getTxtStudentNo());
        this.add(getTxtDept());
        this.add(getTxtBookBarcodeNo1());
        this.add(getTxtBookBarcodeNo2());
        this.add(getTxtBookBarcodeNo3());
        this.add(getTxtBookName1());
        this.add(getTxtBookName2());
        this.add(getTxtBookName3());
        this.add(getBtnComeBack());
        getBtnComeBack().addActionListener(action);
        getTxtStudentNo().addActionListener(action);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        g.setColor(Color.red);
        g.fillRect(0, topSpace - 30, 900, 10);
        //belirli aralıklarla yapılması gerektiği için şuan es geçiyorum
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

    public JLabel getLblStudentNo() {
        if (lblStudentNo == null) {
            lblStudentNo = new JLabel("Öğrenci No");
            lblStudentNo.setBounds(LeftSpace, 10, lblWidth, lblHeight);
            lblStudentNo.setForeground(Color.red);
            lblStudentNo.setFont(lblFont);
        }
        return lblStudentNo;
    }

    public void setLblStudentNo(JLabel lblStudentNo) {
        this.lblStudentNo = lblStudentNo;
    }

    public JLabel getLblDept() {
        if (lblDept == null) {
            lblDept = new JLabel("Borç Durumu");
            lblDept.setBounds(LeftSpace + 400, 10, lblWidth, lblHeight);
            lblDept.setForeground(Color.red);
            lblDept.setFont(lblFont);
        }
        return lblDept;
    }

    public void setLblDept(JLabel lblDept) {
        this.lblDept = lblDept;
    }

    public JLabel getLblLendingDayNumber1() {
        if (lblLendingDayNumber1 == null) {
            lblLendingDayNumber1 = new JLabel("b1 30 gün");
            lblLendingDayNumber1.setBounds(LeftSpace + txtBarcodeWidth + 40, topSpace + (txtHeight + 70) * pushUnderlblCounter, lblWidth, lblHeight);
            lblLendingDayNumber1.setForeground(Color.GREEN);
            lblLendingDayNumber1.setFont(lblFont);
            pushUnderlblCounter++;
        }
        return lblLendingDayNumber1;
    }

    public void setLblLendingDayNumber1(JLabel lblLendingDayNumber1) {
        this.lblLendingDayNumber1 = lblLendingDayNumber1;
    }

    public JLabel getLblLendingDayNumber2() {
        if (lblLendingDayNumber2 == null) {
            lblLendingDayNumber2 = new JLabel("b2 30 gün");
            lblLendingDayNumber2.setBounds(LeftSpace + txtBarcodeWidth + 40, topSpace + (txtHeight + 70) * pushUnderlblCounter, lblWidth, lblHeight);
            lblLendingDayNumber2.setForeground(Color.GREEN);
            lblLendingDayNumber2.setFont(lblFont);
            pushUnderlblCounter++;
        }
        return lblLendingDayNumber2;
    }

    public void setLblLendingDayNumber2(JLabel lblLendingDayNumber2) {
        this.lblLendingDayNumber2 = lblLendingDayNumber2;
    }

    public JLabel getLblLendingDayNumber3() {
        if (lblLendingDayNumber3 == null) {
            lblLendingDayNumber3 = new JLabel("b3 30 gün");
            lblLendingDayNumber3.setBounds(LeftSpace + txtBarcodeWidth + 40, topSpace + (txtHeight + 70) * pushUnderlblCounter, lblWidth, lblHeight);
            lblLendingDayNumber3.setForeground(Color.GREEN);
            lblLendingDayNumber3.setFont(lblFont);
            pushUnderlblCounter++;
        }
        return lblLendingDayNumber3;
    }

    public void setLblLendingDayNumber3(JLabel lblLendingDayNumber3) {
        this.lblLendingDayNumber3 = lblLendingDayNumber3;
    }

    public JTextField getTxtStudentNo() {
        if (txtStudentNo == null) {
            txtStudentNo = new JTextField("öğrenci no");
            txtStudentNo.setBounds(LeftSpace, lblHeight + 10, txtBarcodeWidth, txtHeight);
            txtStudentNo.setFont(txtFont);
        }
        return txtStudentNo;
    }

    public void setTxtStudentNo(JTextField txtStudentNo) {
        this.txtStudentNo = txtStudentNo;
    }

    public JTextField getTxtDept() {
        if (txtDept == null) {
            txtDept = new JTextField("borç tl");
            txtDept.setBounds(LeftSpace + 400, lblHeight + 10, txtBarcodeWidth, txtHeight);
            txtDept.setFont(txtFont);
            txtDept.setEditable(false);
            txtDept.setFocusable(false);
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtDept.setToolTipText("Buraya Erişemezsiniz");
        }
        return txtDept;
    }

    public void setTxtDept(JTextField txtDept) {
        this.txtDept = txtDept;
    }

    public JTextField getTxtBookBarcodeNo1() {
        if (txtBookBarcodeNo1 == null) {
            System.out.println("pushUndertxtCounter : " + pushUndertxtCounter);
            txtBookBarcodeNo1 = new JTextField("barcode 1");
            txtBookBarcodeNo1.setBounds(LeftSpace, topSpace + (txtHeight + 70) * pushUndertxtCounter, txtBarcodeWidth, txtHeight);
            txtBookBarcodeNo1.setFont(txtFont);
            txtBookBarcodeNo1.setEditable(false);
            txtBookBarcodeNo1.setFocusable(false);
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtBookBarcodeNo1.setToolTipText("Buraya Erişemezsiniz");
            pushUndertxtCounter++;

        }
        return txtBookBarcodeNo1;
    }

    public void setTxtBookBarcodeNo1(JTextField txtBookBarcodeNo1) {
        this.txtBookBarcodeNo1 = txtBookBarcodeNo1;
    }

    public JTextField getTxtBookBarcodeNo2() {
        if (txtBookBarcodeNo2 == null) {
            txtBookBarcodeNo2 = new JTextField("barcode 2");
            txtBookBarcodeNo2.setBounds(LeftSpace, topSpace + (txtHeight + 70) * pushUndertxtCounter, txtBarcodeWidth, txtHeight);
            txtBookBarcodeNo2.setFont(txtFont);
            txtBookBarcodeNo2.setEditable(false);
            txtBookBarcodeNo2.setFocusable(false);
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtBookBarcodeNo2.setToolTipText("Buraya Erişemezsiniz");
            pushUndertxtCounter++;
        }/* if (txtBookBarcodeNo1 == null) {
            
            txtBookBarcodeNo1 = new JTextField("barcode 1");
            txtBookBarcodeNo1.setBounds(LeftSpace, topSpace + (txtHeight + 70) * pushUndertxtCounter, txtBarcodeWidth, txtHeight);

            txtBookBarcodeNo1.setFont(txtFont);
            pushUndertxtCounter++;
        }*/
        return txtBookBarcodeNo2;
    }

    public void setTxtBookBarcodeNo2(JTextField txtBookBarcodeNo2) {
        this.txtBookBarcodeNo2 = txtBookBarcodeNo2;
    }

    public JTextField getTxtBookBarcodeNo3() {
        if (txtBookBarcodeNo3 == null) {
            txtBookBarcodeNo3 = new JTextField("barcode 3");
            txtBookBarcodeNo3.setBounds(LeftSpace, topSpace + (txtHeight + 70) * pushUndertxtCounter, txtBarcodeWidth, txtHeight);
            txtBookBarcodeNo3.setFont(txtFont);
            txtBookBarcodeNo3.setEditable(false);
            txtBookBarcodeNo3.setFocusable(false);
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtBookBarcodeNo3.setToolTipText("Buraya Erişemezsiniz");
            pushUndertxtCounter++;
        }
        return txtBookBarcodeNo3;
    }

    public void setTxtBookBarcodeNo3(JTextField txtBookBarcodeNo3) {
        this.txtBookBarcodeNo3 = txtBookBarcodeNo3;
    }

    public JTextField getTxtBookName1() {
        if (txtBookName1 == null) {
            pushUndertxtCounter = 0;
            txtBookName1 = new JTextField("bookname 1");
            txtBookName1.setBounds(LeftSpace, topSpaceBook + (txtHeight + 70) * pushUndertxtCounter, txtBookNameWidth, txtHeight);
            txtBookName1.setFont(txtFont);
            txtBookName1.setEditable(false);
            txtBookName1.setFocusable(false);
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtBookName1.setToolTipText("Buraya Erişemezsiniz");
            pushUndertxtCounter++;
        }

        return txtBookName1;
    }

    public void setTxtBookName1(JTextField txtBookName1) {
        this.txtBookName1 = txtBookName1;
    }

    public JTextField getTxtBookName2() {
        if (txtBookName2 == null) {
            txtBookName2 = new JTextField("bookname 2");
            txtBookName2.setBounds(LeftSpace, topSpaceBook + (txtHeight + 70) * pushUndertxtCounter, txtBookNameWidth, txtHeight);
            txtBookName2.setFont(txtFont);
            txtBookName2.setEditable(false);
            txtBookName2.setFocusable(false);
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtBookName2.setToolTipText("Buraya Erişemezsiniz");
            pushUndertxtCounter++;
        }
        return txtBookName2;
    }

    public void setTxtBookName2(JTextField txtBookName2) {
        this.txtBookName2 = txtBookName2;
    }

    public JTextField getTxtBookName3() {
        if (txtBookName3 == null) {
            txtBookName3 = new JTextField("bookname 3");
            txtBookName3.setBounds(LeftSpace, topSpaceBook + (txtHeight + 70) * pushUndertxtCounter, txtBookNameWidth, txtHeight);
            txtBookName3.setFont(txtFont);
            txtBookName3.setEditable(false);
            txtBookName3.setFocusable(false);
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtBookName3.setToolTipText("Buraya Erişemezsiniz");
            pushUndertxtCounter++;
        }
        return txtBookName3;
    }

    public void setTxtBookName3(JTextField txtBookName3) {
        this.txtBookName3 = txtBookName3;
    }

    public JButton getBtnComeBack() {
        if (btnComeBack == null) {
            btnComeBack = new JButton("Geri dön");
            btnComeBack.setBounds(LeftSpace, 450, 150, 50);
            btnComeBack.setFont(txtFont);
        }
        return btnComeBack;
    }

    public void setBtnComeBack(JButton btnComeBack) {
        this.btnComeBack = btnComeBack;
    }

    public MainGui getMg() {
        if (mg == null) {
            mg = new MainGui();

        }
        return mg;
    }

    public void setMg(MainGui mg) {
        this.mg = mg;
    }

}
