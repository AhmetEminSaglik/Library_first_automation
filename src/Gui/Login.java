package Gui;

import Logic.ActionsLogin;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

        CreateDatabase();
        CreateTable();
        //this.setVisible(false);
        //MainGui mg = new MainGui(this);
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
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();

            String sqlControl = "SHOW DATABASES LIKE 'LIBRARY' ";
            stmt.executeUpdate(sqlControl);
            if (stmt.executeUpdate(sqlControl) == -1) { // conn.createStatement() dene bir de stmt yerine
                return; //Veri tabanı zaten var
            }
            String sql = "CREATE DATABASE LIBRARY";

            stmt.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException ex) {

        }
    }

    public void CreateTable() {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY";

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
            String tableControl = "SHOW TABLES LIKE 'student' ";
            String tableYoneticiControl = "SHOW TABLES LIKE 'admin' ";
            String tableKitapControl = "SHOW TABLES LIKE 'book'";
            if (stmt.executeUpdate(tableControl) == -1 || stmt.executeUpdate(tableYoneticiControl) == -1 || stmt.executeUpdate(tableKitapControl) == -1) {
                return;
            }
            String sql = "CREATE TABLE `library`.`student`"
                    + " ( `id` INT NOT NULL AUTO_INCREMENT , "
                    + "`No` INT NOT NULL , "
                    + "`Name` VARCHAR(50) NOT NULL , "
                    + "`Surname` VARCHAR(50) NOT NULL , "
                    //   + "`Debt` DOUBLE NOT NULL , "
                    + "PRIMARY KEY (`id`)) ;";
            String sql2 = "CREATE TABLE `library`.`admin` "
                    + "( `Username` VARCHAR(50) NOT NULL  , "
                    + "`Password` VARCHAR(50) NOT NULL);";
            /* "CREATE TABLE `library`.`yonetici` ( `id` INT NOT NULL AUTO_INCREMENT , "
                    + "`KullaniciAdi` VARCHAR(255) NOT NULL , "
                    + "`Sifre` VARCHAR(255) NOT NULL "
                    + ", PRIMARY KEY (`id`)); ";*/
            String sql3 = "CREATE TABLE `library`.`book` "
                    + "( `id` INT(50) NOT NULL AUTO_INCREMENT , "
                    + "`BarcodeNo` VARCHAR(50) NOT NULL , "
                    + "`Name` VARCHAR(50) NOT NULL , "
                    + "`AuthorName` VARCHAR(50) NOT NULL , "
                    + "`Category` VARCHAR(50) NOT NULL , "
                    + "`TakenDate` DATE NOT NULL , "
                    + "PRIMARY KEY (`id`));";/*"CREATE TABLE `library`.`kitaplar` "
                    + "( `id` INT NOT NULL AUTO_INCREMENT , "
                    + "`KitapAdi` VARCHAR(255) NOT NULL , `BarkodNo` VARCHAR(255) NOT NULL "
                    + ", `YazarAdi` VARCHAR(255) NOT NULL , `OgrenciId` INT NOT NULL "
                    + ", `Tarih` DATE , PRIMARY KEY (`id`)) ";*/

            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql3);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("BİTTİ");
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
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();

            String sql = "INSERT INTO `student` (`id`, `No`, `Name`, `Surname`, `Debt`) "
                    + "VALUES (NULL, '385931', 'Ahmet Emin', 'SAĞLIK', '10,35')";
            /* "INSERT INTO `ogrenci` "
                    + "( `AdSoyad`, `No`, `Email`, `telno`, `Borc`, `kitap1`, `kitap2`, `kitap3`)"
                    + " VALUES ( 'Mert Can Dudul', '3856161', 'Mertcan@windowslive.com', '538445679',"
                    + " '0', 'Suç ve ceza', 'Matematik', 'Ceza ve suç')"
                    + ", ('Ahmet Emin ', '385646', 'aes@hotmail.com', '25432864', '500000000',"
                    + " 'Kaya ile ayşe', 'ali ata bak', 'Harun mMrte bak')";*/
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");

            String sql2 = "INSERT INTO `admin` (`Username`, `Password`) VALUES ('root', 'toor')";
            /*"INSERT INTO `yonetici`"
                    + " (`KullaniciAdi`, `Sifre`) "
                    + "VALUES ( 'Mert', '123'),"
                    + " ( 'Harun', '123'),"
                    + "('Ferdi','056')";*/
            stmt.executeUpdate(sql2);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        //end main

    }

}
