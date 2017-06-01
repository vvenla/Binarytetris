/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.binarytetris.logic;

import java.util.Random;

/**
 *
 * @author Venla Viljamaa
 */
public class TetrisPeli {

    private PalikkaTaulukko taulukko;
    private int korkeus;
    private int leveys;
    private boolean liikkuuko;
    private int uusiArvo;
    private int haluttuSumma;

    public TetrisPeli(int korkeus, int leveys, int haluttuSumma) {
        this.korkeus = korkeus;
        this.leveys = leveys;
        this.taulukko = new PalikkaTaulukko(korkeus, leveys);
        this.liikkuuko = false;
        this.uusiArvo = 0;
        this.haluttuSumma = haluttuSumma;
    }

    public PalikkaTaulukko getTaulukko() {
        return this.taulukko;
    }

    public void setUusiArvo() {
        this.uusiArvo = new Random().nextInt(haluttuSumma / 2) + 1;
    }
    
    public int getUusiArvo() {
        return this.uusiArvo;
    }

    public void paivita(int rivi) {
        taulukko.setPalikka(uusiArvo, 0, rivi - 1);
        liikuAlas();
        taulukko.loytyykoTaulukostaSummaa(haluttuSumma);
        liikuAlasKaanteinen();
    }

    public void liikuAlas() {
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                taulukko.siirraAlas(y, x);
            }
        }
    }
    
    public void liikuAlasKaanteinen() {
        for (int y = korkeus -1; y >= 0; y--) {
            for (int x = 0; x < leveys; x++) {
                taulukko.siirraAlas(y, x);
            }
        }
    }
    
}
