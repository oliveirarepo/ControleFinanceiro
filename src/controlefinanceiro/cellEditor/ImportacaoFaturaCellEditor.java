/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlefinanceiro.cellEditor;

import ArrayListComboBoxModel.CategoriaComboBoxModel;
import bean.cadastro.Categoria;
import java.awt.Component;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Thiago
 */
public class ImportacaoFaturaCellEditor extends AbstractCellEditor
        implements TableCellEditor {

    private JTextField textField;
    private final JComboBox jCmbCategoria = new JComboBox();
    private int column;

    /*
     * constru
     */
    public ImportacaoFaturaCellEditor(List<Categoria> lstCategoria) {
        super();
        jCmbCategoria.setModel(new CategoriaComboBoxModel(lstCategoria));
    }

    @Override
    public Component getTableCellEditorComponent(JTable table,
            Object value, boolean isSelected, int row, int column) {
        this.column = column;
        switch (column) {
            case 2:
                jCmbCategoria.setSelectedItem(value.toString());
                return jCmbCategoria;
        }
        return null;

    }

    @Override
    public Object getCellEditorValue() {
        if (column == 2) {
            return ((CategoriaComboBoxModel)jCmbCategoria.getModel()).getCategoriaSelecionada(jCmbCategoria.getSelectedIndex());
        }
       return null;
    }
}
