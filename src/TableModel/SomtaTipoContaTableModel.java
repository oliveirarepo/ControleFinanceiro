/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import bean.cadastro.TipoConta;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import Uteis.Uteis;
import bean.movimento.Select;
import javax.swing.JButton;
import javax.swing.JCheckBox;

/**
 *
 * @author Thaigo
 */
public class SomtaTipoContaTableModel extends AbstractTableModel {

    List<TipoConta> lst;
    private double total = 0;
    private final JButton jBtnFiltrar;

    public SomtaTipoContaTableModel(List<TipoConta> lst, JButton jBtnFiltrar) {
        this.lst = lst;
        this.jBtnFiltrar = jBtnFiltrar;
        for (TipoConta t : lst) {
            total += t.getSomaTipoConta();
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TipoConta tipo = lst.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return tipo.isSelected();
            case 1:
                return tipo.getDescricao();
            case 2:
                return Uteis.formatarMoeda(tipo.getSomaTipoConta());
            case 3:
                return Uteis.formatarMoeda((tipo.getSomaTipoConta() / total) * 100) + "%";
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return lst.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        TipoConta c = lst.get(rowIndex);
        switch (columnIndex) {
            case 0:
                JCheckBox jBox = (JCheckBox) aValue;
                c.setSelected(jBox.isSelected());
                jBtnFiltrar.doClick(0);
                break;
        }
        fireTableDataChanged();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return TipoConta.class;
    }

    public List<TipoConta> getLista() {
        return lst;
    }

    public TipoConta getTipoConta(int rowIndex) {
        return lst.get(rowIndex);
    }
    public void selecionarRegistros(boolean select) {
        for (Select p : lst) {
            p.setSelected(select);
        }
    }


}
