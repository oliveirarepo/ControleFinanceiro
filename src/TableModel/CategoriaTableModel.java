/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import bean.cadastro.Categoria;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Thaigo
 */
public class CategoriaTableModel extends AbstractTableModel {

    List<Categoria> lstCategoria;

    public CategoriaTableModel(List<Categoria> lst) {
        this.lstCategoria = lst;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Categoria categoria = lstCategoria.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return categoria.getIdCategoria();
            case 1:
                return categoria.getDescricaoCategoria();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Categoria.class;
    }

    @Override
    public int getRowCount() {
        return lstCategoria.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    public Categoria getValoresCategoria(int rowIndex) {
        return lstCategoria.get(rowIndex);
    }
}
