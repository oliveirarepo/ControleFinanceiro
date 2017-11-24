/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Uteis;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

/**
 *
 * @author thaigo
 */
public class FieldListenerSemSelect implements FocusListener {

    @Override
    public void focusGained(FocusEvent e) {

        Object o = e.getSource();

        if (o instanceof JTextField) {

            JTextField f = (JTextField) o;
            f.setBackground(new Color(255, 255, 160));
        }

    }

    @Override
    public void focusLost(FocusEvent e) {

        Object o = e.getSource();

        if (o instanceof JTextField) {

            JTextField f = (JTextField) o;
            f.setBackground(new Color(255, 255, 255));

        }

    }
}
