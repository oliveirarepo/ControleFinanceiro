/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import java.io.InputStream;

/**
 *
 * @author Thiago
 */
public enum Relatorios {

    $1("RelacaoGraficaGastos.jasper")//relatorios de requisicao de combustivel{
            {
                @Override
                public String getArquivoXml() {
                    return "realtorio";
                }

                @Override
                public String getPathRelatorio() {
                    return "/list/ContaBean.ContasPagar";
                }
            },
    $2("$2relacaoGrafica.jasper") {
                @Override
                public String getArquivoXml() {
                    return "relatorio";
                }

                @Override
                public String getPathRelatorio() {
                    return "/list/conta";
                }
            },
    $3("$3RelacaoAnualCategoria.jasper") {
                @Override
                public String getArquivoXml() {
                    return "relatorio";
                }

                @Override
                public String getPathRelatorio() {
                    return "/list/conta";
                }
            },
    $4("indiceGraficoEndividamento.jasper");
    String nomeRelatorio;

    private Relatorios(String relatorio) {
        this.nomeRelatorio = relatorio;
    }

    public InputStream getRelatorio() {
        return this.getClass().getResourceAsStream(nomeRelatorio);
    }

    public String getArquivoXml() {
        return getArquivoXml();
    }

    public String getPathRelatorio() {
        return getPathRelatorio();
    }

}
