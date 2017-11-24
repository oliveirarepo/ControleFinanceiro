/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.cadastro;

import bean.cadastro.TipoConta;
import Dao.cadastro.TipoContaDao;
import Uteis.SuperControl;
import view.Cadastro.TipoContaCons;
import view.Cadastro.TipoContaFrm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author ThiagoUser
 */
public class TipoContaControler extends SuperControl implements ActionListener {

    private final TipoContaCons tipoContaCons = new TipoContaCons(new javax.swing.JFrame(), true);
    private final TipoContaFrm tipoContaFrm = new TipoContaFrm(new javax.swing.JFrame(), true);
    private TipoConta tipoConta;
    private TipoConta tipoContaPesquisa;
    private List<TipoConta> lstTipoConta = new ArrayList<>();
    private List<TipoConta> novaLista = new ArrayList<>();

    public TipoContaControler(TipoConta tipoConta) {
        this.tipoContaPesquisa = tipoConta;
        tipoContaCons.addActionListener(this);
        tipoContaFrm.addActionListener(this);
        try {
            getListTipoConta();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        tipoContaCons.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "pesquisarContaCons":
                pesquisarContaCons();
                break;
            case "alterarContaCons":
                try {
                    alterarContaCons();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "novoContaCons":
                novoContaCons();
                break;
            case "novoTipoContaFrm":
                novoTipoContaFrm();
                break;
            case "salvarTipoContaFrm":
                try {
                    salvarTipoContaFrm();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "excluirTipoContaFrm":
                try {
                    excluirTipoContaFrm();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "selecionarTipoConta":
                try {
                    selecionarTipoConta();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
        }
    }

    private void pesquisarContaCons() {
        String parametro = tipoContaCons.getParametro();
        if (parametro.length() > 0) {
            novaLista.clear();
            Pattern pattern = Pattern.compile(parametro);
            for (TipoConta tp : lstTipoConta) {
                Matcher id = pattern.matcher(String.valueOf(tp.getIdTipo()));
                Matcher descricao = pattern.matcher(tp.getDescricao());
                if (id.find() || descricao.find()) {
                    novaLista.add(tp);
                }
            }
            tipoContaCons.setListaTipoConta(novaLista);
        } else {
            tipoContaCons.setListaTipoConta(lstTipoConta);
        }
    }

    private void alterarContaCons() throws Exception {
        tipoConta = tipoContaCons.getTipoContaSelecionada();
        tipoContaFrm.setTipoConta(tipoConta);
        tipoContaFrm.setVisible(true);
    }

    private void novoContaCons() {
        tipoConta = new TipoConta();
        tipoContaFrm.setTipoConta(tipoConta);
        tipoContaFrm.setVisible(true);
    }

    private void novoTipoContaFrm() {
        tipoConta = new TipoConta();
        tipoContaFrm.setTipoConta(tipoConta);
    }

    private void salvarTipoContaFrm() throws Exception {
        try {
            iniciarConexao();
            tipoContaFrm.getTipoConta();
            new TipoContaDao().salvar(tipoConta);
            if (!lstTipoConta.contains(tipoConta)) {
                lstTipoConta.add(tipoConta);
            }
            pesquisarContaCons();
            tipoContaFrm.setVisible(false);
        } finally {
            finalizarConexao();
        }

    }

    private void excluirTipoContaFrm() throws SQLException {
        if (JOptionPane.showConfirmDialog(null, "Confirma a exclusão do registro", "Confirma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
            try {
                iniciarConexao();
                new TipoContaDao().excluir(tipoConta.getIdTipo());
                lstTipoConta.remove(tipoConta);
                pesquisarContaCons();
                tipoContaFrm.setVisible(false);
            } finally {
                finalizarConexao();
            }
        }
    }

    private void getListTipoConta() throws SQLException {
        try {
            iniciarConexao();
            lstTipoConta = new TipoContaDao().getList();
            tipoContaCons.setListaTipoConta(lstTipoConta);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            finalizarConexao();
        }
    }

    private void selecionarTipoConta() throws Exception {
        tipoContaPesquisa.setIdTipo(tipoContaCons.getTipoContaSelecionada().getIdTipo());
        tipoContaPesquisa.setDescricao(tipoContaCons.getTipoContaSelecionada().getDescricao());
        tipoContaCons.dispose();
    }

}
