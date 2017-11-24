/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Uteis;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author thaigo
 */
public class UpperCaseParaNumerico extends PlainDocument {

    int maximo;

    public UpperCaseParaNumerico(int max) {
        maximo = max;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if ((getLength() + str.length()) <= maximo) {
            super.insertString(offs, isNumericos(str.toUpperCase()), a);
        }
    }

    private String isNumericos(String valores) {
        try {
            Integer.parseInt(valores.substring((valores.length() -1), valores.length()));
        } catch (Exception ex) {
            valores = valores.substring(0, (valores.length() - 1));
        }
        return valores;
    }
}
