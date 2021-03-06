/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlefinanceiro.cellRenderer;

import bean.cadastro.ContasPagar;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Thiago
 */
public class ImportacaoFaturaCellRenderer extends DefaultTableCellRenderer {

    private final Color corFundo = new java.awt.Color(236, 244, 252);
    private final Font fontSelected = new Font("Microsoft Sans Serif", 1, 11);
    Color cor = new java.awt.Color(255, 255, 255);
    private List<ContasPagar> lst;

    public ImportacaoFaturaCellRenderer(List<ContasPagar> lst) {
        this.lst = lst;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setBackground(Color.white);
        if (lst.get(row).getImportada() == 'S') {
            setBackground(corFundo);
        }
//        if (isSelected) {
//            setBackground(new Color(185, 209, 234));
//            setFont(fontSelected);
//        } else if (row % 2 == 0) {
//            setBackground(corFundo);
//        } else {
//            setBackground(Color.white);
//        }
        return this;

    }

}
