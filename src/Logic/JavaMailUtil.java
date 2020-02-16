package Logic;

import static Logic.JavaMailUtil.sqlConnection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class JavaMailUtil {

    final static int LAST7DAYS = 0;
    final static int LAST3DAYS = 1;
    final static int STARTEDFINE = 2;
    static int last7DayCounter = 0;
    static int last3DayCounter = 0;
    static int over30DayCounter = 0;
    static SqlConnection sqlConnection = new SqlConnection();
    static int CounterOfMail = 0;
    static boolean saveConditionOnMysql = true;
    static Message message;

    static final String usernameEmail = "EmailExample@gmail.com";
    static final String passwordEmail = "Password_From___GoogleAccount_InSecurity_For_Application_Passwords";

    public void FindStudentAndMailThem(int Degree, boolean MessageWillSend) {

        String Query = "";

        if (Degree == 0) {

            Query = "SELECT * FROM book LEFT JOIN student ON book.StudentNo=student.No "
                    + "WHERE NOW() >  BorrowedDate + INTERVAL  23 DAY  AND book.Condition IS NULL";
        } else if (Degree == 1) {

            Query = "SELECT * FROM book LEFT JOIN student ON book.StudentNo=student.No"
                    + " WHERE NOW() >  BorrowedDate + INTERVAL  27 DAY  AND book.Condition < "
                    + LAST3DAYS/* + " or 'Condition' IS NULL )"*/;
        } else if (Degree == 2) {

            Query = "SELECT * FROM book LEFT JOIN student ON book.StudentNo=student.No"
                    + " WHERE NOW() >  BorrowedDate + INTERVAL  30 DAY  AND book.Condition < "
                    + STARTEDFINE /*+ " or book.Condition IS  NULL )"*/;
        } else {
            if (CounterOfMail > 0) {
                if (MessageWillSend == true) {

                    JOptionPane.showMessageDialog(null, CounterOfMail + " Tane Mail atılmıştır\n"
                            + last7DayCounter + " tanesi son 7 güne girenler için\n"
                            + last3DayCounter + " tanesi son 3 güne girenler için\n"
                            + over30DayCounter + " tanesi 30 günü aşanlar için \n\n"
                            + "Artık programı kapatabilirsiniz");

                } else {

                    JOptionPane.showMessageDialog(null, "Toplamda " + CounterOfMail + " Tane Mail Atılacaktır\n"
                            + "Lütfen atılan maillerin ayrıntısını  görmeden programı kapatmayınız");

                    CounterOfMail = 0;
                    last7DayCounter = 0;
                    last3DayCounter = 0;
                    over30DayCounter = 0;

                    FindStudentAndMailThem(0, true);

                }
                sqlConnection.CloseAllConnections();
            }
            return;
        }

        sqlConnection.setResultSet(Query);
        try {

            while (sqlConnection.getResultSet().next()) {
                if (Degree == 0) {
                    last7DayCounter++;

                }
                if (Degree == 1) {
                    last3DayCounter++;

                }
                if (Degree == 2) {
                    over30DayCounter++;

                }

                if (MessageWillSend == true) {
                    saveConditionOnMysql = false;

                    sendEmail(sqlConnection.getResultSet().getString("Student.Email"),
                            Degree, sqlConnection.getResultSet().getString("book.BarcodeNo"));

                    if (saveConditionOnMysql == true) {

                        String updateQuery = "UPDATE book set book.Condition = " + Degree
                                + " WHERE  BarcodeNo LIKE '" + sqlConnection.getResultSet().getString("BarcodeNo") + "'";

                        sqlConnection.Update(updateQuery);

                    }
                } else {
                    CounterOfMail++;
                }

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "HATA : \n\n\n" + ex);
        }
        sqlConnection.CloseAllConnections();

        FindStudentAndMailThem(++Degree, MessageWillSend);
    }

    public static void sendEmail(String recepient, Double Debt) {
        saveConditionOnMysql = true;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(usernameEmail, passwordEmail);
            }

        });
        prepareMessage(session, usernameEmail, recepient, Debt);

        try {

            Transport.send(message);

        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(null, ex + "", "BEKLENMEYEN HATA", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void prepareMessage(Session session, String username, String recepient, Double Debt) {
        try {
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Of Teknoloji Fakültesi Kitap İade Geciktirme Cezası");

            message.setText("az önce iade ettiğiniz kitabı geciktirdiğiniz için  kütüphaneye " + Debt + " Tl "
                    + "borcunuz vardır. Lütfen Birdakine geciktirmemeye özen gösteriniz. İyi günler dileriz");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex + "", "BEKLENMEYEN HATA", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void sendEmail(String recepient, int condition, String BarcodeNo) {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(usernameEmail, passwordEmail);
            }
        });

        Message message = PrepareMessage(session, usernameEmail, recepient, condition, BarcodeNo);

        saveConditionOnMysql = true;

        try {
            Transport.send(message);
            CounterOfMail++;
            saveConditionOnMysql = true;

        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(null, "HATA : \n\n\n" + ex);

        }
    }

    public static Message PrepareMessage(Session session, String username, String recepient, int condition, String BookBarcodeNo) {
        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            String query = "SELECT * FROM student RIGHT JOIN book ON book.StudentNo= student.No  WHERE book.BarcodeNo LIKE'" + BookBarcodeNo + "'";
            SqlConnection sqlConnection2 = new SqlConnection();
            sqlConnection2.setResultSet(query);
            sqlConnection2.getResultSet();

            if (sqlConnection2.getResultSet().next()) {

            }

            switch (condition) {
                case LAST7DAYS:

                    message.setSubject("Of Teknoloji Fakültesi Kütüphanesi SON 7 GÜN Uyarısı ( BİLDİRİM )");
                    message.setText(sqlConnection2.getResultSet().getString("book.CategoryName") + " kategorisindeki, "
                            + sqlConnection2.getResultSet().getString("book.AuthorName") + " isimli yazara ait olan, "
                            + sqlConnection2.getResultSet().getString("book.BarcodeNo") + " Barkod Numaralı, "
                            + sqlConnection2.getResultSet().getString("book.Name") + "  adlı kitabınızın iadesi için son 7 gün kalmıştır\n\n "
                            + lastDayOfBook(sqlConnection2.getResultSet().getString("book.BarcodeNo")) + " TARİHİNDE EN GEÇ KÜTÜPHANEYE TESLİM EDİLMESİ GEREKMEKTEDİR " + " Bilginize...");

                    break;
                case LAST3DAYS:

                    message.setSubject("Of Teknoloji Fakültesi Kütüphanesi SON 3 GÜN Uyarısı ( KRİTİK )");
                    message.setText(sqlConnection2.getResultSet().getString("book.CategoryName") + " kategorisindeki, "
                            + sqlConnection2.getResultSet().getString("book.AuthorName") + " isimli yazara ait olan, "
                            + sqlConnection2.getResultSet().getString("book.BarcodeNo") + " Barkod Numaralı, "
                            + sqlConnection2.getResultSet().getString("book.Name") + "  adlı kitabınızın iadesi için son 3 gün kalmıştır.\n"
                            + lastDayOfBook(sqlConnection2.getResultSet().getString("book.BarcodeNo")) + "TARİHİNDE EN GEÇ KÜTÜPHANEYE TESLİM EDİLMESİ GEREKMETEDİR "
                            + " Lütfen para cezası aşamasına geçilmeden önce kitabınızın süresini uzattırın ya da iade edin. \nTeşekkür eder iyi günler dileriz");
                    break;
                case STARTEDFINE:

                    message.setSubject("Of Teknoloji Fakültesi Kütüphanesi CEZA BAŞLANGIÇ ( CEZA )");
                    message.setText(sqlConnection2.getResultSet().getString("book.CategoryName") + " kategorisindeki, "
                            + sqlConnection2.getResultSet().getString("book.AuthorName") + " isimli yazara ait olan, "
                            + sqlConnection2.getResultSet().getString("book.BarcodeNo") + " Barkod Numaralı, "
                            + sqlConnection2.getResultSet().getString("book.Name") + "   adlı kitabınızın 1 ay süresini geciktirdiğiniz için hakkınızda cezai işlemi başlatılmıştır. "
                            + "Geç getirdiğiniz gün başına 0.5 TL para cezası uygulanmaktadır. En kısa sürede kitabı getirmeniz dileğiyle iyi günler dileriz"
                    );
                    break;

            }
            sqlConnection.CloseAllConnections();
            sqlConnection2.CloseAllConnections();
            return message;

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, ex + "\n\nEmail Gönderilirken Hata Oluştu", "EMAİL GÖNDERME HATASI", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static LocalDate lastDayOfBook(String BarcodeNo) {
        SqlConnection sqlConection3 = new SqlConnection();
        String lasDateQuery = "SELECT * FROM book WHERE BarcodeNo LIKE '" + BarcodeNo + "' ";
        sqlConection3.setResultSet(lasDateQuery);
        LocalDate borrowedDateOfBook = LocalDate.now();
        try {

            if (sqlConection3.getResultSet().next()) {
                Date LastDate = sqlConection3.getResultSet().getDate("BorrowedDate");

                borrowedDateOfBook = LastDate.toLocalDate();

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Kİtap İade için Son Gün Belirlenirken Hata Meydana Geldi");
        }

        return borrowedDateOfBook.plusDays(29);
    }
}
