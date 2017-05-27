/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.binarytetris;

import tetris.binarytetris.logic.PalikkaTaulukko;
import tetris.binarytetris.logic.Palikka;

/**
 *
 * @author Venla Viljamaa
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PalikkaTaulukko taulukko = new PalikkaTaulukko(10, 5);

        taulukko.tulosta();
        System.out.println();

        Palikka palikka = new Palikka(0b0110);
        taulukko.lisaa(3, 0, 0);
        taulukko.lisaa(4, 0, 4);
        taulukko.lisaa(5, 9, 0);

        taulukko.tulosta();
        System.out.println();

        taulukko.poista(0, 4);

        taulukko.tulosta();
    }

}
