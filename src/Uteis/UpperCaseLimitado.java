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
public class UpperCaseLimitado extends PlainDocument {

    int maximo;
    private boolean upperCase = true;

    public UpperCaseLimitado(int max) {
        maximo = max;
    }

    public UpperCaseLimitado(int max, boolean upperCase) {
        maximo = max;
        this.upperCase = upperCase;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if ((getLength() + str.length()) <= maximo) {
            super.insertString(offs, upperCase ? str.toUpperCase() : str, a);
        }
    }
}
