package Gui;

import Logic.ActionTimeFine;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ToolTipManager;

public class FineDebtPayment {

    MainGui mg;
    JFrame jf;
    JPanel jp;

    JLabel lblStudentNo;
    JLabel lblDebt;
    JLabel lblAmountOfPayment;
    JLabel lblResult;

    JTextField txtStudentNo;
    JTextField txtDebt;
    JTextField txtAmountOfPayment;
    JTextField txtResult;

    JTable tbl;
    JScrollPane sp;
    JButton btnPay;
    JButton btnComeBack;
    final int lblWidth = 200;
    final int lblHeight = 30;
    final int txtWidth = lblWidth;
    final int txtHeight = lblHeight;
    final int lblTopSpace = 10;
    final int txtTopSpace = lblTopSpace + lblHeight;
    final int LeftSpace = 30;
    final int pushRightSpace = txtWidth + 50;
    int pushRightCounter = 0;
    public String DataForTable[][] = {
        {"385931", "5486461768456", "Matematik bilmem ne", "20-11-2019", "0.8 Tl"},
        {"385931", "5486461768456", "Matematik bilmem ne", "20-11-2019", "0.8 Tl"},
        {"385931", "5486461768456", "Matematik bilmem ne", "20-11-2019", "0.8 Tl"},
        {"385931", "5486461768456", "Matematik bilmem ne", "20-11-2019", "0.8 Tl"},
        {"385931", "5486461768456", "Matematik bilmem ne", "20-11-2019", "0.8 Tl"},
        {"385931", "5486461768456", "Matematik bilmem ne", "20-11-2019", "0.8 Tl"},
        {"385931", "5486461768456", "Matematik bilmem ne", "20-11-2019", "0.8 Tl"},
        {"385931", "5486461768456", "Matematik bilmem ne", "20-11-2019", "0.8 Tl"},
        {"385931", "5486461768456", "Matematik bilmem ne", "20-11-2019", "0.8 Tl"},
        {"385931", "5486461768456", "Matematik bilmem ne", "20-11-2019", "0.8 Tl"},
        {"385931", "5486461768456", "Matematik bilmem ne", "20-11-2019", "0.8 Tl"},};
    public String HeaderOfTable[] = {"Öğrenci No", "Kitap Barko No", "Kitap Adı", "Kitabın Alındığı Tarih", "Borç (TL)"};
    Font lblFont = new Font("monospaced", Font.BOLD, 17);
    Font txtFont = new Font("", Font.BOLD, 15);
    ActionTimeFine action = new ActionTimeFine(this);

    public FineDebtPayment(MainGui mg) {
        setMg(mg);
        setJf(mg.getJf());
        getJf().setTitle("Para Cezaları & Borç ödeme");
        getJf().add(getJp());

        getJp().add(getLblStudentNo());
        getJp().add(getLblDebt());
        getJp().add(getLblResult());
        pushRightCounter = 0;

        getJp().add(getTxtStudentNo());
        getJp().add(getTxtDebt());
        getJp().add(getTxtResult());
        pushRightCounter = 0;
        getJp().add(getLblAmountOfPayment());
        getJp().add(getTxtAmountOfPayment());
        getJp().add(getBtnPay());
        getJp().add(getBtnComeBack());
        getJp().add(getSp());
        getBtnPay().addActionListener(action);
        getBtnComeBack().addActionListener(action);

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
            jp.setBackground(new Color(150, 140, 224));
            jp.setLayout(null);

        }
        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

    public JLabel getLblStudentNo() {
        if (lblStudentNo == null) {
            lblStudentNo = new JLabel("Öğrenci No");
            lblStudentNo.setBounds(LeftSpace + (pushRightCounter * pushRightSpace), lblTopSpace, lblWidth, lblHeight);
            lblStudentNo.setForeground(Color.BLACK);
            lblStudentNo.setFont(lblFont);
            pushRightCounter++;

        }
        return lblStudentNo;
    }

    public void setLblStudentNo(JLabel lblStudentNo) {

        this.lblStudentNo = lblStudentNo;
    }

    public JLabel getLblDebt() {
        if (lblDebt == null) {
            lblDebt = new JLabel("Borcu");
            lblDebt.setBounds(LeftSpace + (pushRightCounter * pushRightSpace), lblTopSpace, lblWidth, lblHeight);
            lblDebt.setForeground(Color.BLACK);
            lblDebt.setFont(lblFont);
            pushRightCounter++;

        }
        return lblDebt;
    }

    public void setLblDebt(JLabel lblDebt) {
        this.lblDebt = lblDebt;
    }

    public JLabel getLblAmountOfPayment() {
        if (lblAmountOfPayment == null) {
            lblAmountOfPayment = new JLabel("Ödeme Miktarı");
            lblAmountOfPayment.setBounds(LeftSpace + (pushRightCounter * pushRightSpace), lblTopSpace + lblHeight + txtHeight + 10, lblWidth, lblHeight);
            lblAmountOfPayment.setForeground(Color.BLACK);
            lblAmountOfPayment.setFont(lblFont);

        }
        return lblAmountOfPayment;
    }

    public void setLblAmountOfPayment(JLabel lblAmountOfPayment) {
        this.lblAmountOfPayment = lblAmountOfPayment;
    }

    public JLabel getLblResult() {
        if (lblResult == null) {
            lblResult = new JLabel("Sonuç");
            lblResult.setBounds(LeftSpace + (pushRightCounter * pushRightSpace), lblTopSpace, lblWidth, lblHeight);
            lblResult.setForeground(Color.BLACK);
            lblResult.setFont(lblFont);
            pushRightCounter++;

        }
        return lblResult;
    }

    public void setLblResult(JLabel lblResult) {
        this.lblResult = lblResult;
    }

    public JTextField getTxtStudentNo() {
        if (txtStudentNo == null) {
            txtStudentNo = new JTextField("Öğrenci No");
            txtStudentNo.setBounds(LeftSpace + (pushRightCounter * pushRightSpace), txtTopSpace, lblWidth, lblHeight);
            txtStudentNo.setForeground(Color.BLACK);
            txtStudentNo.setFont(txtFont);
            pushRightCounter++;

        }
        return txtStudentNo;
    }

    public void setTxtStudentNo(JTextField txtStudentNo) {
        this.txtStudentNo = txtStudentNo;
    }

    public JTextField getTxtDebt() {
        if (txtDebt == null) {
            txtDebt = new JTextField("Borç");
            txtDebt.setBounds(LeftSpace + (pushRightCounter * pushRightSpace), txtTopSpace, lblWidth, lblHeight);
            txtDebt.setForeground(Color.BLACK);
            txtDebt.setFont(txtFont);
            txtDebt.setFocusable(false);
            txtDebt.setEditable(false);
            ToolTipManager ttm = ToolTipManager.sharedInstance();
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtDebt.setToolTipText("Buraya Erişemezsiniz");
            pushRightCounter++;

        }
        return txtDebt;
    }

    public void setTxtDebt(JTextField txtDebt) {
        this.txtDebt = txtDebt;
    }

    public JTextField getTxtAmountOfPayment() {
        if (txtAmountOfPayment == null) {
            txtAmountOfPayment = new JTextField("Ödeme miktarı");
            txtAmountOfPayment.setBounds(LeftSpace + (pushRightCounter * pushRightSpace), txtTopSpace + lblHeight + txtHeight + 15, lblWidth, lblHeight);
            txtAmountOfPayment.setForeground(Color.BLACK);
            txtAmountOfPayment.setFont(txtFont);
            pushRightCounter++;

        }

        return txtAmountOfPayment;
    }

    public void setTxtAmountOfPayment(JTextField txtAmountOfPayment) {
        this.txtAmountOfPayment = txtAmountOfPayment;
    }

    public JTextField getTxtResult() {
        if (txtResult == null) {
            txtResult = new JTextField("Sonuç");
            txtResult.setBounds(LeftSpace + (pushRightCounter * pushRightSpace), txtTopSpace, lblWidth, lblHeight);
            txtResult.setForeground(Color.BLACK);
            txtResult.setFont(txtFont);
            txtResult.setFocusable(false);
            txtResult.setEditable(false);
            ToolTipManager ttm = ToolTipManager.sharedInstance();
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtResult.setToolTipText("Buraya Erişemezsiniz");
            pushRightCounter++;

        }
        return txtResult;
    }

    public void setTxtResult(JTextField txtResult) {
        this.txtResult = txtResult;
    }

    public JTable getTbl() {
        if (tbl == null) {
            tbl = new JTable(DataForTable, HeaderOfTable);
            tbl.setDefaultEditor(Object.class, null);
            tbl.setCursor(new Cursor(12));
            tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            // {"Öğrenci No", "Kitap Barko No", "Kitap Adı", "Kitabın Alındığı Tarih", "Borç (TL)"};
            tbl.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbl.getColumnModel().getColumn(1).setPreferredWidth(250);
            tbl.getColumnModel().getColumn(2).setPreferredWidth(450);
            tbl.getColumnModel().getColumn(3).setPreferredWidth(250);
            tbl.getColumnModel().getColumn(4).setPreferredWidth(200);
            tbl.setFont(lblFont);
        }
        return tbl;
    }

    public void setTbl(JTable tbl) {
        this.tbl = tbl;
    }

    public JScrollPane getSp() {
        if (sp == null) {
            sp = new JScrollPane(getTbl());
            sp.setBounds(LeftSpace, txtTopSpace + txtHeight * 4, 750, 270);
            //sp.setHorizontalScrollBarPolicy(sp.HORIZONTAL_SCROLLBAR_ALWAYS);
            //sp.setVerticalScrollBarPolicy(sp.VERTICAL_SCROLLBAR_ALWAYS);

        }
        return sp;
    }

    public void setSp(JScrollPane sp) {
        this.sp = sp;
    }

    public JButton getBtnPay() {
        if (btnPay == null) {
            btnPay = new JButton("Öde");
            btnPay.setBounds(LeftSpace + (pushRightCounter * pushRightSpace), txtTopSpace + lblHeight + txtHeight + 15, lblWidth, 30);
            btnPay.setFont(lblFont);

        }
        return btnPay;
    }

    public void setBtnPay(JButton btnPay) {
        this.btnPay = btnPay;
    }

    public JButton getBtnComeBack() {
        if (btnComeBack == null) {
            btnComeBack = new JButton("Geri Dön");
            btnComeBack.setBounds(LeftSpace, 450, 150, 30);

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
