/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uteis;

/**
 *
 * @author Thaigo
 */

import static Uteis.SuperControleFinanceiro.conn;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRXmlDataSource;


import relatorios.Relatorios;

/**
 *
 * @author Thiago
 */
public class GerarPdf extends SuperControl {

    /*
     * gera arquivo pdf com apenas um parametro enviado para o relatorio
     * caminhoArquivo salvar ex MeusDocumento/pdf/meuArquivo (nao colocar meuArquivo.pdf)
     */
    public GerarPdf(String[] lstParametros, Object[] lstValoresParametros, String caminhoNomeArquivoSalvar, String numRelatorio, boolean visualizar, boolean xml) throws IOException, JRException, SQLException {
        if (!xml) {
            gerarPdf(lstParametros, lstValoresParametros, caminhoNomeArquivoSalvar.replaceAll(" ", ""), numRelatorio, visualizar);
        } else {
            gerarPdfBaseXml(lstParametros, lstValoresParametros, caminhoNomeArquivoSalvar, numRelatorio, visualizar);
        }
    }

    /**
     * PDF com Base XML
     *
     * @param caminhoNomeArquivoSalvar caminho para salvar arquivo pdf
     * @param numRelatorio numero do relatorio para busca em Relatorios
     * @param visualizar boolean visualizar o relatório gerado
     * @throws IOException excessão ao salvar arquivo
     * @throws JRException excessão no relatório
     */
    public GerarPdf(String caminhoNomeArquivoSalvar, String numRelatorio, boolean visualizar) throws IOException, JRException {
        gerarPdfBaseXml(caminhoNomeArquivoSalvar.replaceAll(" ", ""), numRelatorio, visualizar);
    }

    JasperPrint jasperPrint;

    private void gerarPdf(String[] lstParametros, Object[] lstValoresParametros, String caminhoArquivoCommNome, String numRelatorio, boolean visualizar) throws IOException, JRException, SQLException {
        try {
            iniciarConexao();
            HashMap parameterMap = getHashMap(lstParametros, lstValoresParametros);

            InputStream caminhoRelatorio = Relatorios.valueOf("$" + numRelatorio).getRelatorio();
            //Cria a pasta de pdf se a mesma nao existir
            File diretorio = new File(System.getProperty("user.dir") + "/TempFiles");
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }
            jasperPrint = JasperFillManager.fillReport(caminhoRelatorio, parameterMap, conn);
            try {
                JasperExportManager.exportReportToPdfFile(jasperPrint, System.getProperty("user.dir") + "/TempFiles/" + caminhoArquivoCommNome + ".pdf");
            } catch (JRException e) {
                throw new JRException("O arquivo está sendo usando por outro usuario ou Programa! " + e.getMessage());
            }

            //visualizar relatorio
            if (visualizar) {
                Runtime.getRuntime().exec("cmd /c start \"\" \"" + System.getProperty("user.dir") + "/TempFiles/" + caminhoArquivoCommNome + ".pdf\"");
            }

        } finally {
            finalizarConexao();
        }
    }

    private void gerarPdfBaseXml(String caminhoArquivoCommNome, String numRelatorio, boolean visualizar) throws IOException, JRException {

        InputStream caminhoRelatorio = Relatorios.valueOf("$" + numRelatorio).getRelatorio();
        //Cria a pasta de pdf se a mesma nao existir
        File diretorio = (new File(System.getProperty("user.dir") + "/TempFiles"));
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
        JRXmlDataSource xml = new JRXmlDataSource(System.getProperty("user.dir") + "/TempFiles/" + Relatorios.valueOf("$" + numRelatorio).getArquivoXml() + ".xml", Relatorios.valueOf("$" + numRelatorio).getPathRelatorio());
        jasperPrint = JasperFillManager.fillReport(caminhoRelatorio, new HashMap(), xml);
        try {
            JasperExportManager.exportReportToPdfFile(jasperPrint, System.getProperty("user.dir") + "/TempFiles/" + caminhoArquivoCommNome + ".pdf");
        } catch (JRException e) {
            throw new JRException("O arquivo está sendo usando por outro usuario ou Programa!");
        }

        //visualizar relatorio
        if (visualizar) {
            Runtime.getRuntime().exec("cmd /c start \"\" \"" + System.getProperty("user.dir") + "/TempFiles/" + caminhoArquivoCommNome + ".pdf\"");
        }

    }

    private void gerarPdfBaseXml(String[] parametros, Object[] valores, String caminhoArquivoCommNome, String numRelatorio, boolean visualizar) throws IOException, JRException {
        HashMap parameterMap = getHashMap(parametros, valores);
        InputStream caminhoRelatorio = Relatorios.valueOf("$" + numRelatorio).getRelatorio();
        //Cria a pasta de pdf se a mesma nao existir
        File diretorio = (new File(System.getProperty("user.dir") + "/TempFiles"));
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
        JRXmlDataSource xml = new JRXmlDataSource(System.getProperty("user.dir") + "/TempFiles/" + Relatorios.valueOf("$" + numRelatorio).getArquivoXml() + ".xml", Relatorios.valueOf("$" + numRelatorio).getPathRelatorio());
        jasperPrint = JasperFillManager.fillReport(caminhoRelatorio, parameterMap, xml);
        try {
            JasperExportManager.exportReportToPdfFile(jasperPrint, System.getProperty("user.dir") + "/TempFiles/" + caminhoArquivoCommNome + ".pdf");
        } catch (JRException e) {
            throw new JRException("O arquivo está sendo usando por outro usuario ou Programa!");
        }

        //visualizar relatorio
        if (visualizar) {
            Runtime.getRuntime().exec("cmd /c start \"\" \"" + System.getProperty("user.dir") + "/TempFiles/" + caminhoArquivoCommNome + ".pdf\"");
        }
    }

    private HashMap getHashMap(String[] lstParametros, Object[] lstValoresParametros) {
        HashMap parameterMap = new HashMap();
        for (int index = 0; index < lstParametros.length; index++) {
            if (lstValoresParametros[index] instanceof Integer) {
                parameterMap.put(lstParametros[index],  (lstValoresParametros[index]));
            } else if (lstValoresParametros[index] instanceof String) {
                parameterMap.put(lstParametros[index],  lstValoresParametros[index]);
            } else if (lstValoresParametros[index] instanceof Double) {
                parameterMap.put(lstParametros[index],  lstValoresParametros[index]);
            } else if (lstValoresParametros[index] instanceof java.util.Date) {
                parameterMap.put(lstParametros[index], (java.util.Date) lstValoresParametros[index]);
            } else if (lstValoresParametros[index] instanceof Timestamp) {
                parameterMap.put(lstParametros[index], (Timestamp) lstValoresParametros[index]);
            } else if (lstValoresParametros[index] instanceof Boolean) {
                parameterMap.put(lstParametros[index], (Boolean) lstValoresParametros[index]);
            } else if (lstValoresParametros[index] instanceof InputStream) {
                parameterMap.put(lstParametros[index], (InputStream) lstValoresParametros[index]);
            } else if (lstValoresParametros[index] instanceof Character) {
                parameterMap.put(lstParametros[index], (Character) lstValoresParametros[index]);
            }
        }
        return parameterMap;
    }
}