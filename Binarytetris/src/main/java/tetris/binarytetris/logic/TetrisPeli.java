package tetris.binarytetris.logic;

import java.util.Random;

/**
 *
 * @author Venla Viljamaa
 */
public class TetrisPeli {

    private final PalikkaTaulukko taulukko;
    private final int korkeus;
    private final int leveys;
    private int uusiArvo;
    private final int haluttuSumma;

    public TetrisPeli(int korkeus, int leveys, int haluttuSumma) {
        this.korkeus = korkeus;
        this.leveys = leveys;
        this.taulukko = new PalikkaTaulukko(korkeus, leveys);
        this.uusiArvo = 0;
        this.haluttuSumma = haluttuSumma;
    }

    public PalikkaTaulukko getTaulukko() {
        return this.taulukko;
    }

    /**
     * Metodi arpoo muuttujalle uusiArvo korkeintaan haluttua summaa puolet
     * pienemmän arvon, vähintään arvon yksi.
     */
    public void setUusiArvo() {
        this.uusiArvo = new Random().nextInt(haluttuSumma / 2) + 1;
    }

    public int getUusiArvo() {
        return this.uusiArvo;
    }

    /**
     * Metodi tarkistaa, muodostuuko vierekkäisten palikoiden arvojen summasta
     * haluttua tasasummaa.
     *
     * @param sarake Määrittelee, monenteenko sarakkeeseen uusi arvo asetetaan.
     *
     * @see tetris.binarytetris.logic.TetrisPeli#liikuAlas()
     * @see tetris.binarytetris.logic.TetrisPeli#tarkistaSummat()
     * @see tetris.binarytetris.logic.TetrisPeli#onkoTaulukossaTilaa()
     */
    public void paivita(int sarake) {
        if (taulukko.getPalikka(0, sarake - 1).getArvo() == 0) {
            taulukko.setPalikka(uusiArvo, 0, sarake - 1);
        }
        while (true) {
            if (liikuAlas() == false) {
                break;
            }
            liikuAlas();
            tarkistaSummat();
//            liikuAlasKaanteinen();
        }

        onkoTaulukossaTilaa();
    }

    /**
     * Metodi liikuttaa kaikkia taulukon alkioita pykälän alaspäin, jos alla
     * olevan palikan arvo on nolla.
     *
     * @see tetris.binarytetris.logic.PalikkaTaulukko#siirraAlas(int, int)
     * @return true jos joku taulukon alkioista liikkuu alas, muuten false.
     */
    public boolean liikuAlas() {
        boolean liikkuuko = false;
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                if (taulukko.siirraAlas(y, x)) {
                    liikkuuko = true;
                }
            }
        }
        return liikkuuko;
    }

//    public void liikuAlasKaanteinen() {
//        for (int y = korkeus - 1; y >= 0; y--) {
//            for (int x = 0; x < leveys; x++) {
//                taulukko.siirraAlas(y, x);
//            }
//        }
//    }
    /**
     * Metodi tarkistaa, onko taulukon ylimmällä rivillä palikoita, joden arvo
     * on nolla.
     *
     * @return true, jos ylimmällä rivillä on vähintään yksi nolla, muuten
     * false.
     */
    public boolean onkoTaulukossaTilaa() {
        for (int x = 0; x < leveys; x++) {
            if (taulukko.getPalikka(0, x).getArvo() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodi tarkistaa löytyykö taulukosta vierekkäisiä tai päällekkäisiä
     * palikoita, joiden summa on täsmälleen haluttu summa.
     *
     * @see tetris.binarytetris.logic.PalikkaTaulukko#tarkistaVaakaSumma(int,
     * int, int)
     * @see tetris.binarytetris.logic.PalikkaTaulukko#tarkistaPystySumma(int,
     * int, int)
     */
    public void tarkistaSummat() {
        for (int y = korkeus - 1; y >= 0; y--) {
            for (int x = leveys - 1; x >= 0; x--) {
                taulukko.tarkistaVaakaSumma(y, x, haluttuSumma);
                taulukko.tarkistaPystySumma(y, x, haluttuSumma);
            }
        }
    }

}
