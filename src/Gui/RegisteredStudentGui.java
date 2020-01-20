package Gui;

import Logic.ActionStudent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

public class RegisteredStudentGui {

    JFrame jf;
    JPanel jp;
    JLabel lblName;
    JLabel lblSurname;
    JLabel lblNo;
    JTable table;
    JScrollPane sp;
    JTextField txtName;
    JTextField txtSurname;
    JTextField txtNo;
    JButton btnComeBack;
    public String DataOfTable[][] = {
        {"486581", "Ahmet Emin SAĞLIK", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "AhmetEminSaglik@hotmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"},
        {"486581", "Ebru", "Ebru@gmail.com", "0538 654 78 45"}};
    public String HeadersOfTable[] = {"Öğrenci No", "Öğrenci Adı-Soyadı", "Email", "Telefon"};

    final int lblTopSpace = 10;
    final int leftSpace = 30;
    final int txtWidth = 220;
    final int txtHeight = 40;
    final int lblWidth = txtWidth;
    final int lblHeight = 25;
    final int pushRightSpace = 50;
    final int txtTopSpace = lblTopSpace + lblHeight + 10;
    final int topSpace = 15;
    int pushRightCounter = 0;
    MainGui mg;
    ActionStudent action = new ActionStudent(this);

    Font font_lbl = new Font("", Font.BOLD, 17);
    Font font_txt = new Font("", Font.BOLD, 13);

    public RegisteredStudentGui(MainGui mg) {
        setMg(mg);
        setJf(mg.getJf());
        getJf().setTitle("KAYITLI ÖĞRENCİ SAYFASI");
        getJp().add(getLblName());
        getJp().add(getLblSurname());
        getJp().add(getLblNo());
        getJp().add(getTxtName());
        getJp().add(getTxtSurname());
        getJp().add(getTxtNo());
        getJp().add(getSp());
        getJp().add(getBtnComeBack());
        getTxtNo().addActionListener(action);
        getTxtName().addActionListener(action);
        getTxtSurname().addActionListener(action);
        getBtnComeBack().addActionListener(action);
        getTable().addMouseListener(action);

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
            jp.setBounds(getJf().getBounds());
            jp.setLayout(null);
            jp.setBackground(new Color(179, 0, 156));
        }
        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

    /*
    final int lblTopSpace = 10;
    final int leftSpace = 30;
    final int txtWidth = 300;
    final int txtHeight = 40;
    final int lblWidth = 150;
    final int lblHeight = 15;
    final int pushRightSpace = txtWidth + 100;
    final int txtTopSpace = lblTopSpace + lblHeight + 10;
    int pushRightCounter = 0;
     */
    public JLabel getLblName() {
        if (lblName == null) {
            lblName = new JLabel("Öğrenci Adı");
            lblName.setBounds(leftSpace, topSpace, lblWidth + (pushRightCounter * (lblWidth + pushRightSpace)), lblHeight);
            lblName.setForeground(Color.WHITE);
            lblName.setFont(font_lbl);

            pushRightCounter++;

        }
        return lblName;
    }

    public void setLblName(JLabel lblName) {
        this.lblName = lblName;
    }

    public JLabel getLblSurname() {
        if (lblSurname == null) {
            lblSurname = new JLabel("Öğrenci Soyadı");
            lblSurname.setBounds(leftSpace + (pushRightCounter * (lblWidth + pushRightSpace)), topSpace, lblWidth, lblHeight);
            lblSurname.setForeground(Color.WHITE);
            lblSurname.setFont(font_lbl);

            pushRightCounter++;

        }
        return lblSurname;
    }

    public void setLblSurname(JLabel lblSurname) {
        this.lblSurname = lblSurname;
    }

    public JLabel getLblNo() {
        if (lblNo == null) {
            lblNo = new JLabel("Öğrenci NO");
            lblNo.setBounds(leftSpace + (pushRightCounter * (lblWidth + pushRightSpace)), topSpace, lblWidth, lblHeight);
            lblNo.setForeground(Color.WHITE);
            lblNo.setFont(font_lbl);
            pushRightCounter++;
        }
        return lblNo;
    }

    public void setLblNo(JLabel lblNo) {
        this.lblNo = lblNo;
    }

    public JTable getTable() {
        if (table == null) {
            table = new JTable(DataOfTable, HeadersOfTable);
            table.setDefaultEditor(Object.class, null);
            table.setFont(font_txt);
            table.setCursor(new Cursor(12));
            table.setFont(new Font("", Font.BOLD, 15));
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table.getColumnModel().getColumn(0).setPreferredWidth(92);
            table.getColumnModel().getColumn(1).setPreferredWidth(250);
            table.getColumnModel().getColumn(2).setPreferredWidth(250);
            table.getColumnModel().getColumn(3).setPreferredWidth(150);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        }
        return table;
    }

    public void setTable(JTable table) {

        this.table = table;
    }

    public JScrollPane getSp() {
        if (sp == null) {
            sp = new JScrollPane(getTable());
            sp.setBounds(leftSpace, 125, (pushRightCounter * (lblWidth + pushRightSpace)) + txtWidth, 300);

        }
        return sp;
    }

    public void setSp(JScrollPane sp) {
        this.sp = sp;
    }

    public JTextField getTxtName() {
        if (txtName == null) {
            pushRightCounter = 0;
            txtName = new JTextField("Öğrenci Adı");
            txtName.setBounds(leftSpace + (pushRightCounter * (lblWidth + pushRightSpace)), topSpace + lblHeight + 10, txtWidth, txtHeight);
            txtName.setForeground(Color.BLACK);
            txtName.setFont(font_txt);
            pushRightCounter++;

        }
        return txtName;
    }

    public void setTxtName(JTextField txtName) {
        this.txtName = txtName;
    }

    public JTextField getTxtSurname() {
        if (txtSurname == null) {
            txtSurname = new JTextField("Öğrenci Soyadı");
            txtSurname.setBounds(leftSpace + (pushRightCounter * (lblWidth + pushRightSpace)), topSpace + lblHeight + 10, txtWidth, txtHeight);
            txtSurname.setForeground(Color.BLACK);
            txtSurname.setFont(font_txt);
            pushRightCounter++;

        }
        return txtSurname;
    }

    public void setTxtSurname(JTextField txtSurname) {
        this.txtSurname = txtSurname;
    }

    public JTextField getTxtNo() {
        if (txtNo == null) {
            txtNo = new JTextField("Öğrenci No");
            txtNo.setBounds(leftSpace + (pushRightCounter * (lblWidth + pushRightSpace)), topSpace + lblHeight + 10, txtWidth, txtHeight);
            txtNo.setForeground(Color.BLACK);
            txtNo.setFont(font_txt);

        }
        return txtNo;
    }

    public void setTxtNo(JTextField txtNo) {
        this.txtNo = txtNo;
    }

    public JButton getBtnComeBack() {
        if (btnComeBack == null) {
            btnComeBack = new JButton("Geri dön");
            btnComeBack.setBounds(leftSpace, 450, 150, 30);
            //btnComeBack.setBackground(Color.BLACK);   btnComeBack.setForeground(Color.WHITE);
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
