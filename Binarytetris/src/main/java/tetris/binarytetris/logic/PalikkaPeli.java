package tetris.binarytetris.logic;

import java.util.Random;

/**
 * Luokka huolehtii pelin taulukon toiminnallisuuksista ja päivityksistä.
 *
 * @author Venla Viljamaa
 */
public class PalikkaPeli {

    private final PalikkaTaulukko taulukko;
    private int uusiArvo;
    private int haluttuSumma;
    private int pisteet;
    private final int voittoSumma;
    private int vaikeustaso;

    /**
     * Konstruktori.
     *
     * @param korkeus taulukon korkeus
     * @param leveys taulukon leveys
     * @param haluttuSumma summa, jota tavoitellaan ensimmäiseksi
     * @param voittoSumma summa, jonka tavoitettua pelaaja voittaa pelin
     */
    public PalikkaPeli(int korkeus, int leveys, int haluttuSumma, int voittoSumma) {
        this.taulukko = new PalikkaTaulukko(korkeus, leveys);
        this.uusiArvo = 0;
        this.haluttuSumma = haluttuSumma;
        this.pisteet = 0;
        this.voittoSumma = voittoSumma;
        this.vaikeustaso = 0;
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

    public void setVaikeustaso(int vaikeustaso) {
        this.vaikeustaso = vaikeustaso;
    }

    public int getVaikeustaso() {
        return vaikeustaso;
    }

    /**
     * Metodi asettaa uuden palikan valittuun sarakkeeseen. Niin kauan kuin
     * palikat pääsevät putoamaan alaspäin tarkistetaan muodostuuko palikoista
     * summaa, ja onko pisteitä tarpeeksi vaikeustason nostoon.
     *
     * @param sarake Määrittelee, monenteenko sarakkeeseen uusi arvo asetetaan.
     *
     * @see tetris.binarytetris.logic.PalikkaPeli#liikuAlas()
     * @see tetris.binarytetris.logic.PalikkaPeli#tarkistaSummat()
     * @see tetris.binarytetris.logic.PalikkaPeli#nostaVaikeustasoa()
     */
    public void paivita(int sarake) {
        if (taulukko.getPalikka(0, sarake - 1).getArvo() == 0) {
            taulukko.setPalikka(uusiArvo, 0, sarake - 1);
        }
        while (true) {
            tarkistaSummat();
            while (nostaVaikeustasoa()) {
                tarkistaSummat();
            }
            if (liikuAlas() == false) {
                break;
            }
        }
        
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
        for (int y = 0; y < taulukko.getKorkeus(); y++) {
            for (int x = 0; x < taulukko.getLeveys(); x++) {
                if (taulukko.siirraAlas(y, x)) {
                    return true;
                }
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
     *
     * @return true jos löytyi summa/summia, muuten false.
     */
    public boolean tarkistaSummat() {
        for (int y = taulukko.getKorkeus() - 1; y >= 0; y--) {
            for (int x = taulukko.getLeveys() - 1; x >= 0; x--) {
                if (taulukko.tarkistaVaakaSumma(y, x, haluttuSumma)) {
                    pisteet++;
//                    nostaVaikeustasoa();
                    return true;
                }
                if (taulukko.tarkistaPystySumma(y, x, haluttuSumma)) {
//                    nostaVaikeustasoa();
                    pisteet++;
                    return true;
                }
                
            }
        }
        return false;
    }

    /**
     * Metodi muuttaa tavoiteltavan summan yhtä isommaksi, kun pelaaja saa
     * tarpeeksi pisteitä. Samalla pistelasku nollataan.
     *
     * @return true jos vaikeustasoa nostetaan, muuten false.
     */
    public boolean nostaVaikeustasoa() {
        if (this.pisteet == vaikeustaso) {
            this.haluttuSumma += 1;
            this.pisteet = 0;
            return true;
        }
        return false;
    }

    /**
     * Metodi tarkistaa ensin, onko taulukossa summia, sitten osuuko joku 
     * palikka taulukon yläreunaan.
     *
     * @see tetris.binarytetris.logic.PalikkaPeli#tarkistaSummat()
     * 
     * @return true jos osuu, muuten false.
     */
    public boolean peliHavitty() {
        tarkistaSummat();
        for (int x = 0; x < taulukko.getLeveys(); x++) {
            if (taulukko.getPalikka(0, x).getArvo() != 0) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Metodi tarkistaa onko voittosumma saavutettu.
     * 
     * @return true jos on saavutettu, muuten false.
     */
    public boolean peliVoitettu() {
        return this.haluttuSumma > voittoSumma;
    }

//    public void tulosta() {
//        System.out.println();
//        for (int y = 0; y < taulukko.getKorkeus(); y++) {
//            for (int x = 0; x < taulukko.getLeveys(); x++) {
//                System.out.print(taulukko.getPalikka(y, x).getArvo() + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//    }

}
