package Gui;

import Logic.ActionsLogin;
import Logic.JavaMailUtil;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JPanel {
//Apoya Ait  sanki başarılı bir şekilde giriş yapılmış gibi devam ediyorum

    JFrame jf = null;
    final int jframeX = 250;
    final int jframeY = 100;
    final int jframeWidth = 850;
    final int jframeHeight = 550;
    JLabel lblusername;
    JLabel lblPass;
    JTextField txtusername;
    JPasswordField jPass;
    //JTextField txtusername;
    // JPasswordField txtpassword;
    JButton signIn;
    Font lblFont = new Font("monospaced", Font.BOLD, 17);
    Font txtFont = new Font("", Font.BOLD, 15);
    ActionsLogin action = new ActionsLogin(this);

    public Login() {

        this.setLayout(null);
        this.setBackground(new Color(255, 234, 167));
        this.add(getLblusername());
        this.add(getLblPass());
        this.add(getTxtusername());
        this.add(getjPass());
        this.add(getSignIn());
        getJf().add(this);
        getJf().setVisible(true);
        getJf().setCursor(new Cursor(3));
        CreateDatabase();
        CreateTable();
        getJf().setCursor(null);
        JavaMailUtil jmu = new JavaMailUtil();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                jmu.FindStudentAndMailThem(0);
            }
        });
        t1.start();
        try {
            t1.join();

        } catch (InterruptedException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JFrame getJf() {
        if (jf == null) {
            jf = new JFrame("GİRİŞ SAYFASI");
            jf.setBounds(jframeX, jframeY, jframeWidth, jframeHeight);
            //jf.setLayout(null);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf.setResizable(false);

        }
        return jf;
    }

    public void setJf(JFrame jf) {
        this.jf = jf;
    }

    public JLabel getLblusername() {
        if (lblusername == null) {
            lblusername = new JLabel("Kullanıcı adı   : ");
            lblusername.setBounds(130, 150, 200, 40);
            lblusername.setFont(lblFont);

        }
        return lblusername;
    }

    public void setLblusername(JLabel lblusername) {
        this.lblusername = lblusername;
    }

    public JLabel getLblPass() {
        if (lblPass == null) {
            lblPass = new JLabel("Parola          : ");
            lblPass.setBounds(130, 210, 200, 40);
            lblPass.setFont(lblFont);

        }
        return lblPass;
    }

    public void setLblPass(JLabel lblPass) {
        this.lblPass = lblPass;
    }

    public JTextField getTxtusername() {
        if (txtusername == null) {
            txtusername = new JTextField("");
            txtusername.setBounds(380, 150, 300, 40);
            txtusername.setFont(txtFont);

        }
        return txtusername;
    }

    public void setTxtusername(JTextField txtusername) {
        this.txtusername = txtusername;
    }

    public JPasswordField getjPass() {
        if (jPass == null) {
            jPass = new JPasswordField("");
            jPass.setBounds(380, 210, 300, 40);
            jPass.setFont(txtFont);

        }
        return jPass;
    }

    public void setjPass(JPasswordField jPass) {
        this.jPass = jPass;
    }

    public JButton getSignIn() {
        if (signIn == null) {
            signIn = new JButton("Oturum aç");
            signIn.setBounds(530, 270, 150, 40);
            signIn.setFont(txtFont);
            signIn.addActionListener(action);
            signIn.setCursor(new Cursor(12));
        }
        return signIn;
    }

    public void setSignIn(JButton signIn) {
        this.signIn = signIn;
    }

    public void CreateDatabase() {

        // JDBC driver name and database URL
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/";

        //  Database credentials
        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;

        while (true) {
            try {

                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);

                stmt = conn.createStatement();

                String sqlControl = "SHOW DATABASES LIKE 'library' ";

                stmt.executeUpdate(sqlControl);

                if (stmt.executeUpdate(sqlControl) == -1) {
                    //stmt.execute("DROP DATABASE library");
                    return; //Veri tabanı zaten var
                }

                String sql = "CREATE DATABASE library";

                stmt.executeUpdate(sql);

            } catch (SQLException se) {
                JOptionPane.showMessageDialog(null, se + "\n\n   Çözüm Önerileri: \n"
                        + "1-) Lütfen xampp portunu açınız");

            } catch (ClassNotFoundException ex) {

                JOptionPane.showMessageDialog(null, "catch (mistake): ClassNotFoundException " + ex);

            }
        }
    }

    public void CreateTable() {
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
            String tableControl = "SHOW TABLES LIKE 'student' ";
            String tableYoneticiControl = "SHOW TABLES LIKE 'admin' ";
            String tableKitapControl = "SHOW TABLES LIKE 'book'";
            if (stmt.executeUpdate(tableControl) == -1 || stmt.executeUpdate(tableYoneticiControl) == -1 || stmt.executeUpdate(tableKitapControl) == -1) {
                // conn.createStatement() dene bir de stmt yerine
                String ControlQueryOver30Days0rOver27 = "SELECT * FROM book WHERE NOW() >  BorrowedDate + INTERVAL 30 DAY ";
                //"SELECT * FROM book WHERE NOW()  >=BorrowedDate + INTERVAL 30 DAY"
                //"SELECT * FROM book WHERE NOW() BETWEEN BorrowedDate + INTERVAL 27 DAY and BorrowedDate+ INTERVAL 30 DAY  "
                int over30Days = 0;

                rs = stmt.executeQuery(ControlQueryOver30Days0rOver27);

                while (rs.next()) {

                    over30Days++;
                }
                ControlQueryOver30Days0rOver27 = "SELECT * FROM book WHERE NOW() BETWEEN BorrowedDate + INTERVAL 27 DAY and BorrowedDate+ INTERVAL 30 DAY ";
                //SELECT * FROM book WHERE NOW() BETWEEN BorrowedDate + INTERVAL 27 DAY and BorrowedDate+ INTERVAL 30 DAY 
                rs = stmt.executeQuery(ControlQueryOver30Days0rOver27);
                int inLast3Days = 0;
                while (rs.next()) {

                    inLast3Days++;
                }
                if (over30Days > 0 || inLast3Days > 0) {
                    JOptionPane.showMessageDialog(null, "30 günü aşmış ödünç verilen kitapların sayısı : " + over30Days
                            + "\n\n Son üç gün  içinde iade edilmesi gereken kitap sayısı : " + inLast3Days);

                }

                if (stmt != null) {

                    stmt.close();

                }
                if (conn != null) {

                    conn.close();

                }
                if (rs != null) {

                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();

                }
                if (conn != null) {
                    conn.close();

                }
                return;
            }/*
            String sql = "CREATE TABLE `library`.`student`"
                    + " ( `id` INT NOT NULL AUTO_INCREMENT , "
                    + "`No` INT NOT NULL , "
                    + "`Name` VARCHAR(50) NOT NULL , "
                    + "`Surname` VARCHAR(50) NOT NULL , "
                    + "`Debt` DOUBLE NOT NULL , "
                    + "PRIMARY KEY (`id`));";*/
            String sql1 = "CREATE TABLE `library`.`student` "
                    + "( `Id` INT NOT NULL AUTO_INCREMENT ,"
                    + " `No`  VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL , "
                    + "`Name` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL , "
                    + "`Surname` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL , "
                    + "`Email` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL , "
                    + "`Phone` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL ,"
                    + " `Debt` DOUBLE NOT NULL , PRIMARY KEY (`Id`));";
            //utf32 COLLATE utf32_turkish_ci             
            //+ "`debt` DOUBLE NOT NULL ); ";
            String sql2 = "CREATE TABLE `library`.`admin` "
                    + "( `Username` VARCHAR(50) NOT NULL  , "
                    + "`Password` VARCHAR(50) NOT NULL);";
            /* "CREATE TABLE `library`.`yonetici` ( `id` INT NOT NULL AUTO_INCREMENT , "
                    + "`KullaniciAdi` VARCHAR(255) NOT NULL , "
                    + "`Sifre` VARCHAR(255) NOT NULL "
                    + ", PRIMARY KEY (`id`)); ";*/
            String sql3 = "CREATE TABLE `library`.`book`"
                    + " ( `Id` INT NOT NULL AUTO_INCREMENT ,"
                    + " `BarcodeNo` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL ,"
                    // CHARACTER SET utf32 COLLATE utf32_turkish_ci NOT NULL
                    + " `Name` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL ,"
                    + " `AuthorName` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL ,"
                    + " `CategoryName` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL ,"
                    + " `StudentNo` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci  ,"
                    + " `BorrowedDate` DATETIME   ,"
                    + " `Condition` INT NULL  ,"
                    + " PRIMARY KEY (`Id`));";
            /*String sql3 = "CREATE TABLE `library`.`book` "
                    + "( `id` INT(50) NOT NULL AUTO_INCREMENT , "
                    + "`BarcodeNo` VARCHAR(50) NOT NULL , "
                    + "`Name` VARCHAR(50) NOT NULL , "
                    + "`AuthorName` VARCHAR(50) NOT NULL , "
                    + "`Category` VARCHAR(50) NOT NULL , "
                    + "`TakenDate` DATE NOT NULL , "
                    + "PRIMARY KEY (`id`));";*/
 /*"CREATE TABLE `library`.`kitaplar` "
                    + "( `id` INT NOT NULL AUTO_INCREMENT , "
                    + "`KitapAdi` VARCHAR(255) NOT NULL , `BarkodNo` VARCHAR(255) NOT NULL "
                    + ", `YazarAdi` VARCHAR(255) NOT NULL , `OgrenciId` INT NOT NULL "
                    + ", `Tarih` DATE , PRIMARY KEY (`id`)) ";*/

            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql3);
        } catch (SQLException se) {
            //Handle errors for JDBC
            JOptionPane.showMessageDialog(null, "Beklenmeyen Hata : \n" + se);
        } catch (Exception e) {
            //Handle errors for Class.forName
            JOptionPane.showMessageDialog(null, "Beklenmeyen Hata : \n" + e);

        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {

                    stmt.close();

                }
                if (conn != null) {

                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing

        }//end try

        Initializer();
    }

    public void Initializer() {
        // JDBC driver name and database URL
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/library";

        //  Database credentials
        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();

            String sql1 = "INSERT INTO `student` (`Id`, `No`, `Name`, `Surname`,`Email`, `Phone`,`Debt`) "
                    + "VALUES (NULL, '385931', 'Ahmet Emin', 'SAĞLIK','Ahmeteminsaglik@gmail.com','0538 065 31 46 ', 10.35)";

            /*     + "( `Id` INT(50) NOT NULL AUTO_INCREMENT ,"
                    + " `No` DOUBLE NOT NULL , "
                    + "`Name` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL , "
                    + "`Surname` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL , "
                    + "`Email` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL , "
                    + "`Phone` VARCHAR(30) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL ,"
                    + " `Debt` DOUBLE NOT NULL , PRIMARY KEY (`Id`));";*/
 /* "INSERT INTO `ogrenci` "
                    + "( `AdSoyad`, `No`, `Email`, `telno`, `Borc`, `kitap1`, `kitap2`, `kitap3`)"
                    + " VALUES ( 'Mert Can Dudul', '3856161', 'Mertcan@windowslive.com', '538445679',"
                    + " '0', 'Suç ve ceza', 'Matematik', 'Ceza ve suç')"
                    + ", ('Ahmet Emin ', '385646', 'aes@hotmail.com', '25432864', '500000000',"
                    + " 'Kaya ile ayşe', 'ali ata bak', 'Harun mMrte bak')";*/
            stmt.executeUpdate(sql1);

            String sql2 = "INSERT INTO `admin` (`Username`, `Password`) "
                    + "VALUES ('1', '1'),"
                    + "('','')";
            /*"INSERT INTO `yonetici`"
                    + " (`KullaniciAdi`, `Sifre`) "
                    + "VALUES ( 'Mert', '123'),"
                    + " ( 'Harun', '123'),"
                    + "('Ferdi','056')";*/
            stmt.executeUpdate(sql2);
        } catch (SQLException se) {
            //Handle errors for JDBC
            JOptionPane.showMessageDialog(null, se);
        } catch (Exception e) {
            //Handle errors for Class.forName
            JOptionPane.showMessageDialog(null, e);
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {

                    stmt.close();

                }
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException se) {
                JOptionPane.showMessageDialog(null, se);
            }//end finally try
        }//end try

        //end main
    }

}
