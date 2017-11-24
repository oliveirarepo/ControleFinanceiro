/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlefinanceiro.cellRenderer;

import Uteis.ControleCellRenderer;
import java.awt.Component;
import javax.swing.JTable;

/**
 *
 * @author Thiago
 */
public class CategoriaCellRenderer extends ControleCellRenderer {

    public CategoriaCellRenderer() {
        super();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        isSelected(isSelected, row);
        return this;

    }

}
