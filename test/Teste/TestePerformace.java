/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ThiagoUser
 */
public class TestePerformace {

    private List pilha = new ArrayList();
    int count = 0;

    public void push(Object obj) {
        pilha.add(count++, obj);
    }

    public static void main(String[] args) {
        new TestePerformace().rodar();
    }

    private void rodar() {

        while (true) {
            push(new Object());
            pop();
        }

    }

    public Object pop() {
        if (count == 0) {
            return null;
        }
        Object o = pilha.get(--count);

        pilha.set(count, null);
        return o;
    }
}
