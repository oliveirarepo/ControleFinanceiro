/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.cadastro;

import Uteis.SuperDao;
import bean.cadastro.Conta;
import Uteis.FabricaConexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiago
 */
public class ContaDao extends SuperDao {

    public void salvar(Conta conta) throws SQLException {
        if (conta.getIdConta() == 0) {
            incluir(conta);
            return;
        }
        alterar(conta);
    }

    private void incluir(Conta conta) throws SQLException {
        String sql = "insert into conta (descricao,saldo,status) values (?,?,?)";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, conta.getDescricao());
            pstm.setFloat(2, conta.getSaldo());
            pstm.setString(3, "A");
            pstm.execute();

            conta.setIdConta(obterUltimoCodigoGeradoTabela("conta", "idConta", conn));
        } catch (SQLException ex) {
            throw new SQLException("Erro ao incluir conta :" + ex.getMessage());
        }
    }

    private void alterar(Conta conta) throws SQLException {
        String sql = "update conta set descricao = ?, saldo = ? where idConta = ? ";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, conta.getDescricao());
            pstm.setFloat(2, conta.getSaldo());
            pstm.setInt(3, conta.getIdConta());
            pstm.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao alterar conta :" + ex.getMessage());
        }
    }

    public void excluir(Conta conta) throws SQLException {
        if (conta.getIdConta() == 1) {
            throw new SQLException("Conta 1 Padrão não  pode ser excluida");
        }
        String sql = "update conta set status = 'I' where idConta = ? ";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, conta.getIdConta());
            pstm.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao excluir conta :" + ex.getMessage());
        }
    }

    public List<Conta> getList() throws SQLException, SQLException, SQLException, SQLException {
        List<Conta> lstConta = new ArrayList();
        String sql = "select idConta,descricao,saldo from conta where status = 'A'";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            try (ResultSet dados = pstm.executeQuery()) {
                while (dados.next()) {
                    lstConta.add(montarDados(dados));
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("erro ao obter lista de conta :" + ex.getMessage());
        }
        return lstConta;
    }

    private Conta montarDados(ResultSet dados) throws SQLException {
        Conta conta = new Conta();
        conta.setIdConta(dados.getInt("idConta"));
        conta.setDescricao(dados.getString("descricao"));
        conta.setSaldo(dados.getFloat("saldo"));
        return conta;
    }

    public Conta getConta(int idConta) throws SQLException {
        conn = FabricaConexao.getConnection();
        String sql = "select idConta,descricao,saldo from conta where idConta = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, idConta);
            try (ResultSet dados = pstm.executeQuery()) {
                while (dados.next()) {
                    return montarDados(dados);
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("erro ao obter conta :" + ex.getMessage());
        }
        return new Conta();
    }

}
