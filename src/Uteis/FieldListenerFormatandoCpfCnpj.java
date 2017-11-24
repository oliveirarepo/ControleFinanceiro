/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Uteis;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * retorna os dados formatados como inteiro
 *
 * @author thaigo
 */
public class FieldListenerFormatandoCpfCnpj implements FocusListener {

    private boolean validaCpf = true;
    private boolean mudarCor = true;
    private boolean informarValidacao = true;

    public FieldListenerFormatandoCpfCnpj() {
        super();
        validaCpf = false;
    }

    /**
     *
     * @param mudarCor muda a cor para vermelho se cpf ou cnpj for invalidos
     * @param informaValidacao mostra menssagem de que cpf e invalido
     */
    public FieldListenerFormatandoCpfCnpj(boolean mudarCor, boolean informaValidacao) {
        super();
        this.mudarCor = mudarCor;
        this.informarValidacao = informaValidacao;
    }

    /**
     *
     * @param mudarCor valida e muda apenas a cor
     */
    public FieldListenerFormatandoCpfCnpj(boolean mudarCor) {
        super();
        this.mudarCor = mudarCor;
        this.informarValidacao = false;
    }

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
            if (f.getText().length() > 0) {
                if (validaCpf) {
                    boolean cpfValido = false;
                    if (f.getText().replaceAll("\\.", "").replaceAll("-", "").length() == 11) {
                        cpfValido = Uteis.isCPF(f.getText());
                    } else {
                        cpfValido = Uteis.isCNPJ(f.getText());
                    }
                    if (!cpfValido) {
                        if (informarValidacao) {
                            JOptionPane.showMessageDialog(null, "CPF ou CNPJ Invalido!");
                        }
                        if (mudarCor) {
                            ((JTextField) o).setForeground(Color.red);
                        }
                    } else {
                        ((JTextField) o).setForeground(Color.black);

                    }

                }
                f.setText(new FormataCampos().getCpfCnpjFormatado(f.getText()));
            }
        }

    }
}
