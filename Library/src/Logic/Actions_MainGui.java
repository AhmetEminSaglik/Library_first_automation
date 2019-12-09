package Logic;

import Gui.MainGui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Actions_MainGui implements ActionListener, DocumentListener {//DocumentListener

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

    }

    @Override
    public void insertUpdate(DocumentEvent e) {

        // e.getDocument().getProperty ("HERE",value) --> HERE must be same I dont know why  but we differenciate witdh value
        if (e.getDocument().getProperty("StudentNo_BookId_txt").equals(1)) {// first for student no

            System.out.println("----Alinan kelime :" + getMg().getStudentNo_txt().getText());
        }

        if (e.getDocument().getProperty("StudentNo_BookId_txt").equals(2)) { // second for bookId
            System.out.println("silindikten sonra :" + getMg().getBookId_txt().getText());
        }

        //    System.out.println("getMg().getStudentNo_txt() : " + getMg().getStudentNo_txt().getDocument());
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
        System.out.println("changedUpdate");
    }

}
