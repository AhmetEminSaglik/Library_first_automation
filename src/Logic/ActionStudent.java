package Logic;

import Gui.RegisteredStudentGui;
import Gui.StudentAddGui;
import Gui.StudentStateGui;
import Gui.StudentUpdateGui;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ActionStudent implements ActionListener, MouseListener, FocusListener {

    StudentAddGui sag;
    StudentStateGui ssg;
    StudentUpdateGui sug;
    RegisteredStudentGui rsg;
    String emptyError = "BOŞ GEÇİLEMEZ";
    //String NumberError = "SADECE SAYI GİRİN";
    boolean StudentCanAdd;
    boolean StudentBringCame;
    boolean StudentCanUpdate;
    Color rsgPlaceHolder = Color.GRAY;
    Font fontTxtPlaceHolder = new Font("", Font.ITALIC, 15);
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
            if (e.getSource() == sag.getBtnAdd()
                    || e.getSource() == sag.getTxtNo()
                    || e.getSource() == sag.getTxtName()
                    || e.getSource() == sag.getTxtSurname()
                    || e.getSource() == sag.getTxtEmail()
                    || e.getSource() == sag.getTxtPhoneNo()) {
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
                clearAllTxtMainGui();
                sug.getMg().getJp().setVisible(true);

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
                ResetStudentState();
                BringStudentState();
            }
        } else if (rsg != null) {
            if (e.getSource() == rsg.getBtnComeBack()) {
                rsg.getJp().setVisible(false);
                rsg.getMg().getJf().setTitle("ANA SAYFA");
                rsg.getMg().getJp().setVisible(true);
                clearAllTxtMainGui();
            } else if (e.getSource() == rsg.getTxtName()) {
                SearchRegisteredStudent(0);

            } else if (e.getSource() == rsg.getTxtSurname()) {
                SearchRegisteredStudent(1);
            } else if (e.getSource() == rsg.getTxtNo()) {
                SearchRegisteredStudent(2);

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
        ResultSet rs = null;
        PreparedStatement preparedStmt = null;
        StudentCanUpdate = true;
        //  Database credentials
        /*   String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;*/
        boolean StudentDeleted = true;
        boolean AlreadyCame = true;
        boolean StudentHasDebt = false;

        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String SqlStudentControlQuery = "SELECT * FROM `student` WHERE  No LIKE '" + sug.getTxtno().getText() + "'";

            rs = stmt.executeQuery(SqlStudentControlQuery);

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
            //PreparedStatement preparedStmt = conn.prepareStatement(SqlStudentdDeleteQuery);
            preparedStmt = conn.prepareStatement(SqlStudentdDeleteQuery);

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
                //preparedStmt.execute();
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
        } finally {

            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();

                }
                if (rs != null) {
                    rs.close();
                }
                if (preparedStmt != null) {
                    preparedStmt.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, " stmt , conn, rs, preparedStmt kapatılırken hata meydana geldi  (330/ActionStudent)");
            }

        }
        /*finally {

        }*/
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
            if (sug.getTxtNewNo().getText().trim().equals("")
                    || sug.getTxtNewName().getText().trim().equals("")
                    || sug.getTxtNewSurname().getText().trim().equals("")
                    || sug.getTxtNewEmail().getText().trim().trim().equals("")
                    || sug.getTxtPhoneNo().getText().trim().equals("")
                    || sug.getTxtno().getText().trim().equals("")) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                sug.getTxtResult().setText("EKSİK BİLGİ / GÜNCELLEME İPTAL");
                sug.getTxtResult().setBackground(new Color(250, 130, 49));
                JOptionPane.showMessageDialog(null, "Lütfen Bütün Bilgileri eksiksiz bir şekilde Doldurunuz", "EKSİK BİLGİ GÜNCELLEMESİ", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String SqlStudentUpdateQuery = "UPDATE `student` SET \n"
                    + "No='" + sug.getTxtNewNo().getText().trim() + "',\n"
                    + "Name='" + sug.getTxtNewName().getText().trim() + "',\n "
                    + "Surname='" + sug.getTxtNewSurname().getText().trim() + "', \n"
                    + "Email = '" + sug.getTxtNewEmail().getText().trim() + "',\n "
                    + "Phone ='" + sug.getTxtPhoneNo().getText().trim() + "'\n "
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
            String SqlBookStudentNoUpdateQuery = "UPDATE `book` SET \n"
                    + "StudentNo = '" + sug.getTxtNewNo().getText().trim() + "' "
                    + "WHERE StudentNo LIKE '" + sug.getTxtno().getText().trim() + "'";

            stmt.executeUpdate(SqlBookStudentNoUpdateQuery);
            sug.getTxtResult().setBackground(Color.GREEN);
            sug.getTxtResult().setText("GÜNCELLENME BAŞARILI");
            //ClearAllTxtGui();
            SuccessVoice();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex + "aaaaaaaaaaaaaaaaa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "\n\n\n1-) Bilgileri Güncelleyebilmeniz İçin Önce Öğrenci Numarasını\n"
                    + "Aratmalısınız\n"
                    + "2-) Yeni Öğrenci Numarasına Sayı girmelisiniz", "GÜNCELLEME HATASI", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            //   JOptionPane.showMessageDialog(null, ex);
        } finally {

            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();

                }
                /*  if (rs != null) {
                    rs.close();
                }
                if (preparedStmt != null) {
                    preparedStmt.close();
                }*/
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, " stmt , conn, rs, preparedStmt kapatılırken hata meydana geldi  (330/ActionStudent)");
            }

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
        ResultSet rs = null;
        StudentBringCame = false;
        Boolean AlreadyCame = false;
        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String SqlStudentUpdateQuery = "SELECT * FROM `student` WHERE  No LIKE '" + sug.getTxtno().getText() + "' ";

            rs = stmt.executeQuery(SqlStudentUpdateQuery);
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
        } finally {

            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();

                }
                if (rs != null) {
                    rs.close();
                }
                /*   if (preparedStmt != null) {
                    preparedStmt.close();
                }*/
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, " stmt , conn, rs, preparedStmt kapatılırken hata meydana geldi  (330/ActionStudent)");
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
        } catch (Exception ex) {
            java.awt.Toolkit.getDefaultToolkit().beep();
            //JOptionPane.showMessageDialog(null, "Bu Öğrenci Numarası Zaten Kayıtlı", "KAYIT HATASI", JOptionPane.ERROR_MESSAGE);
            sag.getTxtResult().setBackground(Color.ORANGE);
            sag.getTxtResult().setText("Öğrenci Zaten Kayıtlı");

        } finally {

            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();

                }
                /* if (rs != null) {
                    rs.close();
                }
                   if (preparedStmt != null) {
                    preparedStmt.close();
                }*/
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, " stmt , conn, rs, preparedStmt kapatılırken hata meydana geldi  (330/ActionStudent)");
            }

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
        ResultSet rs = null;
        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String SqlStudentControlQuery = "SELECT * FROM  `student` WHERE No LIKE '" + sug.getTxtNewNo().getText() + "'";

            rs = stmt.executeQuery(SqlStudentControlQuery);
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
        } finally {

            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();

                }
                if (rs != null) {
                    rs.close();
                }
                /*   if (preparedStmt != null) {
                    preparedStmt.close();
                }*/
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, " stmt , conn, rs, preparedStmt kapatılırken hata meydana geldi  (330/ActionStudent)");
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
        ResultSet rs = null;
        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String SqlStudentControlQuery = "SELECT * FROM  `student` WHERE No LIKE '" + sag.getTxtNo().getText().trim() + "'";

            rs = stmt.executeQuery(SqlStudentControlQuery);
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
        } finally {

            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();

                }
                if (rs != null) {
                    rs.close();
                }
                /*   if (preparedStmt != null) {
                    preparedStmt.close();
                }*/
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, " stmt , conn, rs, preparedStmt kapatılırken hata meydana geldi  (330/ActionStudent)");
            }

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
                if (e.getSource() == sag.getTxtNo() && sag.getTxtNo().getText().equals(emptyError)) {
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

        if (rsg != null) {
            if (e.getSource() == rsg.getTxtNo()) {
                rsg.getTxtNo().setForeground(Color.BLACK);
                rsg.getTxtNo().setFont(new Font("", Font.BOLD, 15));
                rsg.getTxtName().setForeground(rsgPlaceHolder);
                rsg.getTxtName().setFont(fontTxtPlaceHolder);
                rsg.getTxtName().setText("İsim Giriniz");
                rsg.getTxtSurname().setForeground(rsgPlaceHolder);
                rsg.getTxtSurname().setText("Soyisim Giriniz");
                rsg.getTxtSurname().setFont(fontTxtPlaceHolder);
                if (rsg.getTxtNo().getText().trim().equals("Numara Giriniz")) {
                    rsg.getTxtNo().setText("");
                }

            } else if (e.getSource() == rsg.getTxtName()) {
                rsg.getTxtName().setForeground(Color.BLACK);
                rsg.getTxtName().setFont(new Font("", Font.BOLD, 15));
                rsg.getTxtNo().setForeground(rsgPlaceHolder);
                rsg.getTxtNo().setText("Numara Giriniz");
                rsg.getTxtNo().setFont(fontTxtPlaceHolder);
                rsg.getTxtSurname().setForeground(rsgPlaceHolder);
                rsg.getTxtSurname().setText("Soyisim Giriniz");
                rsg.getTxtSurname().setFont(fontTxtPlaceHolder);
                if (rsg.getTxtName().getText().trim().equals("İsim Giriniz")) {
                    rsg.getTxtName().setText("");
                }
            } else if (e.getSource() == rsg.getTxtSurname()) {
                rsg.getTxtSurname().setForeground(Color.BLACK);
                rsg.getTxtSurname().setFont(new Font("", Font.BOLD, 15));
                rsg.getTxtName().setForeground(rsgPlaceHolder);
                rsg.getTxtName().setFont(fontTxtPlaceHolder);
                rsg.getTxtName().setText("İsim Giriniz");
                rsg.getTxtNo().setForeground(rsgPlaceHolder);
                rsg.getTxtNo().setText("Numara Giriniz");
                rsg.getTxtNo().setFont(fontTxtPlaceHolder);
                if (rsg.getTxtSurname().getText().trim().equals("Soyisim Giriniz")) {
                    rsg.getTxtSurname().setText("");
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

    /*
    public void ClearAllTxtGui() {
        if (sug != null) {
            sug.getTxtNewNo().setText("");
            sug.getTxtNewName().setText("");
            sug.getTxtNewSurname().setText("");
            sug.getTxtPhoneNo().setText("");
            sug.getTxtNewEmail().setText("");
        }
    }
     */
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
        ResultSet rs = null;
        try {

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);    //SELECT * FROM book  LEFT JOIN student ON  book.StudentNo =student.No  WHERE book.StudentNo is not null
            stmt = conn.createStatement();

            String BookExistQuery = "Select * FROM book WHERE StudentNo LIKE '" + sug.getTxtNewNo().getText() + "'";
            stmt = conn.createStatement();

            rs = stmt.executeQuery(BookExistQuery);

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
        } finally {

            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();

                }
                if (rs != null) {
                    rs.close();
                }
                /*   if (preparedStmt != null) {
                    preparedStmt.close();
                }*/
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, " stmt , conn, rs, preparedStmt kapatılırken hata meydana geldi  (330/ActionStudent)");
            }

        }

        // eğer kitaplardın StudentNo 'sunda öğrenci numarası  varsa öğrenci silinemicek
        return true;
    }

    public void BringStudentState() {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";

        //  Database credentials
        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String StudentQuery = "SELECT * FROM  student LEFT JOIN book ON book.StudentNo=student.No WHERE No LIKE '" + ssg.getTxtStudentNo().getText().trim() + "'";

            rs = stmt.executeQuery(StudentQuery);
            Date BorrowedDate1 = null;
            Date BorrowedDate2 = null;
            Date BorrowedDate3 = null;
            LocalDate localdate = LocalDate.now();
            SimpleDateFormat df2 = new SimpleDateFormat("EEE, dd MMM yyyy");

            if (rs.next()) {
                ssg.getTxtDept().setText(rs.getString("Debt"));
                ssg.getTxtBookBarcodeNo1().setText(rs.getString("BarcodeNo"));
                ssg.getTxtBookName1().setText(rs.getString("Book.Name"));
                BorrowedDate1 = rs.getDate("borrowedDate");
                if (rs.next()) {
                    ssg.getTxtBookBarcodeNo2().setText(rs.getString("BarcodeNo"));
                    ssg.getTxtBookName2().setText(rs.getString("Book.Name"));
                    //barcode2 = rs.getString("BarcodeNo");

                    BorrowedDate2 = rs.getDate("borrowedDate");

                    if (rs.next()) {
                        ssg.getTxtBookBarcodeNo3().setText(rs.getString("BarcodeNo"));
                        ssg.getTxtBookName3().setText(rs.getString("Book.Name"));
                        BorrowedDate3 = rs.getDate("borrowedDate");
                        // barcode3 = rs.getString("BarcodeNo");
                    }
                }
                int delay = 0;
                String delayString = "";
                if (BorrowedDate1 != null) {
                    String dayQuery = "SELECT DATEDIFF ('" + BorrowedDate1 + "',NOW())";
                    rs = stmt.executeQuery(dayQuery);

                    LocalDate borrowedDate = BorrowedDate1.toLocalDate();
                    LocalDate now = LocalDate.now();

                    Period diff = Period.between(borrowedDate, now);

                    if (diff.getYears() > 0) {
                        delay += 365 * diff.getYears();
                    }
                    if (diff.getMonths() > 0) {
                        delay += 30 * diff.getMonths();
                    }
                    if (diff.getDays() > 0) {
                        delay += diff.getDays();
                    }

                    if (delay <= 30) {
                        delay = 30 - delay;
                        delayString = "+" + Integer.toString(delay);
                        ssg.getLblLendingDayNumber1().setForeground(Color.green);

                    } else {
                        delay -= 30;
                        delayString = "-" + Integer.toString(delay);
                        ssg.getLblLendingDayNumber1().setForeground(Color.red);
                    }

                    ssg.getLblLendingDayNumber1().setText(delayString);

                    if (BorrowedDate2 != null) {
                        borrowedDate = BorrowedDate2.toLocalDate();
                        delay = 0;
                        diff = Period.between(borrowedDate, now);

                        if (diff.getYears() > 0) {
                            delay += 365 * diff.getYears();
                        }
                        if (diff.getMonths() > 0) {
                            delay += 30 * diff.getMonths();
                        }
                        if (diff.getDays() > 0) {
                            delay += diff.getDays();
                        }

                        if (delay < 30) {
                            delay = 30 - delay;
                            delayString = "+" + Integer.toString(delay);
                            ssg.getLblLendingDayNumber2().setForeground(Color.green);
                        } else {
                            delay -= 30;
                            delayString = "-" + Integer.toString(delay);
                            ssg.getLblLendingDayNumber2().setForeground(Color.red);
                        }

                        ssg.getLblLendingDayNumber2().setText(delayString);

                        if (BorrowedDate3 != null) {

                            delay = 0;
                            borrowedDate = BorrowedDate3.toLocalDate();
                            diff = Period.between(borrowedDate, now);
                            System.out.println("dif : " + diff.getMonths());

                            if (diff.getYears() > 0) {
                                delay += 365 * diff.getYears();
                            }
                            if (diff.getMonths() > 0) {
                                delay += 30 * diff.getMonths();
                            }
                            if (diff.getDays() > 0) {
                                delay += diff.getDays();
                            }

                            if (delay < 30) {
                                delay = 30 - delay;
                                delayString = "+" + Integer.toString(delay);
                                ssg.getLblLendingDayNumber3().setForeground(Color.green);
                            } else {
                                delay -= 30;
                                delayString = "-" + Integer.toString(delay);
                                ssg.getLblLendingDayNumber3().setForeground(Color.red);
                            }
                            ssg.getLblLendingDayNumber3().setText(delayString);

                        }
                    }
                }


                /*  if (!barcode1.equals("")) {
                    String TimeQuery = "SELECT (   (BorrowedDate + INTERVAL 30 DAY )- NOW())  from book WHERE book.StudentNo LIKE '" + ssg.getTxtStudentNo().getText().trim() + "' and book.BarcodeNo LIKE '" + barcode1 + "' ";
                    rs = stmt.executeQuery(TimeQuery);

                  if (!barcode2.equals("")) {
                        if (!barcode3.equals("")) {

                        }
                    }
                }*/
                SuccessVoice();
            } else {
                java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, ssg.getTxtStudentNo().getText().trim() + "  Numaralı öğrenci Kayıtlı değildir");

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActionStudent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ActionStudent.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();

                }
                if (rs != null) {
                    rs.close();
                }

                /*   if (preparedStmt != null) {
                    preparedStmt.close();
                }*/
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, " stmt , conn, rs, preparedStmt kapatılırken hata meydana geldi  (330/ActionStudent)");
            }

        }

    }

    public void ResetStudentState() {
        String EmptyTextForStudentState = "KİTAP YOK";
        ssg.getTxtDept().setText("");
        ssg.getTxtBookName1().setText(EmptyTextForStudentState);
        ssg.getTxtBookName2().setText(EmptyTextForStudentState);
        ssg.getTxtBookName3().setText(EmptyTextForStudentState);
        ssg.getTxtBookBarcodeNo1().setText(EmptyTextForStudentState);
        ssg.getTxtBookBarcodeNo2().setText(EmptyTextForStudentState);
        ssg.getTxtBookBarcodeNo3().setText(EmptyTextForStudentState);
        ssg.getLblLendingDayNumber1().setText("---");
        ssg.getLblLendingDayNumber2().setText("---");
        ssg.getLblLendingDayNumber3().setText("---");
        ssg.getLblLendingDayNumber1().setForeground(Color.MAGENTA);
        ssg.getLblLendingDayNumber2().setForeground(Color.MAGENTA);
        ssg.getLblLendingDayNumber3().setForeground(Color.MAGENTA);

    }

    public void SearchRegisteredStudent(int search) {

        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";
        StudentCanUpdate = true;
        //  Database credentials
        String USER = "root";
        String PASS = "";

        final int searchName = 0;
        final int searchSurname = 1;
        final int searchNo = 2;

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        String searchQuery = "";
        switch (search) {
            case searchName:
                searchQuery = "SELECT * FROM student WHERE name LIKE '%" + rsg.getTxtName().getText().trim() + "%'";
                break;
            case searchSurname:
                searchQuery = "SELECT * FROM student WHERE Surname LIKE '%" + rsg.getTxtSurname().getText().trim() + "%'";
                break;
            case searchNo:
                searchQuery = "SELECT * FROM student WHERE No LIKE '" + rsg.getTxtNo().getText().trim() + "%'";
                break;
        }

        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            rs = stmt.executeQuery(searchQuery);
            int totalStudentForQuery = 0;
            while (rs.next()) {
                totalStudentForQuery++;
            }

            rs = stmt.executeQuery(searchQuery);

            int counter = 0;
            rsg.DataOfTable = new String[totalStudentForQuery][6];

            while (rs.next()) {

                rsg.DataOfTable[counter][0] = Integer.toString(counter + 1);
                rsg.DataOfTable[counter][1] = rs.getString("No");
                rsg.DataOfTable[counter][2] = rs.getString("Name");
                rsg.DataOfTable[counter][3] = rs.getString("Surname");
                rsg.DataOfTable[counter][4] = rs.getString("Email");
                rsg.DataOfTable[counter][5] = rs.getString("Phone");

                counter++;
            }

            /*    String ClearQuery = "SELECT * FROM student";
            rs = stmt.executeQuery(ClearQuery);
            int deleteRows = counter;
           while (rs.next() && deleteRows < rsg.DataOfTable.length) {
                rsg.DataOfTable[deleteRows][0] = null;
                rsg.DataOfTable[deleteRows][1] = null;
                rsg.DataOfTable[deleteRows][2] = null;
                rsg.DataOfTable[deleteRows][3] = null;
                rsg.DataOfTable[deleteRows][4] = null;
                rsg.DataOfTable[deleteRows][5] = null;

            }*/
            rsg.getJp().remove(rsg.getSp());
            rsg.setSp(new JTable(rsg.DataOfTable, rsg.HeadersOfTable));
            rsg.getJp().add(rsg.getSp());
            SuccessVoice();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActionStudent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ActionStudent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ShowRegisteredStudent() {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";
        StudentCanUpdate = true;
        //  Database credentials
        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String StudentQuery = "SELECT * FROM  Student";

            rs = stmt.executeQuery(StudentQuery);

            int studetTotalNumber = 0;
            while (rs.next()) {
                studetTotalNumber++;
            }

            rs = stmt.executeQuery(StudentQuery);

            rsg.DataOfTable = new String[studetTotalNumber][6];
            int counter = 0;
            while (rs.next()) {
                rsg.DataOfTable[counter][0] = Integer.toString(counter + 1);
                rsg.DataOfTable[counter][1] = rs.getString("No");
                rsg.DataOfTable[counter][2] = rs.getString("Name");
                rsg.DataOfTable[counter][3] = rs.getString("Surname");
                rsg.DataOfTable[counter][4] = rs.getString("Email");
                rsg.DataOfTable[counter][5] = rs.getString("Phone");

                counter++;
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActionStudent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ActionStudent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
