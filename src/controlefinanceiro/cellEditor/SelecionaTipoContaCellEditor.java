/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlefinanceiro.cellEditor;

import bean.cadastro.TipoConta;
import controlefinanceiro.cellRenderer.SelecionaTipoContaTableCellRenderer;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Thiago
 */
public class SelecionaTipoContaCellEditor extends AbstractCellEditor
        implements TableCellEditor {

    private int column = 0;
    private JCheckBox jChbBoxSelecionado = new JCheckBox();
    /*
     * constru
     */

    public SelecionaTipoContaCellEditor() {
        super();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.column = column;
     
        switch (column) {
            case 0:
                jChbBoxSelecionado = ((SelecionaTipoContaTableCellRenderer) (DefaultTableCellRenderer) table.getDefaultRenderer(TipoConta.class)).getbox();
                jChbBoxSelecionado.setSelected(Boolean.valueOf(value.toString()));
                return jChbBoxSelecionado;
        }

        return null;

    }

    @Override
    public Object getCellEditorValue() {
        switch (column) {
            case 0:
                return jChbBoxSelecionado;
        }

        return null;
    }

    public JCheckBox getCheckBox() {
        return this.jChbBoxSelecionado;
    }
}
