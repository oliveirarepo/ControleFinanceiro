/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import bean.cadastro.Categoria;
import bean.cadastro.Conta;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Thaigo
 */
public class ContaTableModel extends AbstractTableModel {

    List<Conta> lstConta;

    public ContaTableModel(List<Conta> lst) {
        this.lstConta = lst;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Conta umaCategoria = lstConta.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return umaCategoria.getDescricao();
            case 1:
                return Uteis.Uteis.formatarMoeda(umaCategoria.getSaldo());
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return lstConta.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    public Conta getConta(int rowIndex) {
        return lstConta.get(rowIndex);
    }
}
