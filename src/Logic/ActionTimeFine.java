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
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ActionTimeFine implements ActionListener, FocusListener {

    TimeControlExtraTimeGui tcet;
    FineDebtPayment fdp;
    AboutUs au;
    boolean noVoice = false;

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
                JOptionPane.showMessageDialog(null, "süre uzatma sql'e bağlanacak");
            } else if (e.getSource() == tcet.getTxtBookBarcodeNoToExtendTime()) {
                JOptionPane.showMessageDialog(null, tcet.getTxtBookBarcodeNoToExtendTime().getText() + " barkod nolu  kitap bilgileri buraya getirilecek");
            } else if (e.getSource() == tcet.getBtnSearch()
                    || e.getSource() == tcet.getTxtSearchBookBarcodeNo()
                    || e.getSource() == tcet.getTxtSearchStudentNo()) {

                JOptionPane.showMessageDialog(null, "arama sql'e bağlanacak");
                /*    getTxtSearchStudentNo().addActionListener(action);
        getTxtSearchBookBarcodeNo().addActionListener(action);*/
            }
        } else if (fdp != null) {

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

        } else if (au != null) {
            if (e.getSource() == au.getBtnComeBack()) {
                au.stopChangeBackground = true;
                au.getJp().setVisible(false);
                au.getMg().getJp().setVisible(true);
                au.getMg().getJf().setTitle("ANA SAYFA");
                clearAllTxtMainGui();

            }
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
            Logger.getLogger(ActionTimeFine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " hatası ");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Lütfen Sadece Sayı İçeren Değerler Giriniz", "DEĞER HATASI", JOptionPane.ERROR_MESSAGE);
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
            Logger.getLogger(ActionTimeFine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ActionTimeFine.class.getName()).log(Level.SEVERE, null, ex);
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
        }
    }

    @Override
    public void focusLost(FocusEvent e) {

    }

}
