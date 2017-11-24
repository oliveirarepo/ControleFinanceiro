/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparator;

import bean.cadastro.ContasPagar;
import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Thiago
 */
public class ContasPagarComparator implements Comparator<ContasPagar> ,Serializable{

    int ordem = 0;

    public ContasPagarComparator(int ordem) {
        this.ordem = ordem;
    }

    @Override
    public int compare(ContasPagar o1, ContasPagar o2) {
        switch (ordem) {
            case 1:
            case 2:
                return o1.getCreedor().compareTo(o2.getCreedor());
            case 3:
                return o1.getDataCompra().getTime() > o2.getDataCompra().getTime() ? 1 : -1;
            case 4:
                return o1.getDataVenc().getTime() > o2.getDataVenc().getTime() ? 1 : -1;
            case 5:
                return Double.compare(o1.getVALORPAGO(), o2.getVALORPAGO());
            case 6:
                return Double.compare(o1.getVALORPAGO(), o2.getVALORPAGO());
            case 7:
                return o1.getCategoria().getDescricaoCategoria().compareTo(o2.getCategoria().getDescricaoCategoria());
            case 8:
                return o1.getTipoConta().getDescricao().compareTo(o2.getTipoConta().getDescricao());
            case 9:
                return Double.compare(o1.getAndamento(), o2.getAndamento());
            default:
                return Integer.compare(o1.getCODIGO(), o2.getCODIGO());
        }
    }

}
