package Gui;

import Logic.ActionsBook;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ToolTipManager;

public class BookReturnGui {

    MainGui mg;
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
    final int lblTopSpace = 10;
    final int lblLeftSpace = 50;
    final int lblWidth = 250;
    final int lblHeight = 60;
    int lblPushRightCounter = 0;
    int lblPushUnderCounter = 0;

    final int txtTopSpace = lblTopSpace + lblHeight - 10;
    final int txtLeftSpace = 50;
    final int txtWidth = 300;
    final int txtHeight = 40;
    int txtPushRightCounter = 0;
    int txtPushUnderCounter = 0;

    final int lblPushSpaceRight = lblLeftSpace + lblWidth + 100;
    final int lblPushSpaceUnder = lblHeight + 50;
    final int txtPushSpaceUnder = txtHeight + lblHeight + 10;
    final int txtPushSpaceRight = txtLeftSpace + txtWidth + 50;

    ActionsBook action = new ActionsBook(this);
    Font lblFont = new Font("monospaced", Font.BOLD, 17);
    Font txtFont = new Font("", Font.BOLD, 15);

    //BookActions action = new ActionsBook(this);
    public BookReturnGui(MainGui mg) {
        setMg(mg);
        setJf(mg.getJf());
        getMg().getJf().setTitle("KİTAP İADE ");
        getJf().add(getJp());
        getJp().add(getLblBarcodeNo());
        getJp().add(getLblStudentNo());
        getJp().add(getLblBookName());
        getJp().add(getLblAuthorName());
        getJp().add(getLblStudentName());
        getJp().add(getLblResult());
        getJp().add(getTxtBarcodeNo());
        getJp().add(getTxtStudentNo());
        getJp().add(getTxtBookName());
        getJp().add(getTxtAuthorName());
        getJp().add(getTxtStudentName());
        getJp().add(getTxtResult());
        getJp().add(getBtnComeBack());

        btnComeBack.addActionListener(action);
        getTxtBarcodeNo().addActionListener(action);
        getTxtStudentNo().addActionListener(action);
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
            jp.setBackground(new Color(50, 0, 50));
            jp.setBounds(getJf().getBounds());
            jp.setLayout(null);
        }
        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

    public JLabel getLblBarcodeNo() {
        if (lblBarcodeNo == null) {
            lblBarcodeNo = new JLabel("Kitap Barkod No");
            lblBarcodeNo.setBounds(lblLeftSpace + (lblPushSpaceRight * lblPushRightCounter), lblTopSpace + lblPushUnderCounter * lblPushSpaceUnder, lblWidth, lblHeight);
            lblBarcodeNo.setForeground(Color.white);
            lblBarcodeNo.setFont(lblFont);
            lblPushRightCounter++;

        }
        return lblBarcodeNo;
    }

    public void setLblBarcodeNo(JLabel lblBarcodeNo) {
        this.lblBarcodeNo = lblBarcodeNo;
    }

    public JLabel getLblStudentNo() {
        if (lblStudentNo == null) {
            lblStudentNo = new JLabel("Öğrenci Numarası");
            lblStudentNo.setBounds(lblLeftSpace + (lblPushSpaceRight * lblPushRightCounter), lblTopSpace + lblPushSpaceUnder * lblPushUnderCounter, lblWidth, lblHeight);
            lblStudentNo.setForeground(Color.white);
            lblStudentNo.setFont(lblFont);
            lblPushUnderCounter++;
            if (lblPushRightCounter == 1) {
                lblPushRightCounter = 0;
            } else {
                lblPushRightCounter++;
            }

        }
        return lblStudentNo;
    }

    public void setLblStudentNo(JLabel lblStudentNo) {
        this.lblStudentNo = lblStudentNo;
    }

    public JLabel getLblBookName() {
        if (lblBookName == null) {

            lblBookName = new JLabel("Kitap Adı");
            lblBookName.setBounds(lblLeftSpace + (lblPushSpaceRight * lblPushRightCounter), lblTopSpace + lblPushSpaceUnder * lblPushUnderCounter, lblWidth, lblHeight);
            lblBookName.setForeground(Color.white);
            lblBookName.setFont(lblFont);

            lblPushUnderCounter++;
            /*  if (lblPushRightCounter == 1) {
                lblPushRightCounter = 0;
            } else {
                lblPushRightCounter++;
            }*/

        }
        return lblBookName;
    }

    public void setLblBookName(JLabel lblBookName) {
        this.lblBookName = lblBookName;
    }

    public JLabel getLblAuthorName() {
        if (lblAuthorName == null) {

            lblAuthorName = new JLabel("Kitap Yazarı");
            lblAuthorName.setBounds(lblLeftSpace + (lblPushSpaceRight * lblPushRightCounter), lblTopSpace + lblPushSpaceUnder * lblPushUnderCounter, lblWidth, lblHeight);
            lblAuthorName.setForeground(Color.white);
            lblAuthorName.setFont(lblFont);

            if (lblPushRightCounter == 1) {
                lblPushRightCounter = 0;
            } else {
                lblPushRightCounter++;
            }

        }
        return lblAuthorName;
    }

    public void setLblAuthorName(JLabel lblAuthorName) {
        this.lblAuthorName = lblAuthorName;
    }

    public JLabel getLblStudentName() {
        if (lblStudentName == null) {

            lblStudentName = new JLabel("Öğrenci Adı");
            lblStudentName.setBounds(lblLeftSpace + (lblPushSpaceRight * lblPushRightCounter), lblTopSpace + lblPushSpaceUnder * lblPushUnderCounter, lblWidth, lblHeight);
            lblStudentName.setForeground(Color.white);
            lblStudentName.setFont(lblFont);
            lblPushUnderCounter++;

            if (lblPushRightCounter == 1) {
                lblPushRightCounter = 0;
            } else {
                lblPushRightCounter++;
            }
        }
        return lblStudentName;
    }

    public void setLblStudentName(JLabel lblStudentName) {

        this.lblStudentName = lblStudentName;
    }

    public JLabel getLblResult() {
        if (lblResult == null) {

            lblResult = new JLabel("Sonuç ");
            lblResult.setBounds(lblLeftSpace + (lblPushSpaceRight * lblPushRightCounter), lblTopSpace + lblPushSpaceUnder * lblPushUnderCounter, lblWidth, lblHeight);
            lblResult.setForeground(Color.white);
            lblResult.setFont(lblFont);

            lblPushUnderCounter++;
            /*  if (lblPushRightCounter == 1) {
                lblPushRightCounter = 0;
            } else {
                lblPushRightCounter++;
            }*/

        }
        return lblResult;
    }

    public void setLblResult(JLabel lblResult) {
        this.lblResult = lblResult;
    }

    public JTextField getTxtBarcodeNo() {

        if (txtBarcodeNo == null) {
            txtBarcodeNo = new JTextField();
            txtBarcodeNo.setBounds(txtLeftSpace + (txtPushSpaceRight * txtPushRightCounter), txtTopSpace + txtPushUnderCounter * txtPushSpaceUnder, txtWidth, txtHeight);
            txtBarcodeNo.setForeground(Color.BLACK);
            txtBarcodeNo.setFont(txtFont);

            if (txtPushRightCounter == 1) {
                txtPushRightCounter = 0;
            } else {
                txtPushRightCounter++;
            }

        }
        return txtBarcodeNo;
    }

    public void setTxtBarcodeNo(JTextField txtBarcodeNo) {
        this.txtBarcodeNo = txtBarcodeNo;
    }

    public JTextField getTxtStudentNo() {

        if (txtStudentNo == null) {

            txtStudentNo = new JTextField();
            txtStudentNo.setBounds(txtLeftSpace + (txtPushSpaceRight * txtPushRightCounter), txtTopSpace + txtPushUnderCounter * txtPushSpaceUnder, txtWidth, txtHeight);
            txtStudentNo.setForeground(Color.BLACK);
            txtStudentNo.setFont(txtFont);
            txtPushUnderCounter++;
            if (txtPushRightCounter == 1) {
                txtPushRightCounter = 0;
            } else {
                txtPushRightCounter++;
            }

        }
        return txtStudentNo;
    }

    public void setTxtStudentNo(JTextField txtStudentNo) {
        this.txtStudentNo = txtStudentNo;
    }

    public JTextField getTxtBookName() {
        if (txtBookName == null) {

            txtBookName = new JTextField();
            txtBookName.setBounds(txtLeftSpace + (txtPushSpaceRight * txtPushRightCounter), txtTopSpace + txtPushUnderCounter * txtPushSpaceUnder, txtWidth * 5 / 2, txtHeight);
            txtBookName.setForeground(Color.BLACK);
            txtBookName.setFont(txtFont);
            txtBookName.setEditable(false); // --> change a little bit background color
            // txtBookName.setFocusable(false);  // do not change background
            ToolTipManager ttm = ToolTipManager.sharedInstance();
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtBookName.setToolTipText("Buraya Erişemezsiniz");
            txtPushUnderCounter++;

        }
        return txtBookName;
    }

    public void setTxtBookName(JTextField txtBookName) {
        this.txtBookName = txtBookName;
    }

    public JTextField getTxtAuthorName() {
        if (txtAuthorName == null) {

            txtAuthorName = new JTextField();
            txtAuthorName.setBounds(txtLeftSpace + (txtPushSpaceRight * txtPushRightCounter), txtTopSpace + txtPushUnderCounter * txtPushSpaceUnder, txtWidth, txtHeight);
            txtAuthorName.setForeground(Color.BLACK);
            txtAuthorName.setFont(txtFont);
            txtAuthorName.setEditable(false); // --> change a little bit background color
            // txtAuthorName.setFocusable(false);  // do not change background
            ToolTipManager ttm = ToolTipManager.sharedInstance();
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtAuthorName.setToolTipText("Buraya Erişemezsiniz");
            if (txtPushRightCounter == 1) {
                txtPushRightCounter = 0;
            } else {
                txtPushRightCounter++;
            }

        }
        return txtAuthorName;
    }

    public void setTxtAuthorName(JTextField txtAuthorName) {
        this.txtAuthorName = txtAuthorName;
    }

    public JTextField getTxtStudentName() {
        if (txtStudentName == null) {

            txtStudentName = new JTextField();
            txtStudentName.setBounds(txtLeftSpace + (txtPushSpaceRight * txtPushRightCounter), txtTopSpace + txtPushUnderCounter * txtPushSpaceUnder, txtWidth, txtHeight);
            txtStudentName.setForeground(Color.BLACK);
            txtStudentName.setFont(txtFont);
            txtStudentName.setEditable(false); // --> change a little bit background color
            // txtStudentName.setFocusable(false);  // do not change background
            ToolTipManager ttm = ToolTipManager.sharedInstance();
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtStudentName.setToolTipText("Buraya Erişemezsiniz");
            txtPushUnderCounter++;
            if (txtPushRightCounter == 1) {
                txtPushRightCounter = 0;
            } else {
                txtPushRightCounter++;
            }

        }
        return txtStudentName;
    }

    public void setTxtStudentName(JTextField txtStudentName) {
        this.txtStudentName = txtStudentName;
    }

    public JTextField getTxtResult() {
        if (txtResult == null) {

            txtResult = new JTextField();
            txtResult.setBounds(txtLeftSpace + (txtPushSpaceRight * txtPushRightCounter), txtTopSpace + txtPushUnderCounter * txtPushSpaceUnder, txtWidth * 5 / 2, txtHeight);
            txtResult.setForeground(Color.BLACK);
            txtResult.setFont(txtFont);
            txtResult.setEditable(false); // --> change a little bit background color
            // txtResult.setFocusable(false);  // do not change background
            ToolTipManager ttm = ToolTipManager.sharedInstance();
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtResult.setToolTipText("Buraya Erişemezsiniz");
            txtPushUnderCounter++;

            if (txtPushRightCounter == 1) {
                txtPushRightCounter = 0;
            } else {
                txtPushRightCounter++;
            }

        }
        return txtResult;
    }

    public void setTxtResult(JTextField txtResult) {
        this.txtResult = txtResult;
    }

    public JButton getBtnComeBack() {
        if (btnComeBack == null) {
            btnComeBack = new JButton("Geri dön");
            btnComeBack.setBounds(50, 450, 150, 50);
            btnComeBack.setBackground(Color.white);
            btnComeBack.setFont(txtFont);
            btnComeBack.setCursor(new Cursor(12));

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
