package tetris.binarytetris.logic;

import java.util.Random;

/**
 *
 * @author Venla Viljamaa
 */
public class TetrisPeli {

    private final PalikkaTaulukko taulukko;
    private int uusiArvo;
    private int haluttuSumma;
    private int pisteet;
    private int voittoSumma;

    public TetrisPeli(int korkeus, int leveys, int haluttuSumma, int voittoSumma) {
        this.taulukko = new PalikkaTaulukko(korkeus, leveys);
        this.uusiArvo = 0;
        this.haluttuSumma = haluttuSumma;
        this.pisteet = 0;
        this.voittoSumma = voittoSumma;
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

    public int getPisteet() {
        return this.pisteet;
    }

    public int getHaluttuSumma() {
        return this.haluttuSumma;
    }

    /**
     * Metodi tarkistaa, muodostuuko vierekkäisten palikoiden arvojen summasta
     * haluttua tasasummaa.
     *
     * @param sarake Määrittelee, monenteenko sarakkeeseen uusi arvo asetetaan.
     *
     * @see tetris.binarytetris.logic.TetrisPeli#liikuAlas()
     * @see tetris.binarytetris.logic.TetrisPeli#tarkistaSummat()
     * @see tetris.binarytetris.logic.TetrisPeli#nostaVaikeustasoa()
     */
    public void paivita(int sarake) {
        if (taulukko.getPalikka(0, sarake - 1).getArvo() == 0) {
            taulukko.setPalikka(uusiArvo, 0, sarake - 1);
        }
        while (true) {
            tarkistaSummat();
            if (liikuAlas() == false) {
                break;
            }
        }
        nostaVaikeustasoa();

    }

    /**
     * Metodi liikuttaa kaikkia taulukon alkioita pykälän alaspäin, jos alla
     * olevan palikan arvo on nolla.
     *
     * @see tetris.binarytetris.logic.PalikkaTaulukko#siirraAlas(int, int)
     *
     * @return true jos joku taulukon alkioista liikkuu alas, muuten false.
     */
    public boolean liikuAlas() {
        boolean liikkuuko = false;
        for (int y = 0; y < taulukko.getKorkeus(); y++) {
            for (int x = 0; x < taulukko.getLeveys(); x++) {
                if (taulukko.siirraAlas(y, x)) {
                    liikkuuko = true;
                }
            }
        }
        return liikkuuko;
    }

    /**
     * Metodi tarkistaa, osuuko taulukon yläreunaan nollasta poikkeavia
     * palikoita.
     *
     * @return true, jos yläreunaan osuu palikka, muuten false.
     */
    public boolean onkoTaulukossaTilaa() {
        for (int x = 0; x < taulukko.getLeveys(); x++) {
            if (taulukko.getPalikka(0, x).getArvo() != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metodi tarkistaa löytyykö taulukosta vierekkäisiä tai päällekkäisiä
     * palikoita, joiden summa on täsmälleen haluttu summa.
     *
     * @see tetris.binarytetris.logic.PalikkaTaulukko#tarkistaVaakaSumma(int,
     * int, int)
     * @see tetris.binarytetris.logic.PalikkaTaulukko#tarkistaPystySumma(int,
     * int, int)
     *
     * @return true jos löytyi summa/summia, muuten false.
     */
    public boolean tarkistaSummat() {
        for (int y = taulukko.getKorkeus() - 1; y >= 0; y--) {
            for (int x = taulukko.getLeveys() - 1; x >= 0; x--) {
                if (taulukko.tarkistaVaakaSumma(y, x, haluttuSumma)) {
                    pisteet++;
                    return true;
                }
                if (taulukko.tarkistaPystySumma(y, x, haluttuSumma)) {
                    pisteet++;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Metodi muuttaa tavoiteltavan summan yhtä isommaksi, kun pelaaja saa
     * tarpeeksi pisteitä. Samalla pistelasku aloitetaan alusta.
     *
     */
    public void nostaVaikeustasoa() {
        if (this.pisteet == 5) {
            this.pisteet = 0;
            this.haluttuSumma += 1;
        }
    }

    public boolean peliHavitty() {
        tarkistaSummat();
        return !onkoTaulukossaTilaa();
    }

    public boolean peliVoitettu() {
        return this.haluttuSumma > voittoSumma;
    }

}
