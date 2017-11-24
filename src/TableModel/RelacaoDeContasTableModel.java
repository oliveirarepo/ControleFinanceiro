/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import bean.cadastro.ContasPagar;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Thaigo
 */
public class RelacaoDeContasTableModel extends AbstractTableModel {

    List<ContasPagar> lstConta;

    public RelacaoDeContasTableModel(List<ContasPagar> lst) {
        this.lstConta = lst;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return ContasPagar.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ContasPagar conta = lstConta.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return null;
//                return umaConta.getCODIGO();
            case 1:
                return null;
            case 2:
                return "<html>" + conta.getCreedor() + "</html>";
            case 3:
                return Uteis.Uteis.getData(conta.getDataCompra());
            case 4:
                return Uteis.Uteis.getData(conta.getDataVenc());
            case 5:
                if (conta.getCOD_OPERACAO() == 1) {
                    return Uteis.Uteis.formatarMoeda(conta.getVALORPAGO());
                }
                return null;
            case 6:
                if (conta.getCOD_OPERACAO() == 2) {
                    return Uteis.Uteis.formatarMoeda(conta.getVALORPAGO());
                }
                return null;
            case 7:
                return conta.getCategoria().getDescricaoCategoria();
            case 8:
                return conta.getTipoConta().getDescricao();
            case 9:
                return Uteis.Uteis.formatarMoeda(conta.getAndamento()) + "% ";

        }
        return null;
    }

    @Override
    public int getRowCount() {
        return lstConta.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    public ContasPagar getValoresConta(int rowIndex) {
        return lstConta.get(rowIndex);
    }

    public List<ContasPagar> getListaContasPagar() {
        return lstConta;
    }

    public void limparLista() {
        if (lstConta != null) {
            lstConta.clear();
        }
    }
}
