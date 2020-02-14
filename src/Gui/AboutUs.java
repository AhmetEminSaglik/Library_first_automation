package Gui;

import Logic.ActionTimeFine;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AboutUs {

    MainGui mg;
    JFrame jf;
    JPanel jp;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final double screenSizeWidth = screenSize.getWidth();
    final double screenSizeHeight = screenSize.getHeight();

    int firstColor = new Random().nextInt(255);
    int secondColor = new Random().nextInt(255);
    int thirdColor = new Random().nextInt(255);

    JTextField txtOldUsername;
    JTextField txtNewUsername;
    JPasswordField txtOldPassword;
    JTextField txtResult;
    JPasswordField txtNewPassword1;
    JPasswordField txtNewPassword2;
    JTextArea txtAreaAboutMe;
    JLabel lblOldUsername;
    JLabel lblNewUsername;
    JLabel lblOldPassword;
    JLabel lblNewPassword1;
    JLabel lblNewPassword2;
    JLabel lblResult;
    JButton btnChangePassword;
    JButton btnComeBack;
    ActionTimeFine action = new ActionTimeFine(this);
    public boolean stopChangeBackground = false;
    boolean firstCountUp = true;
    boolean SecondCountUp = true;
    boolean ThirdCountUp = true;

    final int txtHeight = 25;
    final int txtWidth = 180;
    final int LeftSpace = 220;
    final int txtTopSpace = 370;
    final int lblHeight = 20;
    final int lblWidth = txtWidth;
    final int lblTopSpace = 350;
    /*txt --> 25 
    lbl 10 --> 10   = 30
     
     */
    int txtpushRight = 0;
    int txtpushUnder = 0;
    int lblpushRight = 0;
    int lblpushUnder = 0;

    int Counter = 30;

    Font txtFont = new Font("", Font.ITALIC, 17);

    Font btnFont = new Font("monospaced", Font.BOLD, (int) (screenSizeWidth / 62.09));
    Font lblFont = new Font("", 0, (int) (screenSizeWidth / 75.88));

    public String FillTextArea() {
        return "    2. Sınıf öğrencisiyken gönüllü  olarak , \n"
                + "Öncelikle kütüphanemizdeki çalışan arkadaşlara ve \n"
                + "kitap kontrolü için dekanımıza  sonrasında da \n"
                + "diğer tüm arkadaşların işlerini kolaylaştırmak için   \n"
                + "bu  otomasyonu    13.02. 2020    tarihinde   \n"
                + "Of Teknoloji Fakültesindeki kütüphanemize \n"
                + "hediye ediyorum.   \n"
                + "                    Ahmet Emin SAĞLIK 385931 \n"
                + "                        13.02.2020";
    }

    public AboutUs(MainGui mg) {
        setMg(mg);
        setJf(mg.getJf());
        getJf().add(getJp());
        getJf().setTitle("HAKKIMIZDA");
        getTxtAreaAboutMe().setBackground(new Color(firstColor, secondColor, thirdColor));
        getJp().add(getTxtAreaAboutMe());
        getJp().add(getBtnComeBack());
        getBtnComeBack().addActionListener(action);
        changeBackgroundColorOfPanel();

        getTxtAreaAboutMe().setText(FillTextArea());
        getTxtAreaAboutMe().setFont(btnFont);
        getJp().add(getTxtOldUsername());
        getJp().add(getTxtNewUsername());
        getJp().add(getTxtResult());
        getJp().add(getTxtOldPassword());
        getJp().add(getTxtNewPassword1());
        getJp().add(getTxtNewPassword2());
        getJp().add(getBtnChangePassword());
        getJp().add(getLblOldUsername());
        getJp().add(getLblNewUsername());
        getJp().add(getLblResult());

        getJp().add(getLblOldPassword());
        getJp().add(getLblNewPassword1());
        getJp().add(getLblNewPassword2());

        getBtnChangePassword().addActionListener(action);
        getTxtOldUsername().addActionListener(action);
        getTxtNewUsername().addActionListener(action);
        getTxtOldPassword().addActionListener(action);
        getTxtNewPassword1().addActionListener(action);
        getTxtNewPassword2().addActionListener(action);
        getTxtOldUsername().addFocusListener(action);
        getTxtNewUsername().addFocusListener(action);
        getTxtOldPassword().addFocusListener(action);
        getTxtNewPassword1().addFocusListener(action);
        getTxtNewPassword2().addFocusListener(action);

    }

    public void ChangeBackground(int colorIndex, int Color) {
        try {
            Thread.sleep(50);
            if (colorIndex == 0) {
                firstColor = Color;
            } else if (colorIndex == 1) {
                secondColor = Color;
            } else {
                thirdColor = Color;
            }

            getJp().setBackground(new Color(firstColor, secondColor, thirdColor));
            getTxtAreaAboutMe().setBackground(new Color(firstColor, secondColor, thirdColor));

            if (firstColor + secondColor + thirdColor < 100) {
                getTxtAreaAboutMe().setForeground(new Color(255, 255, 255));
            } else {
                getTxtAreaAboutMe().setForeground(new Color(0, 0, 0));
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(AboutUs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ChangeColor2(int colorIndex, int startPoint, boolean countUp) {
        if (Counter > 0) {
            if (startPoint == 255) {
                countUp = false;

            } else if (startPoint == 0) {
                countUp = true;
            }
            if (colorIndex == 0) {
                firstCountUp = countUp;
            } else if (colorIndex == 1) {
                SecondCountUp = countUp;
            } else {
                ThirdCountUp = countUp;
            }
            if (countUp == true) {

                ChangeBackground(colorIndex, ++startPoint);

            } else {
                ChangeBackground(colorIndex, --startPoint);

            }
            Counter--;
            ChangeColor2(colorIndex, startPoint, countUp);

        } else {
            Counter = new Random().nextInt(25) + 25;

            return;
        }
    }

    public void changeBackgroundColorOfPanel() {
        Thread t1 = new Thread() {
            public void run() {
                Random random = new Random();
                try {
                    while (true) {
                        Thread.sleep(10);

                        int randomNumber = random.nextInt(3);
                        if (randomNumber == 0) {

                            ChangeColor2(0, firstColor, firstCountUp);

                        } else if (randomNumber == 1) {
                            ChangeColor2(1, secondColor, SecondCountUp);   // changePanelBackGround(1, secondColor, SecondCountDown);
                        } else {
                            ChangeColor2(2, thirdColor, ThirdCountUp);   //  changePanelBackGround(2, thirdColor, ThirdCountDown);

                        }

                    }

                } catch (InterruptedException ex) {

                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        };
        t1.start();
    }

    public JFrame getJf() {
        if (jf == null) {
            jf = new JFrame();
        }
        double jframeWidth = screenSize.getWidth() / 1.6070588235294119;
        double jframeHeight = screenSize.getHeight() / 1.3963636363636365;
        int jframeX = (int) ((screenSize.getWidth() - jframeWidth) / 2);
        int jframeY = (int) ((screenSize.getHeight() - jframeHeight) / 2);
        jf.setBounds(jframeX, jframeY, (int) jframeWidth, (int) jframeHeight);

        return jf;
    }

    public void setJf(JFrame jf) {

        this.jf = jf;
    }

    public JPanel getJp() {
        if (jp == null) {
            jp = new JPanel();
            jp.setBounds(getJf().getBounds());
            jp.setBackground(new Color(firstColor, secondColor, thirdColor));
            jp.setLayout(null);
        }
        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

    public JTextArea getTxtAreaAboutMe() {
        if (txtAreaAboutMe == null) {
            txtAreaAboutMe = new JTextArea();
            txtAreaAboutMe.setFocusable(false);
            txtAreaAboutMe.setFont(new Font("", Font.BOLD, (int) (screenSizeWidth / 75.88)));
            txtAreaAboutMe.setBounds((int) (screenSizeWidth / 13.66), (int) (screenSizeHeight / 15.36),
                    (int) (screenSizeWidth / 1.9514285714285715), (int) (screenSizeHeight / 2.56));
        }
        return txtAreaAboutMe;
    }

    public void setTxtAreaAboutMe(JTextArea txtAreaAboutMe) {
        this.txtAreaAboutMe = txtAreaAboutMe;
    }

    public JButton getBtnComeBack() {
        if (btnComeBack == null) {
            btnComeBack = new JButton("Geri Dön");
            btnComeBack.setFont(btnFont);
            btnComeBack.setBounds((int) (screenSizeWidth / 27.32), (int) (screenSizeHeight / 1.7066666666666668),
                    (int) (screenSizeWidth / 9.106666666666667), (int) (screenSizeHeight / 21.942857142857143));

        }
        return btnComeBack;
    }

    public void setBtnComeBack(JButton btnComeBack) {
        this.btnComeBack = btnComeBack;
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

    public JTextField getTxtOldUsername() {

        if (txtOldUsername == null) {
            txtOldUsername = new JTextField("");
            txtOldUsername.setBounds(LeftSpace + (txtpushRight * (txtWidth + 10)),
                    txtTopSpace + (txtpushUnder * (txtHeight + 5 + lblHeight)),
                    txtWidth, txtHeight);
            txtOldUsername.setForeground(Color.BLACK);
            txtOldUsername.setFont(lblFont);
            txtOldUsername.setVisible(false);
            txtpushRight++;
        }
        return txtOldUsername;
    }

    public void setTxtOldUsername(JTextField txtOldUsername) {
        this.txtOldUsername = txtOldUsername;
    }

    public JTextField getTxtNewUsername() {
        if (txtNewUsername == null) {
            txtNewUsername = new JTextField("");
            txtNewUsername.setBounds(LeftSpace + (txtpushRight * (txtWidth + 10)),
                    txtTopSpace + (txtpushUnder * (txtHeight + 5 + lblHeight)),
                    txtWidth, txtHeight);

            txtNewUsername.setForeground(Color.BLACK);
            txtNewUsername.setFont(lblFont);
            txtNewUsername.setVisible(false);
            txtpushRight++;

        }
        return txtNewUsername;
    }

    public void setTxtNewUsername(JTextField txtNewUsername) {
        this.txtNewUsername = txtNewUsername;
    }

    public JTextField getTxtResult() {
        if (txtResult == null) {
            txtResult = new JTextField("");

            txtResult.setBounds(LeftSpace + (txtpushRight * (txtWidth + 10)),
                    txtTopSpace + (txtpushUnder * (txtHeight + 5 + lblHeight)),
                    txtWidth, txtHeight);
            txtResult.setBackground(new Color(206, 214, 224));
            txtResult.setForeground(Color.BLACK);
            txtResult.setFont(lblFont);
            txtResult.setVisible(false);
            txtpushUnder++;
            txtpushRight -= 2;
            txtResult.setEditable(false);
            txtResult.setFocusable(false);

        }
        return txtResult;
    }

    public void setTxtResult(JTextField txtResult) {
        this.txtResult = txtResult;
    }

    public JPasswordField getTxtOldPassword() {

        if (txtOldPassword == null) {
            txtOldPassword = new JPasswordField("");
            txtOldPassword.setBounds(LeftSpace + (txtpushRight * (txtWidth + 10)),
                    txtTopSpace + (txtpushUnder * (txtHeight + 5 + lblHeight)),
                    txtWidth, txtHeight);

            txtOldPassword.setForeground(Color.BLACK);
            txtOldPassword.setFont(lblFont);
            txtOldPassword.setVisible(false);
            txtpushRight++;
            System.out.println(txtOldUsername.getBounds());
        }
        return txtOldPassword;
    }

    public void setTxtOldPassword(JPasswordField txtOldPassword) {
        this.txtOldPassword = txtOldPassword;
    }

    public JPasswordField getTxtNewPassword1() {
        if (txtNewPassword1 == null) {
            txtNewPassword1 = new JPasswordField("");
            txtNewPassword1.setBounds(LeftSpace + (txtpushRight * (txtWidth + 10)),
                    txtTopSpace + (txtpushUnder * (txtHeight + 5 + lblHeight)),
                    txtWidth, txtHeight);

            txtNewPassword1.setForeground(Color.BLACK);
            txtNewPassword1.setFont(lblFont);
            txtNewPassword1.setVisible(false);
            txtpushRight++;

        }
        return txtNewPassword1;
    }

    public void setTxtNewPassword1(JPasswordField txtNewPassword1) {
        this.txtNewPassword1 = txtNewPassword1;
    }

    public JPasswordField getTxtNewPassword2() {
        if (txtNewPassword2 == null) {

            txtNewPassword2 = new JPasswordField("");
            txtNewPassword2.setBounds(LeftSpace + (txtpushRight * (txtWidth + 10)),
                    txtTopSpace + (txtpushUnder * (txtHeight + 5 + lblHeight)),
                    txtWidth, txtHeight);

            txtNewPassword2.setForeground(Color.BLACK);
            txtNewPassword2.setFont(lblFont);
            txtNewPassword2.setVisible(false);
            txtpushUnder++;
        }
        return txtNewPassword2;
    }

    public void setTxtNewPassword2(JPasswordField txtNewPassword2) {
        this.txtNewPassword2 = txtNewPassword2;
    }

    public JButton getBtnChangePassword() {
        if (btnChangePassword == null) {
            btnChangePassword = new JButton("Giriş bilgilerini değiştir");
            btnChangePassword.setBounds(LeftSpace + txtWidth / 2,
                    txtTopSpace + (txtpushUnder * (txtHeight + 5)) + lblHeight,
                    (txtWidth * 5 / 2) - 45, (int) (screenSizeHeight / 21.942857142857143));
            btnChangePassword.setBackground(new Color(238, 238, 238));
            btnChangePassword.setFont(btnFont);

            System.out.println(txtWidth * 5 / 2);
            txtpushRight++;
        }
        return btnChangePassword;
    }

    public void setBtnChangePassword(JButton btnChangePassword) {
        this.btnChangePassword = btnChangePassword;
    }

    public JLabel getLblOldUsername() {

        if (lblOldUsername == null) {
            lblOldUsername = new JLabel("Şuanki Kullanıcı Adı");
            lblOldUsername.setBounds(LeftSpace + (lblpushRight * (lblWidth + 10)),
                    lblTopSpace + (lblpushUnder * (lblHeight + 5)),
                    lblWidth, lblHeight);
            lblOldUsername.setForeground(Color.BLACK);
            lblOldUsername.setFont(lblFont);
            lblOldUsername.setVisible(false);
            System.out.println(lblOldUsername.getBounds());

            lblpushRight++;
        }
        return lblOldUsername;
    }

    public void setLblOldUsername(JLabel lblOldUsername) {
        this.lblOldUsername = lblOldUsername;
    }

    public JLabel getLblNewUsername() {
        if (lblNewUsername == null) {
            lblNewUsername = new JLabel("Yeni Kullanıcı Adı");
            lblNewUsername.setBounds(LeftSpace + (lblpushRight * (lblWidth + 10)),
                    lblTopSpace + (lblpushUnder * (lblHeight + 5)),
                    lblWidth, lblHeight);
            lblNewUsername.setForeground(Color.BLACK);
            lblNewUsername.setFont(lblFont);
            lblNewUsername.setVisible(false);
            lblpushRight++;
        }
        return lblNewUsername;
    }

    public void setLblNewUsername(JLabel lblNewUsername) {
        this.lblNewUsername = lblNewUsername;
    }

    public JLabel getLblResult() {
        if (lblResult == null) {
            lblResult = new JLabel("SONUÇ ");
            lblResult.setBounds(LeftSpace + (lblpushRight * (lblWidth + 10)),
                    lblTopSpace + (lblpushUnder * (lblHeight + 5)),
                    lblWidth, lblHeight);
            lblResult.setForeground(Color.BLACK);
            lblResult.setFont(lblFont);
            lblResult.setVisible(false);
            lblpushRight -= 2;
            lblpushUnder++;

        }
        return lblResult;
    }

    public void setLblResult(JLabel lblResult) {
        this.lblResult = lblResult;
    }

    public JLabel getLblOldPassword() {
        if (lblOldPassword == null) {
            lblOldPassword = new JLabel("Şuanki Parolanız");
            lblOldPassword.setBounds(LeftSpace + (lblpushRight * (lblWidth + 10)),
                    lblTopSpace + (lblpushUnder * (lblHeight + 5 + txtHeight)),
                    lblWidth, lblHeight);
            lblOldPassword.setForeground(Color.BLACK);
            lblOldPassword.setFont(lblFont);
            lblOldPassword.setVisible(false);
            lblpushRight++;

        }
        return lblOldPassword;
    }

    public void setLblOldPassword(JLabel lblOldPassword) {
        this.lblOldPassword = lblOldPassword;
    }

    public JLabel getLblNewPassword1() {
        if (lblNewPassword1 == null) {
            lblNewPassword1 = new JLabel("Yeni  Şifre");
            lblNewPassword1.setBounds(LeftSpace + (lblpushRight * (lblWidth + 10)),
                    lblTopSpace + (lblpushUnder * (lblHeight + 5 + txtHeight)),
                    lblWidth, lblHeight);
            lblNewPassword1.setForeground(Color.BLACK);
            lblNewPassword1.setFont(lblFont);
            lblNewPassword1.setVisible(false);
            lblpushRight++;

        }
        return lblNewPassword1;
    }

    public void setLblNewPassword1(JLabel lblNewPassword1) {
        this.lblNewPassword1 = lblNewPassword1;
    }

    public JLabel getLblNewPassword2() {
        if (lblNewPassword2 == null) {
            lblNewPassword2 = new JLabel("Şifre Tekrarı");
            lblNewPassword2.setBounds(LeftSpace + (lblpushRight * (lblWidth + 10)),
                    lblTopSpace + (lblpushUnder * (lblHeight + 5 + txtHeight)),
                    lblWidth, lblHeight);
            lblNewPassword2.setForeground(Color.BLACK);
            lblNewPassword2.setFont(lblFont);
            lblNewPassword2.setVisible(false);
            lblpushRight++;

        }
        return lblNewPassword2;
    }

    public void setLblNewPassword2(JLabel lblNewPassword2) {
        this.lblNewPassword2 = lblNewPassword2;
    }

}
