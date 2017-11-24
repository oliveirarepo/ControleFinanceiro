/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparator;

import bean.cadastro.TipoConta;
import java.util.Comparator;

/**
 *
 * @author Thiago
 */
public class SomaTipoContaComparator implements Comparator<TipoConta> {

    int ordem = 0;

    public SomaTipoContaComparator(int ordem) {
        this.ordem = ordem;
    }

    @Override
    public int compare(TipoConta o1, TipoConta o2) {
        switch (ordem) {
            case 0:
                return o1.getDescricao().compareTo(o2.getDescricao());
            case 1:
                return Double.compare(o1.getSomaTipoConta(), o2.getSomaTipoConta());
            case 2:
                return Double.compare(o1.getSomaTipoConta(), o2.getSomaTipoConta());
            case 3:
                return Double.compare(o1.getSomaTipoConta(), o2.getSomaTipoConta());
            default:
                return Integer.compare(o1.getIdTipo(), o2.getIdTipo());
        }
    }

}
