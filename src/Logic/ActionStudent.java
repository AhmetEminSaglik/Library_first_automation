package Logic;

import Gui.RegisteredStudentGui;
import Gui.StudentAddGui;
import Gui.StudentStateGui;
import Gui.StudentUpdateGui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class ActionStudent implements ActionListener, MouseListener {

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
        if (sag != null) {
            if (e.getSource() == sag.getBtnAdd()) {
                JOptionPane.showMessageDialog(null, "Buton aktifleştirildi Sql'e bağlanacak");
            } else if (e.getSource() == sag.getBtnClear()) {
                int answer = JOptionPane.showConfirmDialog(null, "Tüm veriler Silinecektir Emin misiniz? ", "Silme Uyarısı", 0);
                if (answer == JOptionPane.YES_OPTION) {
                    sag.getTxtEmail().setText("");
                    sag.getTxtName().setText("");
                    sag.getTxtNo().setText("");
                    sag.getTxtSurname().setText("");
                    sag.getTxtPhoneNo().setText("");
                }
            } else if (e.getSource() == sag.getBtnComeBack()) {
                sag.getJp().setVisible(false);
                sag.getMg().getJf().setTitle("ANA SAYFA");
                sag.getMg().getJp().setVisible(true);

            }
        } else if (sug != null) {
            if (e.getSource() == sug.getBtnComeBack()) {
                sug.setVisible(false);
                sug.getMg().getJf().setTitle("ANA SAYFA");
                System.out.println("buradan geçti");
                sug.getMg().getJp().setVisible(true);
            } else if (e.getSource() == sug.getTxtno()) {
                JOptionPane.showMessageDialog(null, "sql bağlantısı kurulacak");

            } else if (e.getSource() == sug.getTxtResult()
                    || e.getSource() == sug.getTxtNewSurname()
                    || e.getSource() == sug.getTxtNewNo()
                    || e.getSource() == sug.getTxtNewName()
                    || e.getSource() == sug.getTxtNewEmail()
                    || e.getSource() == sug.getTxtPhoneNo()) {
                JOptionPane.showMessageDialog(null, "sql bağlantısı kurulup her enterda bilgiler kaydedilecektir");

            }
        } else if (ssg != null) {
            if (e.getSource() == ssg.getBtnComeBack()) {
                ssg.setVisible(false);
                ssg.getMg().getJf().setTitle("ANA SAYFA");
                ssg.getMg().getJp().setVisible(true);

            } else if (e.getSource() == ssg.getTxtStudentNo()) {
                JOptionPane.showMessageDialog(null, "sql ile bağlantısı kurulacak");
            }
        } else if (rsg != null) {
            if (e.getSource() == rsg.getBtnComeBack()) {
                rsg.getJp().setVisible(false);
                rsg.getMg().getJf().setTitle("ANA SAYFA");
                rsg.getMg().getJp().setVisible(true);

            } else if (e.getSource() == rsg.getTxtNo()
                    || e.getSource() == rsg.getTxtName()
                    || e.getSource() == rsg.getTxtSurname()) {
                JOptionPane.showMessageDialog(null, "aşağıdaki tablolara bağlanılacak");

            }
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
        rsg.getTable().setSelectionBackground(Color.GREEN);
        System.out.println(rsg.getTable().getSelectedColumn());

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
