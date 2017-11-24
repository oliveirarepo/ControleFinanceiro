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
public class ContasPagarColumnModel extends ControleFinanceiroColumnModel {

    public ContasPagarColumnModel(FontMetrics fm) {
        int digito = fm.stringWidth("0");
        int letra = fm.stringWidth("M");
        addColumn(criarColuna(0, letra * 60, fm, false, "Creedor"));
        addColumn(criarColuna(1, letra * 25, fm, true, "Operação"));
        addColumn(criarColuna(2, letra * 25, fm, true, "Descrição"));
        addColumn(criarColuna(3, letra * 15, fm, true, "Valor"));
        addColumn(criarColuna(4, letra * 20, fm, true, "Vencimento"));
    }

}
