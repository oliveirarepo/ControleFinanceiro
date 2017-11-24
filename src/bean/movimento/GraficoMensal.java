/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.movimento;

/**
 *
 * @author th
 */
public class GraficoMensal {

    private String mes;
    private int idMes;
    private float saldo;
    private float creditos;
    private float debitos;
    private float indiceEndividamento;
    public void limpar(){
        this.mes = null;
    }

    public GraficoMensal(String mes,int idMes) {
        this.mes = mes;
        this.idMes = idMes;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getCreditos() {
        return creditos;
    }

    public void setCreditos(float creditos) {
        this.creditos = creditos;
    }

    public float getDebitos() {
        return debitos;
    }

    public void setDebitos(float debitos) {
        this.debitos = debitos;
    }

    public void somaCreditos(double credito) {
        this.creditos += credito;
    }

    public void somaDebitos(double debito) {
        this.debitos += debito;
    }

    public double getIndiceEndividamento() {
        return indiceEndividamento;
    }

    public void setIndiceEndividamento(float indiceEndividamento) {
        if(Float.isNaN(indiceEndividamento)){
            indiceEndividamento = 0;
        }
        this.indiceEndividamento = indiceEndividamento;
    }

    public int getIdMes() {
        return idMes;
    }

    public void setIdMes(int idMes) {
        this.idMes = idMes;
    }

}
