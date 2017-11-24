/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

/**
 *
 * @author th
 */
public class Cliente {

    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
    private String cidade;
    private String cep;
    private String uf;
    private String enderecoBairro;
    private String numeResid;
    private String estadoCivil;
//    private static final String defaultValue ="";

//    public Cliente() {
//        nome = defaultValue;
//        cpf = defaultValue;
//        telefone = defaultValue;
//        endereco = defaultValue;
//        cidade = defaultValue;
//        cep = defaultValue;
//        uf = defaultValue;
//        enderecoBairro = defaultValue;
//        numeResid = defaultValue;
//        estadoCivil = defaultValue;
//    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getEnderecoBairro() {
        return enderecoBairro;
    }

    public void setEnderecoBairro(String enderecoBairro) {
        this.enderecoBairro = enderecoBairro;
    }

    public String getNumeResid() {
        return numeResid;
    }

    public void setNumeResid(String numeResid) {
        this.numeResid = numeResid;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

}
