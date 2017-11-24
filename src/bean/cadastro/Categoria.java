/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.cadastro;

import bean.movimento.Select;

/**
 *
 * @author Thiago
 */
public class Categoria extends Select {

    private int idCategoria;
    private String descricaoCategoria;
    private float somaValores;

    public Categoria() {
    }

    public Categoria(int idCategoria, String descricao) {
        this.idCategoria = idCategoria;
        this.descricaoCategoria = descricao;
    }

    public Categoria(int idCategoria, String descricao, float soma) {
        this(idCategoria, descricao);
        this.somaValores = soma;
    }

    public void limpar() {
        descricaoCategoria = null;
    }

    public String getDescricaoCategoria() {
        if (descricaoCategoria == null) {
            descricaoCategoria = "";
        }
        return descricaoCategoria;
    }

    public void setDescricaoCategoria(String descricaoCategoria) {
        this.descricaoCategoria = descricaoCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public double getSomaValores() {
        return somaValores;
    }

    public void setSomaValores(float somaValores) {
        this.somaValores = somaValores;
    }

    public void somar(double valor) {
        this.somaValores += valor;
    }

    @Override
    public int getId() {
        return idCategoria;
    }

    @Override
    public String getDescricao() {
        return descricaoCategoria;
    }

}
