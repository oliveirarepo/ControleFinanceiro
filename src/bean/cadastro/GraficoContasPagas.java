/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.cadastro;

/**
 *
 * @author Thaigo
 */
public class GraficoContasPagas {

    private int idTipoConta;
    private String tipoConta;
    private double valor;
    private double perc;
    private String descricao;
    private int mes;

    public GraficoContasPagas(TipoConta tipoConta, double valor) {
        this.idTipoConta = tipoConta.getIdTipo();
        this.tipoConta = tipoConta.getDescricao();
        this.valor = valor;
    }

    public GraficoContasPagas(TipoConta tipoConta, double valor, int mes) {
        this(tipoConta, valor);
//        this.idTipoConta = tipoConta.getIdTipo();
//        this.tipoConta = tipoConta.getDescricao();
//        this.valor = valor;
        this.mes = mes;
    }

    public GraficoContasPagas(Categoria categoria, double valor) {
        this.idTipoConta = categoria.getIdCategoria();
        this.tipoConta = categoria.getDescricaoCategoria();
        this.valor = valor;
    }

    public GraficoContasPagas(Categoria categoria, double valor, int mes) {
        this(categoria, valor);
//        this.idTipoConta = categoria.getIdCategoria();
//        this.tipoConta = categoria.getDescricaoCategoria();
//        this.valor = valor;
        this.mes = mes;
    }

    public int getIdTipoConta() {
        return idTipoConta;
    }

    public void setIdTipoConta(int idTipoConta) {
        this.idTipoConta = idTipoConta;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPerc() {
        return perc;
    }

    public void setPerc(double perc) {
        this.perc = perc;
    }

    public void setSomaValor(double valorpago) {
        this.valor += valorpago;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

}
