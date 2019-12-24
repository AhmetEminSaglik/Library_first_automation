package Gui;

import Logic.BookActions;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BookAddGui {

    MainGui mg;
    JFrame jf;
    JPanel jp;
    JLabel bookBarcodeNo_lbl;
    JLabel bookName_lbl;
    JLabel authorName_lbl;
    JLabel result_lbl;

    JTextField bookBarcodeNo_txt;
    JTextField bookName_txt;
    JTextField authorName_txt;
    JTextField result_txt;
    BookActions bookaction = new BookActions(this);
    JButton comeBack_btn;
    JButton addBook;
    Font font_lbl = new Font("monospaced", Font.BOLD, 20);
    Font font_txt = new Font("monospaced", Font.BOLD, 15);

    public BookAddGui(MainGui mg) {

        this.mg = mg;
        setJf(mg.getJf());
        getJf().setTitle("KİTAP EKLE");
        mg.getJp().setVisible(false);

        addAllThingsONPanel();
        getComeBack_btn().addActionListener(bookaction);
        getJf().add(getJp());

    }

    public void addAllThingsONPanel() {
        getJp().add(getBookBarcodeNo_lbl());
        getJp().add(getBookName_lbl());
        getJp().add(getAuthorName_lbl());
        getJp().add(getResult_lbl());
        getJp().add(getBookBarcodeNo_txt());
        getJp().add(getBookName_txt());
        getJp().add(getAuthorName_txt());
        getJp().add(getResult_txt());
        getJp().add(getComeBack_btn());
        getJp().add(getAddBook());
    }

    /*Mainguiyi gönderip jf üzerine burdaki jp'yi ekleyebilirim
    
     */
    public JFrame getJf() {
        if (jf == null) {
            jf = new JFrame();
        }
        return jf;
    }

    public void setJf(JFrame jf) {
        this.jf = jf;
    }

    public JPanel getJp() {
        if (jp == null) {
            jp = new JPanel();
            getJp().setBackground(Color.red);
            jp.setLayout(null);
        }
        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

    public JLabel getBookBarcodeNo_lbl() {
        if (bookBarcodeNo_lbl == null) {
            bookBarcodeNo_lbl = new JLabel("Kitap Barkod Numarası ");
            bookBarcodeNo_lbl.setBounds(20, 0, 450, 50);
            bookBarcodeNo_lbl.setFont(font_lbl);

        }

        return bookBarcodeNo_lbl;
    }

    public void setBookBarcodeNo_lbl(JLabel bookBarcodeNo_lbl) {
        this.bookBarcodeNo_lbl = bookBarcodeNo_lbl;
    }

    public JLabel getBookName_lbl() {
        if (bookName_lbl == null) {
            bookName_lbl = new JLabel("Kitap Adı");
            bookName_lbl.setBounds(20, 100, 450, 50);
            bookName_lbl.setFont(font_lbl);

        }
        return bookName_lbl;
    }

    public void setBookName_lbl(JLabel bookName_lbl) {
        this.bookName_lbl = bookName_lbl;
    }

    public JLabel getAuthorName_lbl() {
        if (authorName_lbl == null) {
            authorName_lbl = new JLabel("Yazar Adı");
            authorName_lbl.setBounds(20, 200, 450, 50);
            authorName_lbl.setFont(font_lbl);
        }
        return authorName_lbl;
    }

    public void setAuthorName_lbl(JLabel authorName_lbl) {
        this.authorName_lbl = authorName_lbl;
    }

    public JLabel getResult_lbl() {
        if (result_lbl == null) {
            result_lbl = new JLabel("SONUC");
            result_lbl.setBounds(515, 0, 450, 50);
            result_lbl.setFont(font_lbl);

        }
        return result_lbl;
    }

    public void setResult_lbl(JLabel result_lbl) {
        this.result_lbl = result_lbl;
    }

    public JTextField getBookBarcodeNo_txt() {
        if (bookBarcodeNo_txt == null) {
            bookBarcodeNo_txt = new JTextField("Kitap onaylanınca buradaki ve alttaki tüm yazılar kaybolur");
            bookBarcodeNo_txt.setBounds(20, 50, 300, 40);
            bookBarcodeNo_txt.setFont(font_txt);

        }
        return bookBarcodeNo_txt;
    }

    public void setBookBarcodeNo_txt(JTextField bookBarcodeNo_txt) {
        this.bookBarcodeNo_txt = bookBarcodeNo_txt;
    }

    public JTextField getBookName_txt() {
        if (bookName_txt == null) {
            bookName_txt = new JTextField("kkitap adi");
            bookName_txt.setBounds(20, 150, 680, 40);
            bookName_txt.setFont(font_txt);
        }
        return bookName_txt;
    }

    public void setBookName_txt(JTextField bookName_txt) {
        this.bookName_txt = bookName_txt;
    }

    public JTextField getAuthorName_txt() {
        if (authorName_txt == null) {
            authorName_txt = new JTextField("yazar adı");
            authorName_txt.setBounds(20, 250, 300, 40);
            authorName_txt.setFont(font_txt);
        }
        return authorName_txt;
    }

    public void setAuthorName_txt(JTextField authorName_txt) {
        this.authorName_txt = authorName_txt;
    }

    public JTextField getResult_txt() {
        if (result_txt == null) {
            result_txt = new JTextField("Onaylanacak kısım");
            result_txt.setBounds(400, 50, 300, 40);
            //bookBarcodeNo_txt.setBounds(20, 50, 300, 40);
            result_txt.setFont(font_txt);

            result_txt.setEditable(false);
            result_txt.setBackground(Color.WHITE);
            result_txt.setFocusable(false);
        }
        return result_txt;
    }

    public void setResult_txt(JTextField result_txt) {
        this.result_txt = result_txt;
    }

    public JButton getComeBack_btn() {
        if (comeBack_btn == null) {
            comeBack_btn = new JButton("Geri dön");
            comeBack_btn.setBounds(50, 400, 150, 50);
            comeBack_btn.setBackground(Color.white);
            comeBack_btn.setFont(font_txt);

            comeBack_btn.setCursor(new Cursor(12));
        }
        return comeBack_btn;
    }

    public void setComeBack_btn(JButton comeBack_btn) {
        this.comeBack_btn = comeBack_btn;
    }

    public JButton getAddBook() {
        if (addBook == null) {
            addBook = new JButton("Kitabı Ekle");
            addBook.setBounds(400, 250, 150, 50);
            addBook.setBackground(Color.white);
            addBook.setFont(font_txt);

            comeBack_btn.setCursor(new Cursor(12));
        }
        return addBook;
    }

    public void setAddBook(JButton addBook) {
        this.addBook = addBook;
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

}
