package tetris.binarytetris.ui;

import java.util.Scanner;
import tetris.binarytetris.logic.PalikkaTaulukko;
import tetris.binarytetris.logic.TetrisPeli;

/**
 *
 * @author Venla Viljamaa
 */
public class Tekstikayttis {

    private final TetrisPeli peli;
    private final PalikkaTaulukko taulukko;
    private final Scanner lukija;
//    private final int summa;
    private final int leveys;

    public Tekstikayttis(int korkeus, int leveys, Scanner lukija, int summa) {
        this.peli = new TetrisPeli(korkeus, leveys, summa);
        this.taulukko = peli.getTaulukko();
        this.lukija = lukija;
//        this.summa = summa;
        this.leveys = leveys;
    }

    public void pelaa() {
        while (peli.onkoTaulukossaTilaa()) {
            peli.setUusiArvo();
            System.out.println("Seuraava palikka: " + peli.getUusiArvo());
            paivitaPeli();
        }
        System.out.println("Peli loppui");
    }

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
