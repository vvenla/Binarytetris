package tetris.binarytetris.logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Venla Viljamaa
 */
public class PalikkaPeliTest {

    private PalikkaPeli peli;

    public PalikkaPeliTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.peli = new PalikkaPeli(10, 5, 15, 17);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hello() {

    }

    @Test
    public void testGetTaulukko() {
        PalikkaTaulukko taulu = new PalikkaTaulukko(10, 5);
        assertEquals(taulu.getKorkeus(), peli.getTaulukko().getKorkeus());
        assertEquals(taulu.getLeveys(), peli.getTaulukko().getLeveys());
        assertEquals(taulu.getPalikka(3, 4).getArvo(), peli.getTaulukko().getPalikka(3, 4).getArvo());
        assertEquals(taulu.getPalikka(5, 2).getArvo(), peli.getTaulukko().getPalikka(5, 2).getArvo());
    }

    @Test
    public void testGetTaulukko2() {
        assertEquals(0, peli.getTaulukko().getPalikka(3, 4).getArvo());
        assertEquals(0, peli.getTaulukko().getPalikka(5, 2).getArvo());
    }

    @Test
    public void testGetHaluttuSumma() {
        assertEquals(15, peli.getHaluttuSumma());
    }

    @Test
    public void testGetVaikeustaso() {
        peli.setVaikeustaso(7);
        assertEquals(7, peli.getVaikeustaso());
    }

    @Test
    public void testUudenArvonArpominen() {
        peli.setUusiArvo();
        assertTrue(peli.getUusiArvo() < 8);
        assertTrue(peli.getUusiArvo() > 0);
    }

    @Test
    public void testLiikuAlas() {
        peli.getTaulukko().setPalikka(5, 0, 4);
        peli.liikuAlas();
        assertNotEquals(5, peli.getTaulukko().getPalikka(0, 4).getArvo());
        assertTrue(peli.liikuAlas());
    }
    
    @Test
    public void testLiikuAlas2() {
        peli.getTaulukko().setPalikka(5, 9, 4);
        peli.liikuAlas();
        assertFalse(peli.liikuAlas());
    }

    @Test
    public void testPeliHavitty() {
        assertFalse(peli.peliHavitty());
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 5; x++) {
                peli.getTaulukko().setPalikka(2, y, x);
            }
        }
        assertTrue(peli.peliHavitty());
    }

    @Test
    public void testOnkoTaulukossaTilaa2() {
        assertFalse(peli.peliHavitty());
        peli.getTaulukko().setPalikka(5, 0, 0);
        assertTrue(peli.peliHavitty());
    }

    @Test
    public void testTarkistaSummat() {
        peli.getTaulukko().setPalikka(5, 9, 0);
        peli.getTaulukko().setPalikka(5, 8, 0);
        peli.getTaulukko().setPalikka(5, 7, 0);
        peli.tarkistaSummat();
        assertTrue(true);
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 5; x++) {
                assertEquals(0, peli.getTaulukko().getPalikka(y, x).getArvo());
            }
        }
    }

    @Test
    public void testPaivita() {
        peli.setVaikeustaso(1);
        peli.setUusiArvo();
        peli.paivita(3);
        assertNotEquals(0, peli.getTaulukko().getPalikka(9, 2).getArvo());
    }

    @Test
    public void testPaivita2() {
        peli.setVaikeustaso(5);
        peli.setUusiArvo();
        peli.getTaulukko().setPalikka(5, 8, 0);
        peli.getTaulukko().setPalikka(5, 7, 0);
        peli.getTaulukko().setPalikka(5, 6, 0);
        peli.paivita(5);
        peli.getTaulukko().setPalikka(4, 9, 0);
        peli.getTaulukko().setPalikka(5, 9, 1);
        peli.getTaulukko().setPalikka(5, 9, 2);
        peli.getTaulukko().setPalikka(1, 9, 3);
//        peli.tulosta();
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 5; x++) {
                assertEquals(0, peli.getTaulukko().getPalikka(y, x).getArvo());
            }
        }
        for (int x = 0; x < 5; x++) {
            assertNotEquals(0, peli.getTaulukko().getPalikka(9, x).getArvo());
        }
    }

}
