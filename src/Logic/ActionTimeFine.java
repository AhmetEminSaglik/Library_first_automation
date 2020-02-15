package Logic;

import Gui.AboutUs;
import Gui.FineDebtPayment;
import Gui.Login;
import Gui.TimeControlExtraTimeGui;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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
import javax.swing.JPasswordField;
import javax.swing.JTable;

public class ActionTimeFine implements ActionListener, FocusListener/*, ListSelectionListener, MouseListener*/ {

    TimeControlExtraTimeGui tcet;
    FineDebtPayment fdp;
    AboutUs au;
    public boolean noVoice = false;
    String PlaceHolderStudent = "Öğrenci No";
    String PlaceHolderBook = "Kitap Barkod No";
    Font FocusFont = new Font("", Font.BOLD, 15);
    Font LostFocusFont = new Font("", Font.ITALIC, 15);
    boolean visibleOfTxt = false;
    boolean firstEnrty = true;
    boolean usernameChanged = false;
    boolean passwordWillChange = false;
    boolean StopUpdate = false;
    final int FAILED = 0;
    final int CANCELED = 1;

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

                } else if (!tcet.getTxtSearchBookBarcodeNo().getText().trim().equals(PlaceHolderBook)) {
                    SearchStudentBarkodNo(2);

                } else {

                    JOptionPane.showMessageDialog(null, "Arama Yapmak İçin Alanlardan Birisini Doldurmanız Gerekmektedir");
                }
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
                    BringStudentWhoHasDebt(1);
                } else {
                    JOptionPane.showMessageDialog(null, "Ödemek için önce öğrencinin bilgilerini çağırmalısınız");
                }

            }

        } else if (au != null) {
            usernameChanged = false;
            String firstEnrtyInfo = "     ---KULLANICI ADI DEĞİŞTİRME ---\nKullanıcı Adınızı,  Parolanızı ve Yeni Kullanıcı Adınızı girmeniz gerekmektedir.\n\n"
                    + "      --- ŞİFRE DEĞİŞTİRME ---\nKullanıcı Adınızı, Şifrenizi ve Yeni Şifre ile Şifre Tekrarını doldurmanız gerekmektedir.\n\n"
                    + "      --- HEM KULLANICI ADI HEM ŞİFRE DEĞİŞTİRME--- \nBütün bilgileri doldurmanız gerekmektedir.";
            if (e.getSource() == au.getBtnComeBack()) {
                au.stopChangeBackground = true;
                au.getJp().setVisible(false);
                au.getMg().getJp().setVisible(true);
                au.getMg().getJf().setTitle("ANA SAYFA");
                clearAllTxtMainGui();

            } else if (e.getSource() == au.getBtnChangePassword()
                    || e.getSource() == au.getTxtOldUsername()
                    || e.getSource() == au.getTxtOldPassword()
                    || e.getSource() == au.getTxtNewUsername()
                    || e.getSource() == au.getTxtNewPassword1()
                    || e.getSource() == au.getTxtNewPassword2()) {

                if (visibleOfTxt == true) {
                    if (au.getTxtOldUsername().getText().trim().equals("")
                            && au.getTxtNewUsername().getText().trim().equals("")
                            && au.getTxtOldPassword().getPassword().length == 0
                            && au.getTxtNewPassword1().getPassword().length == 0
                            && au.getTxtNewPassword2().getPassword().length == 0) {

                        visibleOfTxt = false;
                        setVisible(visibleOfTxt);
                    } else if (!au.getTxtOldUsername().getText().trim().equals("")
                            && au.getTxtNewUsername().getText().trim().equals("")
                            && au.getTxtOldPassword().getPassword().length == 0
                            && au.getTxtNewPassword1().getPassword().length == 0
                            && au.getTxtNewPassword2().getPassword().length == 0) {
                        java.awt.Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null, firstEnrtyInfo);
                    } else if ((!au.getTxtNewUsername().getText().trim().equals("")
                            && (au.getTxtOldPassword().getPassword().length >= 0
                            || au.getTxtNewPassword1().getPassword().length >= 0
                            || au.getTxtNewPassword2().getPassword().length >= 0))) {

                        if (!au.getTxtOldUsername().getText().trim().equals("")
                                && !au.getTxtNewUsername().getText().trim().equals("")
                                && au.getTxtOldPassword().getPassword().length > 0
                                && au.getTxtNewPassword1().getPassword().length > 0
                                && au.getTxtNewPassword2().getPassword().length > 0) {

                            if (ArePasswordsSame(au.getTxtNewPassword1(), au.getTxtNewPassword2()) == true) {
                                if (!au.getTxtOldUsername().getText().equals(au.getTxtNewUsername().getText())) {

                                    passwordWillChange = true;
                                    //if(answer == JOptionPane.YES_OPTION ) int answer = JOptionPane.showConfirmDialog(null, "Şifrenizi Değiştirmek İstediğinize Emin Misiniz ?");
                                    Object[] options = {"Onayla", "İptal"};
                                    String message = "Kullanıcı Adınızı ve Şifrenizi Değiştirmek İstediğinize Emin Misiniz ?";
                                    int answer = JOptionPane.showOptionDialog(null, message, "GÜNCELLEME ONAYI", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "Onayla");

                                    if (answer == JOptionPane.YES_OPTION) {
//   String ControlOldUsernameAndPasswordQuery = UserExit(username, String.valueOf(au.getTxtOldPassword().getPassword()));
//SearchInDatabase(UserExit(au.getTxtOldUsername().getText().trim(), String.valueOf(au.getTxtOldPassword().getPassword())))
                                        if (!SearchInDatabase(UserExit(au.getTxtOldUsername().getText().trim(),
                                                String.valueOf(au.getTxtOldPassword().getPassword())))) {
                                            JOptionPane.showMessageDialog(null, "Kullanıcı Adı ya da Parola Hatalı");
                                            ResetPasswords();
                                            FailedOrCanceledUpdate(FAILED);
                                        } else if (SearchInDatabase(UserExit(au.getTxtNewUsername().getText().trim(),
                                                ""))) {
                                            JOptionPane.showMessageDialog(null, "Bu kullanıcı adı Alınmış lütfen başka bir kullanıcı adı giriniz");
                                        } else {
                                            if (ArePasswordsSame(au.getTxtOldPassword(), au.getTxtNewPassword1()) == false) {
                                                UpdateUser();
                                                UpdatePassword();
                                                // SuccessVoice();
                                            } else {
                                                FailedOrCanceledUpdate(FAILED);
                                                JOptionPane.showMessageDialog(null, "Yeni Parolanız eski Parolanız ile aynı olamaz");
                                                ResetPasswords();
                                            }
                                        }

                                        // Yeni kullanıcı adında  sql hata alıyorum 
                                    } else {
                                        FailedOrCanceledUpdate(CANCELED);
                                        java.awt.Toolkit.getDefaultToolkit().beep();
                                    }
                                } else {
                                    java.awt.Toolkit.getDefaultToolkit().beep();
                                    JOptionPane.showMessageDialog(null, "Aynı kullanıcı adıyla değişiklik yapamazsınız");
                                    ResetPasswords();
                                    au.getTxtResult().setText("Kullanıcı adları aynı");
                                    au.getTxtResult().setBackground(Color.red);
                                }
                            } else {
                                PasswordsAreDifferent();

                            }

                        } else if (!au.getTxtOldUsername().getText().equals("")
                                && !au.getTxtNewUsername().getText().equals("")
                                && au.getTxtOldPassword().getPassword().length > 0
                                && au.getTxtNewPassword1().getPassword().length == 0
                                && au.getTxtNewPassword2().getPassword().length == 0) {
                            UpdateUser();

                            if (StopUpdate == true) {
                                FailedOrCanceledUpdate(FAILED);
                            }
                        } else if (!au.getTxtNewUsername().getText().equals("")
                                && au.getTxtOldPassword().getPassword().length == 0
                                && au.getTxtNewPassword1().getPassword().length == 0
                                && au.getTxtNewPassword2().getPassword().length == 0) {
                            JOptionPane.showMessageDialog(null, "Kullanıcı adınızı değiştirmek için  Kullanıcı Adını ve  Parolanızı doldurmanız gerekmektedir");
                        } else {
                            JOptionPane.showMessageDialog(null, "Hem kullanıcı adınızı hem şifrenizi değiştirmek için lüfen tüm bilgileri doldurunuz ");
                            // önce şifreyi sonra kullanıcı ismini güncellemeliyim
                        }
                    } else if (au.getTxtOldPassword().getPassword().length > 0
                            || au.getTxtNewPassword1().getPassword().length > 0
                            || au.getTxtNewPassword2().getPassword().length > 0) {
                        if (!au.getTxtOldUsername().getText().trim().equals("")
                                && au.getTxtOldPassword().getPassword().length > 0
                                && au.getTxtNewPassword1().getPassword().length > 0
                                && au.getTxtNewPassword2().getPassword().length > 0) {
                            if (ArePasswordsSame(au.getTxtNewPassword1(), au.getTxtNewPassword2()) == true) {
                                if (ArePasswordsSame(au.getTxtOldPassword(), au.getTxtNewPassword1()) == false) {
                                    UpdatePassword();

                                }
                            } else {
                                PasswordsAreDifferent();
                            }

                            // şifreyi değiştirmeliyim
                        } else {
                            JOptionPane.showMessageDialog(null, "Şifrenizi değiştirmek için lütfen \n şifrenizi, yeni şifreniz ve şifre tekrarını eksiksiz doldurun ");
                        }
                    } else if (!au.getTxtNewUsername().getText().trim().equals("")) {
                        if (!au.getTxtOldUsername().getText().trim().equals("") && !au.getTxtNewUsername().getText().trim().equals("")) {
                            JOptionPane.showMessageDialog(null, "Kullanıcı Adınızı Değiştirmek İstediğinize Emin Misiniz ?");
                            // kullanıcı adını değiştirmeliyim
                        } else {
                            JOptionPane.showMessageDialog(null, "Kullanıcı adını değiştirmek için lütfen \n kullanıcı adını ve yeni kullanıcı adı bölümünü dolduurn"
                                    + "");
                        }
                    }
                    /*if (EnteredTxt() == true) {
                        operationsOnTextfield();

                        if (!au.getTxtOldUsername().getText().trim().equals(noVoice)) {

                        } else {
                            java.awt.Toolkit.getDefaultToolkit().beep();
                            JOptionPane.showMessageDialog(null, "Yeni kullanıcı adınız eskii kullanıcı adınızdan farklı olmalı");
                        }// kullanıcı adı eskisi her türlü de
                        /*
                        kullanıcı adı şifre şifre şifre
                        kullanıcı adı yei kullanıcı adı  */ /*  if ((!au.getTxtOldUsername().getText().trim().equals("") && au.getTxtOldUsername().getText().trim().equals(oldUsername))
                                && (!au.getTxtNewUsername().getText().trim().equals("") && au.getTxtNewUsername().getText().trim().equals(newUser))) {
                            if (au.getTxtOldUsername().getText().trim().equals(au.getTxtNewUsername().getText().trim())) {
                                JOptionPane.showMessageDialog(null, "Eski ve yeni kullanıcı adınız farklı olmalıdır");
                            } else {
                                UpdateUser();
                            }
                        }
                        if (au.getTxtOldPassword().getText().trim().equals(au.getTxtNewUsername().getText().trim())) {
                            java.awt.Toolkit.getDefaultToolkit().beep();
                            JOptionPane.showMessageDialog(null, "Eski ve yeni kullanıcı adınız farklı olmalıdır");

                        }

                        if (au.getTxtOldPassword().getText().trim().equals(au.getTxtNewPassword1().getText().trim())) {
                            if (!au.getTxtNewPassword1().getText().trim().equals(au.getTxtNewPassword2().getText().trim())) {
                                JOptionPane.showMessageDialog(null, "Yeni şifreler birbiriyle uyumsuz");
                            }
                            java.awt.Toolkit.getDefaultToolkit().beep();
                            JOptionPane.showMessageDialog(null, "Yeni şifreniz eski şifreniz ile aynı ");
                        }
                   
                    } else {
                        visibleOfTxt = false;
                        au.getTxtResult().setForeground(Color.GRAY);
                        au.getTxtResult().setBackground(new Color(238, 238, 238));
                        setVisible(visibleOfTxt);
                    }*/

                } else {

                    visibleOfTxt = true;
                    setVisible(visibleOfTxt);
                    if (firstEnrty == true) {
                        JOptionPane.showMessageDialog(null, firstEnrtyInfo);
                    }
                    firstEnrty = false;
                }

            }

        }
    }

    public void ResetPasswords() {
        au.getTxtOldPassword().setText("");
        au.getTxtNewPassword1().setText("");
        au.getTxtNewPassword2().setText("");
    }

    public void PasswordsAreDifferent() {
        java.awt.Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(null, "Yeni Parolanız ile Parola tekrarı uyuşmamaktadır");
        ResetPasswords();
        au.getTxtResult().setText("Yeni Parolalar Farklı");
        au.getTxtResult().setBackground(Color.red);
    }

    public boolean ArePasswordsSame(JPasswordField password1, JPasswordField password2) {
        if (String.valueOf(password1.getPassword()).equals(String.valueOf(password2.getPassword()))) {
            return true;
        }

        return false;
    }

    public boolean SearchInDatabase(String Query) {
        SqlConnection sqlConnection = new SqlConnection();
        sqlConnection.setResultSet(Query);
        try {
            if (sqlConnection.getResultSet().next()) {

                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ActionTimeFine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void FailedOrCanceledUpdate(int choose) {
        java.awt.Toolkit.getDefaultToolkit().beep();
        switch (choose) {
            case FAILED:

                au.getTxtResult().setText("İşlem Başarısız");
                au.getTxtResult().setBackground(Color.red);

                break;
            case CANCELED:
                au.getTxtResult().setText("İşlem iptal edildi");
                au.getTxtResult().setBackground(Color.ORANGE);

                break;
        }

    }

    public void GoToLoginAfterChangeUsernameOrPassword() {
        au.getJf().dispose();
        Login login = new Login();
    }

    public void UpdatePassword() {

        if (StopUpdate != true) {
            SqlConnection sqlConnection = new SqlConnection();
            String username = "";
            if (usernameChanged == true) {
                username = au.getTxtNewUsername().getText().trim();

            } else {
                username = au.getTxtOldUsername().getText().trim();
            }

            String ControlOldUsernameAndPasswordQuery = UserExit(username, String.valueOf(au.getTxtOldPassword().getPassword()));//"SELECT * FROM  admin WHERE Username LIKE '" + username + "' and Password LIKE '" + String.valueOf(au.getTxtOldPassword().getPassword()) + "' ";

            if (SearchInDatabase(ControlOldUsernameAndPasswordQuery) == true) {

                String UpdatePasswordQuery = "UPDATE  admin SET  Password ='" + String.valueOf(au.getTxtNewPassword1().getPassword()) + "' "
                        + "WHERE Username  LIKE '" + username + "' "
                        + "AND Password LIKE '" + String.valueOf(au.getTxtOldPassword().getPassword()) + "' ";

                if (sqlConnection.Update(UpdatePasswordQuery) == 1) {
                    if (usernameChanged == true) {
                        SuccessVoice();
                        au.getTxtResult().setText("İşlem Başarılı");
                        au.getTxtResult().setBackground(Color.CYAN);
                        JOptionPane.showMessageDialog(null, "Hem Kullanıcı Adınız hem de Parolanız değiştirildi. \n\nPROGRAM YENİDEN BAŞLATILACAKk");
                    } else {
                        SuccessVoice();
                        au.getTxtResult().setText("Parola Değiştirildi");

                        au.getTxtResult().setBackground(Color.GREEN);
                        JOptionPane.showMessageDialog(null, "Parolanız değiştirildi. \n\nPROGRAM YENİDEN BAŞLATILACAKk");
                    }
                    GoToLoginAfterChangeUsernameOrPassword();
                }
                /*else {
                    au.getTxtResult().setText("İşlem iptal edildi");
                    au.getTxtResult().setBackground(Color.ORANGE);
                    ResetPasswords();
                }*/

            } else {
                JOptionPane.showMessageDialog(null, "Kullanıcı adınız ya da parolanız yanlış");
                ResetPasswords();
            }
        }

    }
// kullanıcı adı şifre doğru mu 

    public String UserExit(String User, String Password) {
        String oldUserExit = "";

        if (Password.equals("")) {
            oldUserExit = "SELECT * FROM admin WHERE Username LIKE '" + User + "' ";

        } else {
            oldUserExit = "SELECT * FROM admin WHERE Username LIKE '" + User + "' AND "
                    + "Password LIKE '" + Password + "' ";

        }

        return oldUserExit;
    }

    public void UpdateUser() {
        SqlConnection sqlConnection = new SqlConnection();

        String UpdateUser = "UPDATE  admin SET Username='" + au.getTxtNewUsername().getText().trim()
                + "'  WHERE Username LIKE '" + au.getTxtOldUsername().getText().trim() + "'";
        /*  String oldUserExit = "SELECT * FROM admin WHERE Username LIKE '" + au.getTxtOldUsername().getText().trim() + "' AND "
                + "Password LIKE '" + String.valueOf(au.getTxtOldPassword().getPassword()) + "' ";
        String newUserExit = "SELECT * FROM admin WHERE Username LIKE '" + au.getTxtNewUsername().getText().trim() + "'";*/
        if (SearchInDatabase(UserExit(au.getTxtOldUsername().getText().trim(), String.valueOf(au.getTxtOldPassword().getPassword())))) {
            if (SearchInDatabase(UserExit(au.getTxtNewUsername().getText().trim(), ""))) {

                FailedOrCanceledUpdate(FAILED);
                JOptionPane.showMessageDialog(null, "Yeni kullanıcı adı zaten kayıtlı  Lütfen başka bir kullanıcı adı giriniz");

                StopUpdate = true;
            } else {
                sqlConnection.Update(UpdateUser);

                au.getTxtResult().setText("Kullanıcı adı Değişti");
                au.getTxtResult().setBackground(Color.GREEN);
                au.getTxtResult().setForeground(Color.BLACK);
//                JOptionPane.showMessageDialog(null, "Kullanıcı adı değişti.");
                usernameChanged = true;
                if (passwordWillChange == false) {
                    SuccessVoice();
                    JOptionPane.showMessageDialog(null, "Kullanıcı adı değişti. \n\nPROGRAM YENİDEN BAŞLATILACAK");
                    GoToLoginAfterChangeUsernameOrPassword();
                }

            }
        } else {
            java.awt.Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Kullanıcı ya da parola hatalı");
            ResetPasswords();
            FailedOrCanceledUpdate(FAILED);
        }
    }

    /*public void operationsOnTextfield() {
        if ((ControlUser() == Color.RED && ControlPassword() == Color.GREEN)
                || (ControlUser() == Color.GREEN && ControlPassword() == Color.RED)) {
            //  paintBtnChangePassword(Color.ORANGE);
        } else if (ControlUser() == Color.RED || ControlPassword() == Color.RED) {
            //     paintBtnChangePassword(Color.RED);
        } else if (ControlUser() == Color.GREEN || ControlPassword() == Color.GREEN) {
            //  paintBtnChangePassword(Color.GREEN);
        } else {
            //   paintBtnChangePassword(new Color(238, 238, 238));
        }

    }*/
//    public void paintBtnChangePassword(Color color) { au.getBtnChangePassword().setBackground(color);}
    public Color ControlUser() {
        int totalWritedTxt = 0;
        if (!au.getTxtOldUsername().getText().equals("")) {
            totalWritedTxt++;
        }
        if (!au.getTxtNewUsername().getText().equals("")) {
            totalWritedTxt++;
        }
        if (totalWritedTxt == 1) {
            return Color.RED;

        } else if (totalWritedTxt == 2) {
            return Color.GREEN;
        }
        return new Color(238, 238, 238);
    }

    public Color ControlPassword() {
        int totalWritedTxt = 0;
        if (!au.getTxtOldPassword().getPassword().equals("")) {
            totalWritedTxt++;
        }
        if (!au.getTxtNewPassword1().getPassword().equals("")) {
            totalWritedTxt++;
        }
        if (!au.getTxtNewPassword2().getPassword().equals("")) {
            totalWritedTxt++;
        }
        if (totalWritedTxt == 3) {
            return Color.GREEN;
        } else if (totalWritedTxt == 1 || totalWritedTxt == 2) {
            return Color.RED;

        }
        return new Color(238, 238, 238);
    }

    /*public boolean EnteredTxt() {

        if (!au.getTxtOldUsername().getText().equals( )
                || !au.getTxtNewUsername().getText().equals( )
                || !au.getTxtOldPassword().getText().equals( )
                || !au.getTxtNewPassword1().getText().equals( )
                || !au.getTxtNewPassword2().getText().equals( )) {

            return true;
        }

        return false;
    }*/
    public void setVisible(boolean visible) {
        au.getTxtOldUsername().setVisible(visible);
        au.getTxtNewUsername().setVisible(visible);
        au.getTxtResult().setVisible(visible);
        au.getTxtOldPassword().setVisible(visible);
        au.getTxtNewPassword1().setVisible(visible);
        au.getTxtNewPassword2().setVisible(visible);
        au.getLblOldUsername().setVisible(visible);
        au.getLblNewUsername().setVisible(visible);
        au.getLblResult().setVisible(visible);
        au.getLblOldPassword().setVisible(visible);
        au.getLblNewPassword1().setVisible(visible);
        au.getLblNewPassword2().setVisible(visible);
    }

    public boolean ColorControl() {
        if (au.getTxtOldUsername().getForeground() != Color.GRAY
                || au.getTxtNewUsername().getForeground() != Color.GRAY
                || au.getTxtOldPassword().getForeground() != Color.GRAY
                || au.getTxtNewPassword1().getForeground() != Color.GRAY
                || au.getTxtNewPassword2().getForeground() != Color.GRAY) {
            return true;
        }
        return false;

    }

    public void ExtendTime() {
        SqlConnection sqlconnection = new SqlConnection();
        /* String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";

        //  Database credentials
        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;*/
        String ExtendTimeQuery = "UPDATE book SET BorrowedDate= NOW() WHERE BarcodeNo LIKE '" + tcet.getTxtBookBarcodeNoToExtendTime().getText().trim() + "'";
        //JOptionPane.showMessageDialog(null, tcet.getTxtBookBarcodeNoToExtendTime().getText().trim());
        try {

            ////Class.forName(JDBC_DRIVER);
            //conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // stmt = conn.createStatement();
            //SuccessVoice();
            // stmt.executeUpdate(ExtendTimeQuery);
            sqlconnection.Update(ExtendTimeQuery);
            tcet.getTxtResult().setText(" Kitap Süresi Uzatıldı");
            tcet.getTxtResult().setBackground(new Color(29, 209, 161));

            SearchStudentBarkodNo(3);

        }/* catch (ClassNotFoundException ex) {
            Logger.getLogger(ActionTimeFine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ActionTimeFine.class.getName()).log(Level.SEVERE, null, ex);
        }*/ finally {
            //  closeConnections(conn, stmt, rs, null);
            sqlconnection.CloseAllConnections();
        }

    }

    public int TimeOfBook(String barcodeNo) {
        SqlConnection sqlconnection = new SqlConnection();
        /*  String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";

        //  Database credentials
        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;*/
        int delay = 0;
        try {
            //   Class.forName(JDBC_DRIVER);
            //conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //stmt = conn.createStatement();
            String TimeQuery = "SELECT * FROM book  WHERE barcodeNo LIKE '" + barcodeNo + "' ";
            // rs = stmt.executeQuery(TimeQuery);
            sqlconnection.setResultSet(TimeQuery);
            if (sqlconnection.getResultSet().next()) {
                LocalDate borrowedDate = sqlconnection.getResultSet().getDate("BorrowedDate").toLocalDate();
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
        } /*catch (ClassNotFoundException ex) {
            Logger.getLogger(ActionTimeFine.class.getName()).log(Level.SEVERE, null, ex);
        }*/ catch (SQLException ex) {
            Logger.getLogger(ActionTimeFine.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            // closeConnections(conn, stmt, rs, null);
            sqlconnection.CloseAllConnections();
        }
        return delay;

    }

    public void SearchStudentBarkodNo(int SearchNumber) {
        /*  String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";

        //  Database credentials
        String USER = "root";
        String PASS = "";
         */
        SqlConnection sqlConnection = new SqlConnection();
        /* Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
         */
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
            //Class.forName(JDBC_DRIVER);
            // conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // stmt = conn.createStatement();
            String StudentQuery = "";
            int counter = 0;
            sqlConnection.setResultSet(SearchQuery);
            //   rs = stmt.executeQuery(SearchQuery);
            while (sqlConnection.getResultSet().next()) {
                counter++;
            }

            tcet.DataForTable = new String[counter][7];
            counter = 0;
            sqlConnection.setResultSet(SearchQuery);
            while (sqlConnection.getResultSet().next()) {
                //{"", "Öğrenci No", "Öğrenci Adı Soyadı", "Kitap Barkod No", "Kalan gün sayısı ", "Kitap adı"};
                tcet.DataForTable[counter][0] = Integer.toString(counter + 1);
                tcet.DataForTable[counter][1] = sqlConnection.getResultSet().getString("student.No");
                tcet.DataForTable[counter][2] = sqlConnection.getResultSet().getString("student.Name") + sqlConnection.getResultSet().getString("student.Surname");
                tcet.DataForTable[counter][3] = sqlConnection.getResultSet().getString("book.BarcodeNo");
                tcet.DataForTable[counter][4] = Integer.toString(TimeOfBook(sqlConnection.getResultSet().getString("book.BarcodeNo")));
                tcet.DataForTable[counter][5] = sqlConnection.getResultSet().getString("book.Name");
                // public rs2 yapacam ve kapatacam sonrada 

                counter++;
            }

            if (counter == 1 && sqlConnection.getResultSet().last()) {

                tcet.getTxtBookBarcodeNoToExtendTime().setText(sqlConnection.getResultSet().getString("book.BarcodeNo"));
                tcet.getTxtBookNameToExtendTime().setText(sqlConnection.getResultSet().getString("book.Name"));
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
        } /*catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);

        } */ catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            //closeConnections(conn, stmt, rs, null);
            sqlConnection.CloseAllConnections();
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
        SqlConnection sqlConnection = new SqlConnection();
        /*  String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/library";
        String USER = "root";
        String PASS = "";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;*/
        try {

            // Class.forName(JDBC_DRIVER);
            //  conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // stmt = conn.createStatement();
            String BringDebt = "SELECT * FROM Student WHERE No LIKE '" + fdp.getTxtStudentNo().getText().trim() + "'";
            sqlConnection.setResultSet(BringDebt);
// rs = stmt.executeQuery(BringDebt);
            double MoneyFromStudent = Double.parseDouble(fdp.getTxtAmountOfPayment().getText().trim());
            String UpdateDebt = "";
            Double LastDebt = 0.0;
            if (sqlConnection.getResultSet().next() || (sqlConnection.getResultSet().getDouble("Debt") > 0)) {
                UpdateDebt = "UPDATE Student SET Debt = " + (sqlConnection.getResultSet().getDouble("Debt") - MoneyFromStudent) + " WHERE No like'" + fdp.getTxtStudentNo().getText().trim() + "' ";
                LastDebt = (sqlConnection.getResultSet().getDouble("Debt") - MoneyFromStudent);
            } else {

                UpdateDebt = "UPDATE Student SET Debt = " + (sqlConnection.getResultSet().getDouble("Debt") + MoneyFromStudent) + " ";
                LastDebt = (sqlConnection.getResultSet().getDouble("Debt") - MoneyFromStudent);
            }

            //stmt.executeUpdate(UpdateDebt);
            sqlConnection.Update(UpdateDebt);

            BringDebt = "SELECT * FROM Student WHERE No LIKE '" + fdp.getTxtStudentNo().getText().trim() + "'";
            //rs = stmt.executeQuery(BringDebt);
            sqlConnection.setResultSet(BringDebt);
            if (sqlConnection.getResultSet().next()) {
                fdp.getTxtDebt().setText(sqlConnection.getResultSet().getString("Debt"));
                fillDebtAndResult(LastDebt);
            }
            SuccessVoice();

        } /*catch (ClassNotFoundException ex) {
            Logger.getLogger(ActionTimeFine.class
                    .getName()).log(Level.SEVERE, null, ex);
        } */ catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " hatası ");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Lütfen Sadece Sayı İçeren Değerler Giriniz", "DEĞER HATASI", JOptionPane.ERROR_MESSAGE);
        } finally {
            //closeConnections(conn, stmt, rs, null);
            sqlConnection.CloseAllConnections();
        }

    }

    public void BringStudentWhoHasDebt(int determiner) {
        ResetAllTxt();
        SqlConnection sqlConnection = new SqlConnection();
        /* String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/library";

        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;*/
        String bringStudent = "";
        boolean noVoice = false;
        try {
            if (determiner == 0) {
                bringStudent = "SELECT * FROM student  WHERE Debt != 0 ORDER BY Debt DESC";
                noVoice = true;
            } else {
                bringStudent = "SELECT * FROM student  WHERE Debt != 0 and No LIKE '%" + fdp.getTxtStudentNo().getText().trim() + "%' ORDER BY Debt DESC";
            }

            /*  Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();*/
            //rs = stmt.executeQuery(bringStudent);
            sqlConnection.setResultSet(bringStudent);
            int StudentNumberWhoHasDebt = 0;
            while (sqlConnection.getResultSet().next()) {
                StudentNumberWhoHasDebt++;
            }

            //rs = stmt.executeQuery(bringStudent);
            sqlConnection.setResultSet(bringStudent);
            int counter = 0;
            fdp.DataOfTable = new String[StudentNumberWhoHasDebt][6];
            while (sqlConnection.getResultSet().next()) {
                fdp.DataOfTable[counter][0] = Integer.toString(counter + 1);
                fdp.DataOfTable[counter][1] = sqlConnection.getResultSet().getString("No");
                fdp.DataOfTable[counter][2] = sqlConnection.getResultSet().getString("Name") + " " + sqlConnection.getResultSet().getString("Surname");
                fdp.DataOfTable[counter][3] = sqlConnection.getResultSet().getString("Email");
                fdp.DataOfTable[counter][4] = sqlConnection.getResultSet().getString("Phone");
                fdp.DataOfTable[counter][5] = sqlConnection.getResultSet().getString("Debt");
                //"", "Öğrenci No", "Ad-Soyad", "Email", "Telefon Numarası", "Borç (TL)
                counter++;
                if (StudentNumberWhoHasDebt == 1) {
                    fdp.getTxtDebt().setText(sqlConnection.getResultSet().getString("Debt"));
                    fdp.getTxtStudentNo().setText(sqlConnection.getResultSet().getString("No"));
                    fdp.getTxtResult().setText("Bilgiler getirildi");
                    fdp.getTxtResult().setBackground(Color.GREEN);
                    if (sqlConnection.getResultSet().getDouble("Debt") > 0) {
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
        }/* catch (ClassNotFoundException ex) {
            Logger.getLogger(ActionTimeFine.class
                    .getName()).log(Level.SEVERE, null, ex);

        } */ catch (SQLException ex) {
            Logger.getLogger(ActionTimeFine.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            //closeConnections(conn, stmt, rs, null);
            sqlConnection.CloseAllConnections();
        }
    }

    public void SuccessVoice() {
        try {
            //   AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Gui/tik.wav"));
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("tik.wav"));
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
                    if (Double.parseDouble(fdp.getTxtDebt().getText()) < 0.0) {
                        fdp.getTxtAmountOfPayment().setText("-");
                    } else {
                        fdp.getTxtAmountOfPayment().setText("");
                    }
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
        /*else if (au != null) {
            operationsOnTextfield();
//üstüne gelince boş bırakacak eğer doldurmadan geçerse kişi otomatak doldurma yapacak
            if (e.getSource() == au.getTxtOldUsername()) {
                au.getTxtOldUsername().setText("");
                au.getTxtOldUsername().setForeground(Color.BLACK);
            } else if (e.getSource() == au.getTxtNewUsername()) {
                au.getTxtNewUsername().setText("");
                au.getTxtNewUsername().setForeground(Color.BLACK);
            } else if (e.getSource() == au.getTxtOldPassword()) {
                au.getTxtOldPassword().setText("");
                au.getTxtOldPassword().setForeground(Color.BLACK);
            } else if (e.getSource() == au.getTxtNewPassword1()) {
                au.getTxtNewPassword1().setText("");
                au.getTxtNewPassword1().setForeground(Color.BLACK);
            } else if (e.getSource() == au.getTxtNewPassword2()) {
                au.getTxtNewPassword2().setText("");
                au.getTxtNewPassword2().setForeground(Color.BLACK);
            } else {

            }
        }*/
    }

    @Override
    public void focusLost(FocusEvent e) {
        /* if (au != null) {
//üstüne gelince boş bırakacak eğer doldurmadan geçerse kişi otomatak doldurma yapacak
            if (e.getSource() == au.getTxtOldUsername() && au.getTxtOldUsername().getText().trim().equals("")) {
                au.getTxtOldUsername().setText(oldUsername);
                au.getTxtOldUsername().setForeground(Color.GRAY);
            } else if (e.getSource() == au.getTxtNewUsername() && au.getTxtNewUsername().getText().trim().equals("")) {
                au.getTxtNewUsername().setText(newUser);
                au.getTxtNewUsername().setForeground(Color.GRAY);
            } else if (e.getSource() == au.getTxtOldPassword() && au.getTxtOldPassword().getText().trim().equals("")) {
                au.getTxtOldPassword().setText(oldPassword);
                au.getTxtOldPassword().setForeground(Color.GRAY);
            } else if (e.getSource() == au.getTxtNewPassword1() && au.getTxtNewPassword1().getText().trim().equals("")) {
                au.getTxtNewPassword1().setText(newPassword);
                au.getTxtNewPassword1().setForeground(Color.GRAY);
            } else if (e.getSource() == au.getTxtNewPassword2() && au.getTxtNewPassword2().getText().trim().equals("")) {
                au.getTxtNewPassword2().setText(newPassword);
                au.getTxtNewPassword2().setForeground(Color.GRAY);
            }

        }*/
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
 /* public void closeConnections(Connection conn, Statement stmt, ResultSet rs, PreparedStatement preparedStmt) {

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
    }*/
 /*@Override
    public void valueChanged(ListSelectionEvent e) {

        for (int i = 0; i < tcet.model.getRowCount() - 1; i++) {

            for (int j = 0; j < tcet.model.getColumnCount() - 1; j++) {
                tcet.model.getValueAt(i, j);
            }

        }

    }*/
}
