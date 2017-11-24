/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.movimento;

import Dao.cadastro.CategoriaDao;
import Uteis.SuperDao;
import bean.cadastro.ContasPagar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Thaigo
 */
public class MovimentoFinanceiroDao extends SuperDao {

    public void salvar(ContasPagar conta) throws Exception {
        if (conta.getCODIGO() == 0) {
            incluir(conta);
        } else {
            alterar(conta);
        }
    }

    private void incluir(ContasPagar conta) throws Exception {
        String sql = "insert into MOVIMENTOFINANCEIRO (CREEDOR ,COD_OPERACAO,OPERACAO, DATACOMPRA,"
                + " DATAVENC, VALORCOMPRA, DESCONTO, VALORPAGO,JAPAGA,ID_CATEGORIA,"
                + "IDTIPOCONTA,TIPOCONTA,parcela,totalParcela) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, conta.getCreedor());
            pstm.setInt(2, conta.getCOD_OPERACAO());
            pstm.setString(3, conta.getOPERACAO());
            pstm.setTimestamp(4, new java.sql.Timestamp(new Date(conta.getDataCompra().getTime()).getTime()));
            pstm.setTimestamp(5, new java.sql.Timestamp(new Date(conta.getDataVenc().getTime()).getTime()));
            pstm.setDouble(6, conta.getVALORCOMPRA());
            pstm.setDouble(7, conta.getDESCONTO());
            pstm.setDouble(8, conta.getVALORPAGO());
            pstm.setString(9, String.valueOf(conta.getJAPAGA()));
            pstm.setInt(10, conta.getCategoria().getIdCategoria());
            pstm.setInt(11, conta.getTipoConta().getIdTipo());
            pstm.setString(12, conta.getTipoConta().getDescricao());
            pstm.setInt(13, conta.getParcela());
            pstm.setInt(14, conta.getTotalParcelas());
            pstm.execute();
            conta.setCODIGO(obterUltimoCodigoGeradoTabela("MOVIMENTOFINANCEIRO", "CODIGO", conn));

        } catch (SQLException ex) {
            throw new SQLException("erro ao incluir movimento financeiro:" + ex.getMessage());
        }
    }

    private void alterar(ContasPagar conta) throws SQLException {
        String sql = "update MOVIMENTOFINANCEIRO set CREEDOR = ? ,COD_OPERACAO = ?,OPERACAO =?, DATACOMPRA =?,"
                + " DATAVENC =?, VALORCOMPRA=?, DESCONTO=?, VALORPAGO=?,JAPAGA=?,"
                + "ID_CATEGORIA=?, IDTIPOCONTA = ?, TIPOCONTA = ?,parcela = ? , totalParcela = ? where codigo = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, conta.getCreedor());
            pstm.setInt(2, conta.getCOD_OPERACAO());
            pstm.setString(3, conta.getOPERACAO());
            pstm.setTimestamp(4, new java.sql.Timestamp(conta.getDataCompra().getTime()));
            pstm.setTimestamp(5, new java.sql.Timestamp(conta.getDataVenc().getTime()));
            pstm.setDouble(6, conta.getVALORCOMPRA());
            pstm.setDouble(7, conta.getDESCONTO());
            pstm.setDouble(8, conta.getVALORPAGO());
            pstm.setString(9, String.valueOf(conta.getJAPAGA()));
            pstm.setInt(10, conta.getCategoria().getIdCategoria());
            pstm.setInt(11, conta.getTipoConta().getIdTipo());
            pstm.setString(12, conta.getTipoConta().getDescricao());
            pstm.setInt(13, conta.getParcela());
            pstm.setInt(14, conta.getTotalParcelas());
            pstm.setInt(15, conta.getCODIGO());
            pstm.execute();

        } catch (SQLException ex) {
            throw new SQLException("erro ao alterar movimento financeiro:" + ex.getMessage());
        }
    }

    public void Excluir(ContasPagar conta) throws SQLException {
        String sql = "DELETE FROM movimentofinanceiro where CODIGO = ? ";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, conta.getCODIGO());
            pstm.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro ao tentar excluir registro" + ex.getMessage());
        }
    }

    public void baixarDocumento(ContasPagar conta) throws SQLException {
        String sql = "update movimentofinanceiro set japaga = 'S' where codigo = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, conta.getCODIGO());
            pstm.execute();
        } catch (SQLException ex) {
            throw new SQLException("erro ao baixar conta :" + ex.getMessage());
        }
    }

    public List pesquisarPorData(Date dataInicial, Date dataFinal, String operacao, String japaga) throws SQLException {
        List<ContasPagar> lstConta = new ArrayList<>();
        String sql = "select codigo,dataVenc,creedor,cod_operacao,valorPago,japaga,"
                + "id_categoria,dataCompra,idtipoconta,tipoconta from movimentofinanceiro "
                + " where dataVenc between ? and ? and cod_operacao like ? and japaga like ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setTimestamp(1, new java.sql.Timestamp(dataInicial.getTime()));
            pstm.setTimestamp(2, new java.sql.Timestamp(dataFinal.getTime()));
            pstm.setString(3, operacao);
            pstm.setString(4, japaga);
            try (ResultSet dados = pstm.executeQuery()) {
                while (dados.next()) {
                    lstConta.add(montarDadosSimples(dados, conn));
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("erro ao baixar conta :" + ex.getMessage());
        }

        return lstConta;
    }

    public void getListaContasPagar(LinkedList<ContasPagar> lstConta) throws SQLException {
        String sql = "select codigo,dataVenc,creedor,cod_operacao,valorPago,japaga,"
                + "movimentoFinanceiro.id_categoria,operacao,dataCompra,idtipoconta,tipoconta,DESCRICAO_CATEGORIA,parcela,totalParcela"
                + " from movimentofinanceiro left join categoria on (movimentoFinanceiro.id_categoria = categoria.id_categoria) ";
        try (PreparedStatement pstm = conn.prepareStatement(sql); ResultSet dados = pstm.executeQuery()) {
            while (dados.next()) {
                ContasPagar conta = new ContasPagar(dados.getInt("Codigo"),
                        dados.getString("creedor"), dados.getInt("cod_operacao"),
                        dados.getDate("dataCompra"), dados.getDate("dataVenc"),
                        dados.getFloat("valorpago"), dados.getString("JAPAGA").charAt(0),
                        dados.getInt("idtipoconta"), dados.getString("tipoconta"),
                        dados.getInt("id_categoria"), dados.getString("operacao"),
                        dados.getString("DESCRICAO_CATEGORIA"), dados.getInt("parcela"), dados.getInt("totalParcela"));
                lstConta.add(conta);
            }
        } catch (SQLException ex) {
            throw new SQLException("erro ao buscar contas Pagar" + ex.getMessage());
        }
    }

    public List pesquisarPorDataParaGrafico(Date dataInicial, Date dataFinal, String operacao, String japaga) throws SQLException {
        List<ContasPagar> lstConta = new ArrayList<>();
        String sql = "select dataVenc,cod_operacao,valorPago"
                + " from movimentofinanceiro "
                + " where dataVenc between ? and ? and cod_operacao like ? and japaga like ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setTimestamp(1, new java.sql.Timestamp(dataInicial.getTime()));
            pstm.setTimestamp(2, new java.sql.Timestamp(dataFinal.getTime()));
            pstm.setString(3, operacao);
            pstm.setString(4, japaga);
            try (ResultSet dados = pstm.executeQuery()) {
                while (dados.next()) {
                    ContasPagar conta = new ContasPagar();
                    conta.setCOD_OPERACAO(dados.getInt("cod_operacao"));
                    conta.setDataVenc(dados.getDate("dataVenc"));
                    conta.setVALORPAGO(dados.getFloat("valorpago"));
                    lstConta.add(conta);
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro ao montar dados do grafico!:" + ex.getMessage());
        }
        return lstConta;
    }

    public List pesquisarPorData(Date dataInicial, Date dataFinal) throws SQLException {
        List<ContasPagar> lstConta = new ArrayList<>();
        String sql = "select * from MOVIMENTOFINANCEIRO "
                + "left join CATEGORIA on (MovimentoFinanceiro.id_categoria = categoria.id_categoria) "
                + "where movimentofinanceiro.dataVenc between ? and ? order by dataVenc";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setTimestamp(1, new java.sql.Timestamp(dataInicial.getTime()));
            pstm.setTimestamp(2, new java.sql.Timestamp(dataFinal.getTime()));
            try (ResultSet dados = pstm.executeQuery()) {

                while (dados.next()) {
                    ContasPagar conta = new ContasPagar();
                    conta.setCOD_OPERACAO(dados.getInt("cod_operacao"));
                    conta.setDataVenc(dados.getDate("dataVenc"));
                    conta.setVALORPAGO(dados.getFloat("valorpago"));
                    conta.getCategoria().setDescricaoCategoria(dados.getString("DESCRICAO_CATEGORIA"));
                    lstConta.add(conta);
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro ao montar dados do grafico!:" + ex.getMessage());
        }

        return lstConta;
    }

    private ContasPagar montarDadosSimples(ResultSet dados, Connection conn) throws SQLException {
        ContasPagar conta = new ContasPagar();
        conta.setCODIGO(dados.getInt("Codigo"));
        conta.setCreedor(dados.getString("creedor"));
        conta.setCOD_OPERACAO(dados.getInt("cod_operacao"));
        conta.setDataCompra(dados.getDate("dataCompra"));
        conta.setDataVenc(dados.getDate("dataVenc"));
        conta.setVALORPAGO(dados.getFloat("valorpago"));
        conta.setJAPAGA(dados.getString("JAPAGA").charAt(0));
        conta.getTipoConta().setIdTipo(dados.getInt("idtipoconta"));
        conta.getTipoConta().setDescricao(dados.getString("tipoconta"));
        conta.getCategoria().setIdCategoria(dados.getInt("id_categoria"));

        return conta;
    }

    public List consultarPorCreedor(String creedor, String operacao, String jaPaga) throws SQLException {
        List<ContasPagar> lstConta = new ArrayList();
        String sql = "select codigo,dataVenc,creedor,cod_operacao,valorPago,japaga,"
                + "id_categoria,dataCompra,idtipoconta,tipoconta from movimentofinanceiro "
                + "where creedor like ? and cod_operacao like ? and japaga like ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, creedor + "%");
            pstm.setString(2, operacao);
            pstm.setString(3, jaPaga);
            try (ResultSet dados = pstm.executeQuery()) {
                while (dados.next()) {
                    lstConta.add(montarDadosSimples(dados, conn));
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("erro ao consultar movimento financeiro:" + ex.getMessage());
        }
        return lstConta;
    }

    public List consultarPorCategoria(int idCategoria, String operacao, String jaPaga, Date dataInicial, Date dataFinal) throws SQLException {
        List<ContasPagar> lstConta = new ArrayList();
        String sql = "select codigo,dataVenc,creedor,cod_operacao,valorPago,japaga,"
                + "id_categoria,dataCompra,idtipoconta,tipoconta from movimentofinanceiro "
                + "where ID_CATEGORIA = ? and cod_operacao like ? and japaga like ? and dataVenc between ? and ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, idCategoria);
            pstm.setString(2, operacao);
            pstm.setString(3, jaPaga);
            pstm.setTimestamp(4, new java.sql.Timestamp(dataInicial.getTime()));
            pstm.setTimestamp(5, new java.sql.Timestamp(dataFinal.getTime()));
            try (ResultSet dados = pstm.executeQuery()) {
                while (dados.next()) {
                    lstConta.add(montarDadosSimples(dados, conn));
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("erro ao consultar por categoria :" + ex.getMessage());
        }
        return lstConta;
    }

    public List consultarPorTipoConta() throws SQLException {
        List<ContasPagar> lstConta = new ArrayList();
        String sql = "select codigo,dataVenc,creedor,cod_operacao,valorPago,japaga,"
                + "id_categoria,dataCompra,idtipoconta,tipoconta from movimentofinanceiro ";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            try (ResultSet dados = pstm.executeQuery()) {
                while (dados.next()) {
                    lstConta.add(montarDadosSimples(dados, conn));
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("erro ao consultar por tipo de conta :" + ex.getMessage());
        }
        return lstConta;
    }

    public void consultarPorChavePrimaria(ContasPagar conta) throws SQLException {
        String sql = "select codigo,dataVenc,creedor,cod_operacao,operacao,datacompra,valorcompra,"
                + "desconto,valorpago,japaga,id_categoria,"
                + "idtipoconta,tipoconta,parcela,totalParcela from movimentofinanceiro where codigo = ? ";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, conta.getCODIGO());
            try (ResultSet dados = pstm.executeQuery()) {
                while (dados.next()) {
                    conta.setCODIGO(dados.getInt("codigo"));
                    conta.setDataVenc(dados.getDate("datavenc"));
                    conta.setCreedor(dados.getString("creedor"));
                    conta.setCOD_OPERACAO(dados.getInt("cod_operacao"));
                    conta.setOPERACAO(dados.getString("operacao"));
                    conta.setDataCompra(dados.getDate("datacompra"));
                    conta.setVALORCOMPRA(dados.getFloat("valorcompra"));
                    conta.setDESCONTO(dados.getFloat("desconto"));
                    conta.setVALORPAGO(dados.getFloat("valorpago"));
                    conta.setJAPAGA(dados.getString("japaga").charAt(0));
                    conta.getTipoConta().setIdTipo(dados.getInt("idtipoconta"));
                    conta.getTipoConta().setDescricao(dados.getString("tipoconta"));
                    conta.setParcela(dados.getInt("parcela"));
                    conta.setTotalParcelas(dados.getInt("totalParcela"));
                    conta.setCategoria(new CategoriaDao().consultarChavePrimaria(dados.getInt("id_categoria")));

                }
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
}
