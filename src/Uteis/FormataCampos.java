/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Uteis;

import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

/**
 *
 * @author thaigo
 */
public class FormataCampos {

    private JFormattedTextField cnpj_cpf;

    public String getCpfCnpjFormatado(String cpfCnpj) {
        cnpj_cpf = new javax.swing.JFormattedTextField();

        String valor = cpfCnpj.replaceAll("\\.", "").replaceAll("-", "").replaceAll("/", "").replaceAll(" ", "");
        if (valor.length() == 11) {
            try {
                cnpj_cpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            try {
                cnpj_cpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        cnpj_cpf.setText(valor);

        return cnpj_cpf.getText();

    }
    private JFormattedTextField formataCep;

    /*
     * formata cep
     */
    public String getCepFormatado(String cpfCnpj) {
        formataCep = new javax.swing.JFormattedTextField();

        String valor = cpfCnpj.replaceAll("-", "").replaceAll("\\.", "");
        try {
            formataCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###-###")));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        formataCep.setText(valor);

        return formataCep.getText();

    }

    public String getHoraFormatado(String hora) {
        formataCep = new javax.swing.JFormattedTextField();

        String valor = hora.replaceAll(":", "");
        try {
            formataCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##:##")));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        formataCep.setText(valor);

        return formataCep.getText();

    }
    private JFormattedTextField formataTelefone;

    public String getTelefoneFormatado(String fone) {
        formataTelefone = new javax.swing.JFormattedTextField();

        String valor = fone.replaceAll("-", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(" ", "");
        if (valor.length() == 7) {
            try {
                formataTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-####")));
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else if (valor.length() == 8) {
            try {
                formataTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            try {
                formataTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        formataTelefone.setText(valor);

        return formataTelefone.getText();

    }
}
