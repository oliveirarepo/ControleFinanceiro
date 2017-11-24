/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ColumnModel;

import java.awt.FontMetrics;

/**
 *
 * @author Thaigo
 */
public class GraficoMensalColumnModel extends ControleFinanceiroColumnModel {

    public GraficoMensalColumnModel(FontMetrics fm) {
        int digito = fm.stringWidth("0");
        int letra = fm.stringWidth("M");
        addColumn(criarColuna(0, letra * 15, fm, true, "Mês"));
        addColumn(criarColuna(1, letra * 15, fm, true, "Saldo"));
        addColumn(criarColuna(2, letra * 15, fm, true, "Débitos"));
        addColumn(criarColuna(3, letra * 15, fm, true, "Créditos"));
        addColumn(criarColuna(4, letra * 40, fm, true, "Ind. End."));
    }

}
