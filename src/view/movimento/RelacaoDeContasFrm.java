/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.movimento;

import CellRenderer.PadraoCellRenderer;
import CellRenderer.RelacaoDeContasCellRenderer;
import ColumnModel.GraficoMensalColumnModel;
import ColumnModel.RelacaoDeContasColumnModel;
import ColumnModel.SomaCategoriaColumnModel;
import ColumnModel.SomaTipoColumnModel;
import Comparator.CategoriaComparator;
import Comparator.SomaTipoContaComparator;
import TableModel.GraficoTableModel;
import bean.cadastro.Categoria;
import bean.cadastro.ContasPagar;
import bean.cadastro.TipoConta;
import TableModel.RelacaoDeContasTableModel;
import TableModel.SomaCategoriaTableModel;
import TableModel.SomtaTipoContaTableModel;
import Uteis.FieldListener;
import Uteis.UpperCaseLimitado;
import Uteis.Uteis;
import bean.movimento.GraficoMensal;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import Uteis.MouseListener;
import Uteis.ValidarCampos;
import bean.movimento.Select;
import controlefinanceiro.cellEditor.SelecionaTipoContaCellEditor;
import controlefinanceiro.cellRenderer.SelecionaTipoContaTableCellRenderer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Thaigo
 */
public class RelacaoDeContasFrm extends javax.swing.JDialog {

    private final JButton jBtnAutoPesquisaGrafico = new JButton();
    private final JButton jBtnSoma = new JButton();
    private final JButton jBtnOrdenar = new JButton();
    private final JButton jBtnFiltrarMensal = new JButton();
    private final JButton jBtnFiltrarCategoria = new JButton();
    private final JButton jBtnFiltrarTipoConta = new JButton();
    private final Color colorFundo = new java.awt.Color(231, 239, 249);

    /**
     * Creates new form RelacaoDeContasFrm
     */
    private int ordem = 0;
    private FontMetrics fm;
    private static RelacaoDeContasFrm form;

    public static RelacaoDeContasFrm getForm() {
        if (form == null) {
            form = new RelacaoDeContasFrm(new javax.swing.JFrame(), true);
        }
        return form;
    }

    public RelacaoDeContasFrm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
//        setBounds(JFrame.MAXIMIZED_BOTH);
//        this.getRootPane().getContentPane().setBackground(new java.awt.Color(215, 228, 242));antiga cor
        this.getRootPane().getContentPane().setBackground(colorFundo);
        jPanel2.setBackground(colorFundo);
        jPanel3.setBackground(colorFundo);
        jPanel4.setBackground(colorFundo);
        jPanel5.setBackground(colorFundo);
        jPanel6.setBackground(colorFundo);
        jPanel7.setBackground(colorFundo);
        jPanel1.setBackground(colorFundo);
        jPanel8.setBackground(colorFundo);
        jChbFiltrarPorCategoria.setBackground(colorFundo);
        jChbFiltrarTipoConta.setBackground(colorFundo);
        jPanel1.setToolTipText(null);
        this.setTitle("Controle Financeiro - Relação de Contas");
//        this.setResizable(false);
        setTableHeader();
        setTableHeaderCategoria();
        setTableHeaderTipoConta();

        jTblGrafico.setDefaultRenderer(GraficoMensal.class, new PadraoCellRenderer());
        jTblCategoria.setDefaultRenderer(Categoria.class, new PadraoCellRenderer());
        jTblSomaTipoConta.setDefaultRenderer(TipoConta.class, new PadraoCellRenderer());

        jCmbDataInicial.setDate(new Date());
        jCmbDataFinal.setDate(new Date());
        jTxfCreedor.setDocument(new UpperCaseLimitado(45));
        jTxfCreedor.addFocusListener(new FieldListener());

        jBtnAutoPesquisaGrafico.setActionCommand("autoPesquisaGrafico");
        jBtnSoma.setActionCommand("somaValores");
        jBtnOrdenar.setActionCommand("ordernarRegistro");
        jBtnFiltrarMensal.setActionCommand("filtrarMensal");
        jBtnFiltrarCategoria.setActionCommand("filtrarCategoria");
        jBtnFiltrarTipoConta.setActionCommand("filtrarTipoConta");

        jTblRegistros.setAutoCreateColumnsFromModel(false);
        fm = jTblRegistros.getFontMetrics(jTblRegistros.getFont());
        jTblRegistros.setColumnModel(new RelacaoDeContasColumnModel(fm, 0));
        jTblRegistros.getSelectionModel().addListSelectionListener(jTblRegistros);

        jTblSomaTipoConta.setAutoCreateColumnsFromModel(false);
        FontMetrics fmt = jTblSomaTipoConta.getFontMetrics(jTblSomaTipoConta.getFont());
        jTblSomaTipoConta.setColumnModel(new SomaTipoColumnModel(fmt, 0));
        jTblSomaTipoConta.getSelectionModel().addListSelectionListener(jTblSomaTipoConta);

        jTblCategoria.setAutoCreateColumnsFromModel(false);
        FontMetrics fmc = jTblCategoria.getFontMetrics(jTblCategoria.getFont());
        jTblCategoria.setColumnModel(new SomaCategoriaColumnModel(fmc, 0));
        jTblCategoria.getSelectionModel().addListSelectionListener(jTblCategoria);

        jTblGrafico.setAutoCreateColumnsFromModel(false);
        FontMetrics fmMensal = jTblGrafico.getFontMetrics(jTblGrafico.getFont());
        jTblGrafico.setColumnModel(new GraficoMensalColumnModel(fmMensal));
        jTblGrafico.getSelectionModel().addListSelectionListener(jTblGrafico);

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "escape");
        this.getRootPane().getActionMap().put("escape", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
//        GregorianCalendar calenda =;
//        jSpAnoGrafico.setValue(Integer.valueOf(new Date().toString().substring(24, 29)));
        jSpAnoGrafico.setValue(GregorianCalendar.getInstance().get(GregorianCalendar.YEAR));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTblGrafico = new javax.swing.JTable();
        jSpAnoGrafico = new javax.swing.JSpinner();
        jChbFiltrarPorCategoria = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTblCategoria = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTblSomaTipoConta = new javax.swing.JTable();
        jChbFiltrarTipoConta = new javax.swing.JCheckBox();
        jBtnGraficoAnulTipoConta = new javax.swing.JButton();
        jBtnRelacaoGRaficoAnual = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jBtnNovoLancamento = new javax.swing.JButton();
        jBtnEditar = new javax.swing.JButton();
        jBtnExcluir = new javax.swing.JButton();
        jBtnBaixarNota = new javax.swing.JButton();
        jTxfTotalGeral = new javax.swing.JTextField();
        jTxfSoma = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTxfSaldo = new javax.swing.JTextField();
        jBtnBaixaParcial = new javax.swing.JButton();
        jLblRegistros = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCmbPagarReceber = new javax.swing.JComboBox();
        jCmbPagarPagas = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jCmbDataInicial = new com.toedter.calendar.JDateChooser();
        jCmbDataFinal = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jTxfTipoConta = new javax.swing.JTextField();
        jBtnPesquisaTipoConta = new javax.swing.JButton();
        jBtnSelecionaCategoria = new javax.swing.JButton();
        jTxfCategoria = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jTxfCreedor = new javax.swing.JTextField();
        jBtnPesquisar = new javax.swing.JButton();
        jBtnImpressaoGrafica = new javax.swing.JButton();
        jBtnImpressaoGraficaCategoria = new javax.swing.JButton();
        jBtnIndiceEndividamento = new javax.swing.JButton();
        jPnlCAbecalho = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblRegistros = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Controle Financeiro - Relacao de Contas");

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("Ano");

        jTblGrafico.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jTblGrafico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mes", "Saldo"
            }
        ));
        jTblGrafico.setGridColor(new java.awt.Color(0, 0, 0));
        jTblGrafico.setRowHeight(20);
        jTblGrafico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblGraficoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTblGrafico);

        jSpAnoGrafico.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jSpAnoGrafico.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpAnoGraficoStateChanged(evt);
            }
        });

        jChbFiltrarPorCategoria.setText("Categoria");
        jChbFiltrarPorCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChbFiltrarPorCategoriaActionPerformed(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTblCategoria.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jTblCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo", "Valor"
            }
        ));
        jTblCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblCategoriaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTblCategoria);

        jTblSomaTipoConta.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jTblSomaTipoConta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "Valor"
            }
        ));
        jTblSomaTipoConta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblSomaTipoContaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTblSomaTipoConta);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jScrollPane3)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
        );

        jChbFiltrarTipoConta.setText("Tipo Conta");
        jChbFiltrarTipoConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChbFiltrarTipoContaActionPerformed(evt);
            }
        });

        jBtnGraficoAnulTipoConta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Blue pin.png"))); // NOI18N
        jBtnGraficoAnulTipoConta.setText("Anual Tipo Conta");
        jBtnGraficoAnulTipoConta.setActionCommand("relacaoGraficoAnualTipoConta");

        jBtnRelacaoGRaficoAnual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Yellow pin.png"))); // NOI18N
        jBtnRelacaoGRaficoAnual.setText("Anual Categoria");
        jBtnRelacaoGRaficoAnual.setActionCommand("relacaoGraficoAnual");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpAnoGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jChbFiltrarPorCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jChbFiltrarTipoConta)
                .addGap(2, 2, 2)
                .addComponent(jBtnRelacaoGRaficoAnual)
                .addGap(2, 2, 2)
                .addComponent(jBtnGraficoAnulTipoConta))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtnRelacaoGRaficoAnual)
                        .addComponent(jBtnGraficoAnulTipoConta))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jSpAnoGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jChbFiltrarPorCategoria)
                        .addComponent(jChbFiltrarTipoConta)))
                .addGap(1, 1, 1)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jBtnNovoLancamento.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jBtnNovoLancamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Mais.png"))); // NOI18N
        jBtnNovoLancamento.setText("Novo");
        jBtnNovoLancamento.setActionCommand("lancarNovaNota");

        jBtnEditar.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jBtnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pencil_add.png"))); // NOI18N
        jBtnEditar.setText("Editar");
        jBtnEditar.setActionCommand("editarNota");

        jBtnExcluir.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jBtnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        jBtnExcluir.setText("Excluir");
        jBtnExcluir.setActionCommand("excluirNota");

        jBtnBaixarNota.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jBtnBaixarNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1Entrar.png"))); // NOI18N
        jBtnBaixarNota.setText("Baixar");
        jBtnBaixarNota.setActionCommand("baixarNota");

        jTxfTotalGeral.setBackground(new java.awt.Color(0, 0, 0));
        jTxfTotalGeral.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jTxfTotalGeral.setForeground(new java.awt.Color(0, 255, 204));
        jTxfTotalGeral.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTxfTotalGeral.setText("0,00");

        jTxfSoma.setBackground(new java.awt.Color(0, 0, 0));
        jTxfSoma.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jTxfSoma.setForeground(new java.awt.Color(51, 255, 0));
        jTxfSoma.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTxfSoma.setText("0,00");

        jLabel18.setText("Soma");

        jLabel19.setText("Total");

        jLabel20.setText("Conta");

        jTxfSaldo.setEditable(false);
        jTxfSaldo.setBackground(new java.awt.Color(0, 0, 0));
        jTxfSaldo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTxfSaldo.setForeground(new java.awt.Color(255, 255, 255));
        jTxfSaldo.setText("0,00");
        jTxfSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxfSaldoActionPerformed(evt);
            }
        });

        jBtnBaixaParcial.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jBtnBaixaParcial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1Entrar.png"))); // NOI18N
        jBtnBaixaParcial.setText("Baixa Parcial");
        jBtnBaixaParcial.setActionCommand("baixaParcial");

        jLblRegistros.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLblRegistros.setText("0000");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jBtnNovoLancamento)
                .addGap(2, 2, 2)
                .addComponent(jBtnEditar)
                .addGap(2, 2, 2)
                .addComponent(jBtnExcluir)
                .addGap(2, 2, 2)
                .addComponent(jBtnBaixarNota)
                .addGap(2, 2, 2)
                .addComponent(jBtnBaixaParcial)
                .addGap(2, 2, 2)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTxfSoma, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTxfTotalGeral, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel20)
                .addGap(5, 5, 5)
                .addComponent(jTxfSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLblRegistros, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jTxfSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20)
                        .addComponent(jTxfTotalGeral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19)
                        .addComponent(jTxfSoma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)
                        .addComponent(jLblRegistros))
                    .addComponent(jBtnBaixaParcial, javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jBtnBaixarNota, javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jBtnExcluir, javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jBtnEditar, javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jBtnNovoLancamento, javax.swing.GroupLayout.Alignment.CENTER))
                .addGap(2, 2, 2))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTxfSaldo, jTxfTotalGeral});

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Situação"));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel1.setText("Pagar/Receber");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel2.setText("Pagar/Pagas");

        jCmbPagarReceber.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jCmbPagarReceber.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Geral", "Pagar", "Receber" }));

        jCmbPagarPagas.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jCmbPagarPagas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Geral", "Pagar", "Pagas" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jCmbPagarReceber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCmbPagarPagas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCmbPagarReceber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCmbPagarPagas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Por Datas"));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel3.setText("Inicial");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel4.setText("Final");

        jCmbDataInicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCmbDataInicialKeyPressed(evt);
            }
        });

        jCmbDataFinal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCmbDataFinalKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCmbDataInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addComponent(jCmbDataFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(jCmbDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jCmbDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(2, 2, 2))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Categoria / Tipo de conta"));

        jTxfTipoConta.setEditable(false);
        jTxfTipoConta.setBackground(new java.awt.Color(255, 255, 255));
        jTxfTipoConta.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N

        jBtnPesquisaTipoConta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Pesquisar.png"))); // NOI18N
        jBtnPesquisaTipoConta.setActionCommand("pesquisaTipoConta");

        jBtnSelecionaCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Pesquisar.png"))); // NOI18N
        jBtnSelecionaCategoria.setActionCommand("pesquisarCategoria");

        jTxfCategoria.setEditable(false);
        jTxfCategoria.setBackground(new java.awt.Color(255, 255, 255));
        jTxfCategoria.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTxfCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                    .addComponent(jTxfTipoConta))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnPesquisaTipoConta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnSelecionaCategoria, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnSelecionaCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxfCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTxfTipoConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnPesquisaTipoConta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2))
        );

        jPanel5.setBackground(javax.swing.UIManager.getDefaults().getColor("InternalFrame.inactiveTitleGradient"));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Creedor/Situação"));

        jTxfCreedor.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jTxfCreedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxfCreedorKeyPressed(evt);
            }
        });

        jBtnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Pesquisar.png"))); // NOI18N
        jBtnPesquisar.setActionCommand("pesquisarContas");
        jBtnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPesquisarActionPerformed(evt);
            }
        });

        jBtnImpressaoGrafica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Blue pin.png"))); // NOI18N
        jBtnImpressaoGrafica.setText("Tipo Conta");
        jBtnImpressaoGrafica.setActionCommand("impressaoGrafica");

        jBtnImpressaoGraficaCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Yellow pin.png"))); // NOI18N
        jBtnImpressaoGraficaCategoria.setText("Categoria");
        jBtnImpressaoGraficaCategoria.setActionCommand("impressaoGraficaCategoria");

        jBtnIndiceEndividamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Blue pin.png"))); // NOI18N
        jBtnIndiceEndividamento.setMnemonic('I');
        jBtnIndiceEndividamento.setText("Indíce de End.");
        jBtnIndiceEndividamento.setToolTipText("Indíce de Endividamento");
        jBtnIndiceEndividamento.setActionCommand("graficoIndiceEnvidamento");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jTxfCreedor, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jBtnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jBtnImpressaoGrafica)
                        .addGap(2, 2, 2)
                        .addComponent(jBtnImpressaoGraficaCategoria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnIndiceEndividamento)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jBtnPesquisar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxfCreedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnImpressaoGrafica)
                    .addComponent(jBtnImpressaoGraficaCategoria)
                    .addComponent(jBtnIndiceEndividamento))
                .addGap(2, 2, 2))
        );

        jPnlCAbecalho.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/RelacaoDeContas.png"))); // NOI18N
        jLabel21.setText("Relação de contas");

        javax.swing.GroupLayout jPnlCAbecalhoLayout = new javax.swing.GroupLayout(jPnlCAbecalho);
        jPnlCAbecalho.setLayout(jPnlCAbecalhoLayout);
        jPnlCAbecalhoLayout.setHorizontalGroup(
            jPnlCAbecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlCAbecalhoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPnlCAbecalhoLayout.setVerticalGroup(
            jPnlCAbecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlCAbecalhoLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel21)
                .addGap(0, 0, 0))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTblRegistros.setFont(new java.awt.Font("Monospaced", 1, 11)); // NOI18N
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
        });
        jScrollPane1.setViewportView(jTblRegistros);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPnlCAbecalho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(2, 2, 2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPnlCAbecalho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTblRegistrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblRegistrosMouseClicked
        // TODO add your handling code here:/
        jBtnSoma.doClick(0);
        jLblRegistros.setText("" + jTblRegistros.getSelectedRows().length);
    }//GEN-LAST:event_jTblRegistrosMouseClicked

    private void jTxfSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxfSaldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxfSaldoActionPerformed

    private void jTxfCreedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxfCreedorKeyPressed
        // TODO add your handling code here:
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        jBtnPesquisar.doClick(0);
//        }

    }//GEN-LAST:event_jTxfCreedorKeyPressed

    private void jCmbDataInicialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCmbDataInicialKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jCmbDataInicialKeyPressed

    private void jCmbDataFinalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCmbDataFinalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCmbDataFinalKeyPressed

    private void jSpAnoGraficoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpAnoGraficoStateChanged
        // TODO add your handling code here:
        jBtnAutoPesquisaGrafico.doClick(0);
    }//GEN-LAST:event_jSpAnoGraficoStateChanged

    private void jTblGraficoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblGraficoMouseClicked
        // TODO add your handling code here:
        jBtnFiltrarMensal.doClick(0);
    }//GEN-LAST:event_jTblGraficoMouseClicked

    private void jChbFiltrarPorCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChbFiltrarPorCategoriaActionPerformed
        // TODO add your handling code here:
        jBtnAutoPesquisaGrafico.doClick(0);
    }//GEN-LAST:event_jChbFiltrarPorCategoriaActionPerformed

    private void jTblCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblCategoriaMouseClicked
        // TODO add your handling code here:
        jBtnFiltrarCategoria.doClick(0);
    }//GEN-LAST:event_jTblCategoriaMouseClicked

    private void jTblSomaTipoContaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblSomaTipoContaMouseClicked
        // TODO add your handling code here:
        jBtnFiltrarTipoConta.doClick(0);
    }//GEN-LAST:event_jTblSomaTipoContaMouseClicked

    private void jChbFiltrarTipoContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChbFiltrarTipoContaActionPerformed
        // TODO add your handling code here:
        jBtnAutoPesquisaGrafico.doClick(0);
    }//GEN-LAST:event_jChbFiltrarTipoContaActionPerformed

    private void jBtnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnPesquisarActionPerformed

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
            java.util.logging.Logger.getLogger(RelacaoDeContasFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RelacaoDeContasFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RelacaoDeContasFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RelacaoDeContasFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RelacaoDeContasFrm dialog = new RelacaoDeContasFrm(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jBtnBaixaParcial;
    private javax.swing.JButton jBtnBaixarNota;
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnExcluir;
    private javax.swing.JButton jBtnGraficoAnulTipoConta;
    private javax.swing.JButton jBtnImpressaoGrafica;
    private javax.swing.JButton jBtnImpressaoGraficaCategoria;
    private javax.swing.JButton jBtnIndiceEndividamento;
    private javax.swing.JButton jBtnNovoLancamento;
    private javax.swing.JButton jBtnPesquisaTipoConta;
    private javax.swing.JButton jBtnPesquisar;
    private javax.swing.JButton jBtnRelacaoGRaficoAnual;
    private javax.swing.JButton jBtnSelecionaCategoria;
    private javax.swing.JCheckBox jChbFiltrarPorCategoria;
    private javax.swing.JCheckBox jChbFiltrarTipoConta;
    private com.toedter.calendar.JDateChooser jCmbDataFinal;
    private com.toedter.calendar.JDateChooser jCmbDataInicial;
    private javax.swing.JComboBox jCmbPagarPagas;
    private javax.swing.JComboBox jCmbPagarReceber;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLblRegistros;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPnlCAbecalho;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSpinner jSpAnoGrafico;
    private javax.swing.JTable jTblCategoria;
    private javax.swing.JTable jTblGrafico;
    private javax.swing.JTable jTblRegistros;
    private javax.swing.JTable jTblSomaTipoConta;
    private javax.swing.JTextField jTxfCategoria;
    private javax.swing.JTextField jTxfCreedor;
    private javax.swing.JTextField jTxfSaldo;
    private javax.swing.JTextField jTxfSoma;
    private javax.swing.JTextField jTxfTipoConta;
    private javax.swing.JTextField jTxfTotalGeral;
    // End of variables declaration//GEN-END:variables

    private MouseListener mouseListerner;

    public void addActionListener(ActionListener actionSupport) {
        jBtnBaixarNota.addActionListener(actionSupport);
        jBtnEditar.addActionListener(actionSupport);
        jBtnExcluir.addActionListener(actionSupport);
        jBtnNovoLancamento.addActionListener(actionSupport);
        jBtnAutoPesquisaGrafico.addActionListener(actionSupport);
        jBtnSoma.addActionListener(actionSupport);
        jBtnOrdenar.addActionListener(actionSupport);
        jBtnFiltrarMensal.addActionListener(actionSupport);
        jBtnBaixaParcial.addActionListener(actionSupport);
        
        jBtnPesquisar.addActionListener(actionSupport);
        jBtnPesquisaTipoConta.addActionListener(actionSupport);
        jBtnSelecionaCategoria.addActionListener(actionSupport);
        jBtnImpressaoGrafica.addActionListener(actionSupport);
        jBtnImpressaoGraficaCategoria.addActionListener(actionSupport);
        jBtnRelacaoGRaficoAnual.addActionListener(actionSupport);
        jBtnGraficoAnulTipoConta.addActionListener(actionSupport);
        jBtnIndiceEndividamento.addActionListener(actionSupport);
        jBtnFiltrarCategoria.addActionListener(actionSupport);
        jBtnFiltrarTipoConta.addActionListener(actionSupport);
        mouseListerner = new MouseListener(jTblRegistros, "editarNota", actionSupport, true);
        jTblRegistros.addMouseListener(mouseListerner);

    }

    public void setRemoveActionListerner(ActionListener actionSupport) {
        jBtnBaixarNota.removeActionListener(actionSupport);
        jBtnFiltrarCategoria.removeActionListener(actionSupport);
        jBtnEditar.removeActionListener(actionSupport);
        jBtnExcluir.removeActionListener(actionSupport);
        jBtnNovoLancamento.removeActionListener(actionSupport);
        jBtnAutoPesquisaGrafico.removeActionListener(actionSupport);
        jBtnSoma.removeActionListener(actionSupport);
        jBtnOrdenar.removeActionListener(actionSupport);
        jBtnFiltrarMensal.removeActionListener(actionSupport);
        jBtnBaixaParcial.removeActionListener(actionSupport);
        jBtnPesquisar.removeActionListener(actionSupport);
        jBtnPesquisaTipoConta.removeActionListener(actionSupport);
        jBtnSelecionaCategoria.removeActionListener(actionSupport);
        jBtnImpressaoGrafica.removeActionListener(actionSupport);
        jBtnImpressaoGraficaCategoria.removeActionListener(actionSupport);
        jBtnRelacaoGRaficoAnual.removeActionListener(actionSupport);
        jBtnGraficoAnulTipoConta.removeActionListener(actionSupport);
        jBtnFiltrarTipoConta.removeActionListener(actionSupport);
        jBtnIndiceEndividamento.removeActionListener(actionSupport);
        mouseListerner.removeListerner(actionSupport);
    }

    public void removeActionListerner(ActionListener actionSupport) {
        jBtnBaixarNota.removeActionListener(actionSupport);
        jBtnEditar.removeActionListener(actionSupport);
        jBtnExcluir.removeActionListener(actionSupport);
        jBtnNovoLancamento.removeActionListener(actionSupport);
        jBtnAutoPesquisaGrafico.removeActionListener(actionSupport);
        jBtnSoma.removeActionListener(actionSupport);
        jBtnOrdenar.removeActionListener(actionSupport);
        jBtnFiltrarMensal.removeActionListener(actionSupport);
        jBtnBaixaParcial.removeActionListener(actionSupport);
        jBtnPesquisaTipoConta.removeActionListener(actionSupport);
        jBtnSelecionaCategoria.removeActionListener(actionSupport);
        jBtnImpressaoGrafica.removeActionListener(actionSupport);
        jBtnImpressaoGraficaCategoria.removeActionListener(actionSupport);
        jBtnRelacaoGRaficoAnual.removeActionListener(actionSupport);
        jBtnGraficoAnulTipoConta.removeActionListener(actionSupport);
        mouseListerner.removeListerner(actionSupport);
    }

    public void setListaRegistros(List<ContasPagar> lstConta) {
        jLblRegistros.setText("#" + lstConta.size());
        this.jTblRegistros.setModel(new RelacaoDeContasTableModel(lstConta));
        this.jTblRegistros.setDefaultRenderer(ContasPagar.class, new RelacaoDeContasCellRenderer(lstConta));
    }

    public void atualizaRegistros() {
        jTblRegistros.updateUI();
    }

    public ContasPagar getContaSelecionada() throws Exception {
        if (jTblRegistros.getSelectedRow() == -1) {
            throw new Exception("selecione um Registro na lista!");
        }
        return ((RelacaoDeContasTableModel) jTblRegistros.getModel()).getValoresConta(jTblRegistros.getSelectedRow());
    }

    public Date getDataInicial() {
        try {
            return Uteis.getDateTime(jCmbDataInicial.getDate(), 0, 00, 00);
        } catch (Exception ex) {
            return new Date(0);
        }

    }

    public Date getDataFinal() {
        try {
            return Uteis.getDateTime(jCmbDataFinal.getDate(), 23, 59, 59);
        } catch (Exception ex) {
            return new Date(2100, 0, 0);
        }
    }

    public String getAnoGrafico() {
        return jSpAnoGrafico.getValue().toString().replaceAll("\\.", "");
    }

    public int getAnoGraficoInt() {
        return ValidarCampos.getValorCampo(jSpAnoGrafico.getValue().toString().replaceAll("\\.", ""));
    }

    public void setTotalGeral(String totalGeral) {
        if (Uteis.parseDouble(totalGeral) < 0) {
            jTxfTotalGeral.setForeground(new java.awt.Color(255, 0, 102));
        } else {
            jTxfTotalGeral.setForeground(new java.awt.Color(0, 255, 204));
        }
        jTxfTotalGeral.setText(totalGeral);
    }

    public char getPagarReceber() {
        if ("Geral".equals(jCmbPagarReceber.getSelectedItem())) {
            return '%';
        } else if ("Pagar".equals(jCmbPagarReceber.getSelectedItem())) {
            return 'P';
        }
        return 'R';
    }

    public char getPagarPagas() {
        if ("Geral".equals(jCmbPagarPagas.getSelectedItem())) {
            return '%';
        } else if ("Pagar".equals(jCmbPagarPagas.getSelectedItem())) {
            return 'N';
        }
        return 'S';
    }

    public String getCreedor() {
        return jTxfCreedor.getText().toUpperCase();
    }

    public void setListaCategoria(List<Categoria> lsetCategoria) {
        jTxfCategoria.setText("");
        for (Categoria c : lsetCategoria) {
            if (!jTxfCategoria.getText().isEmpty()) {
                jTxfCategoria.setText(jTxfCategoria.getText() + ",");
            }
            jTxfCategoria.setText(jTxfCategoria.getText() + c.getDescricaoCategoria());
        }
    }

    public List<ContasPagar> getListaContasSelecionadas() {
        List<ContasPagar> lst = new ArrayList<>();
        for (int i : jTblRegistros.getSelectedRows()) {
            lst.add(((RelacaoDeContasTableModel) jTblRegistros.getModel()).getValoresConta(i));
        }
        return lst;
    }

    public void setSoma(String soma) {
        jTxfSoma.setText(soma);
        if (Uteis.parseDouble(soma) > 0) {
            jTxfSoma.setForeground(new java.awt.Color(51, 255, 0));
        } else {
            jTxfSoma.setForeground(new java.awt.Color(255, 51, 0));
        }
    }

    public void setSaldo(double saldo) {
        jTxfSaldo.setText(Uteis.formatarMoeda(saldo));
    }

    private void setTableHeader() {
        jTblRegistros.getTableHeader().addMouseListener(new java.awt.event.MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                ordem = jTblRegistros.getTableHeader().columnAtPoint(e.getPoint());
                jTblRegistros.setColumnModel(new RelacaoDeContasColumnModel(fm, ordem));
                jBtnOrdenar.doClick(0);
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

    public int getOrdem() {
        return ordem;
    }

    public void setListaTipoConta(List<TipoConta> lstTipoConta) {
        jTxfTipoConta.setText("");
        for (TipoConta c : lstTipoConta) {
            if (!jTxfTipoConta.getText().isEmpty()) {
                jTxfTipoConta.setText(jTxfTipoConta.getText() + ",");
            }
            jTxfTipoConta.setText(jTxfTipoConta.getText() + c.getDescricao());
        }
    }

    public void updateTable() {
        jTblRegistros.updateUI();
    }

    public List<ContasPagar> getLista() {
        return ((RelacaoDeContasTableModel) jTblRegistros.getModel()).getListaContasPagar();
    }

    public void setListaGrafico(List<GraficoMensal> lstGrafico) {
        jTblGrafico.setModel(new GraficoTableModel(lstGrafico));
    }

    public List<GraficoMensal> getListaGrafico() {
        return ((GraficoTableModel) jTblGrafico.getModel()).getListGrafico();
    }

    public void repaintGrafico() {
        jTblGrafico.repaint();
    }

    public int getMes() {
        return jTblGrafico.getSelectedRow();
    }

    public void setDataFim(String data) {
        try {
            jCmbDataFinal.setDate(Uteis.getDateTime(Uteis.getDate(data), 23, 59, 59));
//            jCmbDataFinal = new JDateChooser(Uteis.getDateTime(Uteis.getDate(data), 23, 59, 59));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void setDataInicial(String data) {
        try {
            jCmbDataInicial.setDate(Uteis.getDateTime(Uteis.getDate(data), 23, 59, 59));
//            jCmbDataInicial = new JDateChooser(Uteis.getDateTime(Uteis.getDate(data), 23, 59, 59));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public boolean filtrarPorCategoria() {
        return jChbFiltrarPorCategoria.isSelected();
    }

    public boolean filtrarPorTipoConta() {
        return jChbFiltrarTipoConta.isSelected();
    }

    public void limpar() {
        if (jTblRegistros.getModel() instanceof RelacaoDeContasTableModel) {
            ((RelacaoDeContasTableModel) jTblRegistros.getModel()).limparLista();
        }
        ((GraficoTableModel) jTblGrafico.getModel()).limparLista();
        jTblGrafico.removeAll();
        jTblRegistros.removeAll();
    }

    public void setSomaTipo(List<TipoConta> lstTipo) {
        jTblSomaTipoConta.setModel(new SomtaTipoContaTableModel(lstTipo, jBtnFiltrarTipoConta));
        jTblSomaTipoConta.setDefaultEditor(TipoConta.class, new SelecionaTipoContaCellEditor());
        jTblSomaTipoConta.setDefaultRenderer(TipoConta.class, new SelecionaTipoContaTableCellRenderer(lstTipo));
    }

    public void setSomaCategoria(List<Categoria> lst) {
        jTblCategoria.setModel(new SomaCategoriaTableModel(lst, jBtnFiltrarCategoria));
        jTblCategoria.setDefaultEditor(Select.class, new SelecionaTipoContaCellEditor());
        jTblCategoria.setDefaultRenderer(Select.class, new SelecionaTipoContaTableCellRenderer(lst));
    }

    private void setTableHeaderCategoria() {
        jTblCategoria.getTableHeader().addMouseListener(new java.awt.event.MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                boolean isSelected;
                isSelected = ((SomaCategoriaColumnModel) jTblCategoria.getColumnModel()).getCheckBox(0).isSelected();
                int order = jTblCategoria.getTableHeader().columnAtPoint(e.getPoint());
                if (order == 0) {
                    if (jTblCategoria.getColumnModel() instanceof SomaCategoriaColumnModel) {
                        ((SomaCategoriaColumnModel) jTblCategoria.getColumnModel()).getCheckBox(0).setSelected(!isSelected);
                        ((SomaCategoriaTableModel) jTblCategoria.getModel()).selecionarRegistros(!isSelected);
                        jBtnFiltrarCategoria.doClick(0);
                    }
                }
//                jTblCategoria.setColumnModel(new SomaCategoriaColumnModel(fm, ordem));
                Collections.sort(((SomaCategoriaTableModel) jTblCategoria.getModel()).getList(), new CategoriaComparator(order));
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

    private void setTableHeaderTipoConta() {
        jTblSomaTipoConta.getTableHeader().addMouseListener(new java.awt.event.MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int order = jTblSomaTipoConta.getTableHeader().columnAtPoint(e.getPoint());
                boolean isSelected;
                isSelected = ((SomaTipoColumnModel) jTblSomaTipoConta.getColumnModel()).getCheckBox(0).isSelected();
                if (order == 0) {
                    if (jTblSomaTipoConta.getColumnModel() instanceof SomaTipoColumnModel) {
                        ((SomaTipoColumnModel) jTblSomaTipoConta.getColumnModel()).getCheckBox(0).setSelected(!isSelected);
                        ((SomtaTipoContaTableModel) jTblSomaTipoConta.getModel()).selecionarRegistros(!isSelected);
                        jBtnFiltrarTipoConta.doClick(0);
                    }
                }

//                jTblSomaTipoConta.setColumnModel(new SomaTipoColumnModel(jTblSomaTipoConta.getFontMetrics(jTblSomaTipoConta.getFont()), o));
                Collections.sort(((SomtaTipoContaTableModel) jTblSomaTipoConta.getModel()).getLista(), new SomaTipoContaComparator(order));
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

    public List<Categoria> getCategoria() {
        List<Categoria> lst = new ArrayList<>();
        if (jTblCategoria.getSelectedRow() == -1) {
            return lst;
        }
        for (int i = 0; i < jTblCategoria.getRowCount(); i++) {
            Categoria c = ((SomaCategoriaTableModel) jTblCategoria.getModel()).getCategoria(i);
            if (c.isSelected()) {
                lst.add(c);
            }
        }
        return lst;
    }

    public List<TipoConta> getTipoConta() {
        List<TipoConta> lst = new ArrayList<>();
        if (jTblSomaTipoConta.getSelectedRow() == -1) {
            return lst;
        }
        for (int i = 0; i < jTblSomaTipoConta.getRowCount(); i++) {
            TipoConta c = ((SomtaTipoContaTableModel) jTblSomaTipoConta.getModel()).getTipoConta(i);
            if (c.isSelected()) {
                lst.add(c);
            }
        }
        return lst;
    }

}
