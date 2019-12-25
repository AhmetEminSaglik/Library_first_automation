package Logic;

import Gui.RegisteredStudentGui;
import Gui.StudentAddGui;
import Gui.StudentStateGui;
import Gui.StudentUpdateGui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ActionStudent implements ActionListener {

    StudentAddGui sag;
    StudentStateGui ssg;
    StudentUpdateGui sug;
    RegisteredStudentGui rsg;

    //StudentGui 
    public ActionStudent(StudentAddGui sag) {
        this.sag = sag;

    }

    public ActionStudent(StudentStateGui ssg) {
        this.ssg = ssg;

    }

    public ActionStudent(StudentUpdateGui sug) {
        this.sug = sug;
    }

    public ActionStudent(RegisteredStudentGui rsg) {
        this.rsg = rsg;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sag.getBtnAdd()) {
            JOptionPane.showMessageDialog(null, "Buton aktifleştirildi Sql'e bağlanacak");
        } else if (e.getSource() == sag.getBtnClear()) {
            int answer = JOptionPane.showConfirmDialog(null, "Tüm veriler Silinecektir Emin misiniz? ", "Silme Uyarısı", 0);
            if (answer == JOptionPane.YES_OPTION) {
                sag.getTxtEmail().setText("");
                sag.getTxtName().setText("");
                sag.getTxtNo().setText("");
                sag.getTxtSurname().setText("");
            }
        } else if (e.getSource() == sag.getBtnComeBack()) {

        }
    }
}
