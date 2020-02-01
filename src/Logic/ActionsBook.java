package Logic;

import Gui.BookAddGui;
import Gui.BookReturnGui;
import Gui.BookSearchListGui;
import Gui.BookUpdateRemoveGui;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ActionsBook implements ActionListener, FocusListener {

    BookAddGui bag;
    BookReturnGui brg;
    BookSearchListGui bslg;
    BookUpdateRemoveGui burg;

    //  MainGui mg;
    JButton clearBook_info_txt;
    JButton saveBook_info_txt;
    String emptyError = "BOŞ GEÇİLEMEZ";
    //  String NumberError = "SADECE SAYI GİRİN";
    public final int DB_BARKODNO = 0;
    public final int DB_BOOKNAME = 1;
    public final int DB_AUTHORNAME = 2;
    public final int DB_CATEGORYNAME = 3;

    Boolean BookCanAdd; // database den kitap kontrolu yapmak için true ise kitap alınabilir
    boolean BookBringCame;
    boolean BookCanUpdate;
    Color bslgPlaceHolder = Color.GRAY;
    Font fontTxtPlaceHolder = new Font("", Font.ITALIC, 15);

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
        SearcBookList(5);
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
            if (e.getSource() == bag.getBtnAddBook()
                    || e.getSource() == bag.getTxtBookBarcodeNo()
                    || e.getSource() == bag.getTxtBookName()
                    || e.getSource() == bag.getTxtCategory()
                    || e.getSource() == bag.getTxtAuthorName()) {
                if (!bag.getTxtBookBarcodeNo().getText().trim().equals("") && !bag.getTxtBookBarcodeNo().getText().trim().equals(emptyError)
                        && !bag.getTxtBookName().getText().trim().equals("") && !bag.getTxtBookName().getText().trim().equals(emptyError)
                        && !bag.getTxtCategory().getText().trim().equals("") && !bag.getTxtCategory().getText().trim().equals(emptyError)
                        && !bag.getTxtAuthorName().getText().trim().equals("") && !bag.getTxtAuthorName().getText().trim().equals(emptyError)) {

                    DBbookAdd();

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
                    if (StudentExist() == true && BookExist() == true) {
                        ReturnBookToLibrary();
                    }

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
            } else if (e.getSource() == bslg.getTxtAuthorName()) {
                SearcBookList(0);
            } else if (e.getSource() == bslg.getTxtBookName()) {
                SearcBookList(1);
            } else if (e.getSource() == bslg.getTxtBarcodeNo()) {
                SearcBookList(2);
            } else if (e.getSource() == bslg.getTxtCategory()) {
                SearcBookList(3);
            }

        } else if (burg != null) {
            if (e.getSource() == burg.getBtnComeBack()) {
                burg.setVisible(false);
                burg.getMg().getJp().setVisible(true);
                burg.getJf().setTitle("ANA SAYFA");
                clearAllTxtMainGui();
            } else if (e.getSource() == burg.getBtnUpdate()
                    || e.getSource() == burg.getTxtNewBarcodeNo()
                    || e.getSource() == burg.getTxtNewBookName()
                    || e.getSource() == burg.getTxtNewAuthorName()
                    || e.getSource() == burg.getTxtNewCategory()) {
                DBBookUpdate();
            } else if (e.getSource() == burg.getBtnDelete()) {
                DBBookDelete();
            } else if (e.getSource() == burg.getTxtBarcodeNo()) {

                DBBookBringData();
            }
        }
    }

    public void clearAllTxtMainGui() {

        if (bag != null) {
            bag.getMg().action.NumbersOfBooks();
            bag.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            bag.getMg().gettxtStudentNo().setText("Öğrenci No Girin");
            bag.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            bag.getMg().getTxtBookBarcode().setText("Kitap Barkod No girin");
            bag.getMg().getTxtBookName().setText("");
            bag.getMg().gettxtResultScreen().setText("");
            bag.getMg().gettxtResultScreen().setBackground(new Color(206, 214, 224));

        } else if (brg != null) {
            brg.getMg().action.NumbersOfBooks();
            brg.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            brg.getMg().gettxtStudentNo().setText("Öğrenci No Girin");
            brg.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            brg.getMg().getTxtBookBarcode().setText("Kitap Barkod No girin");
            brg.getMg().getTxtBookName().setText("");
            brg.getMg().gettxtResultScreen().setText("");
            brg.getMg().gettxtResultScreen().setBackground(new Color(206, 214, 224));
        } else if (bslg != null) {
            bslg.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            bslg.getMg().gettxtStudentNo().setText("Öğrenci No Girin");
            bslg.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            bslg.getMg().getTxtBookBarcode().setText("Kitap Barkod No girin");
            bslg.getMg().getTxtBookName().setText("");
            bslg.getMg().gettxtResultScreen().setText("");
            bslg.getMg().gettxtResultScreen().setBackground(new Color(206, 214, 224));

        } else if (burg != null) {
            burg.getMg().action.NumbersOfBooks();
            burg.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            burg.getMg().gettxtStudentNo().setText("Öğrenci No Girin");
            burg.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            burg.getMg().getTxtBookBarcode().setText("Kitap Barkod No girin");
            burg.getMg().getTxtBookName().setText("");
            burg.getMg().gettxtResultScreen().setText("");
            burg.getMg().gettxtResultScreen().setBackground(new Color(206, 214, 224));
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
            if (e.getSource() == bag.getTxtBookBarcodeNo() && bag.getTxtBookBarcodeNo().getText().trim().equals(emptyError)) {//  || e.getSource() == bag.getTxtBookBarcodeNo() && bag.getTxtBookBarcodeNo().getText().trim().equals(NumberError)

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
        } else if (bslg != null) {
            /*
    Color bslgPlaceHolder = Color.GRAY;
    Font fontTxtPlaceHolder = new Font("", Font.ITALIC, 15);*/
            if (e.getSource() == bslg.getTxtAuthorName()) {

                //   bslgPlaceHolder(bslg.getTxtAuthorName());
                bslg.setTxtAuthorName(bslgPlaceHolder(bslg.getTxtAuthorName()));
            } else if (e.getSource() == bslg.getTxtBarcodeNo()) {

                bslg.setTxtBarcodeNo(bslgPlaceHolder(bslg.getTxtBarcodeNo()));
                //  bslgPlaceHolder(bslg.getTxtAuthorName());
            } else if (e.getSource() == bslg.getTxtBookName()) {
                bslg.setTxtBookName(bslgPlaceHolder(bslg.getTxtBookName()));
                //  bslgPlaceHolder(bslg.getTxtAuthorName());
            } else if (e.getSource() == bslg.getTxtCategory()) {
                bslg.setTxtCategory(bslgPlaceHolder(bslg.getTxtCategory()));
                //  bslgPlaceHolder(bslg.getTxtAuthorName());
            }
        }
    }

    public JTextField bslgPlaceHolder(JTextField jtxt) {
        if (jtxt != bslg.getTxtBarcodeNo()) {
            bslg.getTxtBarcodeNo().setFont(fontTxtPlaceHolder);
            bslg.getTxtBarcodeNo().setForeground(bslgPlaceHolder);
            bslg.getTxtBarcodeNo().setText("Barkod numarası giriniz");
        }
        if (jtxt != bslg.getTxtAuthorName()) {
            bslg.getTxtAuthorName().setFont(fontTxtPlaceHolder);
            bslg.getTxtAuthorName().setForeground(bslgPlaceHolder);
            bslg.getTxtAuthorName().setText("Yazar ismi giriniz");
        }
        if (jtxt != bslg.getTxtBookName()) {
            bslg.getTxtBookName().setFont(fontTxtPlaceHolder);
            bslg.getTxtBookName().setForeground(bslgPlaceHolder);
            bslg.getTxtBookName().setText("Kitap ismi giriniz");
        }
        if (jtxt != bslg.getTxtCategory()) {
            bslg.getTxtCategory().setFont(fontTxtPlaceHolder);
            bslg.getTxtCategory().setForeground(bslgPlaceHolder);
            bslg.getTxtCategory().setText("Kategori ismi giriniz");
        }
        if (jtxt.getText().trim().equals("Barkod numarası giriniz")
                || jtxt.getText().trim().equals("Yazar ismi giriniz")
                || jtxt.getText().trim().equals("Kitap ismi giriniz")
                || jtxt.getText().trim().equals("Kategori ismi giriniz")) {
            jtxt.setText("");
        }
        jtxt.setFont(new Font("", Font.BOLD, 15));
        jtxt.setForeground(Color.BLACK);
        return jtxt;
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
        ResultSet rs = null;
        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String SqlBookControlQuery = "SELECT * FROM  `book` WHERE BarcodeNo LIKE '" + bag.getTxtBookBarcodeNo().getText().trim() + "'";

            rs = stmt.executeQuery(SqlBookControlQuery);

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

    public void DBbookAdd() {
        BookCanAdd = true;
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";

        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            DBbookControl();
            if (!BookCanAdd) {
                throw new Exception();
            }
            stmt = conn.createStatement();
            String SqlBookAdd = "INSERT INTO `book` "
                    + "(`Id`,`BarcodeNo`,`Name`,`AuthorName`,`CategoryName`) VALUES "
                    + "(NULL,"
                    + "'" + bag.getTxtBookBarcodeNo().getText().trim() + "',"
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
            //  bag.getTxtBookBarcodeNo().setText(NumberError);

        } catch (Exception ex) {
            java.awt.Toolkit.getDefaultToolkit().beep();
            //JOptionPane.showMessageDialog(null, "Bu Barkod Numarası Zaten Kayıtlı", "KAYI2T HATASI", JOptionPane.ERROR_MESSAGE);
            bag.getTxtResult().setBackground(Color.ORANGE);
            bag.getTxtResult().setText("Bu Barkod Numarası Zaten Kayıtlı");

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

    /*  public void DBBookList(int sectorNumber) {
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
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String SqlListQuery = null; //

            rs = null;
            switch (sectorNumber) {
                case DB_BARKODNO:
                    SqlListQuery = "SELECT * FROM book where BarcodeNo= '" + bslg.getTxtBarcodeNo().getText() + "'";
                    // SqlListQuery = "SELECT * FROM book where id= 3";
                    rs = stmt.executeQuery(SqlListQuery);
                    //ResultSet = SqlListStatement.executeQuery();
                    int i = 0;
                    while (rs.next()) {
                        JOptionPane.showMessageDialog(null, rs.getInt("Id"));

                        i++;
                    }
                    rs = stmt.executeQuery(SqlListQuery);

                    bslg.data = new String[i][5];
                    int j = 0;
                    while (rs.next()) {
                        JOptionPane.showMessageDialog(null, rs.getInt("Id"));

                        bslg.data[j][2] = rs.getString("Name");

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
                 stmt = conn.createStatement();
            String SqlBookAdd = "INSERT INTO `book` "
                    + "(`Id`,`BarcodeNo`,`Name`,`AuthorName`,`CategoryName`) VALUES "
                    + "(NULL,"
                    + "'" + bag.getTxtBookBarcodeNo().getText() + "',"
                    + "'" + bag.getTxtBookName().getText() + "',"
                    + "'" + bag.getTxtAuthorName().getText() + "',"
                    + "'" + bag.getTxtCategory().getText() + "')";
            stmt.executeUpdate(SqlBookAdd);
            }
        } catch (ClassNotFoundException ex) {

            JOptionPane.showMessageDialog(null, ex);
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex);
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


        /*   } else if (e.getSource() == bslg.getTxtBarcodeNo()) {

                JOptionPane.showMessageDialog(null, "Barkod No'ya tıklandı Sql kaldı ");
            } else if (e.getSource() == bslg.getTxtBookName()) {
                JOptionPane.showMessageDialog(null, "Kitap adı'na tıklandı Sql kaldı ");
            } else if (e.getSource() == bslg.getTxtAuthorName()) {
                JOptionPane.showMessageDialog(null, "Yazar adına tıklandı Sql kaldı ");
            } else if (e.getSource() == bslg.getTxtCategory()) {
    }*/
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

    public void DBBookDelete() {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";
        BookCanUpdate = true;
        //  Database credentials
        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement preparedStmt = null;
        boolean BookDeleted = true;
        boolean AlreadyCame = true;
        boolean StudentTookBook = false;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String SqlBookControlQuery = "SELECT * FROM `book` WHERE  BarcodeNo LIKE '" + burg.getTxtBarcodeNo().getText() + "'";

            rs = stmt.executeQuery(SqlBookControlQuery);

            if (rs.next()) {

                if (!burg.getTxtNewBarcodeNo().getText().equals((rs.getString("BarcodeNo")))
                        || !burg.getTxtNewBookName().getText().equals(rs.getString("Name"))
                        || !burg.getTxtNewAuthorName().getText().equals(rs.getString("AuthorName"))
                        || !burg.getTxtNewCategory().getText().equals(rs.getString("CategoryName"))) {

                    AlreadyCame = false;

                    throw new Exception();
                }
                if (!rs.getString("StudentNo").equals(null)) {

                    StudentTookBook = true;
                    throw new Exception();
                }
            } else {

                throw new Exception("Kayıtlı Kitap Bulunamadı");
            }

            stmt = conn.createStatement();

            String SqlBookdDeleteQuery = "DELETE FROM `book` WHERE BarcodeNo LIKE '" + burg.getTxtNewBarcodeNo().getText() + "'";

            //    stmt.executeQuery(SqlStudentdDeleteQuery);
            preparedStmt = conn.prepareStatement(SqlBookdDeleteQuery);

            int answer = JOptionPane.showConfirmDialog(null, "Kitabı Silmek istediğinizden Emin misiniz ? ", "SİLME UYARISI",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (answer == JOptionPane.YES_OPTION) {
                SuccessVoice();

                burg.getTxtNewBarcodeNo().setText("");
                burg.getTxtNewBookName().setText("");
                burg.getTxtNewAuthorName().setText("");
                burg.getTxtNewCategory().setText("");
                burg.getTxtResult().setText("Kitap Silindi");
                burg.getTxtResult().setBackground(new Color(255, 121, 63));
                preparedStmt.execute();
            } else {
                burg.getTxtResult().setText("Kitap Silme İşlemi İptal Edildi");
                burg.getTxtResult().setBackground(Color.PINK);
                java.awt.Toolkit.getDefaultToolkit().beep();
            }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "ClASS NOT FOUND");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "SQL HATASI", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            java.awt.Toolkit.getDefaultToolkit().beep();
            if (AlreadyCame == false) {

                int answer = JOptionPane.showConfirmDialog(null, "Kitabı Silmek İçin Önce Kitap Bilgilerini Getirmeniz gerekmektedir\n"
                        + "                                        Bilgiler Getirilsin mi ?", "EŞLEŞME HATASI", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    DBBookBringData();
                }
            }
            if (StudentTookBook == true) {
                burg.getTxtResult().setText("Kitap Öğrenci Üzerine Kayıtlı, Silme Başarısız");
                burg.getTxtResult().setBackground(new Color(250, 177, 160));

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

    }

    public void DBBookBringData() {

        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";

        //  Database credentials
        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        BookBringCame = false;
        Boolean AlreadyCame = false;
        boolean BarcodeNoEmpty = false;

        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String SqlBookBrinQuery = "SELECT * FROM `book` WHERE  BarcodeNo LIKE '" + burg.getTxtBarcodeNo().getText().trim() + "'";

            rs = stmt.executeQuery(SqlBookBrinQuery);
            while (rs.next()) {

                BarcodeNoEmpty = true;
                if (burg.getTxtNewBarcodeNo().getText().equals((rs.getString("BarcodeNo")))
                        && burg.getTxtNewBookName().getText().equals(rs.getString("Name"))
                        && burg.getTxtNewAuthorName().getText().equals(rs.getString("AuthorName"))
                        && burg.getTxtNewCategory().getText().equals(rs.getString("CategoryName"))) {
                    AlreadyCame = true;

                    throw new Exception();
                }

                burg.getTxtNewBarcodeNo().setText(rs.getString("BarcodeNo"));
                burg.getTxtNewBookName().setText(rs.getString("Name"));
                burg.getTxtNewAuthorName().setText(rs.getString("AuthorName"));
                burg.getTxtNewCategory().setText(rs.getString("CategoryName"));
                BookBringCame = true;
                burg.getTxtResult().setBackground(new Color(24, 220, 255));
                burg.getTxtResult().setText("Bilgiler Getirildi");

                SuccessVoice();
            }
            if (!BookBringCame) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, burg.getTxtBarcodeNo().getText() + " Barkod Nolu Kitap Kaydı YOKTUR", "EKSİK KAYIT HATASI", JOptionPane.ERROR_MESSAGE);
                burg.getTxtNewBarcodeNo().setText("");
                burg.getTxtNewBookName().setText("");
                burg.getTxtNewAuthorName().setText("");
                burg.getTxtNewCategory().setText("");

                burg.getTxtResult().setBackground(new Color(255, 82, 82));
                burg.getTxtResult().setText("İstenilen Bilgiler Kayıtta Bulunamadı");

            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex + " ");
        } catch (SQLException ex) {
            if (BarcodeNoEmpty == false) {
                JOptionPane.showMessageDialog(null, "Barkod Numarasını Doldurup Aratmalısınız", "SQL HATASI", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            if (AlreadyCame == true) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Aratılan Barkod Nolu Kitap  Zaten Getirildi", "ARAMA HATASI", JOptionPane.ERROR_MESSAGE);
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

    public void DBBookUpdate() {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";
        BookCanUpdate = true;
        boolean emptyArea = false;
        //  Database credentials
        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        try {
            if (burg.getTxtNewBarcodeNo().getText().trim().equals("")
                    || burg.getTxtNewBookName().getText().equals("")
                    || burg.getTxtNewAuthorName().getText().equals("")
                    || burg.getTxtNewCategory().getText().equals("")) {
                emptyArea = true;
                throw new Exception();
            }
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String SqlBookUpdateQuery = "UPDATE `book` SET \n"
                    + "BarcodeNo= '" + burg.getTxtNewBarcodeNo().getText().trim() + "' ,\n"
                    + "Name='" + burg.getTxtNewBookName().getText().trim() + "',\n"
                    + "AuthorName='" + burg.getTxtNewAuthorName().getText().trim() + "', \n"
                    + "CategoryName= '" + burg.getTxtNewCategory().getText().trim() + "'\n"
                    + "WHERE  BarcodeNo LIKE '" + burg.getTxtBarcodeNo().getText().trim() + "' ";

            DBBookControlToUpdate();

            if (!BookCanUpdate) {

                throw new Exception();

            }
            int answer = JOptionPane.showConfirmDialog(null, "Güncellemek İstediğinize Emin misiniz?", "GÜNCELLEME ONAYI", JOptionPane.YES_NO_OPTION);
            if (answer != JOptionPane.YES_OPTION) {
                return;
            }

            stmt.executeUpdate(SqlBookUpdateQuery);

            burg.getTxtResult().setBackground(Color.GREEN);
            burg.getTxtResult().setText("GÜNCELLENME BAŞARILI");
            SuccessVoice();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex + "aaaaaaaaaaaaaaaaa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "\n\n\n1-) Bilgileri Güncelleyebilmeniz İçin Önce Öğrenci Numarasını\n"
                    + "Aratmalısınız\n"
                    + "2-) Yeni Öğrenci Numarasına Sayı girmelisiniz", "GÜNCELLEME HATASI", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            if (emptyArea == true) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Lütfen Bilgileri Eksiksiz Bir Şekilde Doldurunuz", "EKSİK BİLGİ", JOptionPane.ERROR_MESSAGE);
                burg.getTxtResult().setText("Eksik Bilgi, Güncelleme Başarısız");
                burg.getTxtResult().setBackground(new Color(225, 112, 85));//rgb(225, 112, 85)
            }
        } finally {

            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();

                }
                /*    if (rs != null) {
                    rs.close();
                }
                if (preparedStmt != null) {
                    prepSQLException ex) {
                JOptionPane.shoaredStmt.close();
                }*/
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, " stmt , conn, rs, preparedStmt kapatılırken hata meydana geldi  (330/ActionStudent)");
            }

        }

    }

    public void DBBookControlToUpdate() {
        //DBStudentUpdate
        BookCanUpdate = true;
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";
        boolean newBookNoFree = true;
        boolean oldBookNoFree = false;
        boolean allSame = false;

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
            String SqlStudentControlQuery = "SELECT * FROM  `book` WHERE BarcodeNo LIKE '" + burg.getTxtNewBarcodeNo().getText().trim() + "'";

            rs = stmt.executeQuery(SqlStudentControlQuery);
            //rs.next(); // if I did not write this  I can't add new thing   but just 1 time I had to write or I will add as much as I write this

            while (rs.next()) {

                if (burg.getTxtBarcodeNo().getText().equals(rs.getString("BarcodeNo"))) {

                    if (burg.getTxtNewBarcodeNo().getText().equals(rs.getString("BarcodeNo"))
                            && burg.getTxtNewBookName().getText().equals(rs.getString("Name"))
                            && burg.getTxtNewAuthorName().getText().equals(rs.getString("AuthorName"))
                            && burg.getTxtNewCategory().getText().equals(rs.getString("CategoryName"))) {
                        allSame = true;

                        throw new Exception();
                    }
                } else {

                    newBookNoFree = false;

                    throw new Exception();
                }
            }
            SqlStudentControlQuery = "SELECT * FROM  `book` WHERE BarcodeNo LIKE '" + burg.getTxtBarcodeNo().getText() + "'";
            rs = null;
            rs = stmt.executeQuery(SqlStudentControlQuery);
            //      rs.next(); // if I did not write this  I can't add new thing   but just 1 time I had to write or I will add as much as I write this

            while (rs.next()) {

                oldBookNoFree = true;
            }
            if (oldBookNoFree == false) {

                throw new Exception();

            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Class Bulunamadı", "CLASS BULUNAMADI", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "SQL HATASI", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            BookCanUpdate = false;
            java.awt.Toolkit.getDefaultToolkit().beep();
            if (allSame == true) {
                JOptionPane.showMessageDialog(null, "Bilgiler zaten güncel", "GÜNCELLEME HATASI", JOptionPane.ERROR_MESSAGE);
            } else if (newBookNoFree == false) {
                JOptionPane.showMessageDialog(null, " YENİ Kitap Barkod Numarasında Başka Bir Kitap Kayıtlı", "GÜNCELLEME HATASI", JOptionPane.ERROR_MESSAGE);
            } else if (oldBookNoFree == false) {
                JOptionPane.showMessageDialog(null, " ESKİ Kitap BArkod Numarasında Kayıtlı Kitap Bulunmamaktadır\n"
                        + "Güncelleme Başarısız", "GÜNCELLEME HATASI", JOptionPane.ERROR_MESSAGE);
            }

            burg.getTxtResult().setBackground(Color.red);
            burg.getTxtResult().setBackground(new Color(255, 82, 82));
            burg.getTxtResult().setText("Güncelleme Başarısız");
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

    public boolean StudentExist() {
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

            String StudentExistQuery = "Select * FROM student WHERE No LIKE '" + brg.getTxtStudentNo().getText().trim() + "'";
            //stmt = conn.createStatement();

            rs = stmt.executeQuery(StudentExistQuery);
            if (rs.next()) {
                brg.getTxtStudentName().setText(rs.getString("Name"));
                return true;
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "CLASS NOT FOUND EXCEPTION");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "1 SQL HATASI");
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
        java.awt.Toolkit.getDefaultToolkit().beep();
        brg.getTxtResult().setBackground(Color.red);
        brg.getTxtResult().setText("Kayıtlı Öğrenci Bulunamadı");
        return false;

    }

    public boolean BookExist() {
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

            String BookExistQuery = "Select * FROM book WHERE BarcodeNo LIKE '" + brg.getTxtBarcodeNo().getText().trim() + "'";

            rs = stmt.executeQuery(BookExistQuery);
            if (!rs.next()) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                brg.getTxtResult().setBackground(Color.red);
                brg.getTxtResult().setText("Kayıtlı Kitap Bulunamadı");
                return false;
            }
            brg.getTxtBookName().setText(rs.getString("Name"));
            brg.getTxtAuthorName().setText(rs.getString("AuthorName"));

            /*     String BookFree = "Select * FROM book WHERE BarcodeNo LIKE '" + brg.getTxtBarcodeNo().getText().trim() + "' and "
                    + "StudentNo LIKE '" + brg.getTxtStudentNo().getText().trim() + "'";
            rs = stmt.executeQuery(BookFree);
            if (!rs.next()) {
                if (rs.getString("StudentNo").equals(null)) {
                    brg.getTxtResult().setBackground(Color.CYAN);
                    brg.getTxtResult().setText("Kitap Zaten Kütüphanede bulunuyor");
                } else {
                    java.awt.Toolkit.getDefaultToolkit().beep();

                    brg.getTxtResult().setBackground(Color.ORANGE);
                    brg.getTxtResult().setText("Kitap Başkasının üzerine Kayıtlı / İADE YAPILAMADI");
                }
                return false;
            }*/
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "CLASS NOT FOUND EXCEPTION");
        } catch (SQLException ex) {
            java.awt.Toolkit.getDefaultToolkit().beep();
            brg.getTxtResult().setText("EŞLEŞME BAŞARISIZ (Öğrenci Ödünç Alan kişi) / ( YADA ) / Kitap Şuan Kütüphanemizde bulunmaktadır");
            brg.getTxtResult().setBackground(new Color(250, 130, 49));
            return false;
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

        return true;
    }

    public void ReturnBookToLibrary() {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";

        String USER = "root";
        String PASS = "";
        Double debt = 0.0;
        Double Fine = 0.0;
        final Double FineFee = 0.5;
        int delay = 0;
        Date borrowedDate = null;
        boolean FineAdded = false;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
// öğrenci var mı
// kitap var mı
// ikiside uygun ise iade edilir
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);    //SELECT * FROM book  LEFT JOIN student ON  book.StudentNo =student.No  WHERE book.StudentNo is not null
            stmt = conn.createStatement();

            String DeliverBookToLibraryQuery = "UPDATE book SET StudentNo= NULL , BorrowedDate=NULL  WHERE StudentNo LIKE '" + brg.getTxtStudentNo().getText().trim() + "' and "
                    + "BarcodeNo LIKE '" + brg.getTxtBarcodeNo().getText().trim() + "' ";

            String addFine = "SELECT * FROM book INNER JOIN student ON book.studentNo=student.No where BarcodeNo LIKE'" + brg.getTxtBarcodeNo().getText().trim() + "' and "
                    + "studentNo LIKE '" + brg.getTxtStudentNo().getText().trim() + "'";
            rs = stmt.executeQuery(addFine);
            /*    LocalDate localdate = LocalDate.now();
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy");*/
            if (rs.next()) {
                String dayQuery = "SELECT DATEDIFF ('" + rs.getDate("borrowedDate") + "',NOW())";
                borrowedDate = rs.getDate("borrowedDate");
                debt = rs.getDouble("Debt");
                rs = stmt.executeQuery(dayQuery);
                if (rs.next()) {

                    LocalDate borrowedDateForComparison = borrowedDate.toLocalDate();
                    LocalDate localdate = LocalDate.now();
                    Period diff = Period.between(borrowedDateForComparison, localdate);
                    if (diff.getYears() > 0) {
                        delay += 365 * diff.getYears();
                    }
                    if (diff.getMonths() > 0) {
                        delay += 30 * diff.getMonths();
                    }
                    if (diff.getDays() > 0) {
                        delay += diff.getDays();
                    }
                    if (delay > 30) {
                        delay = delay - 30;
                        Fine = FineFee * delay + debt;
                        FineAdded = true;

                    } else {
                        Fine = debt;
                    }

                }
            }

            if (stmt.executeUpdate(DeliverBookToLibraryQuery) == 1) {
                SuccessVoice();
                if (FineAdded == false) {

                    brg.getTxtResult().setBackground(Color.green);
                    brg.getTxtResult().setText("KİTAP İADE BAŞARILI");
                } else {

                    java.awt.Toolkit.getDefaultToolkit().beep();

                    brg.getTxtResult().setBackground(new Color(22, 160, 133));

                    brg.getTxtResult().setText("KİTAP İADE BAŞARILI  / ANCAK 30 GÜNÜ GEÇİRDİĞİ İÇİN CEZA YAPTIRIMI UYGUNLANMIŞTIR");
                }
                String Query = "UPDATE student Set debt=" + Fine + " WHERE  No LIKE '" + brg.getTxtStudentNo().getText().trim() + "'";
                stmt.executeUpdate(Query);
            } else {
                DeliverBookToLibraryQuery = "SELECT * FROM book WHERE BarcodeNo LIKE '" + brg.getTxtBarcodeNo().getText().trim() + "' and StudentNo  IS NULL ";
                rs = stmt.executeQuery(DeliverBookToLibraryQuery);
                if (rs.next()) {

                    java.awt.Toolkit.getDefaultToolkit().beep();
                    brg.getTxtResult().setBackground(Color.ORANGE);
                    brg.getTxtResult().setText("Kitap Kütüphanemize Zaten İade Edilmiş.");
                } else {

                    java.awt.Toolkit.getDefaultToolkit().beep();
                    brg.getTxtResult().setBackground(Color.RED);
                    brg.getTxtResult().setText("Kitap - Öğrenci Uyuşmamaktadır. İade Alınmamıştır.");
                }
            }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "CLASS NOT FOUND EXCEPTION");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " : 2 SQL HATASI");
        } finally {

            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();

                }
                /*   if (rs != null) {
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

    public void SearcBookList(int search) {

        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";
        //  StudentCanUpdate = true;
        //  Database credentials
        String USER = "root";
        String PASS = "";

        final int searchAuthor = 0;
        final int searchBookName = 1;
        final int searchBarcodeNo = 2;
        final int searchCategory = 3;
        final int searchAll = 5;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean noVoice = false;

        String searchQuery = "";
        switch (search) {
            case searchAuthor:
                searchQuery = "SELECT * FROM book WHERE AuthorName LIKE '%" + bslg.getTxtAuthorName().getText().trim() + "%'";
                break;
            case searchBookName:
                searchQuery = "SELECT * FROM book WHERE Name LIKE '%" + bslg.getTxtBookName().getText().trim() + "%'";
                break;
            case searchBarcodeNo:
                searchQuery = "SELECT * FROM book WHERE BarcodeNo LIKE '%" + bslg.getTxtBarcodeNo().getText().trim() + "%'";
                break;
            case searchCategory:
                searchQuery = "SELECT * FROM book WHERE CategoryName LIKE '%" + bslg.getTxtCategory().getText().trim() + "%'";
                break;
            case searchAll:
                searchQuery = "SELECT * FROM book ";
                noVoice = true;
        }

        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            rs = stmt.executeQuery(searchQuery);
            int totalBookForQuery = 0;
            while (rs.next()) {
                totalBookForQuery++;
            }

            rs = stmt.executeQuery(searchQuery);

            int counter = 0;
            bslg.DataOfTable = new String[totalBookForQuery][6];

            while (rs.next()) {

                bslg.DataOfTable[counter][0] = Integer.toString(counter + 1);
                bslg.DataOfTable[counter][1] = rs.getString("BarcodeNo");
                bslg.DataOfTable[counter][2] = rs.getString("Name");
                try {
                    if (rs.getString("StudentNo").equals(null)) {

                    }
                    bslg.DataOfTable[counter][3] = "Öğrencide";
                    //JOptionPane.showMessageDialog(null, "buraya girdi");

                } catch (NullPointerException e) {
                    bslg.DataOfTable[counter][3] = "Müsayit";
                }

                bslg.DataOfTable[counter][4] = rs.getString("CategoryName");
                bslg.DataOfTable[counter][5] = rs.getString("AuthorName");

                counter++;
            }////"", "Barkod No", "Kitap Adı", "Kitap Durumu", "Kitap Kategori", "Yazar Adı"};

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
            bslg.getJp().remove(bslg.getSp());
            bslg.setSp(new JTable(bslg.DataOfTable, bslg.HeadersOfTable));
            bslg.getJp().add(bslg.getSp());

            if (noVoice == false) {
                SuccessVoice();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActionStudent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ActionStudent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
