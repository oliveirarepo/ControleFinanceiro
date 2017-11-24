/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColumnModel;

import Uteis.JComponentTableCellRenderer;
import java.awt.Color;
import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Th
 */
public class ControleFinanceiroColumnModel extends DefaultTableColumnModel {

    protected int ordem;

    protected TableColumn criarColuna(int columnIndex, int largura, FontMetrics fm, boolean resizable, String titulo) {
        int larguraTitulo = fm.stringWidth(titulo + "  ");
        if (largura < larguraTitulo) {
            largura = larguraTitulo;
        }
        // Border headerBorder = UIManager.getBorder("TableHeader.cellBorder");
        JButton jBtnTitulo = new JButton(titulo);

        if (columnIndex < 5) {
            jBtnTitulo.setForeground(Color.blue);
        }
        if (columnIndex == ordem) {
            jBtnTitulo.setForeground(Color.red);
        }
        TableCellRenderer renderer = new JComponentTableCellRenderer();

      //  jBtnTitulo.setBorder(headerBorder);
        TableColumn col = new TableColumn(columnIndex);
        col.setHeaderRenderer(renderer);
        col.setHeaderValue(jBtnTitulo);

        col.setPreferredWidth(largura);

        if (!resizable) {
            col.setMaxWidth(largura);
            col.setMaxWidth(largura);
        }
        col.setResizable(resizable);
        return col;

    }

    protected TableColumn criaCheckBox(int columnIndex, int largura, FontMetrics fm, boolean resizeable, String titulo) {

        Border headerBorder = UIManager.getBorder("TableHeader.cellBorder");
        final JCheckBox jBtnTitulo = new JCheckBox(titulo);
        TableCellRenderer renderer = new JComponentTableCellRenderer();
        jBtnTitulo.setBorder(headerBorder);
        jBtnTitulo.setForeground(Color.white);
        jBtnTitulo.setEnabled(true);

        TableColumn col = new TableColumn(columnIndex);

        if (ordem == columnIndex) {
            jBtnTitulo.setForeground(Color.black);
        }

        col.setHeaderRenderer(renderer);
        col.setHeaderValue(jBtnTitulo);
        col.setPreferredWidth(largura);
        if (!resizeable) {
            col.setMaxWidth(largura);
            col.setMinWidth(largura);
        }
        col.setResizable(resizeable);
        return col;
    }

    protected JButton getButton(int index) {
        return (JButton) getColumn(index).getHeaderValue();
    }

    protected JCheckBox getCheckBox(int index) {
        return (JCheckBox) getColumn(index).getHeaderValue();
    }

    protected List<String> getNomeColunas() {
        List<String> novaLista = new ArrayList<>();
        for (int i = 0; i < getColumnCount(); i++) {
            novaLista.add(getButton(i).getText());
        }
        return novaLista;
    }

    protected void voltarCor() {
        for (int index = 0; index < this.getColumnCount(); index++) {
            getButton(index).setForeground(Color.white);
        }
    }
}
