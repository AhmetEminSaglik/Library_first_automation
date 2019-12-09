package Logic;

import Gui.MainGui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Actions_MainGui implements ActionListener, DocumentListener, MouseListener {//DocumentListener

    MainGui mg = null;

    public Actions_MainGui(MainGui mg) {
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

        }
        if (e.getSource() == getMg().getBookReturn()) {

        }
        if (e.getSource() == getMg().getBookSearch_List()) {

        }
        if (e.getSource() == getMg().getBookUpdate_Remove()) {

        }
        if (e.getSource() == getMg().getStudentAdd()) {

        }
        if (e.getSource() == getMg().getStudentUpdate()) {

        }
        if (e.getSource() == getMg().getStudentState()) {

        }
        if (e.getSource() == getMg().getRegisteredStudent()) {

        }
        if (e.getSource() == getMg().getTimeControl_ExtraTime()) {

        }
        if (e.getSource() == getMg().getOvertime_Fine()) {

        }
        if (e.getSource() == getMg().getAboutUs()) {

        }
        if (e.getSource() == getMg().getExit()) {

            int answer = JOptionPane.showConfirmDialog(null, "ÇIKIŞ UYARISI", "Çıkmak İstediğinize Emin Misiniz?", 2, 3);

            if (answer == 0) {
                System.exit(answer);
            }
        }

    }

    @Override
    public void insertUpdate(DocumentEvent e) {

        if (e.getDocument().getProperty("StudentNo_BookId_txt").equals(1)) {// first for student no

            System.out.println("----Alinan kelime :" + getMg().getStudentNo_txt().getText());
        }

        if (e.getDocument().getProperty("StudentNo_BookId_txt").equals(2)) { // second for bookId
            System.out.println("silindikten sonra :" + getMg().getBookId_txt().getText());
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if (e.getDocument().getProperty("StudentNo_BookId_txt").equals(1)) { // second for bookId
            System.out.println("----Alinan kelime :" + getMg().getStudentNo_txt().getText());
        }
        if (e.getDocument().getProperty("StudentNo_BookId_txt").equals(2)) { // second for bookId
            System.out.println("silindikten sonra :" + getMg().getBookId_txt().getText());
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }

    public void find_buttons_source_with_using_mouse(MouseEvent e, Color background_color, Color foreground_color) { // to clear make which button you on 
        if (e.getSource() == getMg().getBookAdd()) {
            getMg().getBookAdd().setBackground(background_color);
            getMg().getBookAdd().setForeground(foreground_color);
        }
        if (e.getSource() == getMg().getBookReturn()) {
            getMg().getBookReturn().setBackground(background_color);
            getMg().getBookReturn().setForeground(foreground_color);
        }
        if (e.getSource() == getMg().getBookSearch_List()) {
            getMg().getBookSearch_List().setBackground(background_color);
            getMg().getBookSearch_List().setForeground(foreground_color);
        }
        if (e.getSource() == getMg().getBookUpdate_Remove()) {
            getMg().getBookUpdate_Remove().setBackground(background_color);
            getMg().getBookUpdate_Remove().setForeground(foreground_color);
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
        if (e.getSource() == getMg().getTimeControl_ExtraTime()) {
            getMg().getTimeControl_ExtraTime().setBackground(background_color);
            getMg().getTimeControl_ExtraTime().setForeground(foreground_color);
        }
        if (e.getSource() == getMg().getOvertime_Fine()) {
            getMg().getOvertime_Fine().setBackground(background_color);
            getMg().getOvertime_Fine().setForeground(foreground_color);
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
        find_buttons_source_with_using_mouse(e, Color.red, Color.WHITE);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        find_buttons_source_with_using_mouse(e, Color.WHITE, Color.BLACK);// this make buttons as before
    }

}
