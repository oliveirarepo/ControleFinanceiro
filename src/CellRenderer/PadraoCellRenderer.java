/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CellRenderer;

import TableModel.GraficoTableModel;
import bean.movimento.GraficoMensal;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Thiago
 */
public class PadraoCellRenderer extends DefaultTableCellRenderer {

    private final Color corFundo = new java.awt.Color(236, 244, 252);
    private final Font fontSelected = new Font("Monospaced", 1, 12);
    private final Font fontIndice = new Font("Arial", 1, 12);
    private final Color cor = new java.awt.Color(255, 255, 255);
    private final Color fundoPadrao = new java.awt.Color(255, 250, 250);

    private List<Object> lst;

    public PadraoCellRenderer() {
        super();
    }

    public PadraoCellRenderer(List<Object> lst) {
        super();
        this.lst = lst;

    }
    //@ Estaa linha tem que ser removida da ColumnModel se nao, nao muda a fundo
    //        col.setCellRenderer(new CellRenderer());

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        this.setIcon(null);
        setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
        if (column == 4) {
            JProgressBar p = new JProgressBar();
            GraficoMensal g = ((GraficoTableModel) table.getModel()).getGrafico(row);
            if(g.getIndiceEndividamento() < 100){
                p.setForeground(Color.cyan);
            }else{
                p.setForeground(Color.RED);
                
            }
            p.setMaximum(100);
//            p.setFont(fontIndice);
            p.setValue((int) g.getIndiceEndividamento());
            p.setBorderPainted(true);
            p.setStringPainted(true);
            p.setString(Uteis.Uteis.formatarMoeda(g.getIndiceEndividamento()) + "%");
            return p;
        }
        if (column == 5) {
            setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
        }

        if (isSelected) {
            setBackground(new Color(185, 209, 234));
            setFont(fontSelected);
        } else if (row % 2 == 0) {
            setBackground(corFundo);
        } else {
            setBackground(fundoPadrao);
        }
        return this;
    }
}
