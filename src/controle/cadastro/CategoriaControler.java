/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.cadastro;

import view.Cadastro.CategoriaCons;
import view.Cadastro.CategoriaFrm;
import bean.cadastro.Categoria;
import Dao.cadastro.CategoriaDao;
import Uteis.SuperControl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Thaigo
 */
public class CategoriaControler extends SuperControl implements ActionListener {

    private final CategoriaCons categoriaCons = new CategoriaCons(new javax.swing.JFrame(), true);
    private CategoriaFrm categoriaFrm;
    private Categoria categoria = new Categoria();
    private final Categoria categoriaPesquisada;
//    private final CategoriaDao categoriaDao = new CategoriaDao();
    private final List<Categoria> lstCategoria = new ArrayList<>();

    public CategoriaControler(Categoria categoria) {
        this.categoriaPesquisada = categoria;
        categoriaCons.addActionListener(this);
        try {
            pesquisarCategoria();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        categoriaCons.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "novoCons":
                novoCons();
                break;
            case "alterarCategoria":
                try {
                    alterarCategoria();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "pesquisarCategoria":
                try {
                    pesquisarCategoria();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "novoFrm":
                novoFrm();
                break;
            case "salvarFrm":
                try {
                    salvarFrm();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "excluirFrm":
                try {
                    excluirFrm();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "selecionarCategoria":
                try {
                    selecionarCategoria();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
        }
    }

    private void novoCons() {
        categoria = new Categoria();
        categoriaFrm = new CategoriaFrm(new javax.swing.JFrame(), true);
        categoriaFrm.setCategoria(categoria);
        categoriaFrm.addActionListener(this);
        categoriaFrm.setVisible(true);
    }

    private void alterarCategoria() throws Exception {
        categoria = categoriaCons.getCategoriaSelecionada();
        categoriaFrm = new CategoriaFrm(new javax.swing.JFrame(), true);
        categoriaFrm.addActionListener(this);
        categoriaFrm.setCategoria(categoria);
        categoriaFrm.setVisible(true);

    }

    private void novoFrm() {
        categoria = new Categoria();
        categoriaFrm.setCategoria(categoria);
        categoriaFrm.setHabilitaBotoes(true, true, true, false);
    }

    private void salvarFrm() throws Exception {
        categoriaFrm.getCategoria();
        try {
            iniciarConexao();
            new CategoriaDao().salvar(categoria);
            if (!lstCategoria.contains(categoria)) {
                lstCategoria.add(categoria);
            }
            Collections.sort(lstCategoria, categoria);
            categoriaCons.setListaRegistros(lstCategoria);
            categoriaFrm.dispose();
        } finally {
            finalizarConexao();
        }
    }

    private void excluirFrm() throws Exception {
        if (JOptionPane.showConfirmDialog(null, "Confirma a exclusão da Categoria ?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0) {
            try {
                iniciarConexao();
                lstCategoria.remove(categoria);
                new CategoriaDao().excluir(categoria);
                categoriaCons.setListaRegistros(lstCategoria);
                categoriaFrm.dispose();
            } finally {
                finalizarConexao();
            }
        }

    }

    private void pesquisarCategoria() throws SQLException {
        try {
            iniciarConexao();
            new CategoriaDao().getListaCategoria(lstCategoria);
            Collections.sort(lstCategoria, categoria);
            categoriaCons.setListaRegistros(lstCategoria);
        } finally {
            finalizarConexao();
        }
    }

    private void selecionarCategoria() throws Exception {
        categoriaPesquisada.setIdCategoria(categoriaCons.getCategoriaSelecionada().getIdCategoria());
        categoriaPesquisada.setDescricaoCategoria(categoriaCons.getCategoriaSelecionada().getDescricaoCategoria());
        categoriaCons.dispose();
    }
}
