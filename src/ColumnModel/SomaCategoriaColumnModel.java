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
public class SomaCategoriaColumnModel extends ControleFinanceiroColumnModel {

    private int ordem = 0;

    public SomaCategoriaColumnModel(FontMetrics fm, int ordem) {
        int digito = fm.stringWidth("0");
        int letra = fm.stringWidth("M");
        this.ordem = ordem;

        addColumn(criaCheckBox(0, letra * 3, fm, false, "  "));
        addColumn(criarColuna(1, letra * 30, fm, true, "Categoria"));
        addColumn(criarColuna(2, letra * 12, fm, true, "Valor R$"));
        addColumn(criarColuna(3, letra * 15, fm, true, "Margem%"));
    }

    @Override
    public JCheckBox getCheckBox(int index) {
        return super.getCheckBox(index); //To change body of generated methods, choose Tools | Templates.
    }
    

}
