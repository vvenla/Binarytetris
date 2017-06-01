/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.binarytetris;

import java.util.Scanner;
import tetris.binarytetris.gui.Tekstikayttis;
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
        Scanner lukija = new Scanner(System.in);
        Tekstikayttis kayttis = new Tekstikayttis(10, 5, lukija, 15);
        kayttis.pelaa();
    }

}
