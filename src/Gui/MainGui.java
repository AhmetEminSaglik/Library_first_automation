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
import javax.swing.ToolTipManager;

public class MainGui {//extends JPanel

    JFrame jf = null;
    JPanel jp = null;

    JLabel lblStudentNo;
    JLabel lblBookBarcode;
    JLabel lblResultScreen;
    JLabel lblBookName;

    JLabel lblTotalBook;// lblTotalBook,lblRemainBook,lblGivenBook are different label not like aboves
    JLabel lblRemainBook;
    JLabel lblGivenBook;

    JTextField txtStudentNo;
    JTextField txtBookBarcode;
    JTextField txtResultScreen;
    JTextField txtBookName;

    JTextField txtTotalBook;
    JTextField txtGivenBook;//txtGivenBook
    JTextField txtRemainBook;//txtRemainBook

    JButton bookAdd;
    JButton bookReturn;
    JButton bookSearchList;
    JButton bookUpdateRemove;
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
    final ToolTipManager ttm = ToolTipManager.sharedInstance();
    final int oldDelay = ttm.getInitialDelay();

    public ActionsMainGui action = new ActionsMainGui(this);

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

        getJp().setBackground(Color.black);

    }

    public void addAllThingsONPanel() {

        getJp().setVisible(true);
        getJp().add(getLblStudentNo());
        getJp().add(getLblBookBarcode());
        getJp().add(getLblResultScreen());
        getJp().add(getLblBookName());
        getJp().add(getLblRemainBook());
        getJp().add(getLblGivenBook());
        getJp().add(getLblTotalBook());
        getJp().add(getTxtGivenBook());
        getJp().add(getTxtRemainBook());
        getJp().add(getTxtTotalBook());
        getJp().add(gettxtStudentNo());
        getJp().add(getTxtBookBarcode());
        getJp().add(gettxtResultScreen());
        getJp().add(getTxtBookName());
        getJp().add(getBookAdd());
        getJp().add(getBookReturn());
        getJp().add(getBookSearchList());
        getJp().add(getBookUpdateRemove());
        getJp().add(getStudentAdd());
        getJp().add(getStudentUpdate());
        getJp().add(getStudentState());
        getJp().add(getRegisteredStudent());
        getJp().add(getTimeControlExtraTime());
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
            jp.setLayout(null);
        }
        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

    public void setJf(JFrame Jf) {
        this.jf = Jf;
    }

    public JLabel getLblStudentNo() {
        if (lblStudentNo == null) {
            lblStudentNo = build_JlabelForNulls(lblStudentNo);
            lblStudentNo.setText("Öğrenci No ");

        }
        return lblStudentNo;
    }

    public void setLblStudentNo(JLabel lblStudentNo) {
        this.lblStudentNo = lblStudentNo;
    }

    public JLabel getLblBookBarcode() {
        if (lblBookBarcode == null) {
            lblBookBarcode = build_JlabelForNulls(lblBookBarcode);
            lblBookBarcode.setText("Kitap Barkod No");
        }
        return lblBookBarcode;
    }

    public void setLblBookBarcode(JLabel lblBookBarcode) {
        this.lblBookBarcode = lblBookBarcode;
    }

    public JLabel getLblResultScreen() {
        if (lblResultScreen == null) {
            lblResultScreen = build_JlabelForNulls(lblResultScreen);
            lblResultScreen.setText("Sonuç ");

        }
        return lblResultScreen;
    }

    public void setLblResultScreen(JLabel lblResultScreen) {
        this.lblResultScreen = lblResultScreen;
    }

    public JLabel getLblBookName() {
        if (lblBookName == null) {
            lblBookName = new JLabel("Kitap  Adı");
            lblBookName.setBounds(10, 135, 300, 70);
            lblBookName.setForeground(Color.WHITE);
            lblBookName.setFont(new Font("monospaced", Font.BOLD, 25));
        }
        return lblBookName;
    }

    public void setLblBookName(JLabel lblBookName) {
        this.lblBookName = lblBookName;
    }

    public JLabel getLblTotalBook() {
        if (lblTotalBook == null) {
            lblTotalBook = new JLabel("Toplam Kitap Sayısı");
            lblTotalBook.setBounds((total_remain_given_books_x + holding_area_scale) * total_remain_given_books_counter,
                    total_remain_given_books_y, holding_area_scale,
                    50);
            lblTotalBook.setFont(new Font("monospaced", Font.BOLD, 22));
            lblTotalBook.setForeground(new Color(116, 185, 255));
            total_remain_given_books_counter = 0;
        }
        /*    final int push_total_remain_given_books = 30;
              int total_remain_given_books_x = 30;
         */
        return lblTotalBook;
    }

    public void setLblTotalBook(JLabel lblTotalBook) {
        this.lblTotalBook = lblTotalBook;
    }

    public JLabel getLblRemainBook() {
        if (lblRemainBook == null) {
            lblRemainBook = new JLabel("Verilen Kitap Sayısı");
            lblRemainBook.setBounds(10 + (total_remain_given_books_x + holding_area_scale) * total_remain_given_books_counter,
                    total_remain_given_books_y, holding_area_scale, 50);
            lblRemainBook.setFont(new Font("monospaced", Font.BOLD, 22));
            lblRemainBook.setForeground(Color.red);
            total_remain_given_books_counter++;
        }
        return lblRemainBook;
    }

    public void setLblRemainBook(JLabel lblRemainBook) {
        this.lblRemainBook = lblRemainBook;
    }

    public JLabel getLblGivenBook() {
        if (lblGivenBook == null) {
            lblGivenBook = new JLabel("Kalan Kitap Sayısı");
            lblGivenBook.setBounds(15 + (total_remain_given_books_x + holding_area_scale) * total_remain_given_books_counter,
                    1 + total_remain_given_books_y, holding_area_scale, 50);
            lblGivenBook.setFont(new Font("monospaced", Font.BOLD, 22));
            lblGivenBook.setForeground(Color.GREEN);
            total_remain_given_books_counter++;
        }
        return lblGivenBook;
    }

    public void setLblGivenBook(JLabel lblGivenBook) {
        this.lblGivenBook = lblGivenBook;
    }

    public JTextField gettxtStudentNo() {
        if (txtStudentNo == null) {
            txtStudentNo = build_JTextfiledForNulls(txtStudentNo);
            //   txtStudentNo.getDocument().addDocumentListener(action);
            //    txtStudentNo.getDocument().putProperty("StudentNoBookBarcodetxt", 1); // first one is key , the other one is value
            txtStudentNo.addActionListener(action);
            txtStudentNo.addFocusListener(action);
            txtStudentNo.setForeground(Color.GRAY);
            txtStudentNo.setText("Öğrenci No Girin");

//txtStudentNo.set
        }
        return txtStudentNo;
    }

    public void settxtStudentNo(JTextField txtStudentNo) {
        this.txtStudentNo = txtStudentNo;
    }

    public JTextField getTxtBookBarcode() {
        if (txtBookBarcode == null) {
            txtBookBarcode = build_JTextfiledForNulls(txtBookBarcode);
            //  txtBookBarcode.getDocument().addDocumentListener(action);
            //txtBookBarcode.getDocument().putProperty("StudentNoBookBarcodetxt", 2); // first one is key , the other one is value
            txtBookBarcode.addActionListener(action);
            txtBookBarcode.addFocusListener(action);
            txtBookBarcode.setForeground(Color.GRAY);
            txtBookBarcode.setText("Kitap Barkod No girin");

        }
        return txtBookBarcode;
    }

    public void setTxtBookBarcode(JTextField txtBookBarcode) {
        this.txtBookBarcode = txtBookBarcode;
    }

    public JTextField gettxtResultScreen() {
        if (txtResultScreen == null) {
            txtResultScreen = build_JTextfiledForNulls(txtResultScreen);
            txtResultScreen.setEditable(false);
            txtResultScreen.setToolTipText("Buraya Erişemezsiniz");
            ttm.setInitialDelay(100); // when entered  wait time
            ttm.setDismissDelay(1000);// seeing time tooltips
            txtResultScreen.setBackground(new Color(206, 214, 224));
        }
        return txtResultScreen;
    }

    public void settxtResultScreen(JTextField txtResultScreen) {
        this.txtResultScreen = txtResultScreen;
    }

    public JTextField getTxtBookName() {
        if (txtBookName == null) {
            txtBookName = new JTextField("");
            txtBookName.setBounds(3, 190, getJf().getWidth() - 24, 55);
            txtBookName.setFont(new Font("monospaced", Font.BOLD, 19));
            txtBookName.setEditable(false);
            txtBookName.setFocusable(false);
            // txtBookName.setEnabled(false);
            txtBookName.setBackground(new Color(206, 214, 224));

            ttm.setInitialDelay(100); // when entered  wait time
            ttm.setDismissDelay(1000);// seeing time tooltips
            txtBookName.setToolTipText("Buraya Erişemezsiniz");

        }
        return txtBookName;
    }

    public void setTxtBookName(JTextField txtBookName) {
        this.txtBookName = txtBookName;
    }

    public JTextField getTxtTotalBook() {
        if (txtTotalBook == null) {
            txtTotalBook = new JTextField("0");
            txtTotalBook.setBounds(50 + (total_remain_given_books_x + holding_area_scale) * total_remain_given_books_counter,
                    total_remain_given_books_y + 45, 150, 30);
            txtTotalBook.setFont(new Font("", Font.BOLD, 22));
            txtTotalBook.setEditable(false);
            txtTotalBook.setFocusable(false);
            txtTotalBook.setBackground(new Color(116, 185, 255));
            txtTotalBook.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
            txtTotalBook.setBorder(BorderFactory.createCompoundBorder(txtGivenBook.getBorder(), BorderFactory.createEmptyBorder(0, 20, 0, 0)));

            txtTotalBook.addActionListener(action);
            total_remain_given_books_counter++;


            /*  lblTotalBook.setBounds((total_remain_given_books_x + holding_area_scale) * total_remain_given_books_counter,
                    total_remain_given_books_y, holding_area_scale,
                    50);*/
        }
        return txtTotalBook;
    }

    public void setTxtTotalBook(JTextField txtTotalBook) {
        this.txtTotalBook = txtTotalBook;
    }

    public JTextField getTxtGivenBook() {
        if (txtGivenBook == null) {
            txtGivenBook = new JTextField("0");
            txtGivenBook.setBounds(50 + (total_remain_given_books_x + holding_area_scale) * total_remain_given_books_counter,
                    total_remain_given_books_y + 45, 150, 30);
            txtGivenBook.setFont(new Font("", Font.BOLD, 22));
            txtGivenBook.setEditable(false);
            txtGivenBook.setFocusable(false);
            txtGivenBook.setBackground(Color.red);
            txtGivenBook.setForeground(Color.BLACK);
            total_remain_given_books_counter++;
            txtGivenBook.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
            txtGivenBook.setBorder(BorderFactory.createCompoundBorder(txtGivenBook.getBorder(), BorderFactory.createEmptyBorder(0, 50, 0, 0)));

            /*  lblTotalBook.setBounds((total_remain_given_books_x + holding_area_scale) * total_remain_given_books_counter,
                    total_remain_given_books_y, holding_area_scale,
                    50);*/
            txtGivenBook.addActionListener(action);
        }
        return txtGivenBook;
    }

    public void setTxtGivenBook(JTextField txtGivenBook) {

        this.txtGivenBook = txtGivenBook;
    }

    public JTextField getTxtRemainBook() {
        if (txtRemainBook == null) {
            txtRemainBook = new JTextField("0");
            txtRemainBook.setBounds(50 + (total_remain_given_books_x + holding_area_scale) * total_remain_given_books_counter,
                    total_remain_given_books_y + 45, 150, 30);
            txtRemainBook.setBackground(Color.GREEN);
            txtRemainBook.setForeground(Color.BLACK);
            txtRemainBook.setFont(new Font("", Font.BOLD, 22));
            txtRemainBook.setEditable(false);
            txtRemainBook.setFocusable(false);
            txtRemainBook.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
            txtRemainBook.setBorder(BorderFactory.createCompoundBorder(txtGivenBook.getBorder(), BorderFactory.createEmptyBorder(0, 10, 0, 0)));
            txtRemainBook.addActionListener(action);
            total_remain_given_books_counter++;
            /*   lblRemainBook.setBounds(10 + (total_remain_given_books_x + holding_area_scale) * total_remain_given_books_counter,
                    total_remain_given_books_y, holding_area_scale, 50);*/

        }
        return txtRemainBook;
    }

    public void setTxtRemainBook(JTextField txtRemainBook) {
        this.txtRemainBook = txtRemainBook;
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

    public JButton getBookSearchList() {
        if (bookSearchList == null) {
            bookSearchList = build_JbuttonForNulls(bookSearchList, FloorOfBook_X, ThirdButtons_Y);
            bookSearchList.setText("Kitap Listesi & Sorgula");

        }
        return bookSearchList;
    }

    public void setBookSearchList(JButton bookSearchList) {
        this.bookSearchList = bookSearchList;
    }

    public JButton getBookUpdateRemove() {
        if (bookUpdateRemove == null) {
            bookUpdateRemove = build_JbuttonForNulls(bookUpdateRemove, FloorOfBook_X, FourthButtons_Y);
            bookUpdateRemove.setText("Kitap Güncelle & Sil");
        }

        return bookUpdateRemove;
    }

    public void setBookUpdateRemove(JButton bookUpdateRemove) {
        this.bookUpdateRemove = bookUpdateRemove;
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
            studentUpdate.setText("Ögrenci Güncelle & Sil");
        }
        return studentUpdate;
    }

    public void setStudentUpdate(JButton studentUpdate) {
        this.studentUpdate = studentUpdate;
    }

    public JButton getStudentState() {
        if (studentState == null) {
            studentState = build_JbuttonForNulls(studentState, FloorOfStudent_X, ThirdButtons_Y);
            studentState.setText("Ögrenci Durumu");
        }

        return studentState;
    }

    public void setStudentState(JButton studentState) {
        this.studentState = studentState;
    }

    public JButton getRegisteredStudent() {
        if (registeredStudent == null) {
            registeredStudent = build_JbuttonForNulls(registeredStudent, FloorOfStudent_X, FourthButtons_Y);
            registeredStudent.setText("Kayıtlı Ögrenci listesi");

        }
        return registeredStudent;
    }

    public void setRegisteredStudent(JButton registeredStudent) {
        this.registeredStudent = registeredStudent;
    }

    public JButton getTimeControlExtraTime() {
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
            // aboutUs.setBackground(Color.red);
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
