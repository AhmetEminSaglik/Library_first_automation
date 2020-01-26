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
import java.sql.PreparedStatement;
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
                    DbStudentAdd();
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

                    SuccessVoice();
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
                if (!sug.getTxtno().getText().trim().equals("")) {
                    DBStudentBringData();
                } else {
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Öğrenci numarasını doldurmadan aratamazsınız",
                            "ARAMA HATASI", JOptionPane.ERROR_MESSAGE);
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
                DBStudentUpdate();
            } else if (e.getSource() == sug.getBtnDelete()) {
                if (ControlBeforeRemoveStudent() == true) {
                    DBStudentDelete();
                }
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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void DBStudentDelete() {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";
        StudentCanUpdate = true;
        //  Database credentials
        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        boolean StudentDeleted = true;
        boolean AlreadyCame = true;
        boolean StudentHasDebt = false;

        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String SqlStudentControlQuery = "SELECT * FROM `student` WHERE  No LIKE '" + sug.getTxtno().getText() + "'";

            ResultSet rs = stmt.executeQuery(SqlStudentControlQuery);
            /* while (rs.next()) {
                if (!sug.getTxtNewNo().getText().equals(Long.toString((rs.getLong("No"))))
                        || !sug.getTxtNewName().getText().equals(rs.getString("Name"))
                        || !sug.getTxtNewSurname().getText().equals(rs.getString("Surname"))
                        || !sug.getTxtNewEmail().getText().equals(rs.getString("Email"))
                        || !sug.getTxtPhoneNo().getText().equals(rs.getString("Phone"))) {
                    AlreadyCame = false;
                    throw new Exception();
                }
            }*/
            if (rs.next()) {
                if (!sug.getTxtNewNo().getText().equals((rs.getString("No")))
                        || !sug.getTxtNewName().getText().equals(rs.getString("Name"))
                        || !sug.getTxtNewSurname().getText().equals(rs.getString("Surname"))
                        || !sug.getTxtNewEmail().getText().equals(rs.getString("Email"))
                        || !sug.getTxtPhoneNo().getText().equals(rs.getString("Phone"))) {
                    AlreadyCame = false;
                    throw new Exception();
                }
                if (Double.parseDouble(rs.getString("Debt")) > 0.0) {
                    StudentHasDebt = true;
                    throw new Exception();

                }
            } else {
                throw new Exception("Kayıtlı Öğrenci Bulunamadı");
            }

            stmt = conn.createStatement();

            String SqlStudentdDeleteQuery = "DELETE FROM `student` WHERE No LIKE '" + sug.getTxtNewNo().getText() + "'";

            //    stmt.executeQuery(SqlStudentdDeleteQuery);
            PreparedStatement preparedStmt = conn.prepareStatement(SqlStudentdDeleteQuery);

            int answer = JOptionPane.showConfirmDialog(null, "Öğrenciyi Silmek istediğinizden Emin misiniz ? ", "SİLME UYARISI",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (answer == JOptionPane.YES_OPTION) {
                SuccessVoice();

                sug.getTxtNewNo().setText("");
                sug.getTxtNewName().setText("");
                sug.getTxtNewSurname().setText("");
                sug.getTxtPhoneNo().setText("");
                sug.getTxtNewEmail().setText("");
                sug.getTxtResult().setText("Öğrenci Silindi");
                sug.getTxtResult().setBackground(new Color(255, 121, 63));
                preparedStmt.execute();
            } else {
                sug.getTxtResult().setText("Öğrenci Silme İşlemi İptal Edildi");
                sug.getTxtResult().setBackground(Color.PINK);
                java.awt.Toolkit.getDefaultToolkit().beep();
            }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "ClASS NOT FOUND");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "\n\n\nÖğrenci Silebilmek İçin Önce Öğrenci Kayıtlı Öğrenci Bilgilerini Getirmelisiniz");
        } catch (Exception ex) {
            if (AlreadyCame == false) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                int answer = JOptionPane.showConfirmDialog(null, "Öğrenciyi Silmek İçin Önce Öğrenci Bilgilerini Getirmeniz gerekmektedir\n"
                        + "                                        Bilgiler Getirilsin mi ?", "EŞLEŞME HATASI", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    DBStudentBringData();
                }
            } else if (StudentHasDebt == true) {
                //     JOptionPane.showMessageDialog(null, "Öğrencinin " + debt + " TL borcu olduğu için silinemiyor");
                JOptionPane.showMessageDialog(null, "Öğrencinin borcu olduğu için silinemiyor");

                java.awt.Toolkit.getDefaultToolkit().beep();
                sug.getTxtResult().setBackground(new Color(255, 82, 82));
                sug.getTxtResult().setText("Silme Başarısız");
            } else {
                JOptionPane.showConfirmDialog(null, sug.getTxtno().getText() + " Nolu Kayıtlı Öğrenci bulunamadı", "SİLME HATASI", JOptionPane.ERROR_MESSAGE);
                java.awt.Toolkit.getDefaultToolkit().beep();
                sug.getTxtResult().setBackground(new Color(255, 82, 82));
                sug.getTxtResult().setText("Silme Başarısız");
            }
        }
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

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String SqlStudentUpdateQuery = "UPDATE `student` SET \n"
                    + "No='" + sug.getTxtNewNo().getText().trim() + "',\n"
                    + "Name='" + sug.getTxtNewName().getText() + "',\n "
                    + "Surname='" + sug.getTxtNewSurname().getText() + "', \n"
                    + "Email = '" + sug.getTxtNewEmail().getText() + "',\n "
                    + "Phone ='" + sug.getTxtPhoneNo().getText() + "'\n "
                    + "WHERE No LIKE  '" + sug.getTxtno().getText().trim() + "' ";

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
            //ClearAllTxtGui();
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
        Boolean AlreadyCame = false;
        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String SqlStudentUpdateQuery = "SELECT * FROM `student` WHERE  No LIKE '" + sug.getTxtno().getText() + "' ";

            ResultSet rs = stmt.executeQuery(SqlStudentUpdateQuery);
            while (rs.next()) {

                if (sug.getTxtNewNo().getText().equals(rs.getString("No"))
                        && sug.getTxtNewName().getText().equals(rs.getString("Name"))
                        && sug.getTxtNewSurname().getText().equals(rs.getString("Surname"))
                        && sug.getTxtNewEmail().getText().equals(rs.getString("Email"))
                        && sug.getTxtPhoneNo().getText().equals(rs.getString("Phone"))) {
                    AlreadyCame = true;
                    throw new Exception();
                }

                sug.getTxtNewNo().setText(rs.getString("No"));
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
            JOptionPane.showMessageDialog(null, ex);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "\nLütfen Öğrenci Numarasına Sayı girin", "DEĞER HATASI", JOptionPane.ERROR_MESSAGE);

        } catch (Exception ex) {
            if (AlreadyCame == true) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Bilgiler Zaten Getirildi", "UYARI", JOptionPane.INFORMATION_MESSAGE);
            }
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

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            DBStudentControlToAdd();
            if (!StudentCanAdd) {
                throw new Exception();
            }
            stmt = conn.createStatement();
            String SqlStudentAdd = "INSERT INTO `student` "
                    + "(`Id`,`No`,`Name`,`Surname`,`Email`,`Phone`,`Debt`) VALUES "
                    + "(NULL,"
                    + "'" + sag.getTxtNo().getText().trim() + "',"
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
            JOptionPane.showMessageDialog(null, ex);
        } catch (SQLException ex) {
            java.awt.Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Xampp hata meydana geldi ",
                    "SQL HATASI", JOptionPane.ERROR_MESSAGE);

            sag.getTxtNo().setText(NumberError);
            sag.getTxtNo().setForeground(new Color(255, 159, 26));

        } catch (Exception ex) {
            java.awt.Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Bu Öğrenci Numarası Zaten Kayıtlı", "KAYIT HATASI", JOptionPane.ERROR_MESSAGE);
            sag.getTxtResult().setBackground(Color.red);
            sag.getTxtResult().setText("Kayıt Başarısız");

        }

    }

    public void DBStudentControlToUpdate() {

        //DBStudentUpdate
        StudentCanUpdate = true;
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";
        Boolean newStudentNoFree = true;
        Boolean oldStudentNoFree = false;
        Boolean allSame = false;//  Database credentials
        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String SqlStudentControlQuery = "SELECT * FROM  `student` WHERE No LIKE '" + sug.getTxtNewNo().getText() + "'";

            ResultSet rs = stmt.executeQuery(SqlStudentControlQuery);
            //rs.next(); // if I did not write this  I can't add new thing   but just 1 time I had to write or I will add as much as I write this

            while (rs.next()) {
                if (sug.getTxtno().getText().equals(rs.getString("No"))) {
                    if (sug.getTxtNewNo().getText().equals(rs.getString("No"))
                            && sug.getTxtNewName().getText().equals(rs.getString("Name"))
                            && sug.getTxtNewSurname().getText().equals(rs.getString("Surname"))
                            && sug.getTxtNewEmail().getText().equals(rs.getString("Email"))
                            && sug.getTxtPhoneNo().getText().equals(rs.getString("Phone"))) {

                        allSame = true;
                        throw new Exception();
                    }
                } else {
                    newStudentNoFree = false;
                    throw new Exception();
                }
            }
            SqlStudentControlQuery = "SELECT * FROM  `student` WHERE No LIKE '" + sug.getTxtno().getText() + "'";
            rs = null;
            rs = stmt.executeQuery(SqlStudentControlQuery);
            //      rs.next(); // if I did not write this  I can't add new thing   but just 1 time I had to write or I will add as much as I write this

            while (rs.next()) {
                oldStudentNoFree = true;
            }
            if (oldStudentNoFree == false) {

                throw new Exception();

            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex, "CLASS BULUNAMADI", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(ActionStudent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            StudentCanUpdate = false;
            java.awt.Toolkit.getDefaultToolkit().beep();
            if (allSame == true) {
                JOptionPane.showMessageDialog(null, "BİLGİLER ZATEN GÜNCEL", "GÜNCELLEME HATASI", JOptionPane.ERROR_MESSAGE);
            } else if (newStudentNoFree == false) {
                JOptionPane.showMessageDialog(null, " YENİ Öğrenci Numarasında Başka Bir Öğrenci Kayıtlı Olduğu için\n"
                        + "Güncelleme Başarısız", "GÜNCELLEME HATASI", JOptionPane.ERROR_MESSAGE);
            } else if (oldStudentNoFree == false) {
                JOptionPane.showMessageDialog(null, " ESKİ Öğrenci Numarasında Kayıtlı Kimse Bulunmamaktadır\n"
                        + "Güncelleme Başarısız", "GÜNCELLEME HATASI", JOptionPane.ERROR_MESSAGE);
            }

            if (oldStudentNoFree == false) {

                sug.getTxtResult().setBackground(new Color(254, 202, 87));
                sug.getTxtResult().setText("Bilgiler Zaten Güncel");
            } else {

                sug.getTxtResult().setBackground(new Color(255, 82, 82));
                sug.getTxtResult().setText("Güncelleme Başarısız");
            }
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

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String SqlStudentControlQuery = "SELECT * FROM  `student` WHERE No LIKE '" + sag.getTxtNo().getText().trim() + "'";

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
            sag.getMg().gettxtResultScreen().setBackground(new Color(206, 214, 224));
        } else if (ssg != null) {
            ssg.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            ssg.getMg().gettxtStudentNo().setText("Öğrenci No Girin");
            ssg.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            ssg.getMg().getTxtBookBarcode().setText("Kitap Barkod No girin");
            ssg.getMg().getTxtBookName().setText("");
            ssg.getMg().gettxtResultScreen().setText("");
            ssg.getMg().gettxtResultScreen().setBackground(new Color(206, 214, 224));

        } else if (sug != null) {
            sug.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            sug.getMg().gettxtStudentNo().setText("Öğrenci No Girin");
            sug.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            sug.getMg().getTxtBookBarcode().setText("Kitap Barkod No girin");
            sug.getMg().getTxtBookName().setText("");
            sug.getMg().gettxtResultScreen().setText("");
            sug.getMg().gettxtResultScreen().setBackground(new Color(206, 214, 224));

        } else if (rsg != null) {
            rsg.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            rsg.getMg().gettxtStudentNo().setText("Öğrenci No Girin");
            rsg.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            rsg.getMg().getTxtBookBarcode().setText("Kitap Barkod No girin");
            rsg.getMg().getTxtBookName().setText("");
            rsg.getMg().gettxtResultScreen().setText("");
            rsg.getMg().gettxtResultScreen().setBackground(new Color(206, 214, 224));
        }
    }

    public void ClearAllTxtGui() {
        if (sug != null) {
            sug.getTxtNewNo().setText("");
            sug.getTxtNewName().setText("");
            sug.getTxtNewSurname().setText("");
            sug.getTxtPhoneNo().setText("");
            sug.getTxtNewEmail().setText("");
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

    public boolean ControlBeforeRemoveStudent() {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";

        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;

        try {

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);    //SELECT * FROM book  LEFT JOIN student ON  book.StudentNo =student.No  WHERE book.StudentNo is not null
            stmt = conn.createStatement();

            String BookExistQuery = "Select * FROM book WHERE StudentNo LIKE '" + sug.getTxtNewNo().getText() + "'";
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(BookExistQuery);

            int BookCounter = 0;
            while (rs.next()) //    ;
            {

                BookCounter++;

            }

            if (BookCounter > 0) {

                java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Öğrencinin elinde " + BookCounter + " adet kitap vardır. Kayıt Silinemez");
                return false;
            } else {
                SuccessVoice();
                return true;
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActionStudent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ActionStudent.class.getName()).log(Level.SEVERE, null, ex);
        }

        // eğer kitaplardın StudentNo 'sunda öğrenci numarası  varsa öğrenci silinemicek
        return true;
    }
}
