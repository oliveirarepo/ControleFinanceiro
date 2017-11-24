/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import bean.cadastro.TipoConta;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Thaigo
 */
public class TipoContaTableModel extends AbstractTableModel {

    List<TipoConta> lstTipoConta;

    public TipoContaTableModel(List<TipoConta> lst) {
        this.lstTipoConta = lst;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TipoConta umaCategoria = lstTipoConta.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return umaCategoria.getIdTipo();
            case 1:
                return umaCategoria.getDescricao();
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return lstTipoConta.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    public TipoConta getTipoConta(int rowIndex) {
        return lstTipoConta.get(rowIndex);
    }
    public List<TipoConta> getLista(){
        return lstTipoConta;
    }
}
