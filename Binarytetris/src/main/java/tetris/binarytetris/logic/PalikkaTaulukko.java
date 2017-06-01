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
    private int korkeus;
    private int leveys;

    public PalikkaTaulukko(int korkeus, int leveys) {
        this.taulukko = new Palikka[korkeus][leveys];
        this.korkeus = korkeus;
        this.leveys = leveys;
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                taulukko[y][x] = new Palikka(0);
            }
        }
    }

    public Palikka getPalikka(int y, int x) {
        return taulukko[y][x];
    }

    public int getKorkeus() {
        return this.korkeus;
    }

    public int getLeveys() {
        return this.leveys;
    }

    public void setPalikka(int arvo, int y, int x) {
        taulukko[y][x].setArvo(arvo);
    }

    public void siirraAlas(int y, int x) {
        int arvo = taulukko[y][x].getArvo();
        if (y < korkeus - 1) {
            if (taulukko[y + 1][x].getArvo() == 0) {
                taulukko[y + 1][x].setArvo(arvo);
                taulukko[y][x].setArvo(0);
            }
        }
    }

    public void loytyykoTaulukostaSummaa(int summa) {
        for (int y = korkeus - 1; y >= 0; y--) {
            for (int x = 0; x < leveys - 1; x++) {
                onkoVaakaSumma(y, x, summa);
            }
        }
    }

    public void onkoVaakaSumma(int y, int x, int summa) {
        int tarkistettava = 0;
        int vierekkaistenSumma = 0;
        while (tarkistettava < leveys - x) {
            vierekkaistenSumma += taulukko[y][x + tarkistettava].getArvo();
            if (vierekkaistenSumma == summa) {
                System.out.println("summa löytyi");
                for (int i = 0; i <= tarkistettava; i++) {
                    taulukko[y][x + i].setArvo(0);
                }
            }
            tarkistettava++;
        }
    }

//    public void siirraOikealle(int y, int x) {
//        int arvo = taulukko[y][x].getArvo();
//        if (taulukko[y][x + 1].getArvo() == 0) {
//            taulukko[y][x + 1].setArvo(arvo);
//            taulukko[y][x].setArvo(0);
//        }
//    }
}
