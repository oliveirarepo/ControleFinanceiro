/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import Uteis.Uteis;
import bean.cadastro.Categoria;
import bean.movimento.Select;
import javax.swing.JButton;
import javax.swing.JCheckBox;

/**
 *
 * @author Thaigo
 */
public class SomaCategoriaTableModel extends AbstractTableModel {

    List<Categoria> lst;
    private double total;
    private double maiorValor;
    private final JButton jBtnFiltrar;

    public SomaCategoriaTableModel(List<Categoria> lst, JButton jBtnFiltrar) {
        total = 0;
        this.jBtnFiltrar = jBtnFiltrar;
        this.lst = lst;
        for (Categoria c : lst) {
            total += c.getSomaValores();
            if (c.getSomaValores() > maiorValor) {
                maiorValor = c.getSomaValores();
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Categoria c = lst.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.isSelected();
            case 1:
                return c.getDescricaoCategoria();
            case 2:
                return Uteis.formatarMoeda(c.getSomaValores());
            case 3:
                return Uteis.formatarMoeda((c.getSomaValores() / total) * 100) + "%";
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return lst.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Select.class;
    }

    public Categoria getCategoria(int rowIndex) {
        return lst.get(rowIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Categoria c = lst.get(rowIndex);
        switch (columnIndex) {
            case 0:
                JCheckBox jBox = (JCheckBox) aValue;
                c.setSelected(jBox.isSelected());
                jBtnFiltrar.doClick(0);
                break;
        }
        fireTableDataChanged();
    }

    public List<Categoria> getList() {
        return lst;
    }

    public void selecionarRegistros(boolean select) {
        for (Select p : lst) {
            p.setSelected(select);
        }
    }

    public double getTotal() {
        return total;
    }

    public double getMaiorValor() {
        return maiorValor;
    }
}
