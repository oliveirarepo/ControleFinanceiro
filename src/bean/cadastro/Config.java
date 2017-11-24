/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.cadastro;

/**
 *
 * @author Thiago
 */
public class Config {

    private String caminhoBanco;
    private String aparencia;

    public Config() {
        caminhoBanco = "";
    }

    public String getCaminhoBanco() {
        return caminhoBanco;
    }

    public void setCaminhoBanco(String caminhoBanco) {
        this.caminhoBanco = caminhoBanco;
    }

    public String getAparencia() {
        if (aparencia == null) {
            aparencia = "Windows";
        }
        return aparencia;
    }

    public void setAparencia(String aparencia) {
        this.aparencia = aparencia;
    }

}
