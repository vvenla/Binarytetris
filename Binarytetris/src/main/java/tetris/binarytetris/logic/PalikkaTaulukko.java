package tetris.binarytetris.logic;

/**
 * Luokka huolehtii taulukon ominaisuuksista.
 *
 * @author Venla Viljamaa
 */
public class PalikkaTaulukko {

    private final Palikka[][] taulukko;

    /**
     * Konstruktori.
     *
     * @param korkeus taulukon korkeus
     * @param leveys taulukon leveys
     *
     */
    public PalikkaTaulukko(int korkeus, int leveys) {
        this.taulukko = new Palikka[korkeus][leveys];
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                taulukko[y][x] = new Palikka(0);
            }
        }
    }

    public int getKorkeus() {
        return taulukko.length;
    }

    public int getLeveys() {
        return taulukko[0].length;
    }

    /**
     * Metodi palauttaa taulukosta Palikkoa-olion halutulta paikalta taulukosta.
     *
     * @param y Palikan y-koordinaatti
     * @param x Palikan x-koordinaatti
     *
     * @return Palikka-olio taulukon kohdassa y, x.
     */
    public Palikka getPalikka(int y, int x) {
        return taulukko[y][x];
    }

    /**
     * Metodi asettaa tietylle taulukon Palikka-oliolle halutun arvon.
     *
     * @param arvo Palikalle asetettava arvo
     * @param y Palikan y-koordinaatti
     * @param x Palikan x-koordinaatti
     */
    public void setPalikka(int arvo, int y, int x) {
        taulukko[y][x].setArvo(arvo);
    }

    /**
     * Metodi siirtää Palikka-olion alaspäin taulukossa, jos sen alapuolella
     * olevan palikan arvo on nolla.
     *
     * @param y Palikan y-koordinaatti
     * @param x Palikan x-koordinaatti
     *
     * @return true jos siirto tehdään, muuten false.
     */
    public boolean siirraAlas(int y, int x) {
        int arvo = taulukko[y][x].getArvo();
        if (y < getKorkeus() - 1 && getPalikka(y, x).getArvo() != 0) {
            if (taulukko[y + 1][x].getArvo() == 0) {
                taulukko[y + 1][x].setArvo(arvo);
                taulukko[y][x].setArvo(0);
                return true;
            }
        }
        return false;
    }

    /**
     * Metodi tarkistaa, muodostuuko vierekkäisten palikoiden arvojen summasta
     * haluttua tasasummaa.
     *
     * @param y Palikan y-koordinaatti
     * @param x Palikan x-koordinaatti
     * @param haluttuSumma Tarkistettava summa
     *
     * @return true jos löytyy vaakasumma, muuten false.
     */
    public boolean tarkistaVaakaSumma(int y, int x, int haluttuSumma) {
        int tarkistettava = 0;
        int vierekkaistenSumma = 0;
        while (tarkistettava < getLeveys() - x && taulukko[y][x + tarkistettava].getArvo() != 0) {
            vierekkaistenSumma += taulukko[y][x + tarkistettava].getArvo();
            if (vierekkaistenSumma == haluttuSumma) {
                for (int i = 0; i <= tarkistettava; i++) {
                    taulukko[y][x + i].setArvo(0);
                }
                return true;
            }
            tarkistettava++;
        }
        return false;
    }

    /**
     * Metodi tarkistaa, muodostuuko päällekkäisten palikoiden arvojen summasta
     * haluttua tasasummaa.
     *
     * @param y Palikan y-koordinaatti
     * @param x Palikan x-koordinaatti
     * @param haluttuSumma Tarkistettava summa
     *
     * @return true jos summa löytyy, muuten false.
     */
    public boolean tarkistaPystySumma(int y, int x, int haluttuSumma) {
        int tarkistettava = 0;
        int vierekkaistenSumma = 0;
        while (tarkistettava < getKorkeus() - y && taulukko[y + tarkistettava][x].getArvo() != 0) {
            vierekkaistenSumma += taulukko[y + tarkistettava][x].getArvo();
            if (vierekkaistenSumma == haluttuSumma) {
                for (int i = 0; i <= tarkistettava; i++) {
                    taulukko[y + i][x].setArvo(0);
                }
                return true;
            }
            tarkistettava++;
        }
        return false;
    }

}
