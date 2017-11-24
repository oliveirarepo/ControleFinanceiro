/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.movimento;

import ColumnModel.SelecionaTipoContaColumnModel;
import Comparator.SelectComparator;
import TableModel.SelecionaTipoContaTableModel;
import bean.cadastro.TipoConta;
import bean.movimento.Select;
import controlefinanceiro.cellEditor.SelecionaTipoContaCellEditor;
import controlefinanceiro.cellRenderer.SelecionaTipoContaTableCellRenderer;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thiago
 */
public class SelecionarRegistroCons extends javax.swing.JDialog {

    private final JButton jBtnSelecionarConta = new JButton();

    /**
     * Creates new form SelecionaVendedorFrm
     */
    public SelecionarRegistroCons(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setResizable(false);

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "escape");
        this.getRootPane().getActionMap().put("escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }

        });
        jTableHeader();
        jBtnSelecionarConta.setActionCommand("selecionarConta");
        this.getRootPane().getContentPane().setBackground(new java.awt.Color(215, 228, 242));
        jPanel1.setBackground(new java.awt.Color(215, 228, 242));

        jTblRegistros.setAutoCreateColumnsFromModel(false);
        FontMetrics fm = jTblRegistros.getFontMetrics(jTblRegistros.getFont());
        jTblRegistros.setColumnModel(new SelecionaTipoContaColumnModel(fm));
        jTblRegistros.getSelectionModel().addListSelectionListener(jTblRegistros);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTblRegistros = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jBtnConfirmaVendedor = new javax.swing.JButton();
        jPnlCabecalho = new javax.swing.JPanel();
        jLblTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Controle Financeiro -  Pesquisa Tipo de Conta");

        jTblRegistros.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jTblRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTblRegistros.setRowHeight(20);
        jTblRegistros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblRegistrosMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTblRegistrosMouseReleased(evt);
            }
        });
        jTblRegistros.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTblRegistrosPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jTblRegistros);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jBtnConfirmaVendedor.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jBtnConfirmaVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tick.png"))); // NOI18N
        jBtnConfirmaVendedor.setText("Confirmar");
        jBtnConfirmaVendedor.setActionCommand("confirmarVendedores");
        jPanel1.add(jBtnConfirmaVendedor);

        jPnlCabecalho.setBackground(new java.awt.Color(255, 255, 255));

        jLblTitulo.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLblTitulo.setText("Pesquisa Tipo de Conta");

        javax.swing.GroupLayout jPnlCabecalhoLayout = new javax.swing.GroupLayout(jPnlCabecalho);
        jPnlCabecalho.setLayout(jPnlCabecalhoLayout);
        jPnlCabecalhoLayout.setHorizontalGroup(
            jPnlCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlCabecalhoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblTitulo)
                .addContainerGap(546, Short.MAX_VALUE))
        );
        jPnlCabecalhoLayout.setVerticalGroup(
            jPnlCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlCabecalhoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLblTitulo)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPnlCabecalho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPnlCabecalho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTblRegistrosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblRegistrosMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTblRegistrosMouseReleased

    private void jTblRegistrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblRegistrosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTblRegistrosMouseClicked

    private void jTblRegistrosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTblRegistrosPropertyChange
        // TODO add your handling code here:
        if (!(jTblRegistros.getModel() instanceof DefaultTableModel)) {

            if (jTblRegistros.getSelectedColumn() > 0) {
                jBtnSelecionarConta.doClick(0);
            }
        }
    }//GEN-LAST:event_jTblRegistrosPropertyChange

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SelecionarRegistroCons.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SelecionarRegistroCons.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SelecionarRegistroCons.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SelecionarRegistroCons.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SelecionarRegistroCons dialog = new SelecionarRegistroCons(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnConfirmaVendedor;
    private javax.swing.JLabel jLblTitulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPnlCabecalho;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblRegistros;
    // End of variables declaration//GEN-END:variables
    public void addActionListener(ActionListener actionListener) {
        jBtnConfirmaVendedor.addActionListener(actionListener);
        jBtnSelecionarConta.addActionListener(actionListener);
    }

    public void setListaTipoConta(List lst) {
        Collections.sort(lst, new SelectComparator(0));
        jTblRegistros.setModel(new SelecionaTipoContaTableModel(lst));
        jTblRegistros.setDefaultRenderer(TipoConta.class, new SelecionaTipoContaTableCellRenderer(lst));
        jTblRegistros.setDefaultEditor(TipoConta.class, new SelecionaTipoContaCellEditor());
    }

    public void setTitulo(String titulo) {
        jLblTitulo.setText(titulo);
    }

    public void atualizarTabela() {
        jTblRegistros.updateUI();
    }

    public List<Select> getListaVendedorSelecionado() {
        return ((SelecionaTipoContaTableModel) jTblRegistros.getModel()).getList();
    }

    public void selecionarConta() {
        Select c;
        if (jTblRegistros.getModel() instanceof SelecionaTipoContaTableModel) {
            c = ((SelecionaTipoContaTableModel) jTblRegistros.getModel()).getTipoConta(jTblRegistros.getSelectedRow());
            c.setSelected(!c.isSelected());
        }
        jTblRegistros.repaint();

    }

    private void jTableHeader() {
        jTblRegistros.getTableHeader().addMouseListener(new java.awt.event.MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int ordem = jTblRegistros.getTableHeader().columnAtPoint(e.getPoint());
                boolean isSelected;
                isSelected = ((SelecionaTipoContaColumnModel) jTblRegistros.getColumnModel()).getCheckBox(0).isSelected();
                if (ordem == 0) {
                    if (jTblRegistros.getColumnModel() instanceof SelecionaTipoContaColumnModel) {
                        ((SelecionaTipoContaColumnModel) jTblRegistros.getColumnModel()).getCheckBox(0).setSelected(!isSelected);
                         ((SelecionaTipoContaTableModel) jTblRegistros.getModel()).selecionarRegistros(!isSelected);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    private List getListaContas() {
        return ((SelecionaTipoContaTableModel) jTblRegistros.getModel()).getList();
//        if (jTblRegistros.getModel() instanceof SelecionaTipoContaTableModel) {
//        }
    }

    private void atualizarRegistros(boolean selecionar) {
        ((SelecionaTipoContaTableModel) jTblRegistros.getModel()).selecionarRegistros(selecionar);
    }
}