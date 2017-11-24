/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.relatorios;

import Comparator.ContasPagarComparator;
import Dao.cadastro.CategoriaDao;
import Dao.movimento.MovimentoFinanceiroDao;
import Uteis.SuperControl;
import Uteis.Uteis;
import bean.cadastro.Categoria;
import bean.cadastro.ContasPagar;
import bean.relatorios.RelacaoPorCartegoria;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import view.relatorios.RelatoriosFrm;

/**
 *
 * @author ThiagoUser
 */
public class RelatoriosControler extends SuperControl implements ActionListener {

    private RelatoriosFrm RelatoriosFrm = new RelatoriosFrm(new javax.swing.JFrame(), true);
    private LinkedList<ContasPagar> lstContasPagar = new LinkedList<>();
    private List<Categoria> lstCategoria = new ArrayList<>();
    private List<RelacaoPorCartegoria> lstRelacao = new ArrayList<>();

    public RelatoriosControler() {
        this.RelatoriosFrm.addActionsListener(this);
        try {
            getListaMovimentoFinanceiro();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.RelatoriosFrm.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "gerarRelacaoDeContas":
                try {
                    gerarRelacaoDeContas();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;

        }
    }

    private void gerarRelacaoDeContas() throws Exception {
        graficoJaneiro();//1
        graficoFevereiro();//2
        graficoMarco();//3
        graficoAbril();//4
        graficoMaio();//5
        graficoJunho();//6
        graficoJulho();//7
        graficoAgosto();////8
        graficoSetembro();//9
        graficoOtubro();//10
        graficoNovembro();//11
        graficoDezembro();///12
        FileOutputStream saida = new FileOutputStream(new File(System.getProperty("user.dir") + "/TempFiles/GraficoAnual.xml"));
        XStream xStream = new XStream(new DomDriver("UTF-8"));
        xStream.toXML(lstRelacao, saida);
        saida.close();

//        new Impressao().gerarRelatorio(new String[]{"dataInicial", "dataFinal"}, new Date[]{RelatoriosFrm.getDataInicial(), RelatoriosFrm.getDataFinal()}, "1", "Relação de gastos");
    }

    private void getListaMovimentoFinanceiro() throws SQLException {
        try {
            iniciarConexao();
            new MovimentoFinanceiroDao().getListaContasPagar(lstContasPagar);
            new CategoriaDao().getListaCategoria(lstCategoria);
        } finally {
            finalizarConexao();
        }
    }

    private void graficoJaneiro() throws Exception {
        String ano = RelatoriosFrm.getAnoGrafico();
        somar(Uteis.getDateTime(Uteis.getDate("01/01/" + ano), 0, 0, 00), Uteis.getDateTime(Uteis.getDate("31/01/" + ano), 23, 59, 59), "Jan",1);

    }

    private void graficoFevereiro() throws Exception {
        String ano = RelatoriosFrm.getAnoGrafico();
        somar(Uteis.getDateTime(Uteis.getDate("01/02/" + ano), 0, 0, 0), Uteis.getDateTime(Uteis.getDate("28/02/" + ano), 23, 59, 59), "Fev",2);
    }

    private void graficoMarco() throws Exception {
        String ano = RelatoriosFrm.getAnoGrafico();
        somar(Uteis.getDateTime(Uteis.getDate("01/03/" + ano), 0, 0, 0), Uteis.getDateTime(Uteis.getDate("31/03/" + ano), 23, 59, 59), "Mar",3);
    }

    private void graficoAbril() throws Exception {
        String ano = RelatoriosFrm.getAnoGrafico();

        somar(Uteis.getDateTime(Uteis.getDate("01/04/" + ano), 0, 0, 0), Uteis.getDateTime(Uteis.getDate("30/04/" + ano), 23, 59, 59), "Abr",4);
    }

    private void graficoMaio() throws Exception {
        String ano = RelatoriosFrm.getAnoGrafico();

        somar(Uteis.getDateTime(Uteis.getDate("01/05/" + ano), 0, 0, 0), Uteis.getDateTime(Uteis.getDate("31/05/" + ano), 23, 59, 59), "Mai",5);
    }

    private void graficoJunho() throws Exception {
        String ano = RelatoriosFrm.getAnoGrafico();
        somar(Uteis.getDateTime(Uteis.getDate("01/06/" + ano), 0, 0, 0), Uteis.getDateTime(Uteis.getDate("30/06/" + ano), 23, 59, 59), "Jun",6);
    }

    private void graficoJulho() throws Exception {
        String ano = RelatoriosFrm.getAnoGrafico();
        somar(Uteis.getDateTime(Uteis.getDate("01/07/" + ano), 0, 0, 0), Uteis.getDateTime(Uteis.getDate("31/07/" + ano), 23, 59, 59), "Jul",7);
    }

    private void graficoAgosto() throws Exception {
        String ano = RelatoriosFrm.getAnoGrafico();
        somar(Uteis.getDateTime(Uteis.getDate("01/08/" + ano), 0, 0, 0), Uteis.getDateTime(Uteis.getDate("31/08/" + ano), 23, 59, 59), "Ago",8);
    }

    private void graficoSetembro() throws Exception {
        String ano = RelatoriosFrm.getAnoGrafico();
        somar(Uteis.getDateTime(Uteis.getDate("01/09/" + ano), 0, 0, 0), Uteis.getDateTime(Uteis.getDate("30/09/" + ano), 23, 59, 59), "Set",9);
    }

    private void graficoOtubro() throws Exception {
        String ano = RelatoriosFrm.getAnoGrafico();
        somar(Uteis.getDateTime(Uteis.getDate("01/10/" + ano), 0, 0, 0), Uteis.getDateTime(Uteis.getDate("31/10/" + ano), 23, 59, 59), "Out",10);
    }

    private void graficoNovembro() throws Exception {
        String ano = RelatoriosFrm.getAnoGrafico();
        somar(Uteis.getDateTime(Uteis.getDate("01/11/" + ano), 0, 0, 0), Uteis.getDateTime(Uteis.getDate("30/11/" + ano), 23, 59, 59), "Nov",11);
    }

    private void graficoDezembro() throws Exception {
        String ano = RelatoriosFrm.getAnoGrafico();
        somar(Uteis.getDateTime(Uteis.getDate("01/12/" + ano), 0, 0, 0), Uteis.getDateTime(Uteis.getDate("31/12/" + ano), 23, 59, 59), "Dez",12);
    }

    private void somar(Date dataInicio, Date datafim, String mes,int mesInt) {
        Collections.sort(lstContasPagar, new ContasPagarComparator(2));
        for (Categoria cat : lstCategoria) {
            RelacaoPorCartegoria relat = new RelacaoPorCartegoria(cat.getIdCategoria(), cat.getDescricaoCategoria(), 0, mes,mesInt);
            lstRelacao.add(relat);

            for (ContasPagar cp : lstContasPagar) {
                if (cp.getDataVenc().getTime() >= dataInicio.getTime() && cp.getDataVenc().getTime() <= datafim.getTime() && cp.getCategoria().getIdCategoria() == cat.getIdCategoria()) {
                    relat.setTotal(relat.getTotal() + cp.getVALORPAGO());
                }
            }
            if (relat.getTotal() == 0) {
                lstRelacao.remove(relat);
            }
        }

    }

}
