/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.cadastro;

import Uteis.SuperControl;
import bean.cadastro.Categoria;
import bean.cadastro.ContasPagar;
import Dao.cadastro.CategoriaDao;
import Dao.movimento.MovimentoFinanceiroDao;
import Dao.cadastro.TipoContaDao;
import view.movimento.LancamentoContasFrm;
import Uteis.GerarParcelas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Thaigo
 */
public class LacamentoContasControler extends SuperControl implements ActionListener {

    private final LancamentoContasFrm contasFrm = new LancamentoContasFrm(new javax.swing.JFrame(), true);
    private ContasPagar conta = new ContasPagar();
    private final List<Categoria> lstCategoria = new ArrayList<>();
    private List<ContasPagar> lstContasPagar = new ArrayList<>();
    private final List<ContasPagar> lstConta = new ArrayList();

    public LacamentoContasControler(ContasPagar conta, List<ContasPagar> lstContas) {
        this.contasFrm.addActionListener(this);
        this.conta = conta;
        this.lstContasPagar = lstContas;
        try {
            listarCategoria();
            listaTipoConta();
            contasFrm.setConta(conta);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.contasFrm.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "lancarNovaConta":
                lancarNovaConta();
                break;
            case "salvarConta":
                try {
                    salvarConta();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "excluirConta":
                try {
                    excluirConta();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "gerarParcelas":
                try {
                    gerarParcelas();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
        }
    }

    private void lancarNovaConta() {
        conta = new ContasPagar();
        contasFrm.setConta(conta);
        
    }

    private void salvarConta() throws Exception {
        try {
            conta = contasFrm.getConta();
            if (lstConta.isEmpty() && conta.getCODIGO() == 0) {
                throw new Exception("Gere as parcelas!");
            }
            iniciarConexao();
            if (conta.getCODIGO() > 0) {
                new MovimentoFinanceiroDao().salvar(conta);
            } else {

//                lstConta.addAll(GerarParcelas.gerarParcelasContasPagar(1, conta.getCREEDOR(), contasFrm.getVezes(), conta.getDATAVENC(), conta.getDATACOMPRA(), conta.getVALORPAGO(), conta.getOPERACAO()));
                for (ContasPagar contaPagar : lstConta) {
//                    contaPagar.setCODIGO(conta.getCODIGO());
//                    contaPagar.setOPERACAO(conta.getOPERACAO());
//                    contaPagar.setCOD_OPERACAO(conta.getCOD_OPERACAO());
//                    contaPagar.setJAPAGA(conta.getJAPAGA());
//                    contaPagar.setCategoria(conta.getCategoria());
//                    contaPagar.setTipoConta(conta.getTipoConta());
                    new MovimentoFinanceiroDao().salvar(contaPagar);
                    if (lstContasPagar != null) {
                        lstContasPagar.add(contaPagar);
                    }
                }
            }
            contasFrm.dispose();
        } finally {
            finalizarConexao();
        }
    }

    private void excluirConta() throws Exception {
        if (JOptionPane.showConfirmDialog(null, "Confirma Exclusão da Conta", "Atenção ", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0) {
            try {
                iniciarConexao();
                conta = contasFrm.getConta();
                new MovimentoFinanceiroDao().Excluir(conta);
                lancarNovaConta();
            } finally {
                finalizarConexao();
            }
        }
    }

    private void listarCategoria() throws SQLException {
        try {
            iniciarConexao();
            new CategoriaDao().getListaCategoria(lstCategoria);
            contasFrm.setListaCategoria(lstCategoria);
        } finally {
            finalizarConexao();
        }
    }

    private void listaTipoConta() throws SQLException {
        try {
            iniciarConexao();
            contasFrm.setListaTipoConta(new TipoContaDao().getList());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            finalizarConexao();
        }

    }

    private void gerarParcelas() throws Exception {
        conta = contasFrm.getConta();
        lstConta.clear();
        lstConta.addAll(GerarParcelas.gerarParcelasContasPagar(1, conta.getCreedor(), contasFrm.getVezes(), conta.getDataVenc(), conta.getDataCompra(), conta.getVALORPAGO(), conta.getOPERACAO()));
        for (ContasPagar contaPagar : lstConta) {
            contaPagar.setCODIGO(conta.getCODIGO());
            contaPagar.setOPERACAO(conta.getOPERACAO());
            contaPagar.setCOD_OPERACAO(conta.getCOD_OPERACAO());
            contaPagar.setJAPAGA(conta.getJAPAGA());
            contaPagar.setCategoria(conta.getCategoria());
            contaPagar.setTipoConta(conta.getTipoConta());
        }
        contasFrm.setListaContasPagar(lstConta);
    }

}
