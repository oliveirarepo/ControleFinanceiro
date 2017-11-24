/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlefinanceiro.cellRenderer;

import TableModel.SomaCategoriaTableModel;
import Uteis.Uteis;
import Uteis.ValidarCampos;
import bean.cadastro.Categoria;
import bean.movimento.Select;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Thiago
 */
public class SelecionaTipoContaTableCellRenderer extends DefaultTableCellRenderer {

    private final List<Select> lstTipoConta;
    private JCheckBox box;
    private final Color corFundo = new java.awt.Color(236, 244, 252);
    private final Font fontSelected = new Font("Monospaced", 1, 12);
    Color cor = new java.awt.Color(255, 255, 255);
    private static final int colorR[] = new int[]{255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 245, 235, 225, 215, 205, 195, 185, 175, 165, 155, 145, 135, 125, 115, 105, 95, 85, 75, 65, 55, 45, 35, 25, 15, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private final int colorG[] = new int[]{0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 230, 240, 250, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 245, 235, 225, 215, 205, 195, 185, 175, 165, 155, 145, 135, 125, 115, 105, 95, 85, 75, 65, 55, 45, 35, 25, 15, 5};
    private final int colorB[] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 230, 240, 250, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255};

    public SelecionaTipoContaTableCellRenderer(List lstTipoCont) {
//        super();
        this.lstTipoConta = lstTipoCont;
    }

    @Override
    public Component getTableCellRendererComponent(final JTable table, Object value, boolean isSelected,
            boolean hasFocus, final int row, final int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        final Select tipoconta = lstTipoConta.get(row);
        if (isSelected) {
            setBackground(new Color(185, 209, 234));
            setFont(fontSelected);
        } else if (row % 2 == 0) {
            setBackground(corFundo);
        } else {
            setBackground(new Color(255, 250, 250));
        }

        //pega o botao do Cell editor
        if (column == 0) {
            box = new JCheckBox();
            box.setSelected(tipoconta.isSelected());
            box.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    ValidarCampos.tableConfirmaSelecao(table);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
            return box;
        }
        if (column == 3) {
            if (table.getModel() instanceof SomaCategoriaTableModel) {

                JProgressBar p = new JProgressBar();
                Categoria g = ((SomaCategoriaTableModel) table.getModel()).getCategoria(row);
                double total = ((SomaCategoriaTableModel) table.getModel()).getTotal();
                double maiorValor = ((SomaCategoriaTableModel) table.getModel()).getMaiorValor();

//                p.setForeground(new Color(colorR[(colorR.length / lstTipoConta.size() * row)], colorG[(colorR.length / lstTipoConta.size() * row)], colorB[(colorR.length / lstTipoConta.size() * row)]));
//                double perc = ((g.getSomaValores() / total) * 100);
                double perc = (((g.getSomaValores() * 100) / maiorValor));
                p.setForeground(new Color(colorR[(int) perc], colorG[(int) perc], colorB[(int) perc]));

                p.setMaximum(100);
                p.setValue((int) perc);
                p.setBorderPainted(true);
                p.setStringPainted(true);
                p.setString(Uteis.formatarMoeda(perc) + "%");
                return p;
            }
        }

        return this;
    }

    public JCheckBox getbox() {
        return box;
    }
//248,245,239,236,230,227,224,218,215,212,209,203,200,197,194,188,185,182,179,173,170,167,164,158,155,152,146,143
//105,110,120,125,130,135,145,150,155,160,170,175,180,185,190,195,200,205,215,220,217,214,211,205,202,199,196,193
//107,110,113,116,119,122,125,128,131,134,137,140,143,146,149,152,155,158,161,164,160,158,156,154,153,152,150,148    
//    public static void main(String[] args) {
//        for (int x = 107; x < 165; x = x+3) {
//            System.out.print(x);
//            System.out.print(",");
//        }
//    }
//    107 165 160

}
