/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.cadastro;

import Uteis.SuperDao;
import bean.cadastro.TipoConta;
import Uteis.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ThiagoUser
 */
public class TipoContaDao extends SuperDao {

    public void salvar(TipoConta tipoConta) throws SQLException, Exception {
        if (tipoConta.getIdTipo() == 0) {
            incluir(tipoConta);
        }
        alterar(tipoConta);
    }

    private void incluir(TipoConta tipoConta) throws SQLException, Exception {
        String sql = "insert into tipoConta (descricao) values (?)";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, tipoConta.getDescricao());
            pstm.execute();
            tipoConta.setIdTipo(obterUltimoCodigoGeradoTabela("tipoConta", "idtipoConta", conn));
        } catch (SQLException ex) {
            throw new SQLException("erro ao incluir tipo de conta :" + ex.getMessage());
        }
    }

    private void alterar(TipoConta tipoConta) throws SQLException {
        String sql = "update tipoConta set descricao = ? where idTipoConta = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, tipoConta.getDescricao());
            pstm.setInt(2, tipoConta.getIdTipo());
            pstm.execute();
        } catch (SQLException ex) {
            throw new SQLException("erro ao alterar tipo de conta :" + ex.getMessage());
        }

    }

    public void excluir(int idTipoConta) throws SQLException {
        String sql = "delete from tipoConta where idTipoConta = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, idTipoConta);
            pstm.execute();
        } catch (SQLException ex) {
            throw new SQLException("erro ao alterar tipo de conta :" + ex.getMessage());
        }

    }

    public List<TipoConta> getList() throws SQLException {
        List<TipoConta> lstTipoConta = new ArrayList<>();
        String sql = "select IDTIPOCONTA,DESCRICAO from TIPOCONTA";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            try (ResultSet dados = pstm.executeQuery()) {
                while (dados.next()) {
                    TipoConta tipoConta = new TipoConta();
                    tipoConta.setIdTipo(dados.getInt("idTipoConta"));
                    tipoConta.setDescricao(dados.getString("descricao"));
                    lstTipoConta.add(tipoConta);
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("erro ao obter lista de tipo de conta :" + ex.getMessage());
        }
        return lstTipoConta;
    }
}
