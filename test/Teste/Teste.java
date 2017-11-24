/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Th
 */
public class Teste {

    public static void main(String[] args) {
        sort();
        List<Integer> numero = getNumero();
        int cartelas[][] = new int[20][2];
        rout:
        for (int x = 0; x < numero.size(); x++) {
            for (int y = 0; y < 15; y++) {
                List<Integer> cartela = new ArrayList();
                seq1(x, numero, cartela);
                for (int c : cartela) {
                    System.out.print(c + "-");
                }
                System.out.println("");

            }
            numero.set(x, 0);
        }
    }

    private static List<Integer> getNumero() {
        List<Integer> numero = new ArrayList<>();//{1, 2, 3, 4, 5, 6};//, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
        numero.add(1);
        numero.add(2);
        numero.add(3);
        numero.add(4);
        numero.add(5);
        numero.add(6);
        numero.add(7);
        numero.add(8);
        numero.add(9);
        numero.add(10);
        numero.add(11);
        numero.add(12);
        numero.add(13);
        numero.add(14);
        numero.add(15);
        numero.add(16);
        numero.add(17);
        numero.add(18);
        numero.add(19);
        numero.add(20);
        numero.add(21);
        numero.add(22);
        numero.add(23);
        numero.add(24);
        numero.add(25);
        return numero;
    }

    private static void seq1(int x, List<Integer> numero, List<Integer> cartela) {
        for (int num2 : numero) {
            if (cartela.size() == 15) {
                return;
            }

            selecionar(x, num2, numero, cartela);

        }
    }

    private static void selecionar(int x, int num2, List<Integer> numero, List<Integer> cartela) {
//        if (numero.get(x).intValue() == num2 || num2 == 0||cartela.contains(num2)) {
        if (cartela.contains(num2) || num2 == 0) {
            return;
        }
        cartela.add(num2);
    }

    private static void sort() {
        int b = 0;
        int c = 0;

        do {
            b = new Random().nextInt(6);
            c = new Random().nextInt(6);
        } while (b == 0 || c == 0 || b == c);

        System.out.println(b + "/" + c);

    }

    public static void mainX(String[] args) {
        int[] numero = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
        int[][] cartelas = new int[100000][15];

        for (int posi = 0; posi < 100000; posi++) {

            int cartela[] = cartelas[posi];
            int i = 0;

            rout:
            for (int num : numero) {
                while (i < 15) {
                    if (num != cartela[i]) {
                        cartela[i] = num;
                        i++;
                        continue rout;
                    }
                    i++;
                }
            }
        }
        System.out.println(cartelas.length);
        for (int[] x : cartelas) {
            System.out.println("");
            for (int i = 0; i <= 14; i++) {
                System.out.print(x[i] + "-");
            }
        }
    }
}
