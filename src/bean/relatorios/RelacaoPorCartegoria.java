/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.relatorios;

/**
 *
 * @author th
 */
public class RelacaoPorCartegoria {

    private int idCategoria;
    private String descricao;
    private double total;
    private String totalFormat;
    private String mes;
    private int mesInt;

    public RelacaoPorCartegoria(int idCategoria, String descricao, double total, String mes, int mesInt) {
        this.idCategoria = idCategoria;
        this.descricao = descricao;
        this.total = total;
        this.mes = mes;
        this.mesInt = mesInt;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
        this.totalFormat = Uteis.Uteis.formatarMoeda(total);
    }

}
