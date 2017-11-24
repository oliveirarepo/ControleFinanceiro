/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uteis;

import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Popup;
import javax.swing.PopupFactory;

/**
 *
 * @author th
 */
public class SuperControl extends SuperControleFinanceiro {

    protected void iniciarConexao() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = FabricaConexao.getConnection();
        }

    }

    protected void finalizarConexao() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                conn = null;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    protected void showMenssagem(final String menssagem) {
        new Thread() {
            @Override
            public void run() {
                JLabel msg = new JLabel("<html><Font Color='#FF4500' size = '6'>" + menssagem + "</font></html>");
                msg.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, Color.RED, null));
                PopupFactory factory = PopupFactory.getSharedInstance();
                Popup p = factory.getPopup(msg, msg, 0, 0);

                p.show();
                try {
                    Thread.sleep(3000);
                    p.hide();
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }.start();

    }
}
