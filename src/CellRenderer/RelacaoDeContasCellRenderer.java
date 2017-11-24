/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CellRenderer;

import bean.cadastro.ContasPagar;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Thiago
 */
public class RelacaoDeContasCellRenderer extends DefaultTableCellRenderer {

    private final transient List<ContasPagar> lstConta;

    private final Color corFundo = new java.awt.Color(236, 244, 252);
    private final Font fontSelected = new Font("Monospaced", 1, 12);
    private final Color cor = new java.awt.Color(255, 255, 255);
    private final Color fundoPadrao = new java.awt.Color(255, 250, 250);

    private final ImageIcon iconBlue = new javax.swing.ImageIcon(getClass().getResource("/img/Create.png"));
    private final ImageIcon iconRed = new javax.swing.ImageIcon(getClass().getResource("/img/No-entry.png"));
    private final ImageIcon confirm = new javax.swing.ImageIcon(getClass().getResource("/img/confirm.png"));

    private final ImageIcon vencida = new javax.swing.ImageIcon(getClass().getResource("/img/Red pin.png"));
    private final ImageIcon paga = new javax.swing.ImageIcon(getClass().getResource("/img/Blue pin.png"));
    private final ImageIcon aVencer = new javax.swing.ImageIcon(getClass().getResource("/img/Green pin.png"));

    public RelacaoDeContasCellRenderer(List<ContasPagar> lstProduto) {
        this.lstConta = lstProduto;
    }
    //@ Estaa linha tem que ser removida da ColumnModel se nao, nao muda a fundo
    //        col.setCellRenderer(new CellRenderer());

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        ContasPagar c = lstConta.get(row);

        if (c.getJAPAGA() == 'S') {
            setForeground(Color.BLUE);
        } else if (c.getJAPAGA() != 'S' && c.getDataVenc().getTime() < new Date().getTime()) {
            setForeground(Color.RED);
        } else {
            setForeground(Color.black);
        }
        this.setIcon(null);
        setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
        if (column == 4 || column == 5) {
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
        if (column == 0) {
            if (c.getJAPAGA() == 'S') {
                this.setIcon(confirm);
            }
            if (c.getCOD_OPERACAO() == 2) {
                this.setIcon(iconRed);
            }
            if (c.getCOD_OPERACAO() == 1) {
                this.setIcon(iconBlue);
            }
        }
        if (column == 1) {
            if (c.getJAPAGA() == 'S') {
                setIcon(paga);
            } else if (c.getJAPAGA() != 'S' && c.getDataVenc().getTime() < new Date().getTime()) {
                setIcon(vencida);
            } else {
                setIcon(aVencer);

            }
        }
        if (column == 9) {
            JProgressBar p = new JProgressBar();
            p.setMaximum(100);
            p.setValue((int) c.getAndamento());
            p.setBorderPainted(true);
            p.setStringPainted(true);
            p.setString(Uteis.Uteis.formatarMoeda(c.getAndamento()) + "%");
//            if (c.getCOD_OPERACAO() == 2) {
                if (c.getAndamento() < 100) {
                    p.setForeground(Color.RED);
                } else {
                    p.setForeground(Color.cyan);
                }
//            } else {
//                if (c.getAndamento() < 100) {
//                    p.setForeground(new Color(0, 204, 102));
//                } else {
//                    p.setForeground(new Color(0, 153, 204));
//                }
//            }
            return p;
        }

        return this;
    }
}
