/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.binarytetris.logic;


/**
 *
 * @author Venla Viljamaa
 */
public class PalikkaTaulukko {

    private Palikka[][] taulukko;

    public PalikkaTaulukko(int korkeus, int leveys) {
        this.taulukko = new Palikka[korkeus][leveys];
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                taulukko[y][x] = new Palikka(0);
            }
        }
    }

    public void poista(int y, int x) {
        taulukko[y][x].setArvo(0);
    }

    public void lisaa(int arvo, int y, int x) {
        taulukko[y][x].setArvo(arvo);
    }
    

    //tulosta-metodi testailua varten, ei tarvita enää valmiissa ohjelmassa
    public void tulosta() {
        for (int y = 0; y < taulukko.length; y++) {
            for (int x = 0; x < taulukko[0].length; x++) {
                System.out.print(taulukko[y][x].getArvo() + " ");
            }
            System.out.println();
        }
    }

}
