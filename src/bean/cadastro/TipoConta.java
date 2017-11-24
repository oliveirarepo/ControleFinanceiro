/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.cadastro;

import bean.movimento.Select;

/**
 *
 * @author ThiagoUser
 */
public class TipoConta extends Select {

    private int idTipo;
    private String descricao;
    public float somaTipoConta;

    public TipoConta(TipoConta tp, float soma) {
        this(tp.getId(), tp.getDescricao());
        this.somaTipoConta = soma;
    }

    public TipoConta(int idTipoConta, String descricao, float soma) {
        this(idTipoConta, descricao);
        this.somaTipoConta = soma;
    }

    public TipoConta(int idTipoConta, String descricao) {
        this.idTipo = idTipoConta;
        this.descricao = descricao;
    }

    public void limpar() {
        descricao = null;
    }

    public TipoConta() {
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getSomaTipoConta() {
        return somaTipoConta;
    }

    public void setSomaTipoConta(float somaTipoConta) {
        this.somaTipoConta = somaTipoConta;
    }

    @Override
    public int getId() {
        return idTipo;
    }

    @Override
    public String getDescricao() {
        if (descricao == null) {
            descricao = "";
        }
        return descricao;
    }

}
