
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.cadastro;

import Comparator.ContasPagarComparator;
import Comparator.GraficoContasComparator;
import Dao.cadastro.CategoriaDao;
import Dao.cadastro.ContaDao;
import Dao.cadastro.TipoContaDao;
import Dao.movimento.MovimentoFinanceiroDao;
import Uteis.GerarPdf;
import Uteis.SuperControl;
import Uteis.Uteis;
import bean.cadastro.Categoria;
import bean.cadastro.Conta;
import bean.cadastro.ContasPagar;
import bean.cadastro.GraficoContasPagas;
import bean.cadastro.TipoConta;
import bean.movimento.GraficoMensal;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import relatorios.Impressao;
import view.movimento.BaixaParcialFrm;
import view.movimento.RelacaoDeContasFrm;

/**
 *
 * @author Thaigo
 */
public class RelacaoContasControler extends SuperControl implements ActionListener {

    private RelacaoDeContasFrm relacaoDeContasFrm = RelacaoDeContasFrm.getForm();
    private BaixaParcialFrm baixaParcialFrm = new BaixaParcialFrm(null, true);
    private LinkedList<ContasPagar> lstContasPagar = new LinkedList<>();
    private List<Categoria> lstCategoria = new ArrayList<>();
    private Conta conta;//= new Conta();
    private List<GraficoMensal> lstGrafico = new ArrayList<>();
    private List<ContasPagar> lstPesquisa = new ArrayList<>();
    private List<TipoConta> lstTipoConta = new ArrayList<>();

    public RelacaoContasControler() {
        this.relacaoDeContasFrm.addActionListener(this);
        baixaParcialFrm.addActionListeners(this);
        try {
            Date d = new Date();
            iniciarConexao();
            iniciaGrafico();
            getListaGeralContasPagar();
            listarGraficos();
            getConta();
            System.out.println("TEMO_" + (new Date().getTime() - d.getTime()));
            relacaoDeContasFrm.setListaRegistros(lstPesquisa);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            finalizarConexao();
        }
        relacaoDeContasFrm.setVisible(true);
        relacaoDeContasFrm.setRemoveActionListerner(this);
        limparLista(lstCategoria);
        limparLista(lstContasPagar);
        limparLista(lstGrafico);
        limparLista(lstPesquisa);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "pesquisaTipoConta":
                try {
                    pesquisaTipoConta();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "baixarNota":
                try {
                    baixarNota();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "excluirNota":
                try {
                    excluirNota();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "editarNota":
                try {
                    editarNota();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "lancarNovaNota":
                lancarNovaNota();
                break;
            case "autoPesquisaGrafico":
                autoPesquisaGrafico();
                break;
            case "somaValores":
                somaValores(true);
                break;
            case "ordernarRegistro":
                ordernarRegistro();
                break;
            case "filtrarMensal":
                try {
                    filtrarMensal();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "baixaParcial":
                try {
                    baixaParcial();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "confirmaBaixaParcial":
                try {
                    confirmaBaixaParcial();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "cancelaBaixaParcial":
                cancelaBaixaParcial();
                break;
            case "sairRelacaoContas":
                sairRelacaoContas();
                break;
            case "pesquisarContas":
                try {
                    pesquisarContas(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "pesquisarCategoria":
                try {
                    pesquisarCategoria();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "impressaoGrafica":
                try {
                    impressaoGraficaTipoConta();
                } catch (IOException | ParseException | JRException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "impressaoGraficaCategoria":
                try {
                    impressaoGraficaCategoria();
                } catch (IOException | ParseException | JRException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "relacaoGraficoAnual":
                try {
                    relacaoGraficoAnual();
                } catch (IOException | JRException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "relacaoGraficoAnualTipoConta":
                try {
                    relacaoGraficoAnualTipoConta();
                } catch (IOException | JRException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "graficoIndiceEnvidamento":
                graficoIndiceEnvidamento();
                break;
            case "filtrarCategoria":
                try {
                    filtrarCategoria();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "filtrarTipoConta":
                try {
                    filtrarTipoConta();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
        }
    }

    private void baixarNota() throws Exception {
        relacaoDeContasFrm.getContaSelecionada();
        if (JOptionPane.showConfirmDialog(null, "Confirma a baixa do(s) documento(s)?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) != 0) {
            return;
        }
        try {
            iniciarConexao();
            for (ContasPagar contaPagar : relacaoDeContasFrm.getListaContasSelecionadas()) {
                if ('S' == contaPagar.getJAPAGA()) {
                    throw new Exception("o documento\"" + contaPagar.getCreedor() + "\" já se encontra baixado!");
                }
                new MovimentoFinanceiroDao().baixarDocumento(contaPagar);
                if (contaPagar.getCOD_OPERACAO() == 1) {
                    conta.setSaldo(conta.getSaldo() + contaPagar.getVALORPAGO());
                } else {
                    conta.setSaldo(conta.getSaldo() - contaPagar.getVALORPAGO());
                }
                new ContaDao().salvar(conta);
                relacaoDeContasFrm.setSaldo(conta.getSaldo());
                contaPagar.setJAPAGA('S');

            }
            showMenssagem("Operação Realizada com sucesso!");
            relacaoDeContasFrm.atualizaRegistros();
            relacaoDeContasFrm.setTotalGeral(somar(lstContasPagar, true));
        } finally {
            finalizarConexao();
        }
    }

    private void excluirNota() throws Exception {
        relacaoDeContasFrm.getContaSelecionada();
        if (JOptionPane.showConfirmDialog(null, "Confirma Exclusão do(s) docuemnto(s) ?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) != 0) {
            return;
        }
        try {
            iniciarConexao();
            for (ContasPagar contaExcluir : relacaoDeContasFrm.getListaContasSelecionadas()) {
                new MovimentoFinanceiroDao().Excluir(contaExcluir);
                lstContasPagar.remove(contaExcluir);
                relacaoDeContasFrm.getLista().remove(contaExcluir);
                relacaoDeContasFrm.atualizaRegistros();
            }
            showMenssagem("Conta Excluida com sucesso!");
        } finally {
            finalizarConexao();
        }

    }

    private void editarNota() throws Exception {
        try {
            iniciarConexao();
            final ContasPagar contaPagar = relacaoDeContasFrm.getContaSelecionada();
            new MovimentoFinanceiroDao().consultarPorChavePrimaria(contaPagar);
            new LacamentoContasControler(contaPagar, lstContasPagar);
            relacaoDeContasFrm.updateTable();
        } finally {
            finalizarConexao();
        }
    }

    private void lancarNovaNota() {
        new LacamentoContasControler(new ContasPagar(), lstContasPagar);
        relacaoDeContasFrm.updateTable();
    }

    private void autoPesquisaGrafico() {
        listarGraficos();
    }

    private void listarGraficos() {
        try {
            for (int mes = 0; mes <= 11; mes++) {
                grafico(mes);
            }
            relacaoDeContasFrm.repaintGrafico();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void grafico(int mes) {
        int ano = relacaoDeContasFrm.getAnoGraficoInt();
        try {
            somar(Uteis.getData(Uteis.getDataMesInicio(mes, ano)), Uteis.getData(Uteis.getDataMesFim(mes, ano)), lstGrafico.get(mes));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void somar(String inicio, String fim, GraficoMensal graficoMensal) throws Exception {
        float total = 0;
        Date dataInicio = Uteis.getDateTime(Uteis.getDate(inicio), 0, 0, 00);
        Date datafim = Uteis.getDateTime(Uteis.getDate(fim), 23, 59, 59);
        boolean isFiltroCategoria = relacaoDeContasFrm.filtrarPorCategoria();
        boolean isFiltroTipoConta = relacaoDeContasFrm.filtrarPorTipoConta();
        graficoMensal.setCreditos(0);
        graficoMensal.setDebitos(0);
        rout:
        for (ContasPagar cp : lstContasPagar) {
            if (isFiltroCategoria && !lstCategoria.isEmpty()) {
                for (int i = 0; i < lstCategoria.size(); i++) {
                    Categoria categ = lstCategoria.get(i);
                    if (cp.getCategoria().getIdCategoria() == categ.getIdCategoria()) {
                        break;
                    }
                    if ((lstCategoria.size() - 1) == i) {
                        continue rout;
                    }
                }

            }
            if (isFiltroTipoConta && !lstTipoConta.isEmpty()) {
                for (int i = 0; i < lstTipoConta.size(); i++) {
                    TipoConta categ = lstTipoConta.get(i);
                    if (cp.getTipoConta().getIdTipo() == categ.getId()) {
                        break;
                    }
                    if ((lstTipoConta.size() - 1) == i) {
                        continue rout;
                    }
                }

            }
            if (cp.getDataVenc().getTime() >= dataInicio.getTime() && cp.getDataVenc().getTime() <= datafim.getTime()) {
                if (cp.getCOD_OPERACAO() == 2) {
                    total -= cp.getVALORPAGO();
                    graficoMensal.somaDebitos(cp.getVALORPAGO());
                } else {
                    total += cp.getVALORPAGO();
                    graficoMensal.somaCreditos(cp.getVALORPAGO());
                }
            }
        }
        graficoMensal.setSaldo(total);
        graficoMensal.setIndiceEndividamento((graficoMensal.getDebitos() / graficoMensal.getCreditos()) * 100);
    }

    private String somar(List<ContasPagar> lst, boolean atualizarCategoriaTipoConta) {
        float total = 0;
        List<Categoria> lstCat = new ArrayList();
        List<TipoConta> lstTipo = new ArrayList();
        for (ContasPagar cp : lst) {
            if (cp.getCOD_OPERACAO() == 2) {
                total -= cp.getVALORPAGO();
            } else {
                total += cp.getVALORPAGO();
            }
            //****************************Por Tipo de conta ******************************
            if (lstTipo.isEmpty()) {
                TipoConta t = new TipoConta(cp.getTipoConta(), cp.getVALORPAGO());
                t.setSelected(true);
                lstTipo.add(t);
            } else {
                boolean adicionado = false;
                for (TipoConta t : lstTipo) {
                    if (t.getIdTipo() == cp.getTipoConta().getIdTipo()) {
                        t.setSomaTipoConta(t.getSomaTipoConta() + cp.getVALORPAGO());
                        adicionado = true;
                        break;
                    }
                }
                if (!adicionado && cp.getCOD_OPERACAO() == 2) {
                    TipoConta t = new TipoConta(cp.getTipoConta(), cp.getVALORPAGO());
                    t.setSelected(true);
                    lstTipo.add(t);
                }
            }
            //****************************Por Cartegoria agora******************************
            if (lstCat.isEmpty()) {
                Categoria t = new Categoria(cp.getCategoria().getId(), cp.getCategoria().getDescricao(), cp.getVALORPAGO());
                t.setSelected(true);
                lstCat.add(t);
            } else {
                boolean adicionado = false;
                for (Categoria t : lstCat) {
                    if (t.getIdCategoria() == cp.getCategoria().getIdCategoria()) {
                        t.somar(cp.getVALORPAGO());
                        t.setSelected(true);
                        adicionado = true;
                        break;
                    }
                }
                if (!adicionado && cp.getCOD_OPERACAO() == 2) {
                    Categoria t = new Categoria(cp.getCategoria().getIdCategoria(), cp.getCategoria().getDescricaoCategoria(), cp.getVALORPAGO());
                    t.setSelected(true);
                    lstCat.add(t);
                }
            }
        }
        if (atualizarCategoriaTipoConta) {
            relacaoDeContasFrm.setSomaTipo(lstTipo);
            relacaoDeContasFrm.setSomaCategoria(lstCat);
        }
        return Uteis.formatarMoeda(total);
    }

    private void somaValores(boolean updateGrupo) {
        relacaoDeContasFrm.setSoma(somar(relacaoDeContasFrm.getListaContasSelecionadas(), updateGrupo));
    }

    private void getConta() throws SQLException {
        conta = new ContaDao().getConta(1);
        relacaoDeContasFrm.setSaldo(conta.getSaldo());
    }

    private void ordernarRegistro() {
        Collections.sort(relacaoDeContasFrm.getLista(), new ContasPagarComparator(relacaoDeContasFrm.getOrdem()));
        relacaoDeContasFrm.atualizaRegistros();
    }

    private void getListaGeralContasPagar() throws SQLException {
        new MovimentoFinanceiroDao().getListaContasPagar(lstContasPagar);
    }

    private void iniciaGrafico() {
        final String[] mes = new String[]{"Janeiro", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        for (int i = 1; i <= 12; i++) {
            lstGrafico.add(new GraficoMensal(mes[i], i));
        }
        relacaoDeContasFrm.setListaGrafico(lstGrafico);
    }

    private void filtrarMensal() throws Exception {
        int mes = relacaoDeContasFrm.getMes();
        int ano = relacaoDeContasFrm.getAnoGraficoInt();
        Date dataInicio = Uteis.getDataMesInicio(mes, ano);
        Date dataFim = Uteis.getDataMesFim(mes, ano);
        relacaoDeContasFrm.setDataInicial(Uteis.getData(dataInicio));
        relacaoDeContasFrm.setDataFim(Uteis.getData(dataFim));
        pesquisarContas(true);

    }

    private void baixaParcial() throws Exception {
        ContasPagar c = relacaoDeContasFrm.getContaSelecionada();
        baixaParcialFrm.setContaPagar(c);
        baixaParcialFrm.setVisible(true);
    }

    private void confirmaBaixaParcial() throws Exception {
        ContasPagar contaPagar = baixaParcialFrm.getConta();
        float abatimento = baixaParcialFrm.getAbatimento();
        float restante = contaPagar.getVALORPAGO() - abatimento;
        if (abatimento == 0 || restante == 0) {
            throw new Exception("valores invalido!");
        }
        if (baixaParcialFrm.getDataVencimento().getTime() < new Date().getTime() - 10000) {
//            throw new Exception("Data de Vencimento invalida!");
        }
        contaPagar.setVALORPAGO(abatimento);
        try {
            iniciarConexao();
            new MovimentoFinanceiroDao().salvar(contaPagar);
            new MovimentoFinanceiroDao().baixarDocumento(contaPagar);
            contaPagar.setCODIGO(0);
            contaPagar.setVALORPAGO(restante);
            contaPagar.setDataVenc(baixaParcialFrm.getDataVencimento());
            new MovimentoFinanceiroDao().salvar(contaPagar);
            lstContasPagar.clear();
            getListaGeralContasPagar();
            filtrarMensal();
            baixaParcialFrm.dispose();
            JOptionPane.showMessageDialog(null, "Operação Realizada com sucesso!");
        } finally {
            finalizarConexao();
        }
    }

    private void cancelaBaixaParcial() {
        baixaParcialFrm.dispose();
    }

    private void sairRelacaoContas() {
        relacaoDeContasFrm.setVisible(false);
        relacaoDeContasFrm.removeActionListerner(this);

    }

    private void pesquisarContas(boolean updateCategoria) throws Exception {
        //port tipo de conta
        Date dataInicial = relacaoDeContasFrm.getDataInicial();
        Date dataFinal = relacaoDeContasFrm.getDataFinal();
        String creedor = relacaoDeContasFrm.getCreedor();
        Pattern pattern = Pattern.compile(creedor);

        char pagarReceber = relacaoDeContasFrm.getPagarReceber();
        char pagarPagas = relacaoDeContasFrm.getPagarPagas();
        lstPesquisa.clear();
        rout:
        for (ContasPagar c : lstContasPagar) {
            if (!lstTipoConta.isEmpty()) {
                for (int i = 0; i < lstTipoConta.size(); i++) {
                    TipoConta tipoConta = lstTipoConta.get(i);
                    if (c.getTipoConta().getIdTipo() == tipoConta.getIdTipo()) {
                        break;
                    }
                    if ((lstTipoConta.size() - 1) == i) {
                        continue rout;
                    }
                }
            }
            if (!lstCategoria.isEmpty()) {
                for (int i = 0; i < lstCategoria.size(); i++) {
                    Categoria categ = lstCategoria.get(i);
                    if (c.getCategoria().getIdCategoria() == categ.getIdCategoria()) {
                        break;
                    }
                    if ((lstCategoria.size() - 1) == i) {
                        continue rout;
                    }
                }
            }
            if (dataInicial.getTime() > 0 && (c.getDataVenc().getTime() + 1000 < dataInicial.getTime() || c.getDataVenc().getTime() > dataFinal.getTime())) {
                continue;
            }
            if ((pagarReceber == (c.getOPERACAO().charAt(0)) || pagarReceber == '%') && (pagarPagas == '%' || pagarPagas == (c.getJAPAGA()))) {

                Matcher creedorMatcher = pattern.matcher(c.getCreedor());
                if (!creedorMatcher.find()) {
                    continue;
                }
                lstPesquisa.add(c);

            }
        }
        relacaoDeContasFrm.setListaRegistros(lstPesquisa);
        relacaoDeContasFrm.setTotalGeral(somar(lstPesquisa, updateCategoria));

    }

    private void limparLista(List lst) {
        for (int i = 0; i < lst.size(); i++) {
            Object o = lst.get(i);
            o = null;
            lst.set(i, null);
        }
    }

    private void pesquisaTipoConta() throws SQLException, Exception {
        try {
            iniciarConexao();
            new SelecionaRegistroControler(lstTipoConta, new TipoContaDao().getList(), "Pesquisa Tipo de Conta");
            relacaoDeContasFrm.setListaTipoConta(lstTipoConta);
            pesquisarContas(true);
        } finally {
            finalizarConexao();
        }
    }

    private void pesquisarCategoria() throws SQLException, Exception {
        try {
            iniciarConexao();
            new SelecionaRegistroControler(lstCategoria, new CategoriaDao().getList(), "Pesquisa Categoria");
            relacaoDeContasFrm.setListaCategoria(lstCategoria);
            pesquisarContas(true);
        } finally {
            finalizarConexao();
        }
    }

    private void impressaoGraficaTipoConta() throws FileNotFoundException, IOException, ParseException, JRException, SQLException {
        List<GraficoContasPagas> listaGrafica = new ArrayList();
        float total = 0;
        rout:
        for (ContasPagar r : relacaoDeContasFrm.getLista()) {
            total += r.getVALORPAGO();
            if (listaGrafica.isEmpty()) {
                listaGrafica.add(new GraficoContasPagas(r.getTipoConta(), r.getVALORPAGO()));
                continue;
            }
            for (GraficoContasPagas g : listaGrafica) {
                if (g.getIdTipoConta() == r.getTipoConta().getIdTipo()) {
                    g.setSomaValor(r.getVALORPAGO());
                    continue rout;
                }
            }
            listaGrafica.add(new GraficoContasPagas(r.getTipoConta(), r.getVALORPAGO()));
        }
        for (GraficoContasPagas g : listaGrafica) {
            g.setPerc((g.getValor() / total) * 100);
        }
        Collections.sort(listaGrafica, new Comparator<GraficoContasPagas>() {

            @Override
            public int compare(GraficoContasPagas o1, GraficoContasPagas o2) {
                return Double.compare(o1.getValor(), o2.getValor());
            }
        });
        XStream xStream = new XStream(new DomDriver("UTF-8"));
        xStream.alias("conta", GraficoContasPagas.class);
        File f = new File(System.getProperty("user.dir") + "/TempFiles");

        if (!f.exists()) {
            f.mkdir();
        }
        try (FileOutputStream saida = new FileOutputStream(new File(System.getProperty("user.dir") + "/TempFiles/relatorio.xml"))) {
            xStream.toXML(listaGrafica, saida);
            saida.close();
        } catch (IOException ex) {
            throw new FileNotFoundException(ex.getMessage());
        }
        String desc = "Relação gráfica de tipo de contas no período de " + Uteis.getData(relacaoDeContasFrm.getDataInicial()) + " a " + Uteis.getData(relacaoDeContasFrm.getDataFinal());
        new GerarPdf(new String[]{"descricao"}, new String[]{desc}, "grafico", "2", true, true);
    }

    private void impressaoGraficaCategoria() throws FileNotFoundException, IOException, ParseException, JRException, SQLException {
        List<GraficoContasPagas> listaGrafica = new ArrayList();
        float total = 0;
        rout:
        for (ContasPagar r : relacaoDeContasFrm.getLista()) {
            total += r.getVALORPAGO();
            if (listaGrafica.isEmpty()) {
                listaGrafica.add(new GraficoContasPagas(r.getCategoria(), r.getVALORPAGO()));
                continue;
            }
            for (GraficoContasPagas g : listaGrafica) {
                if (g.getIdTipoConta() == r.getCategoria().getIdCategoria()) {
                    g.setSomaValor(r.getVALORPAGO());
                    continue rout;
                }
            }
            listaGrafica.add(new GraficoContasPagas(r.getCategoria(), r.getVALORPAGO()));
        }
        for (GraficoContasPagas g : listaGrafica) {
            g.setPerc((g.getValor() / total) * 100);
        }
        Collections.sort(listaGrafica, new Comparator<GraficoContasPagas>() {

            @Override
            public int compare(GraficoContasPagas o1, GraficoContasPagas o2) {
                return Double.compare(o1.getValor(), o2.getValor());
            }
        });
        XStream xStream = new XStream(new DomDriver("UTF-8"));
        xStream.alias("conta", GraficoContasPagas.class);
        File f = new File(System.getProperty("user.dir") + "/TempFiles");

        if (!f.exists()) {
            f.mkdir();
        }
        try (FileOutputStream saida = new FileOutputStream(new File(System.getProperty("user.dir") + "/TempFiles/relatorio.xml"))) {
            xStream.toXML(listaGrafica, saida);
            saida.close();
        } catch (IOException ex) {
            throw new FileNotFoundException(ex.getMessage());
        }
        String desc = "Relação gráfica de categoria no período de " + Uteis.getData(relacaoDeContasFrm.getDataInicial()) + " A " + Uteis.getData(relacaoDeContasFrm.getDataFinal());

        new GerarPdf(new String[]{"descricao"}, new String[]{desc}, "grafico", "2", true, true);
    }

    private void relacaoGraficoAnual() throws FileNotFoundException, IOException, JRException, SQLException {
        List<GraficoContasPagas> listaGrafica = new ArrayList();
        float total = 0;
        rout:
        for (ContasPagar r : relacaoDeContasFrm.getLista()) {
            total += r.getVALORPAGO();
            if (listaGrafica.isEmpty()) {
                listaGrafica.add(new GraficoContasPagas(r.getCategoria(), r.getVALORPAGO(), r.getDataVenc().getMonth()));
                continue;
            }
            for (GraficoContasPagas g : listaGrafica) {
                if (g.getIdTipoConta() == r.getCategoria().getIdCategoria() && g.getMes() == r.getDataVenc().getMonth()) {
                    g.setSomaValor(r.getVALORPAGO());
                    continue rout;
                }
            }
            listaGrafica.add(new GraficoContasPagas(r.getCategoria(), r.getVALORPAGO(), r.getDataVenc().getMonth()));
        }
        for (GraficoContasPagas g : listaGrafica) {
            g.setPerc((g.getValor() / total) * 100);
        }
        Collections.sort(listaGrafica, new GraficoContasComparator(0));
        XStream xStream = new XStream(new DomDriver("UTF-8"));
        xStream.alias("conta", GraficoContasPagas.class);
        File f = new File(System.getProperty("user.dir") + "/TempFiles");

        if (!f.exists()) {
            f.mkdir();
        }
        try (FileOutputStream saida = new FileOutputStream(new File(System.getProperty("user.dir") + "/TempFiles/relatorio.xml"))) {
            xStream.toXML(listaGrafica, saida);
            saida.close();
        } catch (IOException ex) {
            throw new FileNotFoundException(ex.getMessage());
        }
        String desc = "Relação gráfica de contas no período de " + Uteis.getData(relacaoDeContasFrm.getDataInicial()) + " A " + Uteis.getData(relacaoDeContasFrm.getDataFinal());

        new GerarPdf(new String[]{"descricao"}, new String[]{desc}, "grafico", "3", true, true);
    }

    private void relacaoGraficoAnualTipoConta() throws FileNotFoundException, IOException, JRException, SQLException {
        List<GraficoContasPagas> listaGrafica = new ArrayList();
        float total = 0;
        rout:
        for (ContasPagar r : relacaoDeContasFrm.getLista()) {
            total += r.getVALORPAGO();
            if (listaGrafica.isEmpty()) {
                listaGrafica.add(new GraficoContasPagas(r.getTipoConta(), r.getVALORPAGO(), r.getDataVenc().getMonth()));
                continue;
            }
            for (GraficoContasPagas g : listaGrafica) {
                if (g.getIdTipoConta() == r.getTipoConta().getIdTipo() && g.getMes() == r.getDataVenc().getMonth()) {
                    g.setSomaValor(r.getVALORPAGO());
                    continue rout;
                }
            }
            listaGrafica.add(new GraficoContasPagas(r.getTipoConta(), r.getVALORPAGO(), r.getDataVenc().getMonth()));
        }
        for (GraficoContasPagas g : listaGrafica) {
            g.setPerc((g.getValor() / total) * 100);
        }

        Collections.sort(listaGrafica, new GraficoContasComparator(0));
        XStream xStream = new XStream(new DomDriver("UTF-8"));
        xStream.alias("conta", GraficoContasPagas.class);
        File f = new File(System.getProperty("user.dir") + "/TempFiles");
        if (!f.exists()) {
            f.mkdir();
        }
        try (FileOutputStream saida = new FileOutputStream(new File(System.getProperty("user.dir") + "/TempFiles/relatorio.xml"))) {
            xStream.toXML(listaGrafica, saida);
            saida.close();
        } catch (IOException ex) {
            throw new FileNotFoundException("Erro ao salvar arquivo :" + ex.getMessage());
        }
        String desc = "Relação gráfica de tipo contas no período de " + Uteis.getData(relacaoDeContasFrm.getDataInicial()) + " A " + Uteis.getData(relacaoDeContasFrm.getDataFinal());
        new GerarPdf(new String[]{"descricao"}, new String[]{desc}, "gráfico", "3", true, true);
    }

    private void graficoIndiceEnvidamento() {
        new Impressao().gerarRelatorioComCollection(new String[]{}, new String[]{}, lstGrafico, "4");
    }

    private void filtrarCategoria() throws Exception {
        List<Categoria> lst = relacaoDeContasFrm.getCategoria();
        if (!lst.isEmpty()) {
            lstCategoria = lst;
            pesquisarContas(false);
        }
    }

    private void filtrarTipoConta() throws Exception {
        List<TipoConta> lst = relacaoDeContasFrm.getTipoConta();
        if (!lst.isEmpty()) {
            lstTipoConta = lst;
            pesquisarContas(false);
        }
    }
}
