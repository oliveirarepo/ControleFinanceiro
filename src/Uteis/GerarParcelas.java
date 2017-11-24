/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Uteis;

import bean.cadastro.ContasPagar;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author thiago
 */
public class GerarParcelas {

    public static List<ContasPagar> gerarParcelasContasPagar(int numDoc, String creedor,
            int qtdParcela, Date dataVencimento, Date dataCompra,
            float valorParcela, String operacao) {
        List<ContasPagar> novaList = new ArrayList();
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(dataVencimento);
        int dia = c.get(Calendar.DAY_OF_MONTH);
        // int ano = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH) + 1;
        long data = c.getTimeInMillis();

        for (int i = 0; i < qtdParcela; i++) {
            ContasPagar contasPagar = new ContasPagar();
            if (i != 0) {
                data = data
                        + ((long) c.getActualMaximum(Calendar.DAY_OF_MONTH) * 86400000);
            }
            c.setTimeInMillis(data);
            if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                data += (86400000);
                c.setTimeInMillis(data);
            }
            contasPagar.setDataVenc(c.getTime());
            contasPagar.setParcela(numDoc);
            contasPagar.setTotalParcelas(qtdParcela);
            if (!creedor.contains("  " + numDoc + "/" + qtdParcela)) {
                contasPagar.setCreedor(creedor + "  " + numDoc + "/" + qtdParcela);
            } else {
                contasPagar.setCreedor(creedor);
            }
            contasPagar.setDataCompra(dataCompra);
            contasPagar.setVALORCOMPRA(valorParcela);
            contasPagar.setVALORPAGO(valorParcela);
            contasPagar.setOPERACAO(operacao);

            System.out.println(Uteis.getData(c.getTime()));
            if (dia != c.get(Calendar.DAY_OF_MONTH)) {
                data -= (86400000);
                c.setTimeInMillis(data);
            }
            novaList.add(contasPagar);

            numDoc++;
        }

        return novaList;
    }
}
