/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import bean.cadastro.TipoConta;
import bean.movimento.Select;
import bean.movimento.SelectBean;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Thiago
 */
public class SelecionaTipoContaTableModel extends AbstractTableModel {

    private List<Select> lstTipoConta;

    public SelecionaTipoContaTableModel(List<Select> lstTipoConta) {
        this.lstTipoConta = lstTipoConta;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Boolean.class; //To change body of generated methods, choose Tools | Templates.
        }
        return TipoConta.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Select tpConta = lstTipoConta.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return tpConta.isSelected();
            case 1:
                return tpConta.getId();
            case 2:
                return tpConta.getDescricao();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        SelectBean p = lstTipoConta.get(rowIndex);
        switch (columnIndex) {
            case 0:
                p.setSelected(Boolean.valueOf(aValue.toString()));
                break;
        }
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return lstTipoConta.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    public Select getTipoConta(int rowIndex) {
        return lstTipoConta.get(rowIndex);
    }

    public List<Select> getList() {
        List<Select> lst = new ArrayList<>();
        for (Select p : lstTipoConta) {
            if (p.isSelected()) {
                lst.add(p);
            }
        }
        return lst;
    }

    public void selecionarRegistros(boolean select) {
        for (Select p : lstTipoConta) {
            p.setSelected(select);
        }
    }

}
