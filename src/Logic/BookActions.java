package Logic;

import Gui.BookAddGui;
import Gui.BookReturnGui;
import Gui.BookSearch_ListGui;
import Gui.BookUpdate_RemoveGui;
import Gui.MainGui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class BookActions implements ActionListener {

    BookAddGui bag;
    BookReturnGui brg;
    BookSearch_ListGui bslg;
    BookUpdate_RemoveGui burg;
    MainGui mg;
    JButton clearBook_info_txt;
    JButton saveBook_info_txt;
//eğer bag hata alırsa diğer taraftan burayı setlerim

    public BookActions(BookAddGui bag) {
        this.bag = bag;
        mg = bag.getMg();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (bag != null) {
            if (e.getSource() == bag.getComeBack_btn()) {
                bag.getJp().setVisible(false);
                bag.getMg().getJp().setVisible(true);
                clear_all_txt();
            }

        }

    }

    public void clear_all_txt() {

        bag.getMg().getStudentNo_txt().setText("");
        bag.getMg().getBookBarcode_txt().setText("");
        bag.getMg().getBookName_txt().setText("");
        bag.getMg().getResultScreen_txt().setText("");

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

    public JButton getClearBook_info_txt() {
        return clearBook_info_txt;
    }

    public void setClearBook_info_txt(JButton clearBook_info_txt) {
        this.clearBook_info_txt = clearBook_info_txt;
    }

    public JButton getSaveBook_info_txt() {
        return saveBook_info_txt;
    }

    public void setSaveBook_info_txt(JButton saveBook_info_txt) {
        this.saveBook_info_txt = saveBook_info_txt;
    }

}
