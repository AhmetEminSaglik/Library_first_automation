package Logic;

import Business.Main;
import Gui.Login;
import Gui.MainGui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ActionsLogin implements ActionListener {

    /*
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

            String sqlControl = "SHOW DATABASE LIKE 'LIBRARY' ";
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
            String tableControl = "SHOW TABLES LIKE 'Ogrenci' ";
            String tableYoneticiControl = "SHOW TABLES LIKE 'Yonetici' ";
            String tableKitapControl = "SHOW TABLES LIKE 'Kitap'";
            if (stmt.executeUpdate(tableControl) == -1 || stmt.executeUpdate(tableYoneticiControl) == -1 || stmt.executeUpdate(tableKitapControl) == -1) {
                return;
            }
            String sql = "CREATE TABLE `Ogrenci`"
                    + "( `id` INT NOT NULL , `AdSoyad` VARCHAR(50) NOT NULL ,"
                    + " `No` INT NOT NULL , `Email` VARCHAR(250) NOT NULL ,"
                    + " `telno` INT NOT NULL , `Borc` INT NOT NULL , `kitap1`"
                    + " VARCHAR(255) NOT NULL , `kitap2` VARCHAR(255) NOT NULL ,"
                    + " `kitap3` VARCHAR(255) NOT NULL , PRIMARY KEY (`id`));";
            String sql2 = "CREATE TABLE `library`.`yonetici` ( `id` INT NOT NULL , "
                    + "`KullaniciAdi` VARCHAR(255) NOT NULL , "
                    + "`Sifre` VARCHAR(255) NOT NULL "
                    + ", PRIMARY KEY (`id`)); ";
            String sql3 = "CREATE TABLE `library`.`kitaplar` "
                    + "( `id` INT NOT NULL AUTO_INCREMENT , "
                    + "`KitapAdi` VARCHAR(255) NOT NULL , `BarkodNo` VARCHAR(255) NOT NULL "
                    + ", `YazarAdi` VARCHAR(255) NOT NULL , `OgrenciId` INT NOT NULL "
                    + ", `Tarih` DATE , PRIMARY KEY (`id`)) ";

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
        System.out.println("Goodbye!");
    }*/
    Login login;

    public ActionsLogin(Login login) {
        this.login = login;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (login != null) {
            if (e.getSource() == login.getSignIn()) {
                String JDBC_DRIVER = "com.mysql.jdbc.Driver";
                String DB_URL = "jdbc:mysql://localhost/library";

                //  Database credentials
                String USER = "root";
                String PASS = "";

                Connection conn = null;
                Statement stmt = null;
                try {
                    Class.forName(JDBC_DRIVER);
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);

                    stmt = conn.createStatement();

                    String sqlYoneticiKontrol = "SELECT * FROM admin WHERE Username='" + login.getTxtusername().getText() + "' AND Password ='" + String.valueOf(login.getjPass().getPassword()) + "'";
                    ResultSet rs = stmt.executeQuery(sqlYoneticiKontrol);
                    boolean girisDogru = false;
                    while (rs.next()) {
                        String kullaniciAdi = rs.getString("Username");
                        String Sifre = rs.getString("Password");

                        if (kullaniciAdi.equals(login.getTxtusername().getText()) && Sifre.equals(String.valueOf(login.getjPass().getPassword()))) {
                            login.setVisible(false);
                            MainGui mg = new MainGui(login);
                            mg.action.NumbersOfBooks();
                            girisDogru = true;

                        }

                    }
                    if (!girisDogru) {
                        JOptionPane.showMessageDialog(null, "HATALI KULLANICI  ADI/ ŞİFRE");
                        login.getTxtusername().setText("");
                        login.getjPass().setText("");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    System.out.println("class bulunamadı");
                }

            }
        }
    }

}

//sqlite
