/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.movimento;

/**
 *
 * @author Thiago
 */
public interface SelectBean {

    public int getId();

    public String getDescricao();

    public boolean isSelected();

    public void setSelected(boolean value);
}
