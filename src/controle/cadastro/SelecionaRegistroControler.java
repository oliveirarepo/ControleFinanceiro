/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.cadastro;

import Uteis.SuperControl;
import bean.movimento.Select;
import bean.movimento.SelectBean;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import view.movimento.SelecionarRegistroCons;

/**
 *
 * @author Thiago
 */
public class SelecionaRegistroControler extends SuperControl implements ActionListener {

    private final List<Select> lstPesquisa;
    private final SelecionarRegistroCons selecionarRegistroCons = new SelecionarRegistroCons(new javax.swing.JFrame(), true);

    public SelecionaRegistroControler(List lst, List lstCompleta, String titulo) {
        this.lstPesquisa = lst;
        selecionarRegistroCons.addActionListener(this);
        selecionarRegistroCons.setTitulo(titulo);
        selecionarRegistroCons.setTitle(titulo);
        try {
            getListaTipoConta(true, lstCompleta);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        selecionarRegistroCons.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "confirmarVendedores":
                confirmarVendedores();
                break;
            case "selecionarConta":
                selecionarConta();
                break;
        }
    }

    private void getListaTipoConta(boolean selecionar, List<SelectBean> lst) throws SQLException {
        try {
            iniciarConexao();
            for (SelectBean v1 : lst) {
                for (Select p : lstPesquisa) {
                    if (p.getId() == v1.getId()) {
                        v1.setSelected(selecionar);
                    }
                }
            }
            selecionarRegistroCons.setListaTipoConta(lst);
        } finally {
            finalizarConexao();
        }
    }

    private void confirmarVendedores() {
        lstPesquisa.clear();
        lstPesquisa.addAll(selecionarRegistroCons.getListaVendedorSelecionado());
        selecionarRegistroCons.dispose();
    }

    private void selecionarConta() {
        selecionarRegistroCons.selecionarConta();
    }

}
