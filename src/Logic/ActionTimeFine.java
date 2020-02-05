package Logic;

import Gui.AboutUs;
import Gui.FineDebtPayment;
import Gui.TimeControlExtraTimeGui;
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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ActionTimeFine implements ActionListener, FocusListener/*, ListSelectionListener, MouseListener*/ {

    TimeControlExtraTimeGui tcet;
    FineDebtPayment fdp;
    AboutUs au;
    public boolean noVoice = false;
    String PlaceHolderStudent = "Öğrenci No";
    String PlaceHolderBook = "Kitap Barkod No";
    Font FocusFont = new Font("", Font.BOLD, 15);
    Font LostFocusFont = new Font("", Font.ITALIC, 15);

    public ActionTimeFine(TimeControlExtraTimeGui tcet) {
        this.tcet = tcet;
    }

    public ActionTimeFine(AboutUs au) {
        this.au = au;
    }

    public ActionTimeFine(FineDebtPayment fdp) {
        this.fdp = fdp;
    }

    public void clearAllTxtMainGui() {

        if (tcet != null) {
            tcet.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            tcet.getMg().gettxtStudentNo().setText("Öğrenci No Girin");
            tcet.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            tcet.getMg().getTxtBookBarcode().setText("Kitap Barkod No girin");
            tcet.getMg().getTxtBookName().setText("");
            tcet.getMg().gettxtResultScreen().setText("");
            tcet.getMg().gettxtResultScreen().setBackground(new Color(206, 214, 224));

        } else if (fdp != null) {
            fdp.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            fdp.getMg().gettxtStudentNo().setText("Öğrenci No Girin");
            fdp.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            fdp.getMg().getTxtBookBarcode().setText("Kitap Barkod No girin");
            fdp.getMg().getTxtBookName().setText("");
            fdp.getMg().gettxtResultScreen().setText("");
            fdp.getMg().gettxtResultScreen().setBackground(new Color(206, 214, 224));

        } else if (au != null) {
            au.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            au.getMg().gettxtStudentNo().setText("Öğrenci No Girin");
            au.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            au.getMg().getTxtBookBarcode().setText("Kitap Barkod No girin");
            au.getMg().getTxtBookName().setText("");
            au.getMg().gettxtResultScreen().setText("");
            au.getMg().gettxtResultScreen().setBackground(new Color(206, 214, 224));

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (tcet != null) {

            if (e.getSource() == tcet.getBtnComeBack()) {
                tcet.setVisible(false);
                tcet.getMg().getJf().setTitle("ANA SAYFA");
                tcet.getMg().getJp().setVisible(true);
                clearAllTxtMainGui();
            } else if (e.getSource() == tcet.getBtnExtendTime()) {
                if (!tcet.getTxtBookBarcodeNoToExtendTime().getText().trim().equals("")) {
                    ExtendTime();
                } else {
                    tcet.getTxtResult().setText("Kitap seçmelisiniz");
                    tcet.getTxtResult().setBackground(new Color(231, 76, 60));
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Kitap seçilmeden süre uzatılamaz", "SÜRE UZATMA HATASI", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == tcet.getTxtBookBarcodeNoToExtendTime()) {
                JOptionPane.showMessageDialog(null, tcet.getTxtBookBarcodeNoToExtendTime().getText() + " barkod nolu  kitap bilgileri buraya getirilecek");
            } else if (e.getSource() == tcet.getTxtSearchStudentNo()) {
                noVoice = false;
                SearchStudentBarkodNo(1);
            } else if (e.getSource() == tcet.getTxtSearchBookBarcodeNo()) {
                noVoice = false;
                SearchStudentBarkodNo(2);
            } else if (e.getSource() == tcet.getBtnSearch()) {
                noVoice = false;
                if (!tcet.getTxtSearchStudentNo().getText().trim().equals(PlaceHolderStudent)) {
                    SearchStudentBarkodNo(1);
                } else if (!tcet.getTxtBookBarcodeNoToExtendTime().getText().trim().equals(PlaceHolderBook)) {
                    SearchStudentBarkodNo(2);
                } else {

                    JOptionPane.showMessageDialog(null, "arama yapmak için alanlardan birisini doldurmanız gerekmektedir");
                }
            }
            /*if (e.getSource() == tcet.getBtnSearch()
                    || e.getSource() == tcet.getTxtSearchBookBarcodeNo()
                    || e.getSource() == tcet.getTxtSearchStudentNo()) {

                JOptionPane.showMessageDialog(null, "arama sql'e bağlanacak");
                /*    getTxtSearchStudentNo().addActionListener(action);
        getTxtSearchBookBarcodeNo().addActionListener(action);
        }  */
        } else if (fdp
                != null) {

            if (e.getSource() == fdp.getBtnComeBack()) {
                fdp.getJp().setVisible(false);
                fdp.getMg().getJp().setVisible(true);
                fdp.getMg().getJf().setTitle("ANA SAYFA");
                clearAllTxtMainGui();
            } else if (e.getSource() == fdp.getTxtStudentNo()) {

                BringStudentWhoHasDebt(1);
            } else if (e.getSource() == fdp.getTxtAmountOfPayment() || e.getSource() == fdp.getBtnPay()) {
                if (!fdp.getTxtStudentNo().getText().trim().equals("") && !fdp.getTxtDebt().getText().equals("")) {
                    payDebt();
                } else {
                    JOptionPane.showMessageDialog(null, "Ödemek için önce öğrencinin bilgilerini çağırmalısınız");
                }

            }

        } else if (au
                != null) {
            if (e.getSource() == au.getBtnComeBack()) {
                au.stopChangeBackground = true;
                au.getJp().setVisible(false);
                au.getMg().getJp().setVisible(true);
                au.getMg().getJf().setTitle("ANA SAYFA");
                clearAllTxtMainGui();

            }
        }
    }

    public void ExtendTime() {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";

        //  Database credentials
        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String ExtendTimeQuery = "UPDATE book SET BorrowedDate= NOW() WHERE BarcodeNo LIKE '" + tcet.getTxtBookBarcodeNoToExtendTime().getText().trim() + "'";
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            //SuccessVoice();
            tcet.getTxtResult().setText(" Kitap Süresi Uzatıldı");
            tcet.getTxtResult().setBackground(new Color(29, 209, 161));

            SearchStudentBarkodNo(3);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActionTimeFine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ActionTimeFine.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(conn, stmt, rs, null);
        }

    }

    public int TimeOfBook(String barcodeNo) {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";

        //  Database credentials
        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int delay = 0;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String TimeQuery = "SELECT * FROM book  WHERE barcodeNo LIKE '" + barcodeNo + "' ";
            rs = stmt.executeQuery(TimeQuery);
            if (rs.next()) {
                LocalDate borrowedDate = rs.getDate("BorrowedDate").toLocalDate();
                LocalDate localdate = LocalDate.now();
                Period diff = Period.between(borrowedDate, localdate);

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
                } else {
                    delay -= 30;
                    delay++;
                    delay = -delay;
                }

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActionTimeFine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ActionTimeFine.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(conn, stmt, rs, null);
        }
        return delay;

    }

    public void SearchStudentBarkodNo(int SearchNumber) {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";

        //  Database credentials
        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        final int Student = 1;
        final int BarcodeNo = 2;
        final int bringAll = 0;
        final int ExtendBookBarcode = 3;
        boolean dontUpdateResult = false;
        String SearchQuery = "";

        switch (SearchNumber) {

            case bringAll:
                SearchQuery = "SELECT * FROM book LEFT JOIN student ON book.StudentNo=student.No  WHERE book.studentNo IS NOT NULL";

                break;
            case Student:
                SearchQuery = "SELECT * FROM book LEFT JOIN student ON book.StudentNo=student.No  "
                        + "WHERE book.studentNo IS NOT NULL AND book.studentNo LIKE "
                        + "'%" + tcet.getTxtSearchStudentNo().getText().trim() + "%' ";
                break;
            case BarcodeNo:

                if (!tcet.getTxtSearchBookBarcodeNo().getText().trim().equals("")) {
                    SearchQuery = "SELECT * FROM book LEFT JOIN student ON book.StudentNo=student.No  "
                            + "WHERE book.studentNo IS NOT NULL AND book.BarcodeNo LIKE "
                            + "'" + tcet.getTxtSearchBookBarcodeNo().getText().trim() + "' ";
                } else {
                    SearchQuery = "SELECT * FROM book LEFT JOIN student ON book.StudentNo=student.No  "
                            + "WHERE book.studentNo IS NOT NULL AND book.BarcodeNo LIKE "
                            + "'%" + tcet.getTxtSearchBookBarcodeNo().getText().trim() + "%' ";
                }
                break;

            case ExtendBookBarcode:

                SearchQuery = "SELECT * FROM book LEFT JOIN student ON book.StudentNo=student.No  "
                        + "WHERE book.BarcodeNo LIKE '"
                        + tcet.getTxtBookBarcodeNoToExtendTime().getText().trim() + "' ";
                dontUpdateResult = true;
                break;

        }
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String StudentQuery = "";
            int counter = 0;
            rs = stmt.executeQuery(SearchQuery);
            while (rs.next()) {
                counter++;
            }

            tcet.DataForTable = new String[counter][7];
            counter = 0;
            rs = stmt.executeQuery(SearchQuery);
            while (rs.next()) {
                tcet.DataForTable[counter][0] = Integer.toString(counter);
                tcet.DataForTable[counter][1] = rs.getString("student.No");
                tcet.DataForTable[counter][2] = rs.getString("student.Name") + rs.getString("student.Surname");
                tcet.DataForTable[counter][3] = rs.getString("book.BarcodeNo");
                tcet.DataForTable[counter][4] = rs.getString("book.Name");
                tcet.DataForTable[counter][5] = Integer.toString(TimeOfBook(rs.getString("book.BarcodeNo"))); // public rs2 yapacam ve kapatacam sonrada 

                counter++;
            }

            if (counter == 1 && rs.last()) {

                tcet.getTxtBookBarcodeNoToExtendTime().setText(rs.getString("book.BarcodeNo"));
                tcet.getTxtBookNameToExtendTime().setText(rs.getString("book.Name"));
            }

            //tcet.getSp();
            tcet.remove(tcet.getSp());
            tcet.setSp(new JTable(tcet.DataForTable, tcet.HeaderOfTable));

            tcet.add(tcet.getSp());

            if (noVoice == false) {
                if (counter > 0) {
                    SuccessVoice();
                    if (dontUpdateResult == false) {
                        tcet.getTxtResult().setText("Bilgiler Getirildi");
                        tcet.getTxtResult().setBackground(Color.GREEN);
                    }
                } else {
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    tcet.getTxtResult().setText("Aradığınız Veriler Bulunamadı");
                    tcet.getTxtResult().setBackground(Color.RED);
                    tcet.getTxtBookBarcodeNoToExtendTime().setText("");
                    tcet.getTxtBookNameToExtendTime().setText("");
                }
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            closeConnections(conn, stmt, rs, null);
        }

    }

    public void ResetAllTxt() {
        fdp.getTxtDebt().setText("");
        fdp.getTxtDebt().setBackground(new Color(206, 214, 224));
        fdp.getTxtResult().setText("");
        fdp.getTxtResult().setBackground(new Color(206, 214, 224));
    }

    public void fillDebtAndResult(Double Debt) {
        if (Debt > 0) {
            fdp.getTxtResult().setText(Debt + " TL borcunuz kalmıştır");
            fdp.getTxtResult().setBackground(new Color(255, 118, 117));
            fdp.getTxtDebt().setBackground(Color.red);

            fdp.getTxtDebt().setBackground(Color.orange);
            /*    if (rs.getDouble("Debt") > 0) {
                        fdp.getTxtDebt().setBackground(Color.orange);
                    } else {
                        fdp.getTxtDebt().setBackground(new Color(116, 185, 255));
                        fdp.getTxtAmountOfPayment().setText("-");
                        /*  JOptionPane.showMessageDialog(null, "Kütüphaneden Öğrenciye para verilmesi gerektiği için öğrenciye verilecek para miktarını,\n "
                                + "'' Ödeme Miktarı '' kısmına yazıp ÖDE '  ye tıklayınız.");
                    }*/

        } else if (Debt < 0) {

            Debt = -Debt;

            fdp.getTxtResult().setText(Debt + " TL alacaksınız");
            fdp.getTxtResult().setBackground(new Color(162, 155, 254));
            fdp.getTxtDebt().setBackground(new Color(116, 185, 255));
        } else {
            fdp.getTxtResult().setText("Borcunuz Bitmiştir");
            fdp.getTxtResult().setBackground(new Color(85, 239, 196));
            fdp.getTxtDebt().setText("0.0");
            fdp.getTxtDebt().setBackground(new Color(206, 214, 224));
        }
    }

    public void payDebt() {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/library";
        String USER = "root";
        String PASS = "";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String BringDebt = "SELECT * FROM Student WHERE No LIKE '" + fdp.getTxtStudentNo().getText().trim() + "'";
            rs = stmt.executeQuery(BringDebt);
            double MoneyFromStudent = Double.parseDouble(fdp.getTxtAmountOfPayment().getText().trim());
            String UpdateDebt = "";
            Double LastDebt = 0.0;
            if (rs.next() || (rs.getDouble("Debt") > 0)) {
                UpdateDebt = "UPDATE Student SET Debt = " + (rs.getDouble("Debt") - MoneyFromStudent) + " WHERE No like'" + fdp.getTxtStudentNo().getText().trim() + "' ";
                LastDebt = (rs.getDouble("Debt") - MoneyFromStudent);
            } else {

                UpdateDebt = "UPDATE Student SET Debt = " + (rs.getDouble("Debt") + MoneyFromStudent) + " ";
                LastDebt = (rs.getDouble("Debt") - MoneyFromStudent);
            }

            stmt.executeUpdate(UpdateDebt);

            BringDebt = "SELECT * FROM Student WHERE No LIKE '" + fdp.getTxtStudentNo().getText().trim() + "'";
            rs = stmt.executeQuery(BringDebt);
            if (rs.next()) {
                fdp.getTxtDebt().setText(rs.getString("Debt"));
                fillDebtAndResult(LastDebt);
            }
            SuccessVoice();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActionTimeFine.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " hatası ");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Lütfen Sadece Sayı İçeren Değerler Giriniz", "DEĞER HATASI", JOptionPane.ERROR_MESSAGE);
        } finally {
            closeConnections(conn, stmt, rs, null);
        }

    }

    public void BringStudentWhoHasDebt(int determiner) {
        ResetAllTxt();
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/library";

        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String bringStudent = "";
        boolean noVoice = false;
        try {
            if (determiner == 0) {
                bringStudent = "SELECT * FROM student  WHERE Debt != 0 ORDER BY Debt DESC";
                noVoice = true;
            } else {
                bringStudent = "SELECT * FROM student  WHERE Debt != 0 and No LIKE '%" + fdp.getTxtStudentNo().getText().trim() + "%' ORDER BY Debt DESC";
            }

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            rs = stmt.executeQuery(bringStudent);
            int StudentNumberWhoHasDebt = 0;
            while (rs.next()) {
                StudentNumberWhoHasDebt++;
            }

            rs = stmt.executeQuery(bringStudent);
            int counter = 0;
            fdp.DataOfTable = new String[StudentNumberWhoHasDebt][6];
            while (rs.next()) {
                fdp.DataOfTable[counter][0] = Integer.toString(counter + 1);
                fdp.DataOfTable[counter][1] = rs.getString("No");
                fdp.DataOfTable[counter][2] = rs.getString("Name") + " " + rs.getString("Surname");
                fdp.DataOfTable[counter][3] = rs.getString("Email");
                fdp.DataOfTable[counter][4] = rs.getString("Phone");
                fdp.DataOfTable[counter][5] = rs.getString("Debt");
                //"", "Öğrenci No", "Ad-Soyad", "Email", "Telefon Numarası", "Borç (TL)
                counter++;
                if (StudentNumberWhoHasDebt == 1) {
                    fdp.getTxtDebt().setText(rs.getString("Debt"));
                    fdp.getTxtStudentNo().setText(rs.getString("No"));
                    fdp.getTxtResult().setText("Bilgiler getirildi");
                    fdp.getTxtResult().setBackground(Color.GREEN);
                    if (rs.getDouble("Debt") > 0) {
                        fdp.getTxtDebt().setBackground(Color.orange);
                    } else {
                        fdp.getTxtDebt().setBackground(new Color(116, 185, 255));
                        fdp.getTxtAmountOfPayment().setText("-");
                        /*  JOptionPane.showMessageDialog(null, "Kütüphaneden Öğrenciye para verilmesi gerektiği için öğrenciye verilecek para miktarını,\n "
                                + "'' Ödeme Miktarı '' kısmına yazıp ÖDE '  ye tıklayınız.");*/
                    }
                }
            }
            if (StudentNumberWhoHasDebt == 0) {
                fdp.getTxtResult().setText("Öğrenci Bulunamadı");
                fdp.getTxtResult().setBackground(Color.red);
            }
            fdp.getJp().remove(fdp.getSp());
            fdp.setSp(new JTable(fdp.DataOfTable, fdp.HeaderOfTable));
            fdp.getJp().add(fdp.getSp());
            if (noVoice == false) {
                SuccessVoice();

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActionTimeFine.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(ActionTimeFine.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(conn, stmt, rs, null);
        }
    }

    public void SuccessVoice() {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Gui/tik.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();

        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(ActionsBook.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(ActionsBook.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (LineUnavailableException ex) {
            Logger.getLogger(ActionsBook.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void focusGained(FocusEvent e) {
        if (fdp != null) {

            if (e.getSource() == fdp.getTxtStudentNo()) {

                if (fdp.getTxtStudentNo().getText().trim().equals("Öğrenci No")) {
                    fdp.getTxtStudentNo().setText("");
                }
                fdp.getTxtStudentNo().setForeground(Color.BLACK);
                fdp.getTxtStudentNo().setFont(new Font("", Font.BOLD, 15));
                fdp.getTxtAmountOfPayment().setForeground(Color.gray);
                fdp.getTxtAmountOfPayment().setFont(new Font("", Font.ITALIC, 14));
                fdp.getTxtAmountOfPayment().setText("Ödeme Miktarı");

            } else if (e.getSource() == fdp.getTxtAmountOfPayment()) {
                if (fdp.getTxtAmountOfPayment().getText().trim().equals("Ödeme Miktarı")) {
                    fdp.getTxtAmountOfPayment().setText("");
                }
                fdp.getTxtAmountOfPayment().setForeground(Color.BLACK);
                fdp.getTxtAmountOfPayment().setFont(new Font("", Font.BOLD, 15));

            }
        } else if (tcet != null) {

            // System.out.println(String.valueOf(tcet.getTbl().getValueAt(tcet.getTbl().getSelectedRow(), tcet.getTbl().getSelectedColumn())));
            if (e.getSource() == tcet.getTxtSearchStudentNo() && tcet.getTxtSearchStudentNo().getText().equals(PlaceHolderStudent)) {

                //if (tcet.getTxtSearchStudentNo().getText().equals(PlaceHolderStudent)) {
                tcet.getTxtSearchStudentNo().setText("");
                tcet.getTxtSearchStudentNo().setForeground(Color.BLACK);
                tcet.getTxtSearchStudentNo().setFont(FocusFont);

                tcet.getTxtSearchBookBarcodeNo().setText(PlaceHolderBook);
                tcet.getTxtSearchBookBarcodeNo().setFont(LostFocusFont);
                tcet.getTxtSearchBookBarcodeNo().setForeground(Color.gray);

                /* tcet.getTxtBookBarcodeNoToExtendTime().setText(PlaceHolderBook);
                tcet.getTxtBookBarcodeNoToExtendTime().setFont(LostFocusFont);
                tcet.getTxtBookBarcodeNoToExtendTime().setForeground(Color.gray);*/
                // }
            } else if (e.getSource() == tcet.getTxtSearchBookBarcodeNo() && tcet.getTxtSearchBookBarcodeNo().getText().equals(PlaceHolderBook)) {

                tcet.getTxtSearchBookBarcodeNo().setText("");
                tcet.getTxtSearchBookBarcodeNo().setFont(FocusFont);
                tcet.getTxtSearchBookBarcodeNo().setForeground(Color.BLACK);

                tcet.getTxtSearchStudentNo().setText(PlaceHolderStudent);
                tcet.getTxtSearchStudentNo().setForeground(Color.gray);
                tcet.getTxtSearchStudentNo().setFont(LostFocusFont);

                /*   tcet.getTxtBookBarcodeNoToExtendTime().setText(PlaceHolderBook);
                tcet.getTxtBookBarcodeNoToExtendTime().setFont(LostFocusFont);
                tcet.getTxtBookBarcodeNoToExtendTime().setForeground(Color.gray);*/
            }

            /*else if (e.getSource() == tcet.getTxtBookBarcodeNoToExtendTime() && tcet.getTxtBookBarcodeNoToExtendTime().getText().equals(PlaceHolderBook)) {
                System.out.println("book  uzatma");
                tcet.getTxtBookBarcodeNoToExtendTime().setText("");
                tcet.getTxtBookBarcodeNoToExtendTime().setFont(FocusFont);
                tcet.getTxtBookBarcodeNoToExtendTime().setForeground(Color.BLACK);

                tcet.getTxtSearchBookBarcodeNo().setText(PlaceHolderBook);
                tcet.getTxtSearchBookBarcodeNo().setFont(LostFocusFont);
                tcet.getTxtSearchBookBarcodeNo().setForeground(Color.gray);

                tcet.getTxtSearchStudentNo().setText(PlaceHolderStudent);
                tcet.getTxtSearchStudentNo().setForeground(Color.gray);
                tcet.getTxtSearchStudentNo().setFont(LostFocusFont);

            }*/
            //            System.out.println(String.valueOf(tcet.getTbl().getValueAt(tcet.getTbl().getSelectedRow(), tcet.getTbl().getSelectedColumn())));
        }
    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    /* @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("asda");
        String selectedData = null;
        int[] selectedRow = tcet.getTbl().getSelectedRows();
        int[] selectedColumns = tcet.getTbl().getSelectedColumns();

        for (int i = 0; i < selectedRow.length; i++) {
            for (int j = 0; j < selectedColumns.length; j++) {
                selectedData = (String) tcet.getTbl().getValueAt(selectedRow[i], selectedColumns[j]);
                System.out.println("selectedColumns" + selectedColumns);
                System.out.println("selectedData" + selectedData);

            }
        }
        System.out.println(selectedData);
    }*/

 /*@Override
    public void valueChanged(ListSelectionEvent e) {

        int selectedRow = 0;
        if (!tcet.model.isSelectionEmpty()) {
            selectedRow = tcet.model.getMinSelectionIndex();
            JOptionPane.showMessageDialog(null, "selectedraw" + selectedRow);
        }

        System.out.println(selectedRow);

    }
     */
 /*   @Override
    public void valueChanged(ListSelectionEvent e) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int index = tcet.getTbl().getSelectedColumn();
        if (tcet != null) {
            for (int i = 0; i < tcet.HeaderOfTable.length; i++) {
                if (tcet.getTbl().getSelectedColumn() == i) {
                    System.out.println("A index " + index);
                } else {
                    System.out.println("B index " + index);
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    public void closeConnections(Connection conn, Statement stmt, ResultSet rs, PreparedStatement preparedStmt) {

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
            JOptionPane.showMessageDialog(null, "Sql bağlantısı kapatılırken hata meydana geldi");
        }
    }
}
