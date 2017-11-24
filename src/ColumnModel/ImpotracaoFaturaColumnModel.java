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
public class ImpotracaoFaturaColumnModel extends ControleFinanceiroColumnModel {

    public ImpotracaoFaturaColumnModel(FontMetrics fm) {
        int digito = fm.stringWidth("0");
        int letra = fm.stringWidth("M");
        addColumn(criarColuna(0, letra * 6, fm, true, "Cód."));
        addColumn(criarColuna(1, letra * 50, fm, false, "Creedor"));
        addColumn(criarColuna(2, letra * 30, fm, true, "Operação"));
        addColumn(criarColuna(3, letra * 30, fm, true, "Tipo Conta"));
        addColumn(criarColuna(4, letra * 15, fm, true, "Valor"));
        addColumn(criarColuna(5, letra * 15, fm, true, "Venc."));
        addColumn(criarColuna(6, letra * 15, fm, true, "Compra."));
    }

}
