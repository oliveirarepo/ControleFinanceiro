/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.cadastro;

import Uteis.SuperDao;
import bean.cadastro.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Thiago
 */
public class CategoriaDao extends SuperDao {

    public void salvar(Categoria categoria) throws SQLException {
        if (categoria.getIdCategoria() == 0) {
            incluir(categoria);
        }
        alterar(categoria);
    }

    private void incluir(Categoria categoria) throws SQLException {
        String sql = "insert into categoria (descricao_categoria) values (?)";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, categoria.getDescricaoCategoria());
            pstm.execute();
            categoria.setIdCategoria(obterUltimoCodigoGeradoTabela("categoria", "id_categoria", conn));
            JOptionPane.showMessageDialog(null, "Categoria Cadastrado com sucesso!!!");
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());

        }
    }

    private void alterar(Categoria categoria) throws SQLException {
        String sql = "update categoria set descricao_categoria = ?  where id_categoria = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, categoria.getDescricaoCategoria());
            pstm.setInt(2, categoria.getIdCategoria());
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Categoria Alterado com sucesso!!");
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    public void getListaCategoria(List<Categoria> lstCategoria) throws SQLException {
        String sql = "select id_categoria,descricao_categoria from categoria";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            try (ResultSet dados = pstm.executeQuery()) {
                while (dados.next()) {
                    lstCategoria.add(montarDados(dados));
                }
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());

        }
    }

    public List<Categoria> getList() throws SQLException {
        List<Categoria> lstCategoria = new ArrayList<>();
        String sql = "select id_categoria,descricao_categoria from categoria";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            try (ResultSet dados = pstm.executeQuery()) {
                while (dados.next()) {
                    lstCategoria.add(montarDados(dados));
                }
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());

        }
        return lstCategoria;
    }

    public Categoria consultarChavePrimaria(int idCategoria) throws SQLException {
        String sql = "select id_categoria,descricao_categoria from categoria where  id_categoria = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, idCategoria);
            try (ResultSet dados = pstm.executeQuery()) {
                while (dados.next()) {
                    return montarDados(dados);
                }
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());

        }
        return new Categoria();
    }

    private Categoria montarDados(ResultSet dados) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(dados.getInt("id_categoria"));
        categoria.setDescricaoCategoria(dados.getString("descricao_categoria"));
        return categoria;
    }

    public void excluir(Categoria categoria) throws SQLException {
        String sql = "delete from categoria where id_categoria  = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, categoria.getIdCategoria());
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Categoria excluida com sucesso!");

        } catch (Exception ex) {
            throw new SQLException(ex.getMessage());

        }
    }
}
