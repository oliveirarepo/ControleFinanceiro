/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import bean.cadastro.ContasPagar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Thaigo
 */
public class ContasPagarTableModel extends AbstractTableModel {

    List<ContasPagar> lstCategoria;

    public ContasPagarTableModel(List<ContasPagar> lst) {
        this.lstCategoria = lst;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex >= 3;
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
                return cr.getCreedor();
            case 1:
                return cr.getOPERACAO();
            case 2:
                return cr.getTipoConta().getDescricao();
            case 3:
                return Uteis.Uteis.formatarMoeda(cr.getVALORPAGO());
            case 4:
                return Uteis.Uteis.getData(cr.getDataVenc());

        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ContasPagar cr = lstCategoria.get(rowIndex);
        switch (columnIndex) {
            case 3:
                cr.setVALORPAGO(Uteis.Uteis.parseFloat(aValue.toString()));
                break;
            case 4:
                try {
                    cr.setDataVenc(Uteis.Uteis.getDate(aValue.toString()));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
        }
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return lstCategoria.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    public ContasPagar getContasPagar(int rowIndex) {
        return lstCategoria.get(rowIndex);
    }
}
