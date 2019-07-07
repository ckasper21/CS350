import org.junit.Test;
import static org.junit.Assert.*;

public class CoinPackTest {

    @Test
    public void createCoinPack() {
        // Test - creating CoinPack object
        CoinPack cp = new CoinPack();
        assertNotNull(cp);
    }

    @Test
    public void createCoinPack_Params() {
        // Test - creating CoinPack object
        CoinPack cp = new CoinPack(10,20,30,40);
        assertNotNull(cp);
    }

    @Test (expected = IllegalArgumentException.class)
    public void createCoinPack_Invalid() {
        // Test - creating CoinPack object with bad arguments
        CoinPack cp = new CoinPack(10,-20,30,40);
    }

    @Test
    public void getPennies() {
        CoinPack cp = new CoinPack(10,20,30,40);
        assertEquals(10,cp.pennies());
    }

    @Test
    public void getNickles() {
        CoinPack cp = new CoinPack(10,20,30,40);
        assertEquals(20,cp.nickles());
    }

    @Test
    public void getDimes() {
        CoinPack cp = new CoinPack(10,20,30,40);
        assertEquals(30,cp.dimes());
    }

    @Test
    public void getQuarters() {
        CoinPack cp = new CoinPack(10,20,30,40);
        assertEquals(40,cp.quarters());
    }

    @Test
    public void setPennies() {
        CoinPack cp = new CoinPack();

        // Test - 15 pennies, check cp
        assertTrue(cp.pennies(15));
        assertEquals(15, cp.pennies());

        // Test - try setting negative amount
        assertFalse(cp.pennies(-15));
    }

    @Test
    public void setNickles() {
        CoinPack cp = new CoinPack();

        // Test - 4 nickles, check cp
        assertTrue(cp.nickles(4));
        assertEquals(4, cp.nickles());

        // Test - try setting negative amount
        assertFalse(cp.nickles(-4));
    }

    @Test
    public void setDimes() {
        CoinPack cp = new CoinPack();

        // Test - 22 dimes, check cp
        assertTrue(cp.dimes(22));
        assertEquals(22,cp.dimes());

        // Test - try setting negative amount
        assertFalse(cp.dimes(-22));
    }

    @Test
    public void setQuarters() {
        CoinPack cp = new CoinPack();

        // Test - 17 quarters, check cp
        assertTrue(cp.quarters(17));
        assertEquals(17, cp.quarters());

        // Test - try setting negative amount
        assertFalse(cp.quarters(-17));
    }

}