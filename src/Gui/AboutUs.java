package Gui;

import Logic.ActionTimeFine;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AboutUs {

    MainGui mg;
    JFrame jf;
    JPanel jp;
    int firstColor = 150;
    int secondColor = 0;
    int thirdColor = 150;
    JTextArea txtAreaAboutMe;
    JButton btnComeBack;
    ActionTimeFine action = new ActionTimeFine(this);
    public boolean stopChangeBackground = false;

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

    public void changePanelBackGround(int colorIndex, int whichColor) { // colorIndex determines whichcolor it is
        if (stopChangeBackground) {
            return;
        }
        int maxNumber = whichColor + 30;

        System.out.println(" whichColor  : " + whichColor + " <-> maxNumber : " + maxNumber);

        for (int i = whichColor; i < maxNumber; i++) {
            System.out.println(">>>>>>>>>>>>>>>>>> FOR İÇİNDE >>>>>>>>>>>>>>>> whichColor  : " + whichColor + " <-> maxNumber : " + maxNumber);
            if (i > 255) {
                i = 0;

                maxNumber = maxNumber - 255;

            }
            try {
                Thread.sleep(25);
                if (colorIndex == 0) {
                    firstColor = i;
                } else if (colorIndex == 1) {
                    secondColor = i;
                } else {
                    thirdColor = i;
                }

                getJp().setBackground(new Color(firstColor, secondColor, thirdColor));
                getTxtAreaAboutMe().setBackground(new Color(firstColor, secondColor, thirdColor));
            } catch (InterruptedException ex) {
                Logger.getLogger(AboutUs.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void changeBackgroundColorOfPanel() {
        Thread t1 = new Thread() {
            public void run() {
                Random random = new Random();
                try {
                    while (true) {
                        Thread.sleep(10);
                        /* firstColor = random.nextInt(255);
                        secondColor = random.nextInt(255);
                        thirdColor = random.nextInt(255);*/
                        int randomNumber = random.nextInt(3);
                        if (randomNumber == 0) {
                            changePanelBackGround(0, firstColor);

                        } else if (randomNumber == 1) {
                            changePanelBackGround(1, secondColor);
                        } else {
                            changePanelBackGround(2, thirdColor);
                        }

                    }

                } catch (InterruptedException ex) {
                    System.out.println("hata kodu  : " + ex);
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
            txtAreaAboutMe.setBounds(100, 50, 650, 300);

            txtAreaAboutMe.setText("1daogfjaodıfoıagfaoıfasdfoıasfıosadf q");

        }
        return txtAreaAboutMe;
    }

    public void setTxtAreaAboutMe(JTextArea txtAreaAboutMe) {
        this.txtAreaAboutMe = txtAreaAboutMe;
    }

    public JButton getBtnComeBack() {
        if (btnComeBack == null) {
            btnComeBack = new JButton("Geri Dön");
            btnComeBack.setBounds(50, 450, 150, 35);

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
