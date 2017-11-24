/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.movimento;

import java.util.Comparator;

/**
 *
 * @author Thiago
 */
public abstract class Select implements SelectBean, Comparator<SelectBean> {

    protected boolean selected;

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public int compare(SelectBean o1, SelectBean o2) {
        return o1.getDescricao().compareTo(o2.getDescricao());
    }

}
