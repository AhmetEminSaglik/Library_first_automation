package Logic;

import Gui.AboutUs;
import Gui.FineDebtPayment;
import Gui.TimeControlExtraTimeGui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ActionTimeFine implements ActionListener {

    TimeControlExtraTimeGui tcet;
    FineDebtPayment dep;
    AboutUs au;

    public ActionTimeFine(TimeControlExtraTimeGui tcet) {
        this.tcet = tcet;
    }

    public ActionTimeFine(AboutUs au) {
        this.au = au;
    }

    public ActionTimeFine(FineDebtPayment dep) {
        this.dep = dep;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (tcet != null) {

            if (e.getSource() == tcet.getBtnComeBack()) {
                tcet.setVisible(false);
                tcet.getMg().getJf().setTitle("ANA SAYFA");
                tcet.getMg().getJp().setVisible(true);
            } else if (e.getSource() == tcet.getBtnExtendTime()) {
                JOptionPane.showMessageDialog(null, "süre uzatma sql'e bağlanacak");
            } else if (e.getSource() == tcet.getTxtBookBarcodeNoToExtendTime()) {
                JOptionPane.showMessageDialog(null, tcet.getTxtBookBarcodeNoToExtendTime().getText() + " barkod nolu  kitap bilgileri buraya getirilecek");
            } else if (e.getSource() == tcet.getBtnSearch()
                    || e.getSource() == tcet.getTxtSearchBookBarcodeNo()
                    || e.getSource() == tcet.getTxtSearchStudentNo()) {

                JOptionPane.showMessageDialog(null, "arama sql'e bağlanacak");
                /*    getTxtSearchStudentNo().addActionListener(action);
        getTxtSearchBookBarcodeNo().addActionListener(action);*/
            }
        } else if (dep != null) {
            if (e.getSource() == dep.getBtnComeBack()) {
                dep.getJp().setVisible(false);
                dep.getMg().getJp().setVisible(true);
                dep.getMg().getJf().setTitle("ANA SAYFA");
            }

        } else if (au != null) {
            if (e.getSource() == au.getBtnComeBack()) {
                au.stopChangeBackground = true;
                au.getJp().setVisible(false);
                au.getMg().getJp().setVisible(true);
                au.getMg().getJf().setTitle("ANA SAYFA");

            }
        }
    }

}
