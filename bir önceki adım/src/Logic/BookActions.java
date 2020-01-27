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

public class BookActions implements ActionListener {
    
    BookAddGui bag;
    BookReturnGui brg;
    BookSearchListGui bslg;
    BookUpdateRemoveGui burg;

    //  MainGui mg;
    JButton clearBook_info_txt;
    JButton saveBook_info_txt;

//eğer bag hata alırsa diğer taraftan burayı setlerim
    public BookActions(BookReturnGui brg) {
        this.brg = brg;
        //mg = brg.getMg();

    }
    
    public BookActions(BookAddGui bag) {
        this.bag = bag;
        //  mg = bag.getMg();
    }
    
    public BookActions(BookSearchListGui bslg) {
        this.bslg = bslg;
        //     mg = bslg.getMg();
    }
    
    public BookActions(BookUpdateRemoveGui burg) {
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
                System.out.println("Barkoda tıklandı");
            } else if (e.getSource() == bslg.getTxtBookName()) {
                System.out.println("bookname tıklandı");
            } else if (e.getSource() == bslg.getTxtAuthorName()) {
                System.out.println("yazara tıklandı");
            }
        } else if (burg != null) {
            if (e.getSource() == burg.getBtnComeBack()) {
                burg.setVisible(false);
                burg.getMg().getJp().setVisible(true);
            } else if (e.getSource() == burg.getBtnUpdate()) {
                JOptionPane.showMessageDialog(null, "buton aktifleştirildi sql komutları kaldı");
            } else if (e.getSource() == burg.getBtnDelete()) {
                JOptionPane.showMessageDialog(null, "buton aktifleştirildi sql komutları kaldı");
            }
        }
    }
    
    public void clear_all_txt() {
        
        bag.getMg().getStudentNo_txt().setText("");
        bag.getMg().getBookBarcode_txt().setText("");
        bag.getMg().getBookName_txt().setText("");
        bag.getMg().getResultScreen_txt().setText("");
        
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
