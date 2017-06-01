/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.binarytetris;

import java.util.Scanner;
import tetris.binarytetris.gui.Tekstikayttis;

/**
 *
 * @author Venla Viljamaa
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int korkeus = 10;
        int leveys = 5;
        int haluttuSumma = 15;
        Scanner lukija = new Scanner(System.in);
        Tekstikayttis kayttis = new Tekstikayttis(korkeus, leveys, lukija, haluttuSumma);
        kayttis.pelaa();
    }

}
