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
public class PalikkaTest {

    private Palikka palikka;

    public PalikkaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.palikka = new Palikka(0);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hello() {
    }

    @Test
    public void palikanArvoLuotaessaOnNolla() {
        assertEquals(0, palikka.getArvo());
    }

    @Test
    public void palikalleVoiAntaaDesimaaliArvon() {
        palikka.setArvo(12);
        assertEquals(12, palikka.getArvo());
    }

    @Test
    public void palikalleVoiAntaaBinaariArvon() {
        palikka.setArvo(0b0100);
        assertEquals(4, palikka.getArvo());
    }

    @Test
    public void palikkaToString() {
        assertEquals("0", palikka.toString());
    }

    @Test
    public void palikkaBinaariArvollaToString() {
        palikka.setArvo(0b0110);
        assertEquals("6", palikka.toString());
    }

    @Test
    public void palikkaDesimaaliArvollaToString() {
        palikka.setArvo(11);
        assertEquals("11", palikka.toString());
    }

}
