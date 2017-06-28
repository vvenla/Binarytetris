package tetris.binarytetris.ui;

import java.util.Scanner;
import tetris.binarytetris.logic.PalikkaTaulukko;
import tetris.binarytetris.logic.PalikkaPeli;

/**
 * Tekstikäyttöliittymä testailua varten, ennen graafista käyttöliittymää.
 *
 * @author Venla Viljamaa
 */
public class Tekstikayttis {

    private final PalikkaPeli peli;
    private final PalikkaTaulukko taulukko;
    private final Scanner lukija;
    private final int leveys;

    /**
     * Konstruktori.
     *
     * @param korkeus taulukon korkeus
     * @param leveys taulukon leveys
     * @param lukija Scanner
     * @param summa haluttu summa
     * @param loppusumma voittosumma
     *
     */
    public Tekstikayttis(int korkeus, int leveys, Scanner lukija, int summa, int loppusumma) {
        this.peli = new PalikkaPeli(korkeus, leveys, summa, loppusumma);
        this.taulukko = peli.getTaulukko();
        this.lukija = lukija;
//        this.summa = summa;
        this.leveys = leveys;
    }

    /**
     * Metodi käynnistää pelin.
     *
     */
    public void pelaa() {
        while (!peli.peliHavitty()) {
            peli.setUusiArvo();
            System.out.println("Seuraava palikka: " + peli.getUusiArvo());
            paivitaPeli();
        }
        System.out.println("Peli loppui");
    }

    /**
     * Metodi päivittää peliä.
     *
     */
    public void paivitaPeli() {
        System.out.print("Anna sarake: ");
        String komento = lukija.nextLine();

        if (!komento.matches("[1-" + leveys + "]")) {
            System.out.println("Väärä komento");
        } else {
            peli.paivita(Integer.parseInt(komento));
        }
        tulosta();
    }

    /**
     * Metodi tulostaa pelitilanteen.
     *
     */
    public void tulosta() {
        System.out.println();
        for (int y = 0; y < taulukko.getKorkeus(); y++) {
            for (int x = 0; x < taulukko.getLeveys(); x++) {
                System.out.print(taulukko.getPalikka(y, x).getArvo() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
