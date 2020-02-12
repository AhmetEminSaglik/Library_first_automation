package Gui;

import Logic.ActionTimeFine;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

public final class TimeControlExtraTimeGui extends JPanel {

    JFrame jf;

    JLabel lblBookBarcodeNoToExtendTime;
    JLabel lblResult;
    JLabel lblBookNameToExtendTime;
    JLabel lblSearchStudentNo;
    JLabel lblSearchBookBarcodeNo;

    JTextField txtBookBarcodeNoToExtendTime;
    JTextField txtResult;
    JTextField txtBookNameToExtendTime;
    JTextField txtSearchStudentNo;
    JTextField txtSearchBookBarcodeNo;
    JTable tbl;
    JScrollPane sp;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final double screenSizeWidth = screenSize.getWidth();
    final double screenSizeHeight = screenSize.getHeight();

    public String HeaderOfTable[] = {"", "Öğrenci No", "Öğrenci Adı Soyadı", "Kitap Barkod No", "Kalan gün sayısı ", "Kitap adı"};
    public String DataForTable[][] = {};

    JButton btnExtendTime; //süreyi uzat
    JButton btnSearch;
    JButton btnComeBack;

    final int lblHeight = (int) (screenSizeHeight / 25.6);
    final int lblWidth = (int) (screenSizeWidth / 5.464);
    final int txtHeight = lblHeight;
    final int txtWidth = lblWidth;
    final int lblTopSpace = (int) (screenSizeHeight / 76.8);
    final int txtTopSpace = lblTopSpace + lblHeight + (int) (screenSizeHeight / 153.6);
    final int lblPushRight = txtWidth + (int) (screenSizeWidth / 27.32);
    final int lblPushUnder = lblHeight + txtHeight + (int) (screenSizeHeight / 25.6);
    final int txtPushRight = txtWidth + (int) (screenSizeWidth / 27.32);
    //final int txtPushUnder =0;
    final int leftSpace = (int) (screenSizeWidth / 45.53333333333333);
    int pushUnderCounter = 0;
    Font font_lbl = new Font("", Font.BOLD, 18);
    Font font_txt = new Font("", Font.BOLD, 15);
    Font fontPlaceHolder = new Font("", Font.ITALIC, 15);
    MainGui mg;
    public ActionTimeFine action = new ActionTimeFine(this);
    public TableModel model = null;

    public TimeControlExtraTimeGui(MainGui mg) {
        setMg(mg);
        setJf(mg.getJf());
        getJf().setTitle("SÜRE KONTROL & UZATMA ");

        this.setLayout(null);
        this.setBounds(getJf().getBounds());
        this.setBackground(new Color(99, 110, 114));
        this.setVisible(true);

        this.add(getLblBookBarcodeNoToExtendTime());
        this.add(getBtnExtendTime());
        this.add(getLblResult());
        this.add(getTxtBookBarcodeNoToExtendTime());
        this.add(getTxtResult());
        pushUnderCounter++;
        this.add(getLblBookNameToExtendTime());
        this.add(getTxtBookNameToExtendTime());
        pushUnderCounter++;
        this.add(getLblSearchStudentNo());
        this.add(getLblSearchBookBarcodeNo());
        this.add(getTxtSearchStudentNo());
        this.add(getTxtSearchBookBarcodeNo());
        this.add(getSp());
        this.add(getBtnComeBack());
        this.add(getBtnSearch());
        getBtnComeBack().addActionListener(action);
        getBtnExtendTime().addActionListener(action);
        getBtnSearch().addActionListener(action);
        getTxtBookBarcodeNoToExtendTime().addActionListener(action);
        getTxtSearchStudentNo().addActionListener(action);
        getTxtSearchBookBarcodeNo().addActionListener(action);
        getTxtSearchStudentNo().addFocusListener(action);
        getTxtSearchBookBarcodeNo().addFocusListener(action);
        getTxtBookBarcodeNoToExtendTime().addFocusListener(action);
        //model.addListSelectionListener(action);

        getJf().add(this);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        g.setColor(new Color(255, 255, 0));//rgb(235, 47, 6)
        g.fillRect(0, (int) (screenSizeHeight / 4.266666666666667),
                (int) (screenSizeWidth / 1.6070588235294119), (int) (screenSizeHeight / 76.8));

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

    public JLabel getLblBookBarcodeNoToExtendTime() {
        if (lblBookBarcodeNoToExtendTime == null) {
            lblBookBarcodeNoToExtendTime = new JLabel("Kitap Barkod No");
            lblBookBarcodeNoToExtendTime.setBounds(leftSpace, (lblPushUnder * pushUnderCounter) + lblTopSpace, lblWidth, lblHeight);
            lblBookBarcodeNoToExtendTime.setForeground(new Color(9, 132, 227));
            lblBookBarcodeNoToExtendTime.setFont(font_lbl);

        }
        return lblBookBarcodeNoToExtendTime;
    }

    public void setLblBookBarcodeNoToExtendTime(JLabel lblBookBarcodeNoToExtendTime) {
        this.lblBookBarcodeNoToExtendTime = lblBookBarcodeNoToExtendTime;
    }

    public JLabel getLblResult() {
        if (lblResult == null) {
            lblResult = new JLabel("Sonuç");
            lblResult.setBounds(leftSpace + lblWidth * 2, (lblPushUnder * pushUnderCounter) + lblTopSpace, lblWidth, lblHeight);
            lblResult.setForeground(new Color(9, 132, 227));
            lblResult.setFont(font_lbl);

        }
        return lblResult;
    }

    public void setLblResult(JLabel lblResult) {
        this.lblResult = lblResult;
    }

    public JLabel getLblBookNameToExtendTime() {
        if (lblBookNameToExtendTime == null) {
            lblBookNameToExtendTime = new JLabel("Kitap Adı");
            lblBookNameToExtendTime.setBounds(leftSpace, (lblPushUnder * pushUnderCounter) + lblTopSpace, lblWidth, lblHeight);
            lblBookNameToExtendTime.setForeground(new Color(9, 132, 227));
            lblBookNameToExtendTime.setFont(font_lbl);

        }
        return lblBookNameToExtendTime;
    }

    public void setLblBookNameToExtendTime(JLabel lblBookNameToExtendTime) {
        this.lblBookNameToExtendTime = lblBookNameToExtendTime;
    }

    public JLabel getLblSearchStudentNo() {
        if (lblSearchStudentNo == null) {
            lblSearchStudentNo = new JLabel("Öğrenci No");
            lblSearchStudentNo.setBounds(leftSpace, lblTopSpace + (pushUnderCounter * lblPushUnder), lblWidth, lblHeight);
            lblSearchStudentNo.setForeground(new Color(9, 132, 227));
            lblSearchStudentNo.setFont(font_lbl);
        }
        return lblSearchStudentNo;
    }

    public void setLblSearchStudentNo(JLabel lblSearchStudentNo) {
        this.lblSearchStudentNo = lblSearchStudentNo;
    }

    public JLabel getLblSearchBookBarcodeNo() {
        if (lblSearchBookBarcodeNo == null) {
            lblSearchBookBarcodeNo = new JLabel("Kitap Barkod No");
            lblSearchBookBarcodeNo.setBounds(leftSpace + lblWidth * 2, lblTopSpace + (pushUnderCounter * lblPushUnder), lblWidth, lblHeight);

            lblSearchBookBarcodeNo.setForeground(new Color(9, 132, 227));
            lblSearchBookBarcodeNo.setFont(font_lbl);
        }
        return lblSearchBookBarcodeNo;
    }

    public void setLblSearchBookBarcodeNo(JLabel lblSearchBookBarcodeNo) {
        this.lblSearchBookBarcodeNo = lblSearchBookBarcodeNo;
    }

    public JTextField getTxtBookBarcodeNoToExtendTime() {
        if (txtBookBarcodeNoToExtendTime == null) {
            txtBookBarcodeNoToExtendTime = new JTextField("");
            txtBookBarcodeNoToExtendTime.setBounds(leftSpace, txtTopSpace, txtWidth, txtHeight);
            txtBookBarcodeNoToExtendTime.setForeground(Color.BLACK);
            txtBookBarcodeNoToExtendTime.setFont(font_lbl);
            txtBookBarcodeNoToExtendTime.setBackground(new Color(206, 214, 224));
            txtBookBarcodeNoToExtendTime.setEditable(false);
            txtBookBarcodeNoToExtendTime.setFocusable(false);
            ToolTipManager ttm = ToolTipManager.sharedInstance();
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtBookBarcodeNoToExtendTime.setToolTipText("Buraya Erişemezsiniz");

        }
        return txtBookBarcodeNoToExtendTime;
    }

    public void setTxtBookBarcodeNoToExtendTime(JTextField txtBookBarcodeNoToExtendTime) {
        this.txtBookBarcodeNoToExtendTime = txtBookBarcodeNoToExtendTime;
    }

    public JTextField getTxtResult() {
        if (txtResult == null) {
            txtResult = new JTextField("");
            txtResult.setBounds(leftSpace + lblWidth * 2, txtTopSpace, txtWidth, txtHeight);
            txtResult.setBackground(new Color(206, 214, 224));
            txtResult.setFont(font_txt);
            txtResult.setEditable(false);
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

    public JTextField getTxtBookNameToExtendTime() {
        if (txtBookNameToExtendTime == null) {
            txtBookNameToExtendTime = new JTextField("");
            txtBookNameToExtendTime.setBounds(leftSpace, (lblPushUnder * pushUnderCounter) + txtTopSpace, txtWidth * 3, txtHeight);
            txtBookNameToExtendTime.setBackground(new Color(206, 214, 224));
            txtBookNameToExtendTime.setFont(font_lbl);
            txtBookNameToExtendTime.setEditable(false);
            ToolTipManager ttm = ToolTipManager.sharedInstance();
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtBookNameToExtendTime.setToolTipText("Buraya Erişemezsiniz");

        }
        return txtBookNameToExtendTime;
    }

    public void setTxtBookNameToExtendTime(JTextField txtBookNameToExtendTime) {
        this.txtBookNameToExtendTime = txtBookNameToExtendTime;
    }

    public JTextField getTxtSearchStudentNo() {
        if (txtSearchStudentNo == null) {
            txtSearchStudentNo = new JTextField("Öğrenci No");
            txtSearchStudentNo.setBounds(leftSpace, txtTopSpace + (pushUnderCounter * lblPushUnder), lblWidth, lblHeight);
            txtSearchStudentNo.setForeground(Color.GRAY);
            txtSearchStudentNo.setFont(fontPlaceHolder);
        }
        return txtSearchStudentNo;
    }

    public void setTxtSearchStudentNo(JTextField txtSearchStudentNo) {
        this.txtSearchStudentNo = txtSearchStudentNo;
    }

    public JTextField getTxtSearchBookBarcodeNo() {
        if (txtSearchBookBarcodeNo == null) {
            txtSearchBookBarcodeNo = new JTextField("Kitap Barkod No");
            txtSearchBookBarcodeNo.setBounds(leftSpace + lblWidth * 2, txtTopSpace + (pushUnderCounter * lblPushUnder), lblWidth, lblHeight);
            txtSearchBookBarcodeNo.setForeground(Color.GRAY);
            txtSearchBookBarcodeNo.setFont(fontPlaceHolder);
        }
        return txtSearchBookBarcodeNo;
    }

    public void setTxtSearchBookBarcodeNo(JTextField txtSearchBookBarcodeNo) {
        this.txtSearchBookBarcodeNo = txtSearchBookBarcodeNo;
    }

    public JTable OrganizeTable(JTable table) {
        table.setSelectionBackground(Color.GREEN);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(15);
        table.getColumnModel().getColumn(0).setPreferredWidth((int) (screenSizeWidth / 27.32));
        table.getColumnModel().getColumn(1).setPreferredWidth((int) (screenSizeWidth / 13.66));
        table.getColumnModel().getColumn(2).setPreferredWidth((int) (screenSizeWidth / 6.83));
        table.getColumnModel().getColumn(3).setPreferredWidth((int) (screenSizeWidth / 9.106666666666667));
        table.getColumnModel().getColumn(4).setPreferredWidth((int) (screenSizeWidth / 13.66));
        table.getColumnModel().getColumn(5).setPreferredWidth((int) (screenSizeWidth / 3.415));
        //{"", "Öğrenci No", "Öğrenci Adı Soyadı", "Kitap Barkod No", "Kitap adı", "Kalan gün sayısı "};

        table.setDefaultEditor(Object.class, null);
        table.setFont(font_txt);
        table.setCursor(new Cursor(12));
        table.setRowHeight(20);
        table.setFont(new Font("", Font.BOLD, 15));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        model = table.getModel();
        // table.getSelectionModel().addListSelectionListener(action);
        return table;

    }

    public JTable getTbl() {
        if (tbl == null) {
            tbl = new JTable(DataForTable, HeaderOfTable);
        }
        tbl = OrganizeTable(tbl);
        /*
        tbl.setCursor(new Cursor(12));
        tbl.setFont(new Font("", Font.BOLD, 15));
        tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbl.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(200);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(500);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(100);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbl.setDefaultRenderer(Object.class, centerRenderer);*/

        return tbl;
    }

    public void setTbl(JTable tbl) {
        //OrganizeTable(tbl)
        this.tbl = tbl;
    }

    public JScrollPane getSp() {
        if (sp == null) {
            sp = new JScrollPane(getTbl());
//            model = getTbl().getSelectionModel();

        }
        sp.setBounds(leftSpace, (int) (screenSizeHeight / 2.8444444444444446),
                (int) (screenSizeWidth / 1.7973684210526315), (int) (screenSizeHeight / 4.266666666666667));

        return sp;
    }

    public void setSp(JTable table) {
        table = OrganizeTable(table);
        sp = new JScrollPane(table);
        this.sp = sp;
    }

    public JButton getBtnExtendTime() {
        if (btnExtendTime == null) {
            btnExtendTime = new JButton("Süreyi uzat");
            btnExtendTime.setBounds(leftSpace + txtWidth * 5 / 4, txtTopSpace, lblWidth / 2, txtHeight);

        }
        return btnExtendTime;
    }

    public void setBtnExtendTime(JButton btnExtendTime) {
        this.btnExtendTime = btnExtendTime;
    }

    public JButton getBtnComeBack() {
        if (btnComeBack == null) {
            btnComeBack = new JButton("Geri Dön");
            btnComeBack.setBounds(leftSpace, (int) (screenSizeHeight / 1.6340425531914893),
                    (int) (screenSizeWidth / 13.66), (int) (screenSizeHeight / 25.6));

        }
        return btnComeBack;
    }

    public void setBtnComeBack(JButton btnComeBack) {
        this.btnComeBack = btnComeBack;
    }

    public JButton getBtnSearch() {
        if (btnSearch == null) {
            btnSearch = new JButton("Ara");
            btnSearch.setBounds(leftSpace + txtWidth * 5 / 4, txtTopSpace + (lblPushUnder * pushUnderCounter), lblWidth / 2, txtHeight);
        }
        return btnSearch;
    }

    public void setBtnSearch(JButton btnSearch) {
        this.btnSearch = btnSearch;
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
