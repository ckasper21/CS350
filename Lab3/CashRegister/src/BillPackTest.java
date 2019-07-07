import org.junit.Test;

import static org.junit.Assert.*;

public class BillPackTest {

    @Test
    public void createBillPack() {
        // Test - creating BillPack object
        BillPack bp = new BillPack();
        assertNotNull(bp);
    }

    @Test
    public void createBillPack_Params() {
        // Test - creating BillPack object
        BillPack bp = new BillPack(10,20,30,40,50,60);
        assertNotNull(bp);
    }

    @Test (expected = IllegalArgumentException.class)
    public void createBillPack_Invalid() {
        // Test - creating BillPack object with bad arguments
        BillPack bp = new BillPack(10,-20,30,40,50,60);
    }

    @Test
    public void getOnes() {
        BillPack bp = new BillPack(10,20,30,40,50,60);
        assertEquals(10,bp.ones());
    }

    @Test
    public void getFives() {
        BillPack bp = new BillPack(10,20,30,40,50,60);
        assertEquals(20,bp.fives());
    }

    @Test
    public void getTens() {
        BillPack bp = new BillPack(10,20,30,40,50,60);
        assertEquals(30,bp.tens());
    }

    @Test
    public void getTwenties() {
        BillPack bp = new BillPack(10,20,30,40,50,60);
        assertEquals(40,bp.twenties());
    }

    @Test
    public void getFifties() {
        BillPack bp = new BillPack(10,20,30,40,50,60);
        assertEquals(50,bp.fifties());
    }

    @Test
    public void getHundreds() {
        BillPack bp = new BillPack(10,20,30,40,50,60);
        assertEquals(60,bp.hundreds());
    }

    @Test
    public void setOnes() {
        BillPack bp = new BillPack();

        // Test - 15 ones, check bp
        assertTrue(bp.ones(15));
        assertEquals(15,bp.ones());

        // Test - try setting negative amount
        assertFalse(bp.ones(-15));
    }

    @Test
    public void setFives() {
        BillPack bp = new BillPack();

        // Test - 4 fives, check bp
        assertTrue(bp.fives(4));
        assertEquals(4,bp.fives());

        // Test - try setting negative amount
        assertFalse(bp.fives(-4));
    }

    @Test
    public void setTens() {
        BillPack bp = new BillPack();

        // Test - 20 tens, check bp
        assertTrue(bp.tens(20));
        assertEquals(20,bp.tens());

        // Test - try setting negative amount
        assertFalse(bp.tens(-20));
    }

    @Test
    public void setTwenties() {
        BillPack bp = new BillPack();

        // Test - 31 twenties, check bp
        assertTrue(bp.twenties(31));
        assertEquals(31,bp.twenties());

        // Test - try setting negative amount
        assertFalse(bp.twenties(-31));
    }

    @Test
    public void setFifties() {
        BillPack bp = new BillPack();

        // Test - 2 fifties, check bp
        assertTrue(bp.fifties(2));
        assertEquals(2,bp.fifties());

        // Test - try setting negative amount
        assertFalse(bp.fifties(-2));
    }

    @Test
    public void setHundreds() {
        BillPack bp = new BillPack();

        // Test - 9 hundreds, check bp
        assertTrue(bp.hundreds(9));
        assertEquals(9,bp.hundreds());

        // Test - try setting negative amount
        assertFalse(bp.hundreds(-9));
    }
}