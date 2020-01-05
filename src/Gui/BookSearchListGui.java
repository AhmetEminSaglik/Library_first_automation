package Gui;

import Logic.ActionsBook;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

public class BookSearchListGui {

    MainGui mg;
    JFrame jf;
    JPanel jp;
    JLabel lblBookName;
    JLabel lblAuthorName;
    JLabel lblBarcodeNo;
    JLabel lblCategory;
    JTable table;
    JScrollPane sp;

    JTextField txtBookName;
    JTextField txtAuthorName;
    JTextField txtBarcodeNo;
    JTextField txtCategory;

    JButton btnComeBack;
    public String data[][] = {
        {"544656545",  "ALINDI","Kim Korkar Javadan", "Yazlılım", "Şahin tümer"},
        {"789653684", "Müsayit", "Enerji Korunum Yolları","Enerji", "Canan Atasoy"},
        {"544656545",  "ALINDI","Kim Korkar Javadan", "Yazlılım", "Şahin tümer"},
        {"789653684", "Müsayit", "Enerji Korunum Yolları","Enerji", "Canan Atasoy"},
        {"544656545",  "ALINDI","Kim Korkar Javadan", "Yazlılım", "Şahin tümer"},
        {"789653684", "Müsayit", "Enerji Korunum Yolları","Enerji", "Canan Atasoy"},
        {"544656545",  "ALINDI","Kim Korkar Javadan", "Yazlılım", "Şahin tümer"},
        {"789653684", "Müsayit", "Enerji Korunum Yolları","Enerji", "Canan Atasoy"},
        {"544656545",  "ALINDI","Kim Korkar Javadan", "Yazlılım", "Şahin tümer"},
        {"789653684", "Müsayit", "Enerji Korunum Yolları","Enerji", "Canan Atasoy"},
        {"544656545",  "ALINDI","Kim Korkar Javadan", "Yazlılım", "Şahin tümer"},
        {"789653684", "Müsayit", "Enerji Korunum Yolları","Enerji", "Canan Atasoy"},
        {"544656545",  "ALINDI","Kim Korkar Javadan", "Yazlılım", "Şahin tümer"},
        {"789653684", "Müsayit", "Enerji Korunum Yolları","Enerji", "Canan Atasoy"},
        {"544656545",  "ALINDI","Kim Korkar Javadan", "Yazlılım", "Şahin tümer"},
        {"789653684", "Müsayit", "Enerji Korunum Yolları","Enerji", "Canan Atasoy"},
        {"544656545",  "ALINDI","Kim Korkar Javadan", "Yazlılım", "Şahin tümer"},
        {"789653684", "Müsayit", "Enerji Korunum Yolları","Enerji", "Canan Atasoy"},
        {"789653684", "Müsayit", "Enerji Korunum Yolları","Enerji", "Canan Atasoy"},
        {"789653684", "Müsayit", "Enerji Korunum Yolları","Enerji", "Canan Atasoy"},
        {"544656545",  "ALINDI","Kim Korkar Javadan", "Yazlılım", "Şahin tümer"},
        {"789653684", "Müsayit", "Enerji Korunum Yolları","Enerji", "Canan Atasoy"},
        {"544656545",  "ALINDI","Kim Korkar Javadan", "Yazlılım", "Şahin tümer"},
        {"789653684", "Müsayit", "Enerji Korunum Yolları","Enerji", "Canan Atasoy"},
        {"544656545",  "ALINDI","Kim Korkar Javadan", "Yazlılım", "Şahin tümer"},
        {"789653684", "Müsayit", "Enerji Korunum Yolları","Enerji", "Canan Atasoy"},
        {"544656545",  "ALINDI","Kim Korkar Javadan", "Yazlılım", "Şahin tümer"},
        {"789653684", "Müsayit", "Enerji Korunum Yolları","Enerji", "Canan Atasoy"},
        {"544656545",  "ALINDI","Kim Korkar Javadan", "Yazlılım", "Şahin tümer"},
        {"789653684", "Müsayit", "Enerji Korunum Yolları","Enerji", "Canan Atasoy"},
        {"544656545",  "ALINDI","Kim Korkar Javadan", "Yazlılım", "Şahin tümer"},
        {"789653684", "Müsayit", "Enerji Korunum Yolları","Enerji", "Canan Atasoy"}, //   {"101", "Sachin", "700000"}
    };
    public String column[] = {"Barkod No","Kitap Durumu", "Kitap Adı",  "Kitap Kategori",   "Yazar Adı"};

    final int lblTopSpace = 10;
    final int leftSpace = 50;
    final int txtWidth = 275;
    final int txtHeight = 40;
    final int lblWidth = 150;
    final int lblHeight = 20;
    final int pushRightSpace = txtWidth + 137;
    final int txtTopSpace = lblTopSpace + lblHeight + 10;
    int pushRightCounter = 0;

    Font font_lbl = new Font("monospaced", Font.BOLD, 17);
    Font font_txt = new Font("monospaced", Font.BOLD, 17);
    ActionsBook action = new ActionsBook(this);

    public BookSearchListGui(MainGui mg) {
        setMg(mg);
        setJf(mg.getJf());
        getJf().setTitle("KİTAP LİSTESİ & SORGULAMA ");

        getJf().add(getJp());
        getJp().add(getLblAuthorName());
        getJp().add(getLblBarcodeNo());
        getJp().add(getLblBookName());
        getJp().add(getLblCategory());
        getJp().add(getTxtAuthorName());
        getJp().add(getTxtBarcodeNo());
        getJp().add(getTxtBookName());
        getJp().add(getTxtCategory());
        getJp().add(getBtnComeBack());
        getTxtAuthorName().addActionListener(action);
        getTxtBarcodeNo().addActionListener(action);
        getTxtBookName().addActionListener(action);
        getBtnComeBack().addActionListener(action);

        getTxtCategory().addActionListener(action);
        getJp().add(getSp());
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
            lblBookName.setBounds(leftSpace, lblTopSpace + lblHeight + txtHeight + 15, lblWidth, lblHeight);
            lblBookName.setFont(font_lbl);
            lblBookName.setForeground(Color.WHITE);

        }
        return lblBookName;
    }

    public void setLblBookName(JLabel lblBookName) {
        this.lblBookName = lblBookName;
    }

    public JLabel getLblCategory() {

        if (lblCategory == null) {
            pushRightCounter = 1;
            lblCategory = new JLabel("Kitap Kategori");
            System.out.println("pushRightSpace : " + pushRightSpace + " ~ pushRightCounter : " + pushRightCounter);
            lblCategory.setBounds(leftSpace + (pushRightSpace * pushRightCounter), lblTopSpace + lblHeight + txtHeight + 15, lblWidth, lblHeight);

            lblCategory.setFont(font_lbl);
            lblCategory.setForeground(Color.WHITE);

        }
        return lblCategory;
    }

    public void setLblCategory(JLabel lblCategory) {
        this.lblCategory = lblCategory;
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
        if (table == null) {

            table = new JTable(data, column);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table.setRowHeight(15);
            table.getColumnModel().getColumn(0).setPreferredWidth(150);
            table.getColumnModel().getColumn(1).setPreferredWidth(150);
            table.getColumnModel().getColumn(2).setPreferredWidth(450);
            table.getColumnModel().getColumn(3).setPreferredWidth(200);
            table.getColumnModel().getColumn(4).setPreferredWidth(150);
            table.setDefaultEditor(Object.class, null);
            table.setFont(font_txt);
            table.setCursor(new Cursor(12));
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

            // table.setDefaultRenderer(Object.class, centerRenderer); --> bütün sutunları ortalar
        }

        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JScrollPane getSp() {
        if (sp == null) {
            sp = new JScrollPane(getTable());
            sp.setBounds(leftSpace, txtTopSpace + lblHeight + txtHeight * 3, 700, 250);

        }
        return sp;
    }

    public void setSp(JScrollPane sp) {
        this.sp = sp;
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

    public JTextField getTxtBookName() {
        if (txtBookName == null) {
            pushRightCounter = 0;
            txtBookName = new JTextField("Kitap Adı");
            txtBookName.setBounds(leftSpace, txtTopSpace + lblHeight + txtHeight + 15, txtWidth, txtHeight);
            txtBookName.setFont(font_txt);
            pushRightCounter++;
        }

        return txtBookName;
    }

    public void setTxtBookName(JTextField txtBookName) {
        this.txtBookName = txtBookName;
    }

    public JTextField getTxtCategory() {
        if (txtCategory == null) {
            pushRightCounter = 1;
            txtCategory = new JTextField("Kitap Kategori");
            System.out.println("pushRightSpace : " + pushRightSpace + "  ~ pushRightCounter : " + pushRightCounter);
            txtCategory.setBounds(leftSpace + (pushRightSpace * pushRightCounter), txtTopSpace + lblHeight + txtHeight + 15, txtWidth, txtHeight);
            txtCategory.setFont(font_txt);

        }

        return txtCategory;
    }

    public void setTxtCategory(JTextField txtCategory) {
        this.txtCategory = txtCategory;
    }

    public JTextField getTxtAuthorName() {
        if (txtAuthorName == null) {

            pushRightCounter = 0;
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

    public JButton getBtnComeBack() {
        if (btnComeBack == null) {
            btnComeBack = new JButton("Geri dön");
            btnComeBack.setBounds(leftSpace, 450, 150, 30);

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
