/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class TetrisPeliTest {

    private TetrisPeli peli;

    public TetrisPeliTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.peli = new TetrisPeli(10, 5, 15);
    }

    @After
    public void tearDown() {
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
    public void testUudenArvonArpominen() {
        peli.setUusiArvo();
        assertTrue(peli.getUusiArvo() < 8);
        assertTrue(peli.getUusiArvo() > 0);
    }

    @Test
    public void testPaivita() {
        peli.setUusiArvo();
        peli.paivita(3);
        assertNotEquals(0, peli.getTaulukko().getPalikka(9, 2).getArvo());
    }

    @Test
    public void testLiikuAlas() {
        peli.getTaulukko().setPalikka(5, 0, 4);
        peli.liikuAlas();
        assertNotEquals(0, peli.getTaulukko().getPalikka(9, 4).getArvo());
    }

//    @Test
//    public void testLiikuAlasKaanteinen() {
//        peli.getTaulukko().setPalikka(5, 8, 4);
//        peli.liikuAlas();
//        assertNotEquals(0, peli.getTaulukko().getPalikka(9, 4).getArvo());
//    }
    @Test
    public void testOnkoTaulukossaTilaa() {
        assertTrue(peli.onkoTaulukossaTilaa());
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 5; x++) {
                peli.getTaulukko().setPalikka(2, y, x);
            }
        }
        assertFalse(peli.onkoTaulukossaTilaa());
    }

    @Test
    public void testTarkistaSummat() {
        peli.getTaulukko().setPalikka(5, 9, 0);
        peli.getTaulukko().setPalikka(5, 8, 0);
        peli.getTaulukko().setPalikka(5, 7, 0);
        peli.getTaulukko().setPalikka(5, 9, 2);
        peli.getTaulukko().setPalikka(5, 9, 3);
        peli.getTaulukko().setPalikka(5, 9, 4);
        peli.tarkistaSummat();
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 5; x++) {
                assertEquals(0, peli.getTaulukko().getPalikka(y, x).getArvo());
            }
        }
    }

}
