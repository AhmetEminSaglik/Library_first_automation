package Logic;

import Gui.AboutUs;
import Gui.BookAddGui;
import Gui.BookReturnGui;
import Gui.BookSearchListGui;
import Gui.BookUpdateRemoveGui;
import Gui.FineDebtPayment;
import Gui.MainGui;
import Gui.RegisteredStudentGui;
import Gui.StudentAddGui;
import Gui.StudentStateGui;
import Gui.StudentUpdateGui;
import Gui.TimeControlExtraTimeGui;
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
import javax.swing.event.DocumentEvent;

public class ActionsMainGui implements ActionListener, MouseListener, FocusListener {

    MainGui mg = null;

    boolean studentExist = true;
    boolean bookExist = true;
    boolean StudentCanTakeBook = true;
    boolean BookDeliveredSomeone = false;
    boolean bookFree = true;
    String StudentPlaceHolder = "Öğrenci No Girin";
    String BarcodeNoPlaceHolder = "Kitap Barkod No girin";

    public ActionsMainGui(MainGui mg) {
        this.mg = mg;
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

    /*boolean studentExist = true;
        boolean bookExist = true;
        boolean StudentCanTakeBook = true;
        boolean BookDeliveredSomeone = false;*/
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getMg().gettxtStudentNo() || e.getSource() == getMg().getTxtBookBarcode()) {
            if (getMg().gettxtStudentNo().getText().trim().equals("") || getMg().gettxtStudentNo().getText().equals(StudentPlaceHolder)) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                //     BookCanBeTake();
                JOptionPane.showMessageDialog(null, "Öğrenci numarası boş bırakılamaz", "EKSİK BİLGİ", JOptionPane.ERROR_MESSAGE);
            } else if (getMg().getTxtBookBarcode().getText().trim().equals("")
                    || getMg().getTxtBookBarcode().getText().equals(BarcodeNoPlaceHolder)) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                //   StudentCanTakeBook();
                JOptionPane.showMessageDialog(null, "Kitap Barkod Numarası boş bırakılamaz", "EKSİK BİLGİ", JOptionPane.ERROR_MESSAGE);
            } else {

                DeliverBookToStudent();
            }

        }
        if (e.getSource() == getMg().getTxtBookBarcode()) {

        }
        if (e.getSource() == getMg().getBookAdd()) {
            getMg().getJp().setVisible(false);
            BookAddGui bag = new BookAddGui(getMg());

        }
        if (e.getSource() == getMg().getBookReturn()) {
            getMg().getJp().setVisible(false);
            BookReturnGui brg = new BookReturnGui(getMg());
        }
        if (e.getSource() == getMg().getBookSearchList()) {
            mg.getJp().setVisible(false);
            BookSearchListGui bslg = new BookSearchListGui(getMg());
        }
        if (e.getSource() == getMg().getBookUpdateRemove()) {
            getMg().getJp().setVisible(false);
            BookUpdateRemoveGui burg = new BookUpdateRemoveGui(getMg());
        }
        if (e.getSource() == getMg().getStudentAdd()) {
            StudentAddGui sag = new StudentAddGui(getMg());
        }
        if (e.getSource() == getMg().getStudentUpdate()) {
            getMg().getJp().setVisible(false);
            StudentUpdateGui sug = new StudentUpdateGui(getMg());

        }
        if (e.getSource() == getMg().getStudentState()) {

            StudentStateGui ssg = new StudentStateGui(getMg());

        }
        if (e.getSource() == getMg().getRegisteredStudent()) {
            getMg().getJp().setVisible(false);
            RegisteredStudentGui rsg = new RegisteredStudentGui(getMg());
        }
        if (e.getSource() == getMg().getTimeControlExtraTime()) {
            getMg().getJp().setVisible(false);
            TimeControlExtraTimeGui tc_etg = new TimeControlExtraTimeGui(getMg());

        }
        if (e.getSource() == getMg().getAboutUs()) {
            getMg().getJp().setVisible(false);
            AboutUs aug = new AboutUs(getMg());
        }
        if (e.getSource() == getMg().getFineDebtPayment()) {
            getMg().getJp().setVisible(false);
            FineDebtPayment fdp = new FineDebtPayment(getMg());
        }
        if (e.getSource() == getMg().getExit()) {

            int answer = JOptionPane.showConfirmDialog(null, "Çıkmak İstediğinize Emin Misiniz?", "ÇIKIŞ UYARISI", 2, 3);

            if (answer == 0) {
                System.exit(answer);
            }
        }

    }

    //@Override
    public void insertUpdate(DocumentEvent e) {     // Buraya eklenir hızlı işlem yapma 2 txt ten ikisi de dolduğu anda işlem yapılır

        if (e.getDocument().getProperty("StudentNoBookBarcodetxt").equals(1)
                || e.getDocument().getProperty("StudentNoBookBarcodetxt").equals(2)) {

            try {

                Integer.parseInt(getMg().gettxtStudentNo().getText());

            } catch (NumberFormatException nfe) {

                //  JOptionPane.showMessageDialog(null, "Karakter giremezsiniz. Sayı girmelisiniz");
                int txtLength = getMg().gettxtStudentNo().getText().length();

                int i = getMg().gettxtStudentNo().getText().length();
                while (i > 0) {
                    i--;
                    try {

                        if (getMg().gettxtStudentNo().getText().charAt(i) <= 9 || getMg().gettxtStudentNo().getText().charAt(i) >= 0) {
//sayi değeri 0-9 arası değil ise ki catch e girecek o zaman o char'ı silip yazdıracam ekrana 
                        }
                    } catch (NumberFormatException nfe2) {
                        String StudentText = "";
                        for (int j = 0; j < i; j++) {
                            StudentText = "" + getMg().gettxtStudentNo().getText().charAt(j);

                        }
                        getMg().gettxtStudentNo().setText(StudentText);
                    }
                }//Integer.valueOf(getMg().gettxtStudentNo().getText().replaceAll("[^\\d.]", ""));
                //getMg().gettxtStudentNo().setText(getMg().gettxtStudentNo().getText().charAt()[length() - 1]);
                //getMg().gettxtStudentNo().getText()[getMg().gettxtStudentNo().getText().length() - 1];
                {

                }
            }
        }
        if (e.getDocument().getProperty("StudentNoBookBarcodetxt").equals(1)) {// first for student no

        }

        if (e.getDocument().getProperty("StudentNoBookBarcodetxt").equals(2)) { // second for bookBarcode

        }

    }

    //  @Override
    public void removeUpdate(DocumentEvent e) {
        if (e.getDocument().getProperty("StudentNoBookBarcodetxt").equals(1)) { // second for bookBarcode

        }
        if (e.getDocument().getProperty("StudentNoBookBarcodetxt").equals(2)) { // second for bookBarcode

        }
        if (e.getDocument().getProperty("StudentNoBookBarcodetxt").equals(1)
                || e.getDocument().getProperty("StudentNoBookBarcodetxt").equals(2)) {
            boolean allInteger = false;

            try {

                Integer.parseInt(getMg().gettxtStudentNo().getText());

            } catch (NumberFormatException nfe) {
                //JOptionPane.showMessageDialog(null, "Karakter giremezsiniz. Sayı girmelisiniz (aciton 163 satır şuana kadar çalışmadı)");

            }

        }

    }

    // @Override
    public void changedUpdate(DocumentEvent e) {

    }

    public void findButtonsSourceWithUsingMouse(MouseEvent e, Color background_color, Color foreground_color) { // to clear make which button you on 
        if (e.getSource() == getMg().getBookAdd()) {
            getMg().getBookAdd().setBackground(background_color);
            getMg().getBookAdd().setForeground(foreground_color);
        }
        if (e.getSource() == getMg().getBookReturn()) {
            getMg().getBookReturn().setBackground(background_color);
            getMg().getBookReturn().setForeground(foreground_color);
        }
        if (e.getSource() == getMg().getBookSearchList()) {
            getMg().getBookSearchList().setBackground(background_color);
            getMg().getBookSearchList().setForeground(foreground_color);
        }
        if (e.getSource() == getMg().getBookUpdateRemove()) {
            getMg().getBookUpdateRemove().setBackground(background_color);
            getMg().getBookUpdateRemove().setForeground(foreground_color);
        }
        if (e.getSource() == getMg().getStudentAdd()) {
            getMg().getStudentAdd().setBackground(background_color);
            getMg().getStudentAdd().setForeground(foreground_color);
        }
        if (e.getSource() == getMg().getStudentUpdate()) {
            getMg().getStudentUpdate().setBackground(background_color);
            getMg().getStudentUpdate().setForeground(foreground_color);
        }
        if (e.getSource() == getMg().getStudentState()) {
            getMg().getStudentState().setBackground(background_color);
            getMg().getStudentState().setForeground(foreground_color);
        }
        if (e.getSource() == getMg().getRegisteredStudent()) {
            getMg().getRegisteredStudent().setBackground(background_color);
            getMg().getRegisteredStudent().setForeground(foreground_color);
        }
        if (e.getSource() == getMg().getTimeControlExtraTime()) {
            getMg().getTimeControlExtraTime().setBackground(background_color);
            getMg().getTimeControlExtraTime().setForeground(foreground_color);
        }

        if (e.getSource() == getMg().getAboutUs()) {
            getMg().getAboutUs().setBackground(background_color);
            getMg().getAboutUs().setForeground(foreground_color);
        }
        if (e.getSource() == getMg().getExit()) {
            getMg().getExit().setBackground(background_color);
            getMg().getExit().setForeground(foreground_color);
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
        findButtonsSourceWithUsingMouse(e, Color.GRAY, Color.WHITE);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        findButtonsSourceWithUsingMouse(e, Color.WHITE, Color.BLACK);// this make buttons as before
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == getMg().gettxtStudentNo()) {
            if (getMg().gettxtStudentNo().getText().trim().equals(StudentPlaceHolder)) {
                getMg().gettxtStudentNo().setText("");
                getMg().gettxtStudentNo().setForeground(Color.BLACK);
            }
        } else if (e.getSource() == getMg().getTxtBookBarcode()) {
            if (getMg().getTxtBookBarcode().getText().trim().equals(BarcodeNoPlaceHolder)) {
                getMg().getTxtBookBarcode().setText("");
                getMg().getTxtBookBarcode().setForeground(Color.BLACK);

            }

        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == getMg().gettxtStudentNo()) {
            if (getMg().gettxtStudentNo().getText().trim().equals("")) {
                getMg().gettxtStudentNo().setForeground(Color.GRAY);
                getMg().gettxtStudentNo().setText("Öğrenci No Girin");
            }
        } else if (e.getSource() == getMg().getTxtBookBarcode()) {
            if (getMg().getTxtBookBarcode().getText().trim().equals("")) {
                getMg().getTxtBookBarcode().setForeground(Color.GRAY);
                getMg().getTxtBookBarcode().setText("Kitap Barkod No girin");

            }
        }
    }

    public void StudentCanTakeBook() {
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

            String StudentExistQuery = "SELECT * FROM student WHERE NO LIKE '" + getMg().gettxtStudentNo().getText().trim() + "'";
            ResultSet rs = stmt.executeQuery(StudentExistQuery);

            if (!rs.next()) {

                StudentCanTakeBook = false;
                throw new Exception();
            } else {

            }
            /*
            getMg().gettxtResultScreen().setBackground(Color.CYAN);
            getMg().gettxtResultScreen().setText("Öğrenci Kayıtlı");*/
            String CanStudentTakeBookQuery = "SELECT * FROM book  RIGHT JOIN student ON  book.StudentNo =student.No  WHERE book.StudentNo LIKE'"
                    + getMg().gettxtStudentNo().getText() + "'";

            rs = stmt.executeQuery(CanStudentTakeBookQuery);
            int TookBookCounter = 0;
            while (rs.next()) {
                TookBookCounter++;
            }
            if (TookBookCounter == 3) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Öğrencide Zaten 3 tane kitap var ", "KİTAP VERME HATASI", JOptionPane.ERROR_MESSAGE);
                StudentCanTakeBook = false;
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "CLASS NOT FOUND");
        } catch (SQLException ex) {
            //  JOptionPane.showMessageDialog(null, ex, "SQL HATASI", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            if (StudentCanTakeBook == false) {
                java.awt.Toolkit.getDefaultToolkit().beep();

                getMg().gettxtResultScreen().setBackground(Color.red);
                getMg().gettxtResultScreen().setText("ÖĞRENCİ BULUNAMADI");
            }
        }
    }

    public void BookCanBeTake() {
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

            String BookExistQuery = "Select * FROM book WHERE BarcodeNo LIKE '" + getMg().getTxtBookBarcode().getText().trim() + "'";
            stmt = conn.createStatement();

            /*String StudentExistQuery = "SELECT * FROM student WHERE NO LIKE '" + getMg().gettxtStudentNo().getText().trim() + "'";
            ResultSet rs = stmt.executeQuery(StudentExistQuery);*/
            ResultSet rs = stmt.executeQuery(BookExistQuery);

            if (!rs.next()) //    ResultSet rs = stmt.executeQuery(CanStudentTakeBookQuery);
            {

                bookExist = false;

                throw new Exception();
            }
            /*else {
                if (getMg().gettxtResultScreen().getText().equals("Kitap Bulunamadı")
                        && getMg().gettxtResultScreen().getBackground() == Color.red) {
                  getMg().gettxtResultScreen().setText("Kitap bulundu");
                getMg().gettxtResultScreen().setBackground(new Color(206, 214, 224));  }
            
            }*/
 /*
            getMg().gettxtResultScreen().setBackground(Color.CYAN);
            getMg().gettxtResultScreen().setText("Kitap Kayıtlı");*/

            String BookCanBeDelivered = "SELECT * FROM book  LEFT JOIN student ON  book.StudentNo =student.No  "
                    + "WHERE  book.BarcodeNo LIKE  '" + getMg().getTxtBookBarcode().getText() + "' and book.StudentNo is null";
            rs = stmt.executeQuery(BookCanBeDelivered);
            if (!rs.next()) {

                BookDeliveredSomeone = true;
                throw new Exception();
            }

        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        } catch (Exception ex) {
            bookFree = false;
            if (bookExist == false) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Kitap Barkod Numarası Kayıtlı değil",
                        "KİTAP BARKOD NUMARASI HATASI", JOptionPane.ERROR_MESSAGE);
                getMg().gettxtResultScreen().setText("Kitap Bulunamadı");
                getMg().gettxtResultScreen().setBackground(Color.red);
            } else if (BookDeliveredSomeone == true) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Kitap başkasına verilmiş",
                        "ÖĞRECİDE BULUNAN KİTAP EŞLEŞTİRME HATASI", JOptionPane.ERROR_MESSAGE);
                getMg().gettxtResultScreen().setText("Kitap Önceden Alınmış");
                getMg().gettxtResultScreen().setBackground(Color.ORANGE);
            }
        }

    }

    public void DeliverBookToStudent() {

        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";

        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        StudentCanTakeBook = true;
        BookDeliveredSomeone = false;
        bookFree = true;
        boolean studentExist = true;
        bookExist = true;

        try {
            StudentCanTakeBook();
            if (StudentCanTakeBook == false) {

                return;
            }
            BookCanBeTake();
            if (bookFree == false) {

                return;
            }

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String SqlDeliverBookStudent = "Update  book set StudentNo = '" + getMg().gettxtStudentNo().getText()
                    + "' \n where  BarcodeNo LIKE '" + getMg().getTxtBookBarcode().getText() + "'";
            stmt.executeUpdate(SqlDeliverBookStudent);

            getMg().gettxtResultScreen().setBackground(Color.GREEN);
            getMg().gettxtResultScreen().setText("EŞLEŞME BAŞARILI");
            SuccessVoice();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "SQL HATASI", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void SuccessVoice() {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Gui/tik.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();

        } catch (UnsupportedAudioFileException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (LineUnavailableException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void DeliverBookToLibrary() {
    }
}
