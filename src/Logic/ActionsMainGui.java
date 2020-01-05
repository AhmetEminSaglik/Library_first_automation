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
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ActionsMainGui implements ActionListener, DocumentListener, MouseListener, FocusListener {

    MainGui mg = null;

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

    @Override
    public void actionPerformed(ActionEvent e) {

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

    @Override
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
                        System.out.println(" i : " + i);
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

            System.out.println("----Alinan kelime :" + getMg().gettxtStudentNo().getText());
        }

        if (e.getDocument().getProperty("StudentNoBookBarcodetxt").equals(2)) { // second for bookBarcode
            System.out.println("----Alinan kelime :" + getMg().getTxtBookBarcode().getText());
        }

    }

    @Override
    public void removeUpdate(DocumentEvent e
    ) {
        if (e.getDocument().getProperty("StudentNoBookBarcodetxt").equals(1)) { // second for bookBarcode
            System.out.println("silindikten sonra :" + getMg().gettxtStudentNo().getText());
        }
        if (e.getDocument().getProperty("StudentNoBookBarcodetxt").equals(2)) { // second for bookBarcode
            System.out.println("silindikten sonra :" + getMg().getTxtBookBarcode().getText());
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

    @Override
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
        /*  if (e.getSource() == getMg().getOvertime_Fine()) {
            getMg().getOvertime_Fine().setBackground(background_color);
            getMg().getOvertime_Fine().setForeground(foreground_color);
        }*/
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
            if (getMg().gettxtStudentNo().getText().trim().equals("Öğrenci No Girin")) {
                getMg().gettxtStudentNo().setText("");
                getMg().gettxtStudentNo().setForeground(Color.BLACK);
            }
        } else if (e.getSource() == getMg().getTxtBookBarcode()) {
            if (getMg().getTxtBookBarcode().getText().trim().equals("Kitap Barkod No girin")) {
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

}
