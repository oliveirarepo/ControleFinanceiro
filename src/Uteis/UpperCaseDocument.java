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
public class UpperCaseDocument extends PlainDocument {

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (str == null) {
            return;
        }
        super.insertString(offs, str.toUpperCase(), a);
    }
}  

//seuTextField.setDocument(new UpperCaseDocument());  
