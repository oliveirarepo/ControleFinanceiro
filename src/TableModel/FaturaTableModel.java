/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import bean.cadastro.Categoria;
import bean.cadastro.ContasPagar;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Thaigo
 */
public class FaturaTableModel extends AbstractTableModel {

    List<ContasPagar> lstCategoria;

    public FaturaTableModel(List<ContasPagar> lst) {
        this.lstCategoria = lst;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return ContasPagar.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ContasPagar cr = lstCategoria.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cr.getCODIGO();
            case 1:
                return cr.getCreedor();
            case 2:
                return cr.getCategoria().getDescricaoCategoria();
            case 3:
                return cr.getTipoConta().getDescricao();
            case 4:
                return Uteis.Uteis.formatarMoeda(cr.getVALORPAGO());
            case 5:
                return Uteis.Uteis.getData(cr.getDataVenc());
            case 6:
                return Uteis.Uteis.getData(cr.getDataCompra());

        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ContasPagar cr = lstCategoria.get(rowIndex);
        switch (columnIndex) {
            case 2:
                cr.setCategoria((Categoria) aValue);
                break;
        }
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return lstCategoria.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    public ContasPagar getContasPagar(int rowIndex) {
        return lstCategoria.get(rowIndex);
    }

    public List<ContasPagar> getLista() {
        return lstCategoria;
    }
}
