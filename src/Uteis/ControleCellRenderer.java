/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uteis;

import java.awt.Color;
import java.awt.Font;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Thiago
 */
public class ControleCellRenderer extends DefaultTableCellRenderer {

    protected final Color corFundo = new java.awt.Color(236, 244, 252);
    protected final Font fontSelected = new Font("Monospaced", 1, 12);
    protected final Color cor = new java.awt.Color(255, 255, 255);
    protected final Color fundoPadrao = new java.awt.Color(255, 250, 250);

    protected void isSelected(boolean isSelected, int row) {
        if (isSelected) {
            setBackground(new Color(185, 209, 234));
            setFont(fontSelected);
        }
        if (row % 2 == 0) {
            setBackground(corFundo);
        } else {
            setBackground(fundoPadrao);
        }
    }
}
