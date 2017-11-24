/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ColumnModel;

import java.awt.FontMetrics;
import javax.swing.JCheckBox;

/**
 *
 * @author Thaigo
 */
public class SelecionaTipoContaColumnModel extends ControleFinanceiroColumnModel {

    public SelecionaTipoContaColumnModel(FontMetrics fm) {
        int digito = fm.stringWidth("0");
        int letra = fm.stringWidth("M");
//        addColumn(criarColuna(0, letra * 3, fm, false, "  "));
        addColumn(criaCheckBox(0, 3 * letra, fm, false, "  "));
        addColumn(criarColuna(1, letra * 10, fm, false, "Código"));
        addColumn(criarColuna(2, letra * 100, fm, true, "Descrição"));
    }

    @Override
    public JCheckBox getCheckBox(int index) {
        return super.getCheckBox(index);
    }
}
