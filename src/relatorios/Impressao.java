/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import Uteis.FabricaConexao;
import Uteis.SuperDao;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.view.JasperViewer;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperPrintManager;
//import net.sf.jasperreports.engine.data.JRXmlDataSource;
//import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Thiago
 */
public class Impressao extends SuperDao {

    /**
     *
     * @param lstParametros parametros para serem parassados para o relatorios
     * de acordo com o tipo int,String,java.util.date
     * @param lstValoresParametros valores dos parametros em um arrays de
     * valores
     * @param numRelatorio numero do relatorio de acordo com o que foi declarado
     * na classe relatorios
     * @param nomeRelatorio nome que aparecerá na tela do relatorio
     */
    public void gerarRelatorio(String[] lstParametros, Object[] lstValoresParametros, String numRelatorio, String nomeRelatorio) throws Exception {

        Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();

        Connection conn = null;
        try {
            conn = FabricaConexao.getConnection();
            final JDialog jDailog = new JDialog(new javax.swing.JFrame(), nomeRelatorio, true);

            jDailog.setLocationRelativeTo(null);
            jDailog.setResizable(true);
            HashMap parameterMap = getHashMap(lstParametros, lstValoresParametros);

            InputStream caminhoRelatorio = Relatorios.valueOf("$" + numRelatorio).getRelatorio();

            JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoRelatorio, parameterMap, conn);
            JasperViewer jrViewer = new JasperViewer(jasperPrint, true);
            jDailog.getContentPane().add(jrViewer.getContentPane());
            jDailog.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "escape");
            jDailog.getRootPane().getActionMap().put("escape", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jDailog.dispose();
                }
            });
            jDailog.setSize(dm.width, dm.height - 50);
            jDailog.setLocation(0, 0);
            jDailog.setVisible(true);

        } catch (HeadlessException | SQLException | JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            conn.close();
        }
    }

    public void gerarRelatorioBaseXml(String[] lstParametros, Object[] lstValoresParametros, String numRelatorio, String nomeRelatorio) throws JRException {

        Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
        final JDialog jDailog = new JDialog(new javax.swing.JFrame(), nomeRelatorio, true);

        jDailog.setSize(960, 600);
        jDailog.setLocationRelativeTo(null);
        jDailog.setResizable(true);
        HashMap parameterMap = getHashMap(lstParametros, lstValoresParametros);

        InputStream relatorioJasper = Relatorios.valueOf("$" + numRelatorio).getRelatorio();

        JRXmlDataSource xml = new JRXmlDataSource(System.getProperty("user.dir") + "/" + Relatorios.valueOf("$" + numRelatorio).getArquivoXml() + ".xml", Relatorios.valueOf("$" + numRelatorio).getPathRelatorio());

        JasperPrint jasperPrint = JasperFillManager.fillReport(relatorioJasper, parameterMap, xml);

        JasperViewer jrViewer = new JasperViewer(jasperPrint, true);
        jDailog.getContentPane().add(jrViewer.getContentPane());
        jDailog.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "escape");
        jDailog.getRootPane().getActionMap().put("escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDailog.dispose();
            }
        });
        jDailog.setSize(dm.width, dm.height - 50);
        jDailog.setLocation(0, 0);
        jDailog.setVisible(true);

    }

    /*
     * imprime em um jpanel o relatorio solicitado
     */
    public Container imprimirEmUmJPanel(String[] lstParametros, Object[] lstValoresParametros, String numRelatorio) throws SQLException {
        JasperViewer jrViewer = null;
        Connection conn = null;
        try {
            conn = FabricaConexao.getConnection();
            HashMap parameterMap = getHashMap(lstParametros, lstValoresParametros);

            String caminhoRelatorio = System.getProperty("user.dir") + Relatorios.valueOf("$" + numRelatorio).getRelatorio();

            JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoRelatorio, parameterMap, conn);
            jrViewer = new JasperViewer(jasperPrint, true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            conn.close();
        }
        return jrViewer.getContentPane();
    }

    /*
     * faz impressao direta 
     */
    public void gerarRelatorioImpressaoDireta(String[] lstParametros, Object[] lstValoresParametros, String numRelatorio) throws Exception {
        Connection conn = null;
        try {
            conn = FabricaConexao.getConnection();
            HashMap parameterMap = getHashMap(lstParametros, lstValoresParametros);
            InputStream caminhoRelatorio = Relatorios.valueOf("$" + numRelatorio).getRelatorio();
            JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoRelatorio, parameterMap, conn);
            JasperPrintManager.printReport(jasperPrint, false);
        } catch (SQLException | JRException e) {

        } finally {
            conn.close();
        }
    }

    /**
     * Relatório gerado apartir de uma coleção
     *
     * @param lstParametros lista com nome dos parametros
     * @param lstValoresParametros lista com valores dos parametros
     * @param colecao nome da coleção a ser impressa
     * @param numRelatorio número do relatório
     */
    public void gerarRelatorioComCollection(String[] lstParametros, Object[] lstValoresParametros, List colecao, String numRelatorio) {

        HashMap parameterMap = getHashMap(lstParametros, lstValoresParametros);
        InputStream relatorioJasper = Relatorios.valueOf("$" + numRelatorio).getRelatorio();
        final JRBeanCollectionDataSource coll = new JRBeanCollectionDataSource(colecao);
        try {
            Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
            final JDialog jDailog = new JDialog(new javax.swing.JFrame(), "Relatório", true);
            jDailog.setSize(960, 600);
            jDailog.setLocationRelativeTo(null);
            jDailog.setResizable(true);
            JasperPrint jasperPrint = JasperFillManager.fillReport(relatorioJasper, parameterMap, coll);
            JasperViewer jrViewer = new JasperViewer(jasperPrint, true);
            jDailog.getContentPane().add(jrViewer.getContentPane());
            jDailog.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "escape");
            jDailog.getRootPane().getActionMap().put("escape", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jDailog.dispose();
                }
            });
            jDailog.setSize(dm.width, dm.height - 50);
            jDailog.setLocation(0, 0);
            jDailog.setVisible(true);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

     private HashMap getHashMap(String[] lstParametros, Object[] lstValoresParametros) {
        HashMap parameterMap = new HashMap();
        for (int index = 0; index < lstParametros.length; index++) {
            if (lstValoresParametros[index] instanceof Integer) {
                parameterMap.put(lstParametros[index], (int) (lstValoresParametros[index]));
            } else if (lstValoresParametros[index] instanceof String) {
                parameterMap.put(lstParametros[index], (String) lstValoresParametros[index]);
            } else if (lstValoresParametros[index] instanceof Double) {
                parameterMap.put(lstParametros[index], (Double) lstValoresParametros[index]);
            } else if (lstValoresParametros[index] instanceof java.util.Date) {
                parameterMap.put(lstParametros[index], (java.util.Date) lstValoresParametros[index]);
            } else if (lstValoresParametros[index] instanceof Timestamp) {
                parameterMap.put(lstParametros[index], (Timestamp) lstValoresParametros[index]);
            } else if (lstValoresParametros[index] instanceof InputStream) {
                parameterMap.put(lstParametros[index], (InputStream) lstValoresParametros[index]);
            } else {
                parameterMap.put(lstParametros[index], (Object) lstValoresParametros[index]);
            }
        }
        return parameterMap;
    }

}
