package Gui;

import Logic.Actions_MainGui;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainGui extends JPanel {

    JFrame jf = null;

    JLabel studentNo_jlabel;
    JLabel bookId_jlabel;
    JLabel resultScreen_jlabel;
    JLabel bookName_label;
    JTextField StudentNo_txt;
    JTextField bookId_txt;
    JTextField resultScreen_txt;
    JTextField bookName_txt;
    JButton bookAdd;
    JButton bookReturn;
    JButton bookSearch_List;
    JButton bookUpdate_Remove;
    JButton studentAdd;
    JButton studentUpdate;
    JButton studentState;
    JButton registeredStudent;
    JButton TimeControl_ExtraTime;
    JButton Overtime_Fine;
    JButton aboutUs;
    JButton exit;
    final int holding_area_scale = 270;
    final int push_scale = 8;
    final int txt_height = 40;
    int push_right_lbl_counter = 0;
    int push_right_txt_counter = 0;

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
    Actions_MainGui action = new Actions_MainGui(this);

    public MainGui() {
    }

    public MainGui(Login login) {
        this.setLayout(null);
        this.jf = login.getJf();

        System.out.println("maingui" + getJf().getContentPane());
        setBounds(0, 0, getJf().getWidth(), getJf().getHeight());
        getJf().add(this);
        this.setVisible(true);
        addAllThingsONPanel();

        this.setBackground(Color.BLACK);

    }

    public void addAllThingsONPanel() {

        this.setVisible(true);
        this.add(getStudentNo_jlabel());
        this.add(getBookId_jlabel());
        this.add(getResultScreen_jlabel());
        this.add(getBookName_label());
        this.add(getStudentNo_txt());
        this.add(getBookId_txt());
        this.add(getResultScreen_txt());
        this.add(getBookName_txt());
        this.add(getBookAdd());
        this.add(getBookReturn());
        this.add(getBookSearch_List());
        this.add(getBookUpdate_Remove());
        this.add(getStudentAdd());
        this.add(getStudentUpdate());
        this.add(getStudentState());
        this.add(getRegisteredStudent());
        this.add(getTimeControl_ExtraTime());
        this.add(getOvertime_Fine());
        this.add(getAboutUs());
        this.add(getExit());

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

        if (whichFloor_X == FloorOf_Time_Exit_X && (sectorButtons_Y == FirstButtons_Y || sectorButtons_Y == SecondButtons_Y)) {
            jbtn.setFont(new Font("", Font.BOLD, 12));
        } else {
            jbtn.setFont(new Font("", Font.BOLD, 17));
        }
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

    public JLabel getBookId_jlabel() {
        if (bookId_jlabel == null) {
            bookId_jlabel = build_JlabelForNulls(bookId_jlabel);
            bookId_jlabel.setText("Kitap Barkod No");
        }
        return bookId_jlabel;
    }

    public void setBookId_jlabel(JLabel bookId_jlabel) {
        this.bookId_jlabel = bookId_jlabel;
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

    public JLabel getBookName_label() {
        if (bookName_label == null) {
            bookName_label = new JLabel("Kitap  Adı");
            bookName_label.setBounds(10, 90, 300, 70);
            bookName_label.setForeground(Color.WHITE);
            bookName_label.setFont(new Font("monospaced", Font.BOLD, 25));
        }
        return bookName_label;
    }

    public void setBookName_label(JLabel bookName_label) {
        this.bookName_label = bookName_label;
    }

    public JTextField getStudentNo_txt() {
        if (StudentNo_txt == null) {
            StudentNo_txt = build_JTextfiledForNulls(StudentNo_txt);
            StudentNo_txt.getDocument().addDocumentListener(action);
            StudentNo_txt.getDocument().putProperty("StudentNo_BookId_txt", 1); // first one is key , the other one is value
            StudentNo_txt.addActionListener(action);

        }
        return StudentNo_txt;
    }

    public void setStudentNo_txt(JTextField StudentNo_txt) {
        this.StudentNo_txt = StudentNo_txt;
    }

    public JTextField getBookId_txt() {
        if (bookId_txt == null) {
            bookId_txt = build_JTextfiledForNulls(bookId_txt);
            bookId_txt.getDocument().addDocumentListener(action);
            bookId_txt.getDocument().putProperty("StudentNo_BookId_txt", 2); // first one is key , the other one is value
            bookId_txt.addActionListener(action);
        }
        return bookId_txt;
    }

    public void setBookId_txt(JTextField bookId_txt) {
        this.bookId_txt = bookId_txt;
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
            bookName_txt.setBounds(3, 150, getJf().getWidth() - 24, 70);
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
            bookSearch_List.setText("Kitap Listesi&Sorgula");
        }
        return bookSearch_List;
    }

    public void setBookSearch_List(JButton bookSearch_List) {
        this.bookSearch_List = bookSearch_List;
    }

    public JButton getBookUpdate_Remove() {
        if (bookUpdate_Remove == null) {
            bookUpdate_Remove = build_JbuttonForNulls(bookUpdate_Remove, FloorOfBook_X, FourthButtons_Y);
            bookUpdate_Remove.setText("Kitap Guncelle&Sil");
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
            TimeControl_ExtraTime.setText("Süre Kontrol& kitap süre uzatma");
        }
        return TimeControl_ExtraTime;
    }

    public void setTimeControl_ExtraTime(JButton TimeControl_ExtraTime) {
        this.TimeControl_ExtraTime = TimeControl_ExtraTime;
    }

    public JButton getOvertime_Fine() {
        if (Overtime_Fine == null) {
            Overtime_Fine = build_JbuttonForNulls(Overtime_Fine, FloorOf_Time_Exit_X, SecondButtons_Y);
            Overtime_Fine.setText("Süreyi aşanlar&para cezası");
        }
        return Overtime_Fine;
    }

    public void setOvertime_Fine(JButton Overtime_Fine) {
        this.Overtime_Fine = Overtime_Fine;
    }

    public JButton getAboutUs() {
        if (aboutUs == null) {
            aboutUs = build_JbuttonForNulls(aboutUs, FloorOf_Time_Exit_X, ThirdButtons_Y);
            aboutUs.setText("Hakkımızda");
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
