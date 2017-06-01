package tetris.binarytetris.logic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class PalikkaTaulukkoTest {

    private PalikkaTaulukko taulukko;

    public PalikkaTaulukkoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.taulukko = new PalikkaTaulukko(10, 5);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hello() {
    }

    @Test
    public void taulukkoAlustetaanNolliksi() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 5; x++) {
                assertEquals(0, taulukko.getPalikka(y, x).getArvo());
            }
        }
    }
    
    @Test
    public void getKorkeusToimii() {
        assertEquals(10, taulukko.getKorkeus());
    }
    
    @Test
    public void getLeveysToimii() {
        assertEquals(5, taulukko.getLeveys());
    }

    @Test
    public void setPalikkaToimii() {
        taulukko.setPalikka(3, 0, 0);
        assertEquals(3, taulukko.getPalikka(0, 0).getArvo());
    }

    @Test
    public void setPalikkaToimii2() {
        taulukko.setPalikka(5, 0, 4);
        taulukko.setPalikka(2, 9, 0);
        assertEquals(5, taulukko.getPalikka(0, 4).getArvo());
        assertEquals(2, taulukko.getPalikka(9, 0).getArvo());
    }

    @Test
    public void siirraAlasToimii() {
        taulukko.setPalikka(3, 0, 0);
        taulukko.siirraAlas(0, 0);
        assertEquals(3, taulukko.getPalikka(1, 0).getArvo());
        assertEquals(0, taulukko.getPalikka(0, 0).getArvo());
    }
    
    @Test
    public void vaakaSummanTarkistusToimii() {
        taulukko.setPalikka(3, 0, 0);
        taulukko.setPalikka(3, 0, 1);
        taulukko.setPalikka(3, 0, 2);
        taulukko.loytyykoTaulukostaSummaa(9);
        assertEquals(0, taulukko.getPalikka(0, 0).getArvo());
        assertEquals(0, taulukko.getPalikka(0, 1).getArvo());
        assertEquals(0, taulukko.getPalikka(0, 2).getArvo());
    }

}