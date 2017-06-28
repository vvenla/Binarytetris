package tetris.binarytetris.logic;

/**
 * Luokka hoitaa Palikka-olion ominaisuuksia.
 *
 * @author Venla Viljamaa
 */
public class Palikka {

    private int arvo;

    /**
     * Konstruktori.
     *
     * @param arvo palikan arvo
     */
    public Palikka(int arvo) {
        this.arvo = arvo;
    }

    public int getArvo() {
        return this.arvo;
    }

    public void setArvo(int arvo) {
        this.arvo = arvo;
    }

    @Override
    public String toString() {
        return "" + this.arvo;
    }

}
