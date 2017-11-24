/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Uteis.Uteis;
import bean.movimento.GraficoMensal;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Thaigo
 */
public class GraficoTableModel extends AbstractTableModel {

    List<GraficoMensal> lstGrafico;

    public GraficoTableModel(List<GraficoMensal> lstGrafico) {
        this.lstGrafico = lstGrafico;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        GraficoMensal grafico = lstGrafico.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return "<html><font color='#36648B'>" + grafico.getMes() + "</html>";
            case 1:
                if (grafico.getSaldo() <= 0) {
                    return "<html><font color='#FF3030'>" + Uteis.formatarMoeda(grafico.getSaldo()) + "</html>";
                }
                return "<html><font color='#2E8B57'>" + Uteis.formatarMoeda(grafico.getSaldo()) + "</html>";
            case 2:
                return "<html><font color='#FF3030'>" + Uteis.formatarMoeda(grafico.getDebitos()) + "</html>";
            case 3:
                return "<html><font color='#2E8B57'>" + Uteis.formatarMoeda(grafico.getCreditos()) + "</html>";
            case 4:
                return Uteis.formatarValor3Decimais(grafico.getIndiceEndividamento());
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return lstGrafico.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return GraficoMensal.class;
    }
    
    

    public GraficoMensal getGrafico(int rowIndex) {
        return lstGrafico.get(rowIndex);
    }
    public List<GraficoMensal> getListGrafico() {
        return lstGrafico;
    }
    public void limparLista(){
        lstGrafico.clear();
    }

}
