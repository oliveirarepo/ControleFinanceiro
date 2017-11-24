/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparator;

import bean.cadastro.Categoria;
import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Thiago
 */
public class CategoriaComparator implements Comparator<Categoria>, Serializable {

    int ordem = 0;

    public CategoriaComparator(int ordem) {
        this.ordem = ordem;
    }

    @Override
    public int compare(Categoria o1, Categoria o2) {
        switch (ordem) {
            case 1:
                return o1.getDescricaoCategoria().compareTo(o2.getDescricaoCategoria());
            case 2:
                return Double.compare(o1.getSomaValores(), o2.getSomaValores());
            case 3:
                return Double.compare(o1.getSomaValores(), o2.getSomaValores());
            default:
                return Integer.compare(o1.getIdCategoria(), o2.getIdCategoria());
        }
    }

}
