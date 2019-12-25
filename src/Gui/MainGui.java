package Gui;

import Logic.ActionsMainGui;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainGui {//extends JPanel

    JFrame jf = null;
    JPanel jp = null;

    JLabel studentNo_jlabel;
    JLabel bookBarcode_jlabel;
    JLabel resultScreen_jlabel;
    JLabel bookName_jlabel;

    JLabel totalBook_jlabel;// totalBook_jlabel,remainBook_jlabel,givenBook_jlabel are different label not like aboves
    JLabel remainBook_jlabel;
    JLabel givenBook_jlabel;

    JTextField StudentNo_txt;
    JTextField bookBarcode_txt;
    JTextField resultScreen_txt;
    JTextField bookName_txt;

    JTextField totalBook_txt;
    JTextField remainBook_txt;
    JTextField givenBook_txt;

    JButton bookAdd;
    JButton bookReturn;
    JButton bookSearch_List;
    JButton bookUpdate_Remove;
    JButton studentAdd;
    JButton studentUpdate;
    JButton studentState;
    JButton registeredStudent;
    JButton TimeControl_ExtraTime;
    JButton FineDebtPayment;
    JButton aboutUs;
    JButton exit;
    final int holding_area_scale = 270;
    final int push_scale = 8;
    final int txt_height = 40;
    int push_right_lbl_counter = 0;
    int push_right_txt_counter = 0;

    final int push_total_remain_given_books = 30;
    int total_remain_given_books_x = 15;
    int total_remain_given_books_y = 80;
    int total_remain_given_books_counter = 0;

    final int button_width = 220;
    final int button_height = 50;
    final int passing_floor_X = button_width + 40;
    final int FloorOfBook_X = 45;
    final int FloorOfStudent_X = FloorOfBook_X + passing_floor_X;
    final int FloorOf_Time_Exit_X = FloorOfStudent_X + passing_floor_X;
    final int passing_floor_Y = 60;
    final int FirstButtons_Y = 260;
    final int SecondButtons_Y = FirstButtons_Y + passing_floor_Y;
    final int ThirdButtons_Y = SecondButtons_Y + passing_floor_Y;
    final int FourthButtons_Y = ThirdButtons_Y + passing_floor_Y;
    ActionsMainGui action = new ActionsMainGui(this);

    public MainGui() {
    }

    public MainGui(Login login) {

        //getJp(). = login.getJf();
        setJf(login.getJf());
        getJf().setTitle("ANA SAYFA");
        getJp().setBounds(0, 0, getJf().getWidth(), getJf().getHeight());
        getJf().add(getJp());
        getJp().setVisible(true);
        addAllThingsONPanel();

        getJp().setBackground(Color.BLACK);

    }

    public void addAllThingsONPanel() {

        getJp().setVisible(true);
        getJp().add(getStudentNo_jlabel());
        getJp().add(getBookBarcode_jlabel());
        getJp().add(getResultScreen_jlabel());
        getJp().add(getBookName_jlabel());
        getJp().add(getRemainBook_jlabel());
        getJp().add(getGivenBook_jlabel());
        getJp().add(getTotalBook_jlabel());
        getJp().add(getRemainBook_txt());
        getJp().add(getGivenBook_txt());
        getJp().add(getTotalBook_txt());
        getJp().add(getStudentNo_txt());
        getJp().add(getBookBarcode_txt());
        getJp().add(getResultScreen_txt());
        getJp().add(getBookName_txt());
        getJp().add(getBookAdd());
        getJp().add(getBookReturn());
        getJp().add(getBookSearch_List());
        getJp().add(getBookUpdate_Remove());
        getJp().add(getStudentAdd());
        getJp().add(getStudentUpdate());
        getJp().add(getStudentState());
        getJp().add(getRegisteredStudent());
        getJp().add(getTimeControl_ExtraTime());
        getJp().add(getFineDebtPayment());
        getJp().add(getAboutUs());
        getJp().add(getExit());

    }

    public JLabel build_JlabelForNulls(JLabel jlabel) {
        jlabel = new JLabel("BURAYA YAZI YAZILACAK");
        jlabel.setBounds(10 + (holding_area_scale + push_scale) * push_right_lbl_counter, 10, holding_area_scale, 30);
        jlabel.setFont(new Font("monospaced", Font.BOLD, 25));
        jlabel.setForeground(Color.white);
        push_right_lbl_counter++;

        return jlabel;
        /* final int holding_area_scale = 270;
    final int push_scale = 20;
   int push_right_lbl_counter = 0;
   int push_right_txt_counter = 0;*/
    }

    public JTextField build_JTextfiledForNulls(JTextField jtxt) {
        jtxt = new JTextField("");
        jtxt.setBackground(Color.WHITE);
        jtxt.setBounds(3 + (holding_area_scale + push_scale) * push_right_txt_counter, 50, holding_area_scale, txt_height);
        jtxt.setFont(new Font("monospaced", Font.BOLD, 17));
        push_right_txt_counter++;
        jtxt.setLayout(null);
        //jtxt.getDocument().addDocumentListener(action);
        // jtxt.getDocument().putProperty("Student_name", 2); // first one is key , the other one is value

        return jtxt;
    }

    public JButton build_JbuttonForNulls(JButton jbtn, int whichFloor_X, int sectorButtons_Y) {
        //studentUpdate = build_JbuttonForNulls(studentUpdate, FloorOfStudent_X, SecondButtons_Y);
        jbtn = new JButton();
        jbtn.setCursor(new Cursor(12));
        jbtn.setBounds(whichFloor_X, sectorButtons_Y, button_width, button_height);
        jbtn.setFont(new Font("", Font.BOLD, 17));
        jbtn.setForeground(Color.BLACK);

        jbtn.setBackground(Color.WHITE);
        jbtn.addActionListener(action);
        jbtn.addMouseListener(action);
        jbtn.setBorderPainted(false);
        return jbtn;
    }

    public JFrame getJf() {
        if (jf == null) {
            jf = new JFrame();

        }
        return jf;
    }

    public JPanel getJp() {
        if (jp == null) {
            jp = new JPanel();
            jp.setBounds(getJf().getBounds());
            getJp().setLayout(null);
        }
        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

    public void setJf(JFrame Jf) {
        this.jf = Jf;
    }

    public JLabel getStudentNo_jlabel() {
        if (studentNo_jlabel == null) {
            studentNo_jlabel = build_JlabelForNulls(studentNo_jlabel);
            studentNo_jlabel.setText("Öğrenci No ");

        }
        return studentNo_jlabel;
    }

    public void setStudentNo_jlabel(JLabel studentNo_jlabel) {
        this.studentNo_jlabel = studentNo_jlabel;
    }

    public JLabel getBookBarcode_jlabel() {
        if (bookBarcode_jlabel == null) {
            bookBarcode_jlabel = build_JlabelForNulls(bookBarcode_jlabel);
            bookBarcode_jlabel.setText("Kitap Barkod No");
        }
        return bookBarcode_jlabel;
    }

    public void setBookBarcode_jlabel(JLabel bookBarcode_jlabel) {
        this.bookBarcode_jlabel = bookBarcode_jlabel;
    }

    public JLabel getResultScreen_jlabel() {
        if (resultScreen_jlabel == null) {
            resultScreen_jlabel = build_JlabelForNulls(resultScreen_jlabel);
            resultScreen_jlabel.setText("Sonuç ");

        }
        return resultScreen_jlabel;
    }

    public void setResultScreen_jlabel(JLabel resultScreen_jlabel) {
        this.resultScreen_jlabel = resultScreen_jlabel;
    }

    public JLabel getBookName_jlabel() {
        if (bookName_jlabel == null) {
            bookName_jlabel = new JLabel("Kitap  Adı");
            bookName_jlabel.setBounds(10, 135, 300, 70);
            bookName_jlabel.setForeground(Color.WHITE);
            bookName_jlabel.setFont(new Font("monospaced", Font.BOLD, 25));
        }
        return bookName_jlabel;
    }

    public void setBookName_jlabel(JLabel bookName_jlabel) {
        this.bookName_jlabel = bookName_jlabel;
    }

    public JLabel getTotalBook_jlabel() {
        if (totalBook_jlabel == null) {
            totalBook_jlabel = new JLabel("Toplam Kitap Sayısı");
            totalBook_jlabel.setBounds((total_remain_given_books_x + holding_area_scale) * total_remain_given_books_counter,
                    total_remain_given_books_y, holding_area_scale,
                    50);
            totalBook_jlabel.setFont(new Font("monospaced", Font.BOLD, 22));
            totalBook_jlabel.setForeground(new Color(116, 185, 255));
            total_remain_given_books_counter = 0;
        }
        /*    final int push_total_remain_given_books = 30;
              int total_remain_given_books_x = 30;
         */
        return totalBook_jlabel;
    }

    public void setTotalBook_jlabel(JLabel totalBook_jlabel) {
        this.totalBook_jlabel = totalBook_jlabel;
    }

    public JLabel getRemainBook_jlabel() {
        if (remainBook_jlabel == null) {
            remainBook_jlabel = new JLabel("Verilen Kitap Sayısı");
            remainBook_jlabel.setBounds(10 + (total_remain_given_books_x + holding_area_scale) * total_remain_given_books_counter,
                    total_remain_given_books_y, holding_area_scale, 50);
            remainBook_jlabel.setFont(new Font("monospaced", Font.BOLD, 22));
            remainBook_jlabel.setForeground(Color.red);
            total_remain_given_books_counter++;
        }
        return remainBook_jlabel;
    }

    public void setRemainBook_jlabel(JLabel remainBook_jlabel) {
        this.remainBook_jlabel = remainBook_jlabel;
    }

    public JLabel getGivenBook_jlabel() {
        if (givenBook_jlabel == null) {
            givenBook_jlabel = new JLabel("Kalan Kitap Sayısı");
            givenBook_jlabel.setBounds(15 + (total_remain_given_books_x + holding_area_scale) * total_remain_given_books_counter,
                    1 + total_remain_given_books_y, holding_area_scale, 50);
            givenBook_jlabel.setFont(new Font("monospaced", Font.BOLD, 22));
            givenBook_jlabel.setForeground(Color.GREEN);
            total_remain_given_books_counter++;
        }
        return givenBook_jlabel;
    }

    public void setGivenBook_jlabel(JLabel givenBook_jlabel) {
        this.givenBook_jlabel = givenBook_jlabel;
    }

    public JTextField getStudentNo_txt() {
        if (StudentNo_txt == null) {
            StudentNo_txt = build_JTextfiledForNulls(StudentNo_txt);
            StudentNo_txt.getDocument().addDocumentListener(action);
            StudentNo_txt.getDocument().putProperty("StudentNo_BookBarcode_txt", 1); // first one is key , the other one is value
            StudentNo_txt.addActionListener(action);
//StudentNo_txt.set
        }
        return StudentNo_txt;
    }

    public void setStudentNo_txt(JTextField StudentNo_txt) {
        this.StudentNo_txt = StudentNo_txt;
    }

    public JTextField getBookBarcode_txt() {
        if (bookBarcode_txt == null) {
            bookBarcode_txt = build_JTextfiledForNulls(bookBarcode_txt);
            bookBarcode_txt.getDocument().addDocumentListener(action);
            bookBarcode_txt.getDocument().putProperty("StudentNo_BookBarcode_txt", 2); // first one is key , the other one is value
            bookBarcode_txt.addActionListener(action);

        }
        return bookBarcode_txt;
    }

    public void setBookBarcode_txt(JTextField bookBarcode_txt) {
        this.bookBarcode_txt = bookBarcode_txt;
    }

    public JTextField getResultScreen_txt() {
        if (resultScreen_txt == null) {
            resultScreen_txt = build_JTextfiledForNulls(resultScreen_txt);
            resultScreen_txt.setEditable(false);
        }
        return resultScreen_txt;
    }

    public void setResultScreen_txt(JTextField resultScreen_txt) {
        this.resultScreen_txt = resultScreen_txt;
    }

    public JTextField getBookName_txt() {
        if (bookName_txt == null) {
            bookName_txt = new JTextField("");
            bookName_txt.setBounds(3, 190, getJf().getWidth() - 24, 55);
            bookName_txt.setFont(new Font("monospaced", Font.BOLD, 19));
            bookName_txt.setEditable(false);
            bookName_txt.setFocusable(false);
            // bookName_txt.setEnabled(false);
            bookName_txt.setBackground(Color.WHITE);

        }
        return bookName_txt;
    }

    public void setBookName_txt(JTextField bookName_txt) {
        this.bookName_txt = bookName_txt;
    }

    public JTextField getTotalBook_txt() {
        if (totalBook_txt == null) {
            totalBook_txt = new JTextField("10810");
            totalBook_txt.setBounds(50 + (total_remain_given_books_x + holding_area_scale) * total_remain_given_books_counter,
                    total_remain_given_books_y + 45, 150, 30);
            totalBook_txt.setFont(new Font("", Font.BOLD, 22));
            totalBook_txt.setEditable(false);
            totalBook_txt.setFocusable(false);
            totalBook_txt.setBackground(new Color(116, 185, 255));
            totalBook_txt.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
            totalBook_txt.setBorder(BorderFactory.createCompoundBorder(remainBook_txt.getBorder(), BorderFactory.createEmptyBorder(0, 20, 0, 0)));
            total_remain_given_books_counter++;

            /*  totalBook_jlabel.setBounds((total_remain_given_books_x + holding_area_scale) * total_remain_given_books_counter,
                    total_remain_given_books_y, holding_area_scale,
                    50);*/
        }
        return totalBook_txt;
    }

    public void setTotalBook_txt(JTextField totalBook_txt) {
        this.totalBook_txt = totalBook_txt;
    }

    public JTextField getRemainBook_txt() {
        if (remainBook_txt == null) {
            remainBook_txt = new JTextField("9999");
            remainBook_txt.setBounds(50 + (total_remain_given_books_x + holding_area_scale) * total_remain_given_books_counter,
                    total_remain_given_books_y + 45, 150, 30);
            remainBook_txt.setFont(new Font("", Font.BOLD, 22));
            remainBook_txt.setEditable(false);
            remainBook_txt.setFocusable(false);
            remainBook_txt.setBackground(Color.red);
            remainBook_txt.setForeground(Color.BLACK);
            total_remain_given_books_counter++;
            remainBook_txt.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
            remainBook_txt.setBorder(BorderFactory.createCompoundBorder(remainBook_txt.getBorder(), BorderFactory.createEmptyBorder(0, 20, 0, 0)));
            /*  totalBook_jlabel.setBounds((total_remain_given_books_x + holding_area_scale) * total_remain_given_books_counter,
                    total_remain_given_books_y, holding_area_scale,
                    50);*/

        }
        return remainBook_txt;
    }

    public void setRemainBook_txt(JTextField remainBook_txt) {

        this.remainBook_txt = remainBook_txt;
    }

    public JTextField getGivenBook_txt() {
        if (givenBook_txt == null) {
            givenBook_txt = new JTextField("375");
            givenBook_txt.setBounds(50 + (total_remain_given_books_x + holding_area_scale) * total_remain_given_books_counter,
                    total_remain_given_books_y + 45, 150, 30);
            givenBook_txt.setBackground(Color.GREEN);
            givenBook_txt.setForeground(Color.BLACK);
            givenBook_txt.setFont(new Font("", Font.BOLD, 22));
            givenBook_txt.setEditable(false);
            givenBook_txt.setFocusable(false);
            givenBook_txt.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
            givenBook_txt.setBorder(BorderFactory.createCompoundBorder(remainBook_txt.getBorder(), BorderFactory.createEmptyBorder(0, 20, 0, 0)));
            total_remain_given_books_counter++;
            /*   remainBook_jlabel.setBounds(10 + (total_remain_given_books_x + holding_area_scale) * total_remain_given_books_counter,
                    total_remain_given_books_y, holding_area_scale, 50);*/

        }
        return givenBook_txt;
    }

    public void setGivenBook_txt(JTextField givenBook_txt) {
        this.givenBook_txt = givenBook_txt;
    }

    public JButton getBookAdd() {
        if (bookAdd == null) {
            bookAdd = build_JbuttonForNulls(bookAdd, FloorOfBook_X, FirstButtons_Y);
            bookAdd.setText("Kitap Ekle");
        }
        return bookAdd;
    }

    public void setBookAdd(JButton bookAdd) {
        this.bookAdd = bookAdd;
    }

    public JButton getBookReturn() {
        if (bookReturn == null) {
            bookReturn = build_JbuttonForNulls(bookReturn, FloorOfBook_X, SecondButtons_Y);
            bookReturn.setText("Kitap İade");
        }
        return bookReturn;
    }

    public void setBookReturn(JButton bookReturn) {
        this.bookReturn = bookReturn;
    }

    public JButton getBookSearch_List() {
        if (bookSearch_List == null) {
            bookSearch_List = build_JbuttonForNulls(bookSearch_List, FloorOfBook_X, ThirdButtons_Y);
            bookSearch_List.setText("Kitap Listesi & Sorgula");

        }
        return bookSearch_List;
    }

    public void setBookSearch_List(JButton bookSearch_List) {
        this.bookSearch_List = bookSearch_List;
    }

    public JButton getBookUpdate_Remove() {
        if (bookUpdate_Remove == null) {
            bookUpdate_Remove = build_JbuttonForNulls(bookUpdate_Remove, FloorOfBook_X, FourthButtons_Y);
            bookUpdate_Remove.setText("Kitap Guncelle & Sil");
        }

        return bookUpdate_Remove;
    }

    public void setBookUpdate_Remove(JButton bookUpdate_Remove) {
        this.bookUpdate_Remove = bookUpdate_Remove;
    }

    public JButton getStudentAdd() {
        if (studentAdd == null) {
            studentAdd = build_JbuttonForNulls(studentAdd, FloorOfStudent_X, FirstButtons_Y);
            studentAdd.setText("Ögrenci Ekle");
        }

        return studentAdd;
    }

    public void setStudentAdd(JButton studentAdd) {
        this.studentAdd = studentAdd;
    }

    public JButton getStudentUpdate() {
        if (studentUpdate == null) {
            studentUpdate = build_JbuttonForNulls(studentUpdate, FloorOfStudent_X, SecondButtons_Y);
            studentUpdate.setText("Ögrenci Güncelle");
        }
        return studentUpdate;
    }

    public void setStudentUpdate(JButton studentUpdate) {
        this.studentUpdate = studentUpdate;
    }

    public JButton getStudentState() {
        if (studentState == null) {
            studentState = build_JbuttonForNulls(studentState, FloorOfStudent_X, ThirdButtons_Y);
            studentState.setText("Ogrenci Durumu");
        }

        return studentState;
    }

    public void setStudentState(JButton studentState) {
        this.studentState = studentState;
    }

    public JButton getRegisteredStudent() {
        if (registeredStudent == null) {
            registeredStudent = build_JbuttonForNulls(registeredStudent, FloorOfStudent_X, FourthButtons_Y);
            registeredStudent.setText("Kayıtlı Ogrenci listesi");

        }
        return registeredStudent;
    }

    public void setRegisteredStudent(JButton registeredStudent) {
        this.registeredStudent = registeredStudent;
    }

    public JButton getTimeControl_ExtraTime() {
        if (TimeControl_ExtraTime == null) {
            TimeControl_ExtraTime = build_JbuttonForNulls(TimeControl_ExtraTime, FloorOf_Time_Exit_X, FirstButtons_Y);
            TimeControl_ExtraTime.setText("Süre Kontrol & Uzatma");

        }
        return TimeControl_ExtraTime;
    }

    public void setTimeControl_ExtraTime(JButton TimeControl_ExtraTime) {
        this.TimeControl_ExtraTime = TimeControl_ExtraTime;
    }

    public JButton getFineDebtPayment() {
        if (FineDebtPayment == null) {
            FineDebtPayment = build_JbuttonForNulls(FineDebtPayment, FloorOf_Time_Exit_X, SecondButtons_Y);
            FineDebtPayment.setText("Para Cezaları & Ödeme");

        }
        return FineDebtPayment;
    }

    public void setFineDebtPayment(JButton FineDebtPayment) {
        this.FineDebtPayment = FineDebtPayment;
    }

    public JButton getAboutUs() {
        if (aboutUs == null) {
            aboutUs = build_JbuttonForNulls(aboutUs, FloorOf_Time_Exit_X, ThirdButtons_Y);
            aboutUs.setText("Hakkımızda & Yardım");
            aboutUs.setBackground(Color.red);
        }
        return aboutUs;
    }

    public void setAboutUs(JButton aboutUs) {
        this.aboutUs = aboutUs;
    }

    public JButton getExit() {
        if (exit == null) {
            exit = build_JbuttonForNulls(exit, FloorOf_Time_Exit_X, FourthButtons_Y);
            exit.setText("Çıkış");
        }
        return exit;
    }

    public void setExit(JButton exit) {
        this.exit = exit;
    }

}
