/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Uteis;


import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.JTable;

/**
 *
 * @author thaigo
 */
//como usar
//table.addMouseListener(new ExemploMouseAdapter(table));
public class MouseListener extends MouseAdapter {

    private final JTable table;
    private String command;
    private JButton jBtnComando = new JButton();
    private ActionListener actionListerner;
    private boolean editar = true;
    private int colNaoEdita = 200;

    /**
     * @param table a tabela que está sendo utilizada
     * @param command actioncommand do botao ha executar quando clicar na tabela
     * @param actionListerner actionsupport da tela
     */
    @Deprecated
    public MouseListener(JTable table, String command, ActionListener actionListerner) {
        this.table = table;
        this.command = command;
        this.actionListerner = actionListerner;

    }

    /**
     * @param table a tabela que está sendo utilizada
     * @param command actioncommand do botao ha executar quando clicar na tabela
     * @param actionListerner actionsupport da tela
     * @param editar para controle do usuario botao editar
     */
//    public MouseListener(JTable table, String command, ActionListener actionListerner, boolean editar) {
//        this.table = table;
//        this.command = command;
//        this.actionListerner = actionListerner;
//        this.editar = editar;
//        jBtnComando.addActionListener(actionListerner);
//        jBtnComando.setActionCommand(command);
//    }

    /**
     * @param table a tabela que está sendo utilizada
     * @param command actioncommand do botao ha executar quando clicar na tabela
     * @param actionListerner actionsupport da tela
     * @param editar para controle do usuario botao editar
     * @param colunaNaoEdita coluna que não usa duplo clique, colunas com jCheckBox
     */
    public MouseListener(JTable table, String command, ActionListener actionListerner, boolean editar, int colunaNaoEdita) {
        this.table = table;
        this.command = command;
        this.actionListerner = actionListerner;
        this.editar = editar;
        jBtnComando.addActionListener(actionListerner);
        jBtnComando.setActionCommand(command);
        this.colNaoEdita = colunaNaoEdita;
    }

    public MouseListener(JTable table, String command, ActionListener actionListener, boolean editar) {
        this.table = table;
        this.command = command;
        this.editar = editar;
        jBtnComando.addActionListener(actionListener);
        jBtnComando.setActionCommand(command);
    }

    public MouseListener(JTable table, JButton jBtn, boolean editar) {
        this.table = table;
        this.editar = editar;
        this.jBtnComando = jBtn;
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        if (e.getClickCount() == 2 && editar && table.getSelectedColumn() != colNaoEdita) {
            jBtnComando.doClick(0);
        }
    }

    public void removeListerner(ActionListener listerner) {
        jBtnComando.removeActionListener(listerner);
    }
}
