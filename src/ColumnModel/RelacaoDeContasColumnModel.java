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
public class RelacaoDeContasColumnModel extends ControleFinanceiroColumnModel {

    private int ordem = 0;

    public RelacaoDeContasColumnModel(FontMetrics fm, int ordem) {
        int digito = fm.stringWidth("0");
        int letra = fm.stringWidth("M");
        this.ordem = ordem;
//        addColumn(criarColuna(0, letra * 10, fm, false, "Codigo"));
        addColumn(criarColuna(0, letra * 4, fm, true, "  "));
        addColumn(criarColuna(1, letra * 4, fm, true, "  "));
        addColumn(criarColuna(2, letra * 50, fm, true, "Creedor"));
        addColumn(criarColuna(3, letra * 16, fm, true, "Data Compra"));
        addColumn(criarColuna(4, letra * 16, fm, true, "Data Venci"));
        addColumn(criarColuna(5, letra * 15, fm, true, "Credito"));
        addColumn(criarColuna(6, letra * 15, fm, true, "Debito"));
        addColumn(criarColuna(7, letra * 25, fm, true, "Categoria"));
        addColumn(criarColuna(8, letra * 25, fm, true, "Tipo"));
        addColumn(criarColuna(9, letra * 25, fm, true, "Andamento"));
    }

}
