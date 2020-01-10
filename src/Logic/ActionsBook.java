package Logic;

import Gui.BookAddGui;
import Gui.BookReturnGui;
import Gui.BookSearchListGui;
import Gui.BookUpdateRemoveGui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ActionsBook implements ActionListener, FocusListener {

    BookAddGui bag;
    BookReturnGui brg;
    BookSearchListGui bslg;
    BookUpdateRemoveGui burg;

    //  MainGui mg;
    JButton clearBook_info_txt;
    JButton saveBook_info_txt;
    String emptyError = "BOŞ GEÇİLEMEZ";
    String NumberError = "SADECE SAYI GİRİN";
    public final int DB_BARKODNO = 0;
    public final int DB_BOOKNAME = 1;
    public final int DB_AUTHORNAME = 2;
    public final int DB_CATEGORYNAME = 3;
    Boolean BookCanAdd; // database den kitap kontrolu yapmak için true ise kitap alınabilir

    //eğer bag hata alırsa diğer taraftan burayı setlerim
    public ActionsBook(BookReturnGui brg) {
        this.brg = brg;
        //mg = brg.getMg();

    }

    public ActionsBook(BookAddGui bag) {
        this.bag = bag;
        //  mg = bag.getMg();
    }

    public ActionsBook(BookSearchListGui bslg) {
        this.bslg = bslg;
        //     mg = bslg.getMg();
    }

    public ActionsBook(BookUpdateRemoveGui burg) {
        this.burg = burg;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (bag != null) {
            if (e.getSource() == bag.getBtnComeBack()) {

                bag.getJp().setVisible(false);
                bag.getMg().getJp().setVisible(true);
                bag.getMg().getJf().setTitle("ANA SAYFA");
                clearAllTxtMainGui();
            }
            if (e.getSource() == bag.getBtnAddBook()) {
                if (!bag.getTxtBookBarcodeNo().getText().trim().equals("") && !bag.getTxtBookBarcodeNo().getText().trim().equals(emptyError)
                        && !bag.getTxtBookName().getText().trim().equals("") && !bag.getTxtBookName().getText().trim().equals(emptyError)
                        && !bag.getTxtCategory().getText().trim().equals("") && !bag.getTxtCategory().getText().trim().equals(emptyError)
                        && !bag.getTxtAuthorName().getText().trim().equals("") && !bag.getTxtAuthorName().getText().trim().equals(emptyError)) {

                    try {

                        if (Long.parseLong(bag.getTxtBookBarcodeNo().getText()) > 0) {
                            DBbookAdd();
                        } else {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException ex) {
                        java.awt.Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null, "Kitap Barkod Numarası 0 dan büyük SAYI  OLMAK zorundadır", "SAYI FORMAT HATASI", JOptionPane.ERROR_MESSAGE);
                        bag.getTxtBookBarcodeNo().setText("");
                    }

                } else {
                    java.awt.Toolkit.getDefaultToolkit().beep();

                    if (bag.getTxtBookBarcodeNo().getText().trim().equals("")) {
                        bag.getTxtBookBarcodeNo().setForeground(Color.red);
                        bag.getTxtBookBarcodeNo().setText(emptyError);
                    }
                    if (bag.getTxtBookName().getText().trim().equals("")) {
                        bag.getTxtBookName().setForeground(Color.red);
                        bag.getTxtBookName().setText(emptyError);

                    }
                    if (bag.getTxtCategory().getText().trim().equals("")) {
                        bag.getTxtCategory().setForeground(Color.red);
                        bag.getTxtCategory().setText(emptyError);
                    }
                    if (bag.getTxtAuthorName().getText().trim().equals("")) {
                        bag.getTxtAuthorName().setForeground(Color.red);
                        bag.getTxtAuthorName().setText(emptyError);
                    }
                }

            }
        } else if (brg != null) {
            if (e.getSource() == brg.getBtnComeBack()) {
                brg.getJp().setVisible(false);
                brg.getMg().getJp().setVisible(true);
                brg.getMg().getJf().setTitle("ANA SAYFA");
                clearAllTxtMainGui();
            } else if (e.getSource() == brg.getTxtBarcodeNo() || e.getSource() == brg.getTxtStudentNo()) {
                if (brg.getTxtBarcodeNo().getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Kitap Barkod Numarası boş bırakılamaz");

                } else if (brg.getTxtStudentNo().getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Öğrenci Numarası boş bırakılamaz");

                } else {
                    JOptionPane.showMessageDialog(null, "sql e bağlanacak");
                }
            }
        } else if (bslg != null) {
            /*        getTxtAuthorName().addActionListener(action);
        getTxtBarcodeNo().addActionListener(action);
        getTxtBookName().addActionListener(action);
        getBtnComeBack().addActionListener(action);*/
            if (e.getSource() == bslg.getBtnComeBack()) {
                bslg.getJp().setVisible(false);
                bslg.getMg().getJf().setTitle("ANA SAYFA");
                bslg.getMg().getJp().setVisible(true);
                clearAllTxtMainGui();
            } else if (e.getSource() == bslg.getTxtBarcodeNo()) {
                DBBookList(DB_BARKODNO);
            } else if (e.getSource() == bslg.getTxtBookName()) {
                DBBookList(DB_BOOKNAME);
            } else if (e.getSource() == bslg.getTxtAuthorName()) {
                DBBookList(DB_AUTHORNAME);
            } else if (e.getSource() == bslg.getTxtCategory()) {

                DBBookList(DB_CATEGORYNAME);
            }
        } else if (burg != null) {
            if (e.getSource() == burg.getBtnComeBack()) {
                burg.setVisible(false);
                burg.getMg().getJp().setVisible(true);
                burg.getJf().setTitle("ANA SAYFA");
                clearAllTxtMainGui();
            } else if (e.getSource() == burg.getBtnUpdate()) {
                JOptionPane.showMessageDialog(null, "buton aktifleştirildi sql komutları kaldı");
            } else if (e.getSource() == burg.getBtnDelete()) {
                JOptionPane.showMessageDialog(null, "buton aktifleştirildi sql komutları kaldı");
            } else if (e.getSource() == burg.getTxtToBeChangedBarcodeNo()) {
                JOptionPane.showConfirmDialog(null, "Aktifleşti sql'e bağlanılacak");
            }
        }
    }

    public void clearAllTxtMainGui() {

        if (bag != null) {
            bag.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            bag.getMg().gettxtStudentNo().setText("Öğrenci No Girin");
            bag.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            bag.getMg().getTxtBookBarcode().setText("Kitap Barkod No girin");
            bag.getMg().getTxtBookName().setText("");
            bag.getMg().gettxtResultScreen().setText("");
        } else if (brg != null) {
            brg.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            brg.getMg().gettxtStudentNo().setText("Öğrenci No Girin");
            brg.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            brg.getMg().getTxtBookBarcode().setText("Kitap Barkod No girin");
            brg.getMg().getTxtBookName().setText("");
            brg.getMg().gettxtResultScreen().setText("");

        } else if (bslg != null) {
            bslg.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            bslg.getMg().gettxtStudentNo().setText("Öğrenci No Girin");
            bslg.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            bslg.getMg().getTxtBookBarcode().setText("Kitap Barkod No girin");
            bslg.getMg().getTxtBookName().setText("");
            bslg.getMg().gettxtResultScreen().setText("");

        } else if (burg != null) {
            burg.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            burg.getMg().gettxtStudentNo().setText("Öğrenci No Girin");
            burg.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            burg.getMg().getTxtBookBarcode().setText("Kitap Barkod No girin");
            burg.getMg().getTxtBookName().setText("");
            burg.getMg().gettxtResultScreen().setText("");
        }
    }

    /*public MainGui getMg() {
        if (mg == null) {
            mg = new MainGui();
        }

        return mg;
    }

    public void setMg(MainGui mg) {
        this.mg = mg;
    }*/
    public JButton getClearBook_info_txt() {
        return clearBook_info_txt;
    }

    public void setClearBook_info_txt(JButton clearBook_info_txt) {
        this.clearBook_info_txt = clearBook_info_txt;
    }

    public JButton getSaveBook_info_txt() {
        return saveBook_info_txt;
    }

    public void setSaveBook_info_txt(JButton saveBook_info_txt) {
        this.saveBook_info_txt = saveBook_info_txt;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (bag != null) {
            if (bag.getTxtResult().getBackground() == Color.GREEN) {
                bag.getTxtResult().setBackground(new Color(206, 214, 224));
                bag.getTxtResult().setText("");
                bag.getTxtAuthorName().setText("");
                bag.getTxtCategory().setText("");
                bag.getTxtBookName().setText("");
                bag.getTxtBookBarcodeNo().setText("");
            }
            if (e.getSource() == bag.getTxtBookBarcodeNo() && bag.getTxtBookBarcodeNo().getText().trim().equals(emptyError)
                    || e.getSource() == bag.getTxtBookBarcodeNo() && bag.getTxtBookBarcodeNo().getText().trim().equals(NumberError)) {

                bag.getTxtBookBarcodeNo().setText("");
                bag.getTxtBookBarcodeNo().setForeground(Color.black);
            }
            if (e.getSource() == bag.getTxtBookName() && bag.getTxtBookName().getText().trim().equals(emptyError)) {

                bag.getTxtBookName().setText("");
                bag.getTxtBookName().setForeground(Color.black);
            }
            if (e.getSource() == bag.getTxtCategory() && bag.getTxtCategory().getText().trim().equals(emptyError)) {

                bag.getTxtCategory().setText("");
                bag.getTxtCategory().setForeground(Color.black);
            }
            if (e.getSource() == bag.getTxtAuthorName() && bag.getTxtAuthorName().getText().trim().equals(emptyError)) {

                bag.getTxtAuthorName().setText("");
                bag.getTxtAuthorName().setForeground(Color.black);
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (bag != null) {
            if (e.getSource() == bag.getTxtBookBarcodeNo() && bag.getTxtBookBarcodeNo().getText().trim().equals("")) {
                bag.getTxtBookBarcodeNo().setForeground(Color.red);
                bag.getTxtBookBarcodeNo().setText(emptyError);

            }
            if (e.getSource() == bag.getTxtBookName() && bag.getTxtBookName().getText().trim().equals("")) {
                bag.getTxtBookName().setForeground(Color.red);
                bag.getTxtBookName().setText(emptyError);

            }
            if (e.getSource() == bag.getTxtCategory() && bag.getTxtCategory().getText().trim().equals("")) {
                bag.getTxtCategory().setForeground(Color.red);
                bag.getTxtCategory().setText(emptyError);
            }
            if (e.getSource() == bag.getTxtAuthorName() && bag.getTxtAuthorName().getText().trim().equals("")) {
                bag.getTxtAuthorName().setForeground(Color.red);
                bag.getTxtAuthorName().setText(emptyError);
            }
        }

    }

    public void DBbookControl() {
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
            String SqlBookControlQuery = "SELECT * FROM  `book` WHERE BarcodeNo=" + bag.getTxtBookBarcodeNo().getText();

            ResultSet rs = stmt.executeQuery(SqlBookControlQuery);

            //rs.next(); // if I did not write this  I can't add new thing   but just 1 time I had to write or I will add as much as I write this
            while (rs.next()) {

                throw new Exception("Önceden Bu Barkod Numarası Alınmış");
            }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "11111111111111111", "Kayıt Hatası", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Lütfen Kitap Barkod Numarsına Sadece Sayı Girin", "Kayıt Hatası", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            BookCanAdd = false;
        }

    }

    public void DBbookAdd() {
        BookCanAdd = true;
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

            DBbookControl();
            if (!BookCanAdd) {
                throw new Exception();
            }
            stmt = conn.createStatement();
            String SqlBookAdd = "INSERT INTO `book` "
                    + "(`Id`,`BarcodeNo`,`Name`,`AuthorName`,`CategoryName`) VALUES "
                    + "(NULL,"
                    + "'" + bag.getTxtBookBarcodeNo().getText() + "',"
                    + "'" + bag.getTxtBookName().getText() + "',"
                    + "'" + bag.getTxtAuthorName().getText() + "',"
                    + "'" + bag.getTxtCategory().getText() + "')";
            stmt.executeUpdate(SqlBookAdd);

            /* bag.getTxtAuthorName().setText("");
                    bag.getTxtCategory().setText("");
                    bag.getTxtBookName().setText("");
                    bag.getTxtBookBarcodeNo().setText("");*/
            bag.getTxtResult().setBackground(Color.GREEN);
            bag.getTxtResult().setText("Kitap Kayıt Başarılı");
            SuccessVoice();

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (SQLException ex) {
            java.awt.Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "1-)Kitap Barcode Numarasına Sadece Sayı Girilebilir\n\n "
                    + "2-)Xampp hata meydana geldi  \n\n "
                    + "(yukarıdaki çözümlerden birisini deneyin)", "SQL HATASI", JOptionPane.ERROR_MESSAGE);

            bag.getTxtBookBarcodeNo().setForeground(new Color(255, 159, 26));
            bag.getTxtBookBarcodeNo().setText(NumberError);

        } catch (Exception ex) {
            java.awt.Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Bu Barkod Numarası Zaten Kayıtlı", "KAYIT HATASI", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void DBBookList(int sectorNumber) {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";

        //  Database credentials
        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            stmt = conn.createStatement();
            String SqlListQuery = null; //

            ResultSet rs = null;
            switch (sectorNumber) {
                case DB_BARKODNO:
                    SqlListQuery = "SELECT * FROM book where BarcodeNo= '" + bslg.getTxtBarcodeNo().getText() + "'";
                    // SqlListQuery = "SELECT * FROM book where id= 3";
                    rs = stmt.executeQuery(SqlListQuery);
                    //ResultSet = SqlListStatement.executeQuery();
                    int i = 0;
                    while (rs.next()) {
                        JOptionPane.showMessageDialog(null, rs.getInt("Id"));
                        System.out.println(rs.getString("Name"));
                        i++;
                    }
                    rs = stmt.executeQuery(SqlListQuery);

                    bslg.data = new String[i][5];
                    int j = 0;
                    while (rs.next()) {
                        JOptionPane.showMessageDialog(null, rs.getInt("Id"));
                        System.out.println(rs.getString("Name"));
                        bslg.data[j][2] = rs.getString("Name");
                        System.out.println("bslg.data[" + j + "][2]  : " + bslg.data[j][2]);
                        j++;
                        //    bslg.setTable(bslg.data, bslg.column);
                        //  bslg.setTable(bslg.data, bslg.column);
                        // bslg.getSp();
                    }

                    break;
                case DB_BOOKNAME:
                    break;
                case DB_AUTHORNAME:
                    break;
                case DB_CATEGORYNAME:
                    break;
                /* stmt = conn.createStatement();
            String SqlBookAdd = "INSERT INTO `book` "
                    + "(`Id`,`BarcodeNo`,`Name`,`AuthorName`,`CategoryName`) VALUES "
                    + "(NULL,"
                    + "'" + bag.getTxtBookBarcodeNo().getText() + "',"
                    + "'" + bag.getTxtBookName().getText() + "',"
                    + "'" + bag.getTxtAuthorName().getText() + "',"
                    + "'" + bag.getTxtCategory().getText() + "')";
            stmt.executeUpdate(SqlBookAdd);*/
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }

        /*   } else if (e.getSource() == bslg.getTxtBarcodeNo()) {

                JOptionPane.showMessageDialog(null, "Barkod No'ya tıklandı Sql kaldı ");
            } else if (e.getSource() == bslg.getTxtBookName()) {
                JOptionPane.showMessageDialog(null, "Kitap adı'na tıklandı Sql kaldı ");
            } else if (e.getSource() == bslg.getTxtAuthorName()) {
                JOptionPane.showMessageDialog(null, "Yazar adına tıklandı Sql kaldı ");
            } else if (e.getSource() == bslg.getTxtCategory()) {*/
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
