/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparator;

import bean.movimento.Select;
import java.util.Comparator;

/**
 *
 * @author Thiago
 */
public class SelectComparator implements Comparator<Select> {

    int ordem = 0;

    public SelectComparator(int ordem) {
        this.ordem = ordem;
    }

    @Override
    public int compare(Select o1, Select o2) {
        switch (ordem) {
            case 0:
                return o1.getDescricao().compareTo(o2.getDescricao());
            default:
                return Integer.compare(o1.getId(), o2.getId());
        }
    }

}
