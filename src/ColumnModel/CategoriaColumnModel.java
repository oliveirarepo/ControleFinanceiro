/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ColumnModel;

import java.awt.FontMetrics;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Thaigo
 */
public class CategoriaColumnModel extends DefaultTableColumnModel{
    
    public CategoriaColumnModel(FontMetrics fm) {
        int digito = fm.stringWidth("0");
        int letra = fm.stringWidth("M");
        addColumn(criarColuna(0, letra * 10, fm, false, "Código"));
        addColumn(criarColuna(1, letra * 100, fm, true, "Descrição"));
    }

    private TableColumn criarColuna(int columnIndex, int largura, FontMetrics fm, boolean resizable, String titulo) {
        int larguraTitulo = fm.stringWidth(titulo + "  ");
        if (largura < larguraTitulo) {
            largura = larguraTitulo;
        }
        TableColumn col = new TableColumn(columnIndex);
        col.setCellRenderer(new CellRenderer());
        col.setHeaderRenderer(null);
        col.setHeaderValue(titulo);
        col.setPreferredWidth(largura);
        if (!resizable) {
            col.setMaxWidth(largura);
            col.setMaxWidth(largura);
        }
        col.setResizable(resizable);
        return col;
    }
}
