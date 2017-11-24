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
 * @author thiago
 */
public class FieldListernerFormatandoCep implements FocusListener{
     @Override
    public void focusGained(FocusEvent e) {
        Object o = e.getSource();
        if (o instanceof JTextField) {
            JTextField f = (JTextField) o;
            f.setBackground(new Color(255, 255, 160));
            f.selectAll();

        }

    }

    @Override
    public void focusLost(FocusEvent e) {
        Object o = e.getSource();
        if (o instanceof JTextField) {
            JTextField f = (JTextField) o;
            f.setBackground(new Color(255, 255, 255));
            //formata os dados se o for maior que zero para nao formata sem numero
            if (f.getText().length() > 0) {
                f.setText(new FormataCampos().getCepFormatado(f.getText()));
            }
        }

    }   
}
