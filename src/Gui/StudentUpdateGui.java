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

public class StudentUpdateGui extends JPanel {

    JFrame jf;
//getLblToBeChangedBarcodeNo
    JLabel lblno;
    JLabel lblNewNo;
    JLabel lblNewName;
    JLabel lblNewSurname;
    JLabel lblNewEmail;
    JLabel lblResult;
    JLabel lblPhoneNo;

    JTextField txtno;
    JTextField txtNewNo;
    JTextField txtNewName;
    JTextField txtNewSurname;
    JTextField txtNewEmail;
    JTextField txtResult;
    JTextField txtPhoneNo;

    JButton btnComeBack;
    JButton btnUpdate;
    JButton btnDelete;

    MainGui mg;
    ActionStudent action = new ActionStudent(this);

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
        this.mg = mg;
        this.jf = mg.getJf();
        getJf().setTitle("ÖĞRENCİ GÜNCELLE");
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
        this.add(getLblPhoneNo());
        this.add(getLblResult());
        this.add(getTxtno());
        this.add(getTxtNewNo());
        this.add(getTxtNewName());
        this.add(getTxtNewSurname());
        this.add(getTxtNewEmail());
        this.add(getTxtResult());
        this.add(getTxtPhoneNo());
        this.add(getBtnComeBack());
        this.add(getBtnUpdate());
        this.add(getBtnDelete());
        getBtnComeBack().addActionListener(action);
        getTxtno().addActionListener(action);
        getTxtResult().addActionListener(action);
        getTxtNewSurname().addActionListener(action);
        getTxtNewNo().addActionListener(action);
        getTxtNewName().addActionListener(action);
        getTxtNewEmail().addActionListener(action);
        getTxtPhoneNo().addActionListener(action);
        getBtnUpdate().addActionListener(action);
        getBtnDelete().addActionListener(action);

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
            lblNewNo = new JLabel("Yeni Öğrenci Numarası");
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
            lblNewName = new JLabel("Yeni Öğrenci Adı");
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
            lblNewSurname = new JLabel("Yeni Öğrenci Soyadı");
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
            lblNewEmail = new JLabel("Yeni Öğrenci Email");
            lblNewEmail.setBounds(leftSpace, lblTopSpace + (lblHeight + txtHeight + 30) * lblPushUnderCounter, lblWidth, lblHeight);
            lblNewEmail.setForeground(Color.WHITE);
            lblNewEmail.setFont(lblFont);

        }

        return lblNewEmail;
    }

    public void setLblNewEmail(JLabel lblNewEmail) {
        this.lblNewEmail = lblNewEmail;
    }

    public JLabel getLblPhoneNo() {
        if (lblPhoneNo == null) {
            lblPhoneNo = new JLabel("Yeni Öğrenci Telefon No");
            lblPhoneNo.setBounds(leftSpace + txtWidth + 50, lblTopSpace + (lblHeight + txtHeight + 30) * lblPushUnderCounter, lblWidth, lblHeight);
            //  (leftSpace + txtWidth + 50, lblTopSpace + (lblHeight + txtHeight + 30) * lblPushUnderCounter, lblWidth, lblHeight);

            lblPhoneNo.setForeground(Color.WHITE);
            lblPhoneNo.setFont(lblFont);
            lblPushUnderCounter++;
        }
        return lblPhoneNo;
    }

    public void setLblPhoneNo(JLabel lblPhoneNo) {
        this.lblPhoneNo = lblPhoneNo;
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
            txtNewNo = new JTextField("");
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
            txtNewName = new JTextField("");
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
            txtNewSurname = new JTextField("");
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
            txtNewEmail = new JTextField("");
            txtNewEmail.setBounds(leftSpace, txtTopSpace + txtPushSpaceUnder * txtPushUnderCounter, txtWidth, txtHeight);
            txtNewEmail.setFont(txtFont);

        }
        return txtNewEmail;
    }

    public void setTxtNewEmail(JTextField txtNewEmail) {
        this.txtNewEmail = txtNewEmail;
    }

    public JTextField getTxtPhoneNo() {
        if (txtPhoneNo == null) {
            txtPhoneNo = new JTextField("");
            txtPhoneNo.setBounds(leftSpace + txtWidth + 50, txtTopSpace + txtPushSpaceUnder * txtPushUnderCounter, txtWidth, txtHeight);
            txtPhoneNo.setFont(txtFont);

        }
        return txtPhoneNo;
    }

    public void setTxtPhoneNo(JTextField txtPhoneNo) {
        this.txtPhoneNo = txtPhoneNo;
    }

    public JTextField getTxtResult() {
        if (txtResult == null) {
            txtResult = new JTextField("");
            //txtResult.setBounds(leftSpace, txtTopSpace + (txtPushSpaceUnder * txtPushUnderCounter), txtWidth, txtHeight);
            txtResult.setBounds(txtWidth + leftSpace + 50, 50, txtWidth, txtHeight);
            txtResult.setFont(txtFont);
            txtResult.setEditable(false);
            txtResult.setBackground(new Color(206, 214, 224));
            ToolTipManager ttm = ToolTipManager.sharedInstance();
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtResult.setToolTipText("Buraya Erişemezsiniz");

        }
        return txtResult;
    }

    public void setTxtResult(JTextField txtResult) {
        this.txtResult = txtResult;
    }

    public JButton getBtnComeBack() {
        if (btnComeBack == null) {
            btnComeBack = new JButton("Geri dön");
            btnComeBack.setBounds(leftSpace, 450, 200, 30);
        }
        return btnComeBack;
    }

    public void setBtnComeBack(JButton btnComeBack) {
        this.btnComeBack = btnComeBack;
    }

    public JButton getBtnUpdate() {
        if (btnUpdate == null) {
            btnUpdate = new JButton("Öğrenci Güncelle");
            btnUpdate.setBounds(leftSpace + 550, 450, 200, 30);
        }
        return btnUpdate;
    }

    public void setBtnUpdate(JButton btnUpdate) {
        this.btnUpdate = btnUpdate;
    }

    public JButton getBtnDelete() {
        if (btnDelete == null) {
            btnDelete = new JButton("Öğrenci Sil");
            btnDelete.setBounds(leftSpace + 275, 450, 200, 30);
        }
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
