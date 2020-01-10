package Logic;

import Gui.RegisteredStudentGui;
import Gui.StudentAddGui;
import Gui.StudentStateGui;
import Gui.StudentUpdateGui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

public class ActionStudent implements ActionListener, MouseListener, FocusListener {

    StudentAddGui sag;
    StudentStateGui ssg;
    StudentUpdateGui sug;
    RegisteredStudentGui rsg;
    String emptyError = "BOŞ GEÇİLEMEZ";
    String NumberError = "SADECE SAYI GİRİN";
    boolean StudentCanAdd;
    boolean StudentBringCame;
    boolean StudentCanUpdate;

    //StudentGui 
    public ActionStudent(StudentAddGui sag) {
        this.sag = sag;
    }

    public ActionStudent(StudentStateGui ssg) {
        this.ssg = ssg;
    }

    public ActionStudent(StudentUpdateGui sug) {
        this.sug = sug;
    }

    public ActionStudent(RegisteredStudentGui rsg) {
        this.rsg = rsg;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (sag != null) {
            if (e.getSource() == sag.getBtnAdd()) {
                if (!sag.getTxtNo().getText().trim().equals("") && !sag.getTxtNo().getText().equals(emptyError)
                        && !sag.getTxtName().getText().trim().equals("") && !sag.getTxtName().getText().equals(emptyError)
                        && !sag.getTxtSurname().getText().trim().equals("") && !sag.getTxtSurname().getText().equals(emptyError)
                        && !sag.getTxtEmail().getText().trim().equals("") && !sag.getTxtEmail().getText().equals(emptyError)
                        && !sag.getTxtPhoneNo().getText().trim().equals("") && !sag.getTxtPhoneNo().getText().equals(emptyError)) {
                    try {
                        if (Integer.parseInt(sag.getTxtNo().getText()) > 0) {
                            DbStudentAdd();
                        } else {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException ex) {
                        java.awt.Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null, "Öğrenci Numarası 0 dan büyük SAYI  OLMAK zorundadır", "SAYI FORMAT HATASI", JOptionPane.ERROR_MESSAGE);
                        sag.getTxtNo().setText("");
                    }
                } else {
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    if (sag.getTxtNo().getText().trim().equals("")) {
                        sag.getTxtNo().setForeground(Color.red);
                        sag.getTxtNo().setText(emptyError);
                    }
                    if (sag.getTxtName().getText().trim().equals("")) {
                        sag.getTxtName().setForeground(Color.red);
                        sag.getTxtName().setText(emptyError);

                    }
                    if (sag.getTxtSurname().getText().trim().equals("")) {
                        sag.getTxtSurname().setForeground(Color.red);
                        sag.getTxtSurname().setText(emptyError);
                    }
                    if (sag.getTxtEmail().getText().trim().equals("")) {
                        sag.getTxtEmail().setForeground(Color.red);
                        sag.getTxtEmail().setText(emptyError);
                    }
                    if (sag.getTxtPhoneNo().getText().trim().equals("")) {
                        sag.getTxtPhoneNo().setForeground(Color.red);
                        sag.getTxtPhoneNo().setText(emptyError);
                    }
                }
            } else if (e.getSource() == sag.getBtnClear()) {
                int answer = JOptionPane.showConfirmDialog(null, "Tüm veriler Silinecektir Emin misiniz? ", "Silme Uyarısı", 0);
                if (answer == JOptionPane.YES_OPTION) {
                    sag.getTxtEmail().setText("");
                    sag.getTxtName().setText("");
                    sag.getTxtNo().setText("");
                    sag.getTxtSurname().setText("");
                    sag.getTxtPhoneNo().setText("");
                    sag.getTxtResult().setText("");
                    sag.getTxtResult().setBackground(new Color(206, 214, 224));
                }
            } else if (e.getSource() == sag.getBtnComeBack()) {
                sag.getJp().setVisible(false);
                sag.getMg().getJf().setTitle("ANA SAYFA");
                sag.getMg().getJp().setVisible(true);
                clearAllTxtMainGui();
            }
        } else if (sug != null) {
            if (e.getSource() == sug.getBtnComeBack()) {
                sug.setVisible(false);
                sug.getMg().getJf().setTitle("ANA SAYFA");
                sug.getMg().getJp().setVisible(true);
                clearAllTxtMainGui();

            } else if (e.getSource() == sug.getTxtno()) {
                try {
                    if (Integer.parseInt(sug.getTxtno().getText()) > 0) {
                        DBStudentBringData();
                    } else {
                        java.awt.Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null, "Öğrenci Numarası 0 dan büyük olmak zorundadır", "NEGATİF DEĞER HATASI", JOptionPane.ERROR_MESSAGE);
                        sug.getTxtno().setText("");
                    }
                } catch (NumberFormatException ex) {
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Öğrenci Numarasına Sayı girmelisiniz", "SAYI FORMAT HATASI", JOptionPane.ERROR_MESSAGE);
                    sug.getTxtno().setText("");
                }
            } else if (e.getSource() == sug.getBtnUpdate()
                    || e.getSource() == sug.getTxtResult()
                    || e.getSource() == sug.getTxtNewSurname()
                    || e.getSource() == sug.getTxtNewNo()
                    || e.getSource() == sug.getTxtNewName()
                    || e.getSource() == sug.getTxtNewEmail()
                    || e.getSource() == sug.getTxtPhoneNo()) {
                /* if (sug.getTxtResult().getBackground() == Color.green) {
                    clearAllTxtMainGui();
                }*/
                try {
                    if (Integer.parseInt(sug.getTxtNewNo().getText()) > 0) {
                        DBStudentUpdate();
                    } else {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Öğrenci Numarası 0 dan büyük SAYI  OLMAK zorundadır", "SAYI FORMAT HATASI", JOptionPane.ERROR_MESSAGE);
                    sug.getTxtNewNo().setText("");
                }
            } else if (e.getSource() == sug.getBtnDelete()) {

            }
        } else if (ssg != null) {
            if (e.getSource() == ssg.getBtnComeBack()) {
                ssg.setVisible(false);
                ssg.getMg().getJf().setTitle("ANA SAYFA");
                ssg.getMg().getJp().setVisible(true);
                clearAllTxtMainGui();
            } else if (e.getSource() == ssg.getTxtStudentNo()) {

            }
        } else if (rsg != null) {
            if (e.getSource() == rsg.getBtnComeBack()) {
                rsg.getJp().setVisible(false);
                rsg.getMg().getJf().setTitle("ANA SAYFA");
                rsg.getMg().getJp().setVisible(true);
                clearAllTxtMainGui();
            } else if (e.getSource() == rsg.getTxtNo()
                    || e.getSource() == rsg.getTxtName()
                    || e.getSource() == rsg.getTxtSurname()) {
                JOptionPane.showMessageDialog(null, "aşağıdaki tablolara bağlanılacak");

            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        rsg.getTable().setSelectionBackground(Color.GREEN);
        System.out.println(rsg.getTable().getSelectedColumn());

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void DBStudentUpdate() {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";
        StudentCanUpdate = true;
        //  Database credentials
        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            stmt = conn.createStatement();
            String SqlStudentUpdateQuery = "UPDATE `student` SET \n"
                    + "No=" + sug.getTxtNewNo().getText() + ",\n"
                    + "Name='" + sug.getTxtNewName().getText() + "',\n "
                    + "Surname='" + sug.getTxtNewSurname().getText() + "', \n"
                    + "Email = '" + sug.getTxtNewEmail().getText() + "',\n "
                    + "Phone ='" + sug.getTxtPhoneNo().getText() + "'\n "
                    + "WHERE No= " + sug.getTxtno().getText() + " ";

            DBStudentControlToUpdate();
            if (!StudentCanUpdate) {
                throw new Exception("Güncellenecek Hedef Öğrenecek Numarası Kayıt Bulundurduğu için\n"
                        + "Güncelleme İptal Edilmiştir");

            }
            int answer = JOptionPane.showConfirmDialog(null, "Güncellemek İstediğinize Emin misiniz?", "GÜNCELLEME ONAYI", JOptionPane.YES_NO_OPTION);
            if (answer != JOptionPane.YES_OPTION) {
                return;
            }
            /*  String[] options = {"Güncelle", "iptal", "asdas"};
            
            int selection = JOptionPane.showOptionDialog(null, "Güncellemek İstediğinize Emin misiniz?", "GÜNCELLEME ONAYI", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, "iptal");

            JOptionPane.showMessageDialog(null, Integer.parseInt(options[selection]));
            JOptionPane.showMessageDialog(null, selection);
            JOptionPane.showMessageDialog(null, "sdfafsfas");*/
            stmt.executeUpdate(SqlStudentUpdateQuery);
            sug.getTxtResult().setBackground(Color.GREEN);
            sug.getTxtResult().setText("GÜNCELLENME BAŞARILI");
            SuccessVoice();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex + "aaaaaaaaaaaaaaaaa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "1-) Bilgileri Güncelleyebilmeniz İçin Önce Öğrenci Numarasını\n"
                    + "Aratmalısınız\n"
                    + "2-) Yeni Öğrenci Numarasına Sayı girmelisiniz", "GÜNCELLEME HATASI", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            //   JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void DBStudentBringData() {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";

        //  Database credentials
        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        StudentBringCame = false;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            stmt = conn.createStatement();
            String SqlStudentUpdateQuery = "SELECT * FROM `student` WHERE  No=" + sug.getTxtno().getText();

            ResultSet rs = stmt.executeQuery(SqlStudentUpdateQuery);
            while (rs.next()) {
                sug.getTxtNewNo().setText(Long.toString(rs.getLong("No")));
                sug.getTxtNewName().setText(rs.getString("Name"));
                sug.getTxtNewSurname().setText(rs.getString("SurName"));
                sug.getTxtNewEmail().setText(rs.getString("Email"));
                sug.getTxtPhoneNo().setText(rs.getString("Phone"));
                StudentBringCame = true;
                sug.getTxtResult().setBackground(new Color(24, 220, 255));
                sug.getTxtResult().setText("Bilgiler Getirildi");
                SuccessVoice();
            }
            if (!StudentBringCame) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, sug.getTxtno().getText() + " Nolu öğrenci Kaydı YOKTUR", "EKSİK KAYIT HATASI", JOptionPane.ERROR_MESSAGE);
                sug.getTxtNewNo().setText("");
                sug.getTxtNewName().setText("");
                sug.getTxtNewSurname().setText("");
                sug.getTxtNewEmail().setText("");
                sug.getTxtPhoneNo().setText("");
                sug.getTxtResult().setBackground(new Color(255, 82, 82));
                sug.getTxtResult().setText("İstenilen Bilgiler Kayıtta Bulunamadı");

            }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex + "111111111111111111");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lütfen Öğrenci Numarasına Sayı girin", "DEĞER HATASI", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void DbStudentAdd() {
        StudentCanAdd = true;
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";

        //  Database credentials
        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            DBStudentControlToAdd();
            if (!StudentCanAdd) {
                throw new Exception();
            }
            stmt = conn.createStatement();
            String SqlStudentAdd = "INSERT INTO `student` "
                    + "(`Id`,`No`,`Name`,`Surname`,`Email`,`Phone`,`Debt`) VALUES "
                    + "(NULL,"
                    + "'" + sag.getTxtNo().getText() + "',"
                    + "'" + sag.getTxtName().getText() + "',"
                    + "'" + sag.getTxtSurname().getText() + "',"
                    + "'" + sag.getTxtEmail().getText() + "',"
                    + "'" + sag.getTxtPhoneNo().getText() + "',"
                    + "'0.0' )";
            stmt.executeUpdate(SqlStudentAdd);
            sag.getTxtResult().setBackground(Color.GREEN);
            sag.getTxtResult().setText("Öğrenci Kayıt edilmiştir");
            SuccessVoice();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex + "ddddddddddddddddddddd");
        } catch (SQLException ex) {
            java.awt.Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Xampp hata meydana geldi ",
                    "SQL HATASI", JOptionPane.ERROR_MESSAGE);

            sag.getTxtNo().setText(NumberError);
            sag.getTxtNo().setForeground(new Color(255, 159, 26));

        } catch (Exception ex) {
            java.awt.Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Bu Öğrenci Numarası Zaten Kayıtlı", "KAYIT HATASI", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void DBStudentControlToUpdate() {
        //DBStudentUpdate
        StudentCanUpdate = true;
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";

        //  Database credentials
        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            stmt = conn.createStatement();
            String SqlStudentControlQuery = "SELECT * FROM  `student` WHERE No=" + sug.getTxtNewNo().getText();

            ResultSet rs = stmt.executeQuery(SqlStudentControlQuery);
            //rs.next(); // if I did not write this  I can't add new thing   but just 1 time I had to write or I will add as much as I write this
            while (rs.next()) {

                throw new Exception();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActionStudent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ActionStudent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            StudentCanUpdate = false;
            java.awt.Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, sug.getTxtNewNo().getText() + " Numarada Başka Bir Öğrenci Kayıtlı Olduğu için\n"
                    + "Güncelleme Başarısız");
            sug.getTxtResult().setBackground(Color.red);
            sug.getTxtResult().setBackground(new Color(255, 82, 82));
            sug.getTxtResult().setText("Güncelleme Başarısız");
        }
    }

    public void DBStudentControlToAdd() {
        StudentCanAdd = true;
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";

        //  Database credentials
        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            stmt = conn.createStatement();
            String SqlStudentControlQuery = "SELECT * FROM  `student` WHERE No=" + sag.getTxtNo().getText();

            ResultSet rs = stmt.executeQuery(SqlStudentControlQuery);
            //rs.next(); // if I did not write this  I can't add new thing   but just 1 time I had to write or I will add as much as I write this
            while (rs.next()) {

                throw new Exception();
            }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "BEKLENMEYEN HATA ", "Kayıt Hatası", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, ex, "SQL HATASI", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            StudentCanAdd = false;
        }

    }

    @Override
    public void focusGained(FocusEvent e) {
        if (sag != null) {
            if (sag.getTxtResult().getBackground() == Color.GREEN) {
                sag.getTxtResult().setBackground(new Color(206, 214, 224));
                sag.getTxtResult().setText("");
                sag.getTxtNo().setText("");
                sag.getTxtName().setText("");
                sag.getTxtSurname().setText("");
                sag.getTxtEmail().setText("");
                sag.getTxtPhoneNo().setText("");

            } else {
                if (e.getSource() == sag.getTxtNo() && sag.getTxtNo().getText().equals(emptyError)
                        || e.getSource() == sag.getTxtNo() && sag.getTxtNo().getText().equals(NumberError)) {
                    sag.getTxtNo().setText("");
                    sag.getTxtNo().setForeground(Color.BLACK);
                }
                if (e.getSource() == sag.getTxtName() && sag.getTxtName().getText().equals(emptyError)) {
                    sag.getTxtName().setForeground(Color.BLACK);
                    sag.getTxtName().setText("");

                }
                if (e.getSource() == sag.getTxtSurname() && sag.getTxtSurname().getText().equals(emptyError)) {
                    sag.getTxtSurname().setText("");
                    sag.getTxtSurname().setForeground(Color.BLACK);
                }
                if (e.getSource() == sag.getTxtPhoneNo() && sag.getTxtPhoneNo().getText().equals(emptyError)) {
                    sag.getTxtPhoneNo().setText("");
                    sag.getTxtPhoneNo().setForeground(Color.BLACK);
                }
                if (e.getSource() == sag.getTxtEmail() && sag.getTxtEmail().getText().equals(emptyError)) {
                    sag.getTxtEmail().setText("");
                    sag.getTxtEmail().setForeground(Color.BLACK);
                }
            }
        }
    }

    public void clearAllTxtMainGui() {

        if (sag != null) {
            sag.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            sag.getMg().gettxtStudentNo().setText("Öğrenci No Girin");
            sag.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            sag.getMg().getTxtBookBarcode().setText("Kitap Barkod No girin");
            sag.getMg().getTxtBookName().setText("");
            sag.getMg().gettxtResultScreen().setText("");
        } else if (ssg != null) {
            ssg.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            ssg.getMg().gettxtStudentNo().setText("Öğrenci No Girin");
            ssg.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            ssg.getMg().getTxtBookBarcode().setText("Kitap Barkod No girin");
            ssg.getMg().getTxtBookName().setText("");
            ssg.getMg().gettxtResultScreen().setText("");

        } else if (sug != null) {
            sug.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            sug.getMg().gettxtStudentNo().setText("Öğrenci No Girin");
            sug.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            sug.getMg().getTxtBookBarcode().setText("Kitap Barkod No girin");
            sug.getMg().getTxtBookName().setText("");
            sug.getMg().gettxtResultScreen().setText("");

        } else if (rsg != null) {
            rsg.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            rsg.getMg().gettxtStudentNo().setText("Öğrenci No Girin");
            rsg.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            rsg.getMg().getTxtBookBarcode().setText("Kitap Barkod No girin");
            rsg.getMg().getTxtBookName().setText("");
            rsg.getMg().gettxtResultScreen().setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (sag != null) {
            if (e.getSource() == sag.getTxtNo() && sag.getTxtNo().getText().trim().equals("")) {
                sag.getTxtNo().setText(emptyError);
                sag.getTxtNo().setForeground(Color.RED);
            }
            if (e.getSource() == sag.getTxtName() && sag.getTxtName().getText().trim().equals("")) {
                sag.getTxtName().setText(emptyError);
                sag.getTxtName().setForeground(Color.RED);
            }
            if (e.getSource() == sag.getTxtSurname() && sag.getTxtSurname().getText().trim().equals("")) {
                sag.getTxtSurname().setText(emptyError);
                sag.getTxtSurname().setForeground(Color.RED);
            }
            if (e.getSource() == sag.getTxtPhoneNo() && sag.getTxtPhoneNo().getText().trim().equals("")) {
                sag.getTxtPhoneNo().setText(emptyError);
                sag.getTxtPhoneNo().setForeground(Color.RED);
            }
            if (e.getSource() == sag.getTxtEmail() && sag.getTxtEmail().getText().trim().equals("")) {
                sag.getTxtEmail().setText(emptyError);
                sag.getTxtEmail().setForeground(Color.RED);
            }
        }
    }

    public void SuccessVoice() {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Gui/tik.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();

        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(ActionsBook.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ActionsBook.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(ActionsBook.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
