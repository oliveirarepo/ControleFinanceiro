/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Uteis;

import controlefinanceiro.Main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Thiago
 */
public class FabricaConexao {

//Verifica se a foto existe  
    static String url = "jdbc:firebirdsql:localhost:" + Main.config.getCaminhoBanco() + "?encoding=ISO8859_1";
    static String usuario = "SYSDBA";
    static String senha = "masterkey";
    static Connection conn;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            conn = DriverManager.getConnection(url, usuario, senha);
            return conn;
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
}
