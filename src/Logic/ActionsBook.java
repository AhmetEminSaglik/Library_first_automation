package Logic;

import Gui.BookAddGui;
import Gui.BookReturnGui;
import Gui.BookSearchListGui;
import Gui.BookUpdateRemoveGui;
import Gui.MainGui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ActionsBook implements ActionListener {

    BookAddGui bag;
    BookReturnGui brg;
    BookSearchListGui bslg;
    BookUpdateRemoveGui burg;

    //  MainGui mg;
    JButton clearBook_info_txt;
    JButton saveBook_info_txt;

//eğer bag hata alırsa diğer taraftan burayı setlerim
    public ActionsBook(BookReturnGui brg) {
        this.brg = brg;
        //mg = brg.getMg();

    }

    public ActionsBook(BookAddGui bag) {
        this.bag = bag;
        //  mg = bag.getMg();
    }

    public ActionsBook(BookSearchListGui bslg) {
        this.bslg = bslg;
        //     mg = bslg.getMg();
    }

    public ActionsBook(BookUpdateRemoveGui burg) {
        this.burg = burg;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (bag != null) {
            if (e.getSource() == bag.getBtnComeBack()) {

                bag.getJp().setVisible(false);
                bag.getMg().getJp().setVisible(true);
                bag.getMg().getJf().setTitle("ANA SAYFA");
                clear_all_txt();
            }
            if (e.getSource() == bag.getBtnAddBook()) {
                JOptionPane.showMessageDialog(null, "Kitap Ekle action eklendi >>> SQL KALDI");
            }
        } else if (brg != null) {
            if (e.getSource() == brg.getBtnComeBack()) {
                brg.getJp().setVisible(false);
                brg.getMg().getJp().setVisible(true);
                brg.getMg().getJf().setTitle("ANA SAYFA");
            } else if (e.getSource() == brg.getTxtBarcodeNo()) {
                JOptionPane.showMessageDialog(null, "sql e bağlanacak");

            } else if (e.getSource() == brg.getTxtStudentNo()) {
                JOptionPane.showMessageDialog(null, "sql e bağlanacak");
            }
        } else if (bslg != null) {
            /*        getTxtAuthorName().addActionListener(action);
        getTxtBarcodeNo().addActionListener(action);
        getTxtBookName().addActionListener(action);
        getBtnComeBack().addActionListener(action);*/
            if (e.getSource() == bslg.getBtnComeBack()) {
                bslg.getJp().setVisible(false);
                bslg.getMg().getJf().setTitle("ANA SAYFA");
                bslg.getMg().getJp().setVisible(true);

            } else if (e.getSource() == bslg.getTxtBarcodeNo()) {
                JOptionPane.showMessageDialog(null, "Barkod No'ya tıklandı Sql kaldı ");
            } else if (e.getSource() == bslg.getTxtBookName()) {
                JOptionPane.showMessageDialog(null, "Kitap adı'na tıklandı Sql kaldı ");
            } else if (e.getSource() == bslg.getTxtAuthorName()) {
                JOptionPane.showMessageDialog(null, "Yazar adına tıklandı Sql kaldı ");
            } else if (e.getSource() == bslg.getTxtCategory()) {

                JOptionPane.showMessageDialog(null, "Kategoriye tıklandı Sql kaldı ");
            }
        } else if (burg != null) {
            if (e.getSource() == burg.getBtnComeBack()) {
                burg.setVisible(false);
                burg.getMg().getJp().setVisible(true);
                burg.getJf().setTitle("ANA SAYFA");
            } else if (e.getSource() == burg.getBtnUpdate()) {
                JOptionPane.showMessageDialog(null, "buton aktifleştirildi sql komutları kaldı");
            } else if (e.getSource() == burg.getBtnDelete()) {
                JOptionPane.showMessageDialog(null, "buton aktifleştirildi sql komutları kaldı");
            } else if (e.getSource() == burg.getTxtToBeChangedBarcodeNo()) {
                JOptionPane.showConfirmDialog(null, "Aktifleşti sql'e bağlanılacak");
            }
        }
    }

    public void clear_all_txt() {

        bag.getMg().gettxtStudentNo().setText("");
        bag.getMg().getTxtBookBarcode().setText("");
        bag.getMg().getTxtBookName().setText("");
        bag.getMg().gettxtResultScreen().setText("");

    }

    /*public MainGui getMg() {
        if (mg == null) {
            mg = new MainGui();
        }

        return mg;
    }

    public void setMg(MainGui mg) {
        this.mg = mg;
    }*/
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
