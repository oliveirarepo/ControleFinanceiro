/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlefinanceiro.cellEditor;

import Uteis.CalendarComboBox;
import Uteis.FieldListneterFormatandoValorMonetario;
import Uteis.UpperCaseValores;
import java.awt.Component;
import java.awt.Font;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Thiago
 */
public class ContasPagarCellEditor extends AbstractCellEditor
        implements TableCellEditor {

    private JTextField textField;
    private JComboBox jCmbVencimento;
    private int column;

    /*
     * constru
     */
    public ContasPagarCellEditor() {
        super();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table,
            Object value, boolean isSelected, int row, int column) {
        textField = new JTextField();
        this.column = column;
        textField.setFont(new Font("Arial", 1, 12));
        switch (column) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                textField.addFocusListener(new FieldListneterFormatandoValorMonetario());
                textField.setDocument(new UpperCaseValores(10));
                textField.setText(value.toString());
                textField.requestFocus();
                textField.selectAll();
                return textField;
            case 4:
                jCmbVencimento = new CalendarComboBox(true);
                try {
                    jCmbVencimento.setSelectedItem(value.toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                return jCmbVencimento;

        }

        return null;

    }

    @Override
    public Object getCellEditorValue() {
        if (column == 4) {
            return jCmbVencimento.getSelectedItem().toString();
        }
        return textField.getText();
    }
}
