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

public class BookAddGui {

    MainGui mg;
    JFrame jf;
    JPanel jp;
    JLabel lblBookBarcodeNo;
    JLabel lblBookName;
    JLabel lblAuthorName;
    JLabel lblCategory;
    JLabel lblResult;

    JTextField txtBookName;
    JTextField txtBookBarcodeNo;
    JTextField txtAuthorName;
    JTextField txtCategory;
    JTextField txtResult;

    ActionsBook bookaction = new ActionsBook(this);
    JButton btnComeBack;
    JButton btnBtnAddBook;
    Font font_lbl = new Font("monospaced", Font.BOLD, 20);
    Font font_txt = new Font("monospaced", Font.BOLD, 15);

    public BookAddGui(MainGui mg) {

        this.mg = mg;
        setJf(mg.getJf());
        getJf().setTitle("KİTAP EKLE");

        addAllThingsONPanel();
        getBtnComeBack().addActionListener(bookaction);
        getBtnAddBook().addActionListener(bookaction);
        getTxtBookBarcodeNo().addActionListener(bookaction);

        getJf().add(getJp());

    }

    public void addAllThingsONPanel() {
        getJp().add(getLblBookBarcodeNo());
        getJp().add(getLblBookName());
        getJp().add(getLblAuthorName());
        getJp().add(getLblResult());
        getJp().add(getLblCategory());
        getJp().add(getTxtBookBarcodeNo());
        getJp().add(getTxtBookName());
        getJp().add(getTxtAuthorName());
        getJp().add(getTxtResult());
        getJp().add(getTxtCategory());
        getJp().add(getBtnComeBack());
        getJp().add(getBtnAddBook());

        getTxtAuthorName().addActionListener(bookaction);
        getTxtCategory().addActionListener(bookaction);
        getTxtBookName().addActionListener(bookaction);
        getTxtBookName().addFocusListener(bookaction);
        getTxtCategory().addFocusListener(bookaction);
        getTxtAuthorName().addFocusListener(bookaction);

    }

    /*Mainguiyi gönderip jf üzerine burdaki jp'yi ekleyebilirim
    
     */
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
            getJp().setBackground(new Color(87, 88, 187));
            jp.setLayout(null);
        }
        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

    public JLabel getLblBookBarcodeNo() {
        if (lblBookBarcodeNo == null) {
            lblBookBarcodeNo = new JLabel("Kitap Barkod Numarası ");
            lblBookBarcodeNo.setBounds(20, 0, 450, 50);
            lblBookBarcodeNo.setFont(font_lbl);

        }

        return lblBookBarcodeNo;
    }

    public void setLblBookBarcodeNo(JLabel lblBookBarcodeNo) {
        this.lblBookBarcodeNo = lblBookBarcodeNo;
    }

    public JLabel getLblBookName() {
        if (lblBookName == null) {
            lblBookName = new JLabel("Kitap Adı");
            lblBookName.setBounds(20, 100, 450, 50);
            lblBookName.setFont(font_lbl);

        }
        return lblBookName;
    }

    public void setLblBookName(JLabel lblBookName) {
        this.lblBookName = lblBookName;
    }

    public JLabel getLblAuthorName() {
        if (lblAuthorName == null) {
            lblAuthorName = new JLabel("Yazar Adı");
            lblAuthorName.setBounds(20, 200, 450, 50);
            lblAuthorName.setFont(font_lbl);
        }
        return lblAuthorName;
    }

    public void setLblAuthorName(JLabel lblAuthorName) {
        this.lblAuthorName = lblAuthorName;
    }

    public JLabel getLblCategory() {
        if (lblCategory == null) {
            lblCategory = new JLabel("Kategori Adı");
            lblCategory.setBounds(400, 200, 450, 50);
            lblCategory.setFont(font_lbl);
        }
        return lblCategory;
    }

    public void setLblCategory(JLabel lblCategory) {
        this.lblCategory = lblCategory;
    }

    public JLabel getLblResult() {
        if (lblResult == null) {
            lblResult = new JLabel("SONUC");
            lblResult.setBounds(515, 0, 450, 50);
            lblResult.setFont(font_lbl);

        }
        return lblResult;
    }

    public void setLblResult(JLabel lblResult) {
        this.lblResult = lblResult;
    }

    public JTextField getTxtBookBarcodeNo() {
        if (txtBookBarcodeNo == null) {
            txtBookBarcodeNo = new JTextField("");
            txtBookBarcodeNo.setBounds(20, 50, 300, 40);
            txtBookBarcodeNo.setFont(font_txt);

        }
        return txtBookBarcodeNo;
    }

    public void setTxtBookBarcodeNo(JTextField txtBookBarcodeNo) {
        this.txtBookBarcodeNo = txtBookBarcodeNo;
    }

    public JTextField getTxtBookName() {
        if (txtBookName == null) {
            txtBookName = new JTextField("");
            txtBookName.setBounds(20, 150, 680, 40);
            txtBookName.setFont(font_txt);
        }
        return txtBookName;
    }

    public void setTxtBookName(JTextField txtBookName) {
        this.txtBookName = txtBookName;
    }

    public JTextField getTxtAuthorName() {
        if (txtAuthorName == null) {
            txtAuthorName = new JTextField("");
            txtAuthorName.setBounds(20, 250, 300, 40);
            txtAuthorName.setFont(font_txt);
        }
        return txtAuthorName;
    }

    public void setTxtAuthorName(JTextField txtAuthorName) {
        this.txtAuthorName = txtAuthorName;
    }

    public JTextField getTxtCategory() {

        if (txtCategory == null) {
            txtCategory = new JTextField("");
            txtCategory.setBounds(400, 250, 300, 40);
            txtCategory.setFont(font_txt);
        }
        return txtCategory;
    }

    public void setTxtCategory(JTextField txtCategory) {
        this.txtCategory = txtCategory;
    }

    public JTextField getTxtResult() {
        if (txtResult == null) {
            txtResult = new JTextField("");
            txtResult.setBounds(400, 50, 300, 40);
            //txtBookBarcodeNo.setBounds(20, 50, 300, 40);
            txtResult.setFont(new Font("monospaced", Font.BOLD, 17));

            txtResult.setEditable(false);
            txtResult.setBackground(Color.WHITE);
            txtResult.setFocusable(false);
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
            btnComeBack.setBounds(50, 400, 150, 50);
            btnComeBack.setBackground(Color.white);
            btnComeBack.setFont(font_txt);

            btnComeBack.setCursor(new Cursor(12));
        }
        return btnComeBack;
    }

    public void setBtnComeBack(JButton btnComeBack) {
        this.btnComeBack = btnComeBack;
    }

    public JButton getBtnAddBook() {
        if (btnBtnAddBook == null) {
            btnBtnAddBook = new JButton("Kitabı Ekle");
            btnBtnAddBook.setBounds(400, 400, 150, 50);
            btnBtnAddBook.setBackground(Color.white);
            btnBtnAddBook.setFont(font_txt);
            btnBtnAddBook.setCursor(new Cursor(12));
        }
        return btnBtnAddBook;
    }

    public void setBtnAddBook(JButton btnBtnAddBook) {
        this.btnBtnAddBook = btnBtnAddBook;
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
