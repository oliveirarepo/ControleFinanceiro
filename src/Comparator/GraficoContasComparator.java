/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparator;

import bean.cadastro.GraficoContasPagas;
import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Thaigo
 */
public class GraficoContasComparator implements Comparator<GraficoContasPagas>, Serializable {

    private int ordem;

    public GraficoContasComparator(int ordem) {
        this.ordem = ordem;
    }

    @Override
    public int compare(GraficoContasPagas o1, GraficoContasPagas o2) {
        return Integer.compare(o1.getMes(), o2.getMes());
    }

}
