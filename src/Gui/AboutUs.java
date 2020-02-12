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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
    JTextArea txtAreaAboutMe;
    JButton btnComeBack;
    ActionTimeFine action = new ActionTimeFine(this);
    public boolean stopChangeBackground = false;
    boolean firstCountUp = true;
    boolean SecondCountUp = true;
    boolean ThirdCountUp = true;

    int Counter = 30;

    public AboutUs(MainGui mg) {
        setMg(mg);
        setJf(mg.getJf());
        getJf().add(getJp());
        getJf().setTitle("HAKKIMIZDA & YARDIM");
        getTxtAreaAboutMe().setBackground(new Color(firstColor, secondColor, thirdColor));
        getJp().add(getTxtAreaAboutMe());
        getJp().add(getBtnComeBack());
        getBtnComeBack().addActionListener(action);
        changeBackgroundColorOfPanel();

    }

    public void ChangeBackground(int colorIndex, int Color) {
        try {
            Thread.sleep(25);
            if (colorIndex == 0) {
                firstColor = Color;
            } else if (colorIndex == 1) {
                secondColor = Color;
            } else {
                thirdColor = Color;
            }

            getJp().setBackground(new Color(firstColor, secondColor, thirdColor));
            getTxtAreaAboutMe().setBackground(new Color(firstColor, secondColor, thirdColor));

            if (firstColor + secondColor + thirdColor < 350) {
                getTxtAreaAboutMe().setForeground(new Color(255, 255, 255));
            } else {
                getTxtAreaAboutMe().setForeground(new Color(0, 0, 0));
            }
            //getJp().setForeground(new Color(255 - firstColor, 255 - secondColor, 255 - thirdColor));
            //getTxtAreaAboutMe().setForeground(new Color(255 - firstColor, 255 - secondColor, 255 - thirdColor));
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
            //txtAreaAboutMe.setBackground(new Color(firstColor, secondColor, thirdColor));
            txtAreaAboutMe.setFont(new Font("", Font.BOLD, 18));
            txtAreaAboutMe.setBounds((int) (screenSizeWidth / 13.66), (int) (screenSizeHeight / 15.36),
                    (int) (screenSizeWidth / 2.1015384615384614), (int) (screenSizeHeight / 2.56));

            txtAreaAboutMe.setText("Burası doldurulacak...");

        }
        return txtAreaAboutMe;
    }

    public void setTxtAreaAboutMe(JTextArea txtAreaAboutMe) {
        this.txtAreaAboutMe = txtAreaAboutMe;
    }

    public JButton getBtnComeBack() {
        if (btnComeBack == null) {
            btnComeBack = new JButton("Geri Dön");
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

}
