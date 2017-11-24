/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.cadastro;

import bean.cadastro.Conta;
import Dao.cadastro.ContaDao;
import Uteis.SuperControl;
import view.Cadastro.ContaCons;
import view.Cadastro.ContaFrm;
import view.Cadastro.Transferencias;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author thiago
 */
public class ContaControler extends SuperControl implements ActionListener {

    private ContaCons contaCons;
    private Conta conta;
    private final Conta contaPesquisa;
    private final ContaFrm contaFrm;
    private List<Conta> lstConta;
    private Transferencias transferencias;

    public ContaControler(Conta conta) {
        this.contaPesquisa = conta;
        this.contaCons = new ContaCons(new javax.swing.JFrame(), true);
        this.contaCons.addActionListener(this);
        contaFrm = new ContaFrm(new javax.swing.JFrame(), true);
        contaFrm.addActionListener(this);
        try {
            getList();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        contaCons.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "novoContaCons":
                novoContaCons();
                break;
            case "alterarContaCons":
                try {
                    alterarContaCons();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "pesquisaContaCons":
                pesquisaContaCons();
                break;
            case "novoContaFrm":
                novoContaFrm();
                break;
            case "salvarContaFrm":
                try {
                    salvarContaFrm();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "excluirContaFrm":
                try {
                    excluirContaFrm();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "transferenciaEntreContas":
                transferenciaEntreContas();
                break;
            case "confirmaTransferencia":
                try {
                    confirmaTransferencia();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;

        }
    }

    private void novoContaCons() {
        conta = new Conta();
        contaFrm.setConta(conta);
        contaFrm.setVisible(true);
    }

    private void alterarContaCons() throws Exception {
        conta = contaCons.getContaSelecionada();
        contaFrm.setConta(conta);
        contaFrm.setVisible(true);

    }

    private void pesquisaContaCons() {
        JOptionPane.showMessageDialog(null, "nao implementado");
    }

    private void novoContaFrm() {
        conta = new Conta();
        contaFrm.setConta(conta);
    }

    private void salvarContaFrm() throws SQLException, Exception {
        contaFrm.getConta();
        try {
            iniciarConexao();
            new ContaDao().salvar(conta);
            if (!lstConta.contains(conta)) {
                lstConta.add(conta);
            }
            contaCons.setListConta(lstConta);
            contaFrm.dispose();
        } finally {
            finalizarConexao();
        }
    }

    private void excluirContaFrm() throws SQLException {
        if (JOptionPane.showConfirmDialog(null, "Confirma a exclusçao ?", "Atenção", JOptionPane.YES_NO_OPTION) == 0) {
            try {
                iniciarConexao();
                new ContaDao().excluir(conta);
                lstConta.remove(conta);
                contaCons.setListConta(lstConta);
                contaFrm.dispose();
                showMenssagem("Conta Excluida com sucesso!");
            } finally {
                finalizarConexao();
            }
        }
    }

    private void getList() throws SQLException {
        try {
            iniciarConexao();
            lstConta = new ContaDao().getList();
            contaCons.setListConta(lstConta);

        } finally {
            finalizarConexao();
        }
    }

    private void transferenciaEntreContas() {
        transferencias = new Transferencias(new javax.swing.JFrame(), true);
        transferencias.addActionListener(this);
        transferencias.setListaContas(lstConta);
        transferencias.setVisible(true);
    }

    private void confirmaTransferencia() throws Exception {
        try {
            iniciarConexao();
            Conta deConta = transferencias.getDeConta();
            Conta paraConta = transferencias.getParaConta();
            lstConta.remove(deConta);
            lstConta.remove(paraConta);
            float valor = transferencias.getValor();
            deConta.setSaldo(deConta.getSaldo() - valor);
            paraConta.setSaldo(paraConta.getSaldo() + valor);
            new ContaDao().salvar(deConta);
            new ContaDao().salvar(paraConta);
            lstConta.add(deConta);
            lstConta.add(paraConta);
            contaCons.setListConta(lstConta);
            transferencias.dispose();
        } finally {
            finalizarConexao();
        }
    }

}
