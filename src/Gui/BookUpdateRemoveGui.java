package Gui;

import Logic.ActionsBook;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ToolTipManager;

public class BookUpdateRemoveGui extends JPanel {

    MainGui mg;
    JFrame jf;

    JLabel lblToBeChangedBarcodeNo;
    JLabel lblNewBarcodeNo;
    JLabel lblNewBookName;
    JLabel lblNewAuthorName;
    JLabel lblResult;
    JLabel lblNewCategory;

    JTextField txtToBeChangedBarcodeNo;
    JTextField txtNewBarcodeNo;
    JTextField txtNewBookName;
    JTextField txtNewAuthorName;
    JTextField txtResult;
    JTextField txtNewCategory;

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
    ActionsBook action = new ActionsBook(this);

    public BookUpdateRemoveGui(MainGui mg) {
        setMg(mg);
        this.setBounds(getJf().getBounds());
        this.setBackground(new Color(50, 150, 150));
        this.setLayout(null);
        getMg().getJf().setTitle("KİTAP GÜNCELLEME & SİLME");

        setJf(mg.getJf());
        mg.getJp().setVisible(false);
        getJf().add(this);

        this.add(getLblToBeChangedBarcodeNo());
        this.add(getLblNewBarcodeNo());
        this.add(getLblNewCategory());
        this.add(getLblNewBookName());
        this.add(getLblNewAuthorName());
        this.add(getLblResult());

        this.add(getTxtToBeChangedBarcodeNo());
        this.add(getTxtNewBarcodeNo());
        this.add(getTxtNewCategory());
        this.add(getTxtNewBookName());
        this.add(getTxtNewAuthorName());
        this.add(getTxtResult());
        this.add(getBtnComeBack());
        this.add(getBtnUpdate());
        this.add(getBtnDelete());
        getBtnComeBack().addActionListener(action);
        getBtnDelete().addActionListener(action);
        getBtnUpdate().addActionListener(action);
        getTxtToBeChangedBarcodeNo().addActionListener(action);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        g.setColor(Color.orange);
        g.fillRect(0, heightpaintY, 850, 10);

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

    /* public JPanel getJp() {
        if (jp == null) {
            jp = new JPanel();
            jp.setBounds(getJf().getBounds());
            jp.setBackground(new Color(0, 170, 0));
        }

        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }*/
    public JLabel getLblToBeChangedBarcodeNo() {
        if (lblToBeChangedBarcodeNo == null) {
            lblToBeChangedBarcodeNo = new JLabel("Güncellenecek kitap Barkod Numarası");
            lblToBeChangedBarcodeNo.setBounds(leftSpace, 15, lblWidth, lblHeight);
            lblToBeChangedBarcodeNo.setForeground(Color.white);
            lblToBeChangedBarcodeNo.setFont(lblFont);

        }
        return lblToBeChangedBarcodeNo;
    }

    public void setLblToBeChangedBarcodeNo(JLabel lblToBeChangedBarcodeNo) {
        this.lblToBeChangedBarcodeNo = lblToBeChangedBarcodeNo;
    }

    public JLabel getLblNewBarcodeNo() {

        if (lblNewBarcodeNo == null) {
            lblNewBarcodeNo = new JLabel("Yeni Barkod Numarası");
            lblNewBarcodeNo.setBounds(leftSpace, lblTopSpace + (lblHeight + txtHeight) * lblPushUnderCounter, lblWidth, lblHeight);
            lblNewBarcodeNo.setForeground(Color.WHITE);
            lblNewBarcodeNo.setFont(lblFont);

        }

        return lblNewBarcodeNo;
    }

    public void setLblNewBarcodeNo(JLabel lblNewBarcodeNo) {
        this.lblNewBarcodeNo = lblNewBarcodeNo;
    }

    public JLabel getLblNewCategory() {
        if (lblNewCategory == null) {
            lblNewCategory = new JLabel("Yeni Kategori Numarası");
            lblNewCategory.setBounds(leftSpace + txtWidth + 50, lblTopSpace + (lblHeight + txtHeight) * lblPushUnderCounter, lblWidth, lblHeight);
            lblNewCategory.setForeground(Color.WHITE);
            lblNewCategory.setFont(lblFont);
            lblPushUnderCounter++;
        }
        return lblNewCategory;
    }

    public void setLblNewCategory(JLabel lblNewCategory) {
        this.lblNewCategory = lblNewCategory;
    }

    public JLabel getLblNewBookName() {

        if (lblNewBookName == null) {
            lblNewBookName = new JLabel("Yeni Kitap İsmi ");
            lblNewBookName.setBounds(leftSpace, lblTopSpace + (lblHeight + txtHeight + 30) * lblPushUnderCounter, lblWidth, lblHeight);
            lblNewBookName.setForeground(Color.WHITE);
            lblNewBookName.setFont(lblFont);
            lblPushUnderCounter++;
        }

        return lblNewBookName;
    }

    public void setLblNewBookName(JLabel lblNewBookName) {
        this.lblNewBookName = lblNewBookName;
    }

    public JLabel getLblNewAuthorName() {
        if (lblNewAuthorName == null) {
            lblNewAuthorName = new JLabel("Yeni Yazar İsmi");
            lblNewAuthorName.setBounds(leftSpace, lblTopSpace + (lblHeight + txtHeight + 30) * lblPushUnderCounter, lblWidth, lblHeight);
            lblNewAuthorName.setForeground(Color.WHITE);
            lblNewAuthorName.setFont(lblFont);
            lblPushUnderCounter++;
        }
        return lblNewAuthorName;
    }

    public void setLblNewAuthorName(JLabel lblNewAuthorName) {
        this.lblNewAuthorName = lblNewAuthorName;
    }

    public JLabel getLblResult() {
        if (lblResult == null) {
            lblResult = new JLabel("SONUÇ");
            //lblResult.setBounds(leftSpace, lblTopSpace + (lblHeight + txtHeight + 30) * lblPushUnderCounter, lblWidth, lblHeight);
            lblResult.setBounds(leftSpace + lblWidth + 42, 15, lblWidth, lblHeight);
            lblResult.setForeground(Color.WHITE);
            lblResult.setFont(lblFont);

            lblPushUnderCounter++;
        }
        return lblResult;
    }

    public void setLblResult(JLabel lblResult) {
        this.lblResult = lblResult;
    }

    public JTextField getTxtToBeChangedBarcodeNo() {
        if (txtToBeChangedBarcodeNo == null) {
            txtToBeChangedBarcodeNo = new JTextField();
            txtToBeChangedBarcodeNo.setBounds(leftSpace, 50, txtWidth, txtHeight);
            txtToBeChangedBarcodeNo.setFont(txtFont);

        }

        return txtToBeChangedBarcodeNo;
    }

    public void setTxtToBeChangedBarcodeNo(JTextField txtToBeChangedBarcodeNo) {
        this.txtToBeChangedBarcodeNo = txtToBeChangedBarcodeNo;
    }

    public JTextField getTxtNewBarcodeNo() {
        if (txtNewBarcodeNo == null) {
            txtNewBarcodeNo = new JTextField("asd");
            txtNewBarcodeNo.setBounds(leftSpace, txtTopSpace + (txtPushSpaceUnder * txtPushUnderCounter), txtWidth, txtHeight);
            txtNewBarcodeNo.setFont(txtFont);

        }
        return txtNewBarcodeNo;
    }

    public void setTxtNewBarcodeNo(JTextField txtNewBarcodeNo) {
        this.txtNewBarcodeNo = txtNewBarcodeNo;
    }

    public JTextField getTxtNewCategory() {
        //lblNewCategory.setBounds(leftSpace + txtWidth + 50, lblTopSpace + (lblHeight + txtHeight) * lblPushUnderCounter, lblWidth, lblHeight);
        if (txtNewCategory == null) {
            txtNewCategory = new JTextField("yeni kategori numarası 6556465");
            txtNewCategory.setBounds(leftSpace + txtWidth + 50, txtTopSpace + (txtPushSpaceUnder * txtPushUnderCounter), txtWidth, txtHeight);
            txtNewCategory.setFont(txtFont);
            txtPushUnderCounter++;
        }
        return txtNewCategory;
    }

    public void setTxtNewCategory(JTextField txtNewCategory) {
        this.txtNewCategory = txtNewCategory;
    }

    public JTextField getTxtNewBookName() {
        if (txtNewBookName == null) {
            txtNewBookName = new JTextField();
            txtNewBookName.setBounds(leftSpace, txtTopSpace + (txtPushSpaceUnder * txtPushUnderCounter), txtWidth * 2 + 50, txtHeight);
            txtNewBookName.setFont(txtFont);
            txtPushUnderCounter++;
        }
        return txtNewBookName;
    }

    public void setTxtNewBookName(JTextField txtNewBookName) {
        this.txtNewBookName = txtNewBookName;
    }

    public JTextField getTxtNewAuthorName() {
        if (txtNewAuthorName == null) {
            txtNewAuthorName = new JTextField();
            txtNewAuthorName.setBounds(leftSpace, txtTopSpace + (txtPushSpaceUnder * txtPushUnderCounter), txtWidth, txtHeight);
            txtNewBookName.setFont(txtFont);

        }
        return txtNewAuthorName;
    }

    public void setTxtNewAuthorName(JTextField txtNewAuthorName) {
        this.txtNewAuthorName = txtNewAuthorName;
    }

    public JTextField getTxtResult() {
        if (txtResult == null) {
            txtResult = new JTextField();
            //txtResult.setBounds(leftSpace, txtTopSpace + (txtPushSpaceUnder * txtPushUnderCounter), txtWidth, txtHeight);
            txtResult.setBounds(txtWidth + leftSpace + 50, 50, txtWidth, txtHeight);
            txtResult.setFont(txtFont);
            txtResult.setEditable(false);
            ToolTipManager ttm = ToolTipManager.sharedInstance();
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtResult.setToolTipText("Buraya erişemezsiniz");

            txtPushUnderCounter++;
        }
        return txtResult;
    }

    public void setTxtResult(JTextField txtResult) {
        this.txtResult = txtResult;
    }

    public JButton getBtnComeBack() {
        if (btnComeBack == null) {
            btnComeBack = new JButton("Geri Dön");
            // btnComeBack.setBounds(leftSpace + txtWidth + 50, txtTopSpace + (txtPushSpaceUnder * txtPushUnderCounter) - 90, txtWidth, txtHeight);
            btnComeBack.setBounds(leftSpace, txtTopSpace + (txtPushSpaceUnder * txtPushUnderCounter), txtWidth, txtHeight);
        }
        return btnComeBack;
    }

    public void setBtnComeBack(JButton btnComeBack) {
        this.btnComeBack = btnComeBack;
    }

    public JButton getBtnUpdate() {
        if (btnUpdate == null) {
            btnUpdate = new JButton("Güncelle");
            btnUpdate.setBounds(leftSpace + txtWidth + 50, txtTopSpace + (txtPushSpaceUnder * txtPushUnderCounter) - 90, txtWidth, txtHeight);
            //txtNewAuthorName.setBounds(leftSpace, txtTopSpace + (txtPushSpaceUnder * txtPushUnderCounter), txtWidth, txtHeight);
            txtPushUnderCounter++;
        }
        return btnUpdate;
    }

    public void setBtnUpdate(JButton btnUpdate) {
        this.btnUpdate = btnUpdate;
    }

    public JButton getBtnDelete() {
        if (btnDelete == null) {
            btnDelete = new JButton();
            btnDelete = new JButton("Sil");
            btnDelete.setBounds(leftSpace + txtWidth + 50, txtTopSpace + (txtPushSpaceUnder * txtPushUnderCounter) - 90, txtWidth, txtHeight);

        }
        return btnDelete;
    }

    public void setBtnDelete(JButton btnDelete) {
        this.btnDelete = btnDelete;
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
