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
 * retorna os dados formatados com duas casas decimais
 *
 * @author thaigo
 */
public class FieldListneterFormatandoValorMonetario implements FocusListener {

    public FieldListneterFormatandoValorMonetario() {
        super();
        color = new Color(255, 255, 255);
    }

    public FieldListneterFormatandoValorMonetario(Color color) {
        super();
        this.color = color;
    }
    private Color color;

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
            f.setBackground(color);
            f.setText(Uteis.formatarMoeda(f.getText()));
        }

    }
}
