/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Uteis;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Thiago
 */
public class SuperDao extends SuperControleFinanceiro {

    protected int obterUltimoCodigoGeradoTabela(String tabela, String campo, Connection conn) throws SQLException {
        String sql = "Select first 1  " + campo + " From " + tabela + " order by " + campo + " DESC ";
        try (Statement pstmt = conn.prepareStatement(sql)) {
            ResultSet dados = pstmt.executeQuery(sql);
            while (dados.next()) {
                return montarDados(dados, campo);
            }
        }
        return 0;
    }

    private int montarDados(ResultSet dados, String campo) throws SQLException {
        return dados.getInt(campo);
    }
}
