/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.cadastro;

import Dao.cadastro.CategoriaDao;
import Dao.movimento.MovimentoFinanceiroDao;
import Uteis.LocalizarArquivo;
import Uteis.SuperControl;
import Uteis.Uteis;
import bean.cadastro.Categoria;
import bean.cadastro.ContasPagar;
import bean.cadastro.TipoConta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import view.Cadastro.ImpotracaoFatura;

/**
 *
 * @author Thiago
 */
public class ImportacaoFaturaControler extends SuperControl implements ActionListener {

    private final ImpotracaoFatura impotracaoFatura = new ImpotracaoFatura(new javax.swing.JFrame(), true);
    private final List<ContasPagar> lstFatura = new ArrayList();
    private final List<ContasPagar> lstGeradas = new ArrayList();

    public ImportacaoFaturaControler() {
        getListaCategoria();
        try {
            importar();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (BiffException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        impotracaoFatura.addActionListerner(this);
        impotracaoFatura.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "pesquisaTipoConta":
                pesquisaTipoConta();
                break;
            case "pesquisaCategoria":
                pesquisaCategoria();
                break;
            case "aplicarVencimento":
                try {
                    aplicarVencimento();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
            case "confirmarImportacao":
                try {
                    confirmarImportacao();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
        }
    }

    private void pesquisaCategoria() {
        Categoria categoria = new Categoria();
        new CategoriaControler(categoria);
        impotracaoFatura.setCategoria(categoria);
    }

    private void pesquisaTipoConta() {
        TipoConta tipoConta = new TipoConta();
        new TipoContaControler(tipoConta);
        impotracaoFatura.setTipoConta(tipoConta);

    }

    private void importar() throws IOException, BiffException, SQLException, Exception {
        lstFatura.clear();
//        File f = new File("D:/pasta.xls");
//        Workbook workbook = Workbook.getWorkbook(f);
        Workbook workbook = Workbook.getWorkbook(new File(new LocalizarArquivo().selecionaArquivo("Arquivo xls, xlsx, xlsb, xlsm", "CSV", "xls", "xlsx", "xlsb", "xlsm")));
        Sheet sheet = workbook.getSheet(0);

        int linhas = sheet.getRows();
        for (int linha = 0; linha < linhas; linha++) {
            ContasPagar conta = new ContasPagar();
            Cell a1 = sheet.getCell(0, linha);
            Cell a2 = sheet.getCell(1, linha);
            Cell a3 = sheet.getCell(2, linha);
            //convertendo em String
            String dataDados = a1.getContents();
            if (dataDados.length() < 7) {
                continue;
            }

            String data = a1.getContents();
            String creedor = a2.getContents();
            float valor = (Float.valueOf(a3.getContents().replaceAll("\\.", "").replaceAll(",", ".")));
            if (valor <= 0) {
                continue;
            }
            conta.setCreedor(creedor);
            conta.setDataCompra(Uteis.getDataFatura(data));
            conta.setVALORCOMPRA(valor);
            conta.setVALORPAGO(valor);
            conta.setOPERACAO("PAGAMENTO");
            conta.setCOD_OPERACAO(2);
            conta.setParcela(1);
            conta.setTotalParcelas(1);
            conta.setJAPAGA('N');
            lstFatura.add(conta);
        }

        workbook.close();
        teste();
        impotracaoFatura.setListaContas(lstFatura);
//        salvarLista(lstConta);
    }

    private void teste() throws SQLException {
        LinkedList<ContasPagar> lstBanco = new LinkedList<>();
        try {
            iniciarConexao();
            new MovimentoFinanceiroDao().getListaContasPagar(lstBanco);
            long inicio = new Date().getTime() - (86400000l * 730);
            long fim = new Date().getTime() + (86400000l * 730);
            LinkedList<ContasPagar> lst = new LinkedList<>();
            for (ContasPagar c : lstBanco) {
                if (c.getTipoConta().getId() != 9) {
                    continue;
                }
                if (c.getCOD_OPERACAO() == 1) {
                    continue;
                }
                if (c.getDataVenc().getTime() > inicio && c.getDataVenc().getTime() < fim) {
                    lst.add(c);
                }
            }
            lstBanco.clear();
            Collections.sort(lst, new Comparator<ContasPagar>() {

                @Override
                public int compare(ContasPagar o1, ContasPagar o2) {
                    return o1.getCODIGO() > o2.getCODIGO() ? -1 : 0;
                }
            });
            lstBanco.addAll(lst);
            lstGeradas.clear();
            int total = 0;
            rout:
            for (ContasPagar c : lstFatura) {
                for (ContasPagar conta : lstBanco) {
                    if (c.getCredorCompara().equals(conta.getCredorCompara()) && c.getVALORPAGO() == conta.getVALORPAGO() && c.getDataVenc().getMonth() == conta.getDataVenc().getMonth() && c.getDataCompra().getMonth() == conta.getDataCompra().getMonth()) {
                        c.setImportada('S');
                        c.setTipoConta(conta.getTipoConta());
                        c.setCategoria(conta.getCategoria());
                        c.setCODIGO(conta.getCODIGO());
                        continue rout;
                    }
                }
                total++;

            }
//            JOptionPane.showMessageDialog(null, "contas já existentes" + total + " novas:" + (lstFatura.size() - total));
            impotracaoFatura.setRegistros(total, (lstFatura.size() - total));
//            JOptionPane.showMessageDialog(null, "contas já existentes" + total + " novas:" + (lstFatura.size() - total));
        } finally {
            finalizarConexao();
        }
    }

    private void gerarParcel(ContasPagar conta) {
        String nome = conta.getCreedor();
        int parcela = Integer.valueOf(nome.substring(nome.indexOf("(") + 1, nome.indexOf("/")));
        parcela++;
        int totalParcela = Integer.valueOf(nome.substring(nome.indexOf("/") + 1, nome.indexOf(")")));
//        System.out.println(parcela + "/" + totalParcela);
        String creedor = nome.substring(0, nome.indexOf("("));
        Date venc = conta.getDataVenc();
        while (parcela <= totalParcela) {
            ContasPagar c = new ContasPagar();
            c.setCreedor(creedor + "(" + (parcela < 10 ? "0" + parcela : parcela) + "/" + (totalParcela < 10 ? "0" + totalParcela : totalParcela) + ")");
            c.setVALORCOMPRA(conta.getVALORCOMPRA());
            c.setVALORPAGO(conta.getVALORPAGO());
            c.setDataCompra(conta.getDataCompra());
            c.setCategoria(conta.getCategoria());
            c.setTipoConta(conta.getTipoConta());
            c.setParcela(parcela);
            c.setTotalParcelas(totalParcela);
            c.setOPERACAO("PAGAMENTO");
            c.setCOD_OPERACAO(2);
            c.setJAPAGA('N');
            venc = new Date(venc.getTime() + (86400000l * 30));
            c.setDataVenc(venc);
            parcela++;
            lstGeradas.add(c);
        }
        conta.setCreedor(conta.getCreedor().replaceAll("\\(0", "(").replaceAll("\\/0", "/"));
    }

    private void aplicarVencimento() throws Exception {
        Date d = impotracaoFatura.getDataVencimento();
        for (ContasPagar c : lstFatura) {
            c.setDataVenc(d);
        }
        teste();
        impotracaoFatura.repaintTable();

    }

    private void confirmarImportacao() throws SQLException, Exception {
        LinkedList<ContasPagar> lstCompara = new LinkedList<>();
        lstGeradas.clear();
        for (ContasPagar c : lstFatura) {
            if (c.getCreedor().contains(")")) {
                gerarParcel(c);
            }
        }
        lstFatura.addAll(lstGeradas);
        try {
            iniciarConexao();
            new MovimentoFinanceiroDao().getListaContasPagar(lstCompara);
            long inicio = new Date().getTime() - (86400000l * 730);
            long fim = new Date().getTime() + (86400000l * 730);
            LinkedList<ContasPagar> lst = new LinkedList<>();
            for (ContasPagar c : lstCompara) {
                if (c.getCOD_OPERACAO() == 1) {
                    continue;
                }
                if (c.getDataVenc().getTime() > inicio && c.getDataVenc().getTime() < fim) {
                    lst.add(c);
                }
            }
            lstCompara.clear();
            lstCompara.addAll(lst);
            lstGeradas.clear();
            int total = 0;
            rout:
            for (ContasPagar c : lstFatura) {
                for (ContasPagar conta : lstCompara) {
                    if (c.getCredorCompara().equals(conta.getCredorCompara()) && c.getVALORPAGO() == conta.getVALORPAGO() && c.getDataVenc().getMonth() == conta.getDataVenc().getMonth() && c.getDataCompra().getMonth() == conta.getDataCompra().getMonth()) {
                        continue rout;
                    }
                }
                if (c.getCODIGO() > 0) {
                    continue rout;
                }
                total++;
                c.setParcela(c.getParcela() == 0 ? 1 : c.getParcela());
                c.setTotalParcelas(c.getTotalParcelas() == 0 ? 1 : c.getTotalParcelas());
                new MovimentoFinanceiroDao().salvar(c);
            }
            System.out.println("TOTAL DE REGISTROS" + total);
//            JOptionPane.showMessageDialog(null, "Importação finalizada " + total);
        } finally {
            finalizarConexao();
        }
    }

    private void getListaCategoria() {
        try {
            iniciarConexao();
            List<Categoria> lst = new ArrayList<>();
            new CategoriaDao().getListaCategoria(lst);
            impotracaoFatura.setCellEditor(lst);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            finalizarConexao();
        }
    }
}
