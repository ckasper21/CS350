import org.junit.Test;

import static org.junit.Assert.*;

public class DrawerTest {

    @Test
    public void createDrawer() {
        Drawer d = new Drawer();
        assertNotNull(d);
    }

    @Test
    public void createDrawer_Params() {
        Drawer d = new Drawer(1,2,3,4,5,6,7,8,9,10);
        assertNotNull(d);
    }

    @Test
    public void createDrawer_CoinPackBillPack() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);
        assertNotNull(d);
    }

    @Test
    public void drawerTotalInCents() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);

        // Test - d = 171641 (in cents)
        //          cp = 141 total
        //          bp = 171500 total

        assertEquals(171641, d.drawerTotalInCents());

    }

    @Test
    public void penny() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);

        // Test - get pennies
        assertEquals(1, d.penny());
    }

    @Test
    public void nickle() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);

        // Test - get nickles
        assertEquals(2, d.nickle());
    }

    @Test
    public void dime() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);

        // Test - get dimes
        assertEquals(3, d.dime());
    }

    @Test
    public void quarter() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);

        // Test - get quarters
        assertEquals(4, d.quarter());
    }

    @Test
    public void one() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);

        // Test - get ones
        assertEquals(5, d.one());
    }

    @Test
    public void five() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);

        // Test - get fives
        assertEquals(6, d.five());
    }

    @Test
    public void ten() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);

        // Test - get tens
        assertEquals(7, d.ten());
    }

    @Test
    public void twenty() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);

        // Test - get ones
        assertEquals(8, d.twenty());
    }

    @Test
    public void fifty() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);

        // Test - get fifties
        assertEquals(9, d.fifty());
    }

    @Test
    public void hundred() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);

        // Test - get hundreds
        assertEquals(10, d.hundred());
    }

    @Test
    public void coinPack() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);

        // Test - get CoinPack
        assertSame(cp, d.coinPack());
    }

    @Test
    public void billPack() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);

        // Test - get BillPack
        assertSame(bp, d.billPack());
    }

    @Test
    public void depositChange() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);

        // Test - change d coins, check each amount

        d.depositChange(2,4,6,8);

        assertEquals(3, d.penny());
        assertEquals(6, d.nickle());
        assertEquals(9, d.dime());
        assertEquals(12, d.quarter());
    }

    @Test (expected = IllegalArgumentException.class)
    public void depositChange_Invalid() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);
        d.depositChange(-4,3,2,1);
    }

    @Test
    public void depositChange_viaCoinPack() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);
        CoinPack cp2 = new CoinPack(10,20,30,40);

        // Test - change d coins w/ CoinPack, check each amount

        d.depositChange(cp2);

        assertEquals(11, d.penny());
        assertEquals(22, d.nickle());
        assertEquals(33, d.dime());
        assertEquals(44, d.quarter());

    }

    @Test
    public void depositBills() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);

        // Test - change d bills, check each amount

        d.depositBills(1,3,5,7,9,11);
        assertEquals(6, d.one());
        assertEquals(9, d.five());
        assertEquals(12, d.ten());
        assertEquals(15, d.twenty());
        assertEquals(18, d.fifty());
        assertEquals(21, d.hundred());

    }

    @Test (expected = IllegalArgumentException.class)
    public void depositBills_Invalid() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);
        d.depositBills(3,2,1,0,-1,-2);
    }

    @Test
    public void depositBills_viaBillPack() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);
        BillPack bp2 = new BillPack(10,11,12,13,14,15);

        // Test - change d bills with BillPack - check amounts

        d.depositBills(bp2);
        assertEquals(15, d.one());
        assertEquals(17, d.five());
        assertEquals(19, d.ten());
        assertEquals(21, d.twenty());
        assertEquals(23, d.fifty());
        assertEquals(25, d.hundred());
    }

    @Test
    public void removeChange() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);

        // Test - change d coin amount, check each amount

        d.removeChange(1,2,3,4);

        assertEquals(0, d.penny());
        assertEquals(0, d.nickle());
        assertEquals(0, d.dime());
        assertEquals(0, d.quarter());

    }

    @Test
    public void removeChange_Invalid() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);

        // Test - check if it has sufficient funds
        assertFalse(d.removeChange(2,3,4,5));
    }

    @Test
    public void removeChange_viaCoinPack() {
        CoinPack cp = new CoinPack(5,6,7,8);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);
        CoinPack cp2 = new CoinPack(1,2,3,4);

        // Test - change d coins w/ CoinPack, check each amount

        d.removeChange(cp2);

        assertEquals(4, d.penny());
        assertEquals(4, d.nickle());
        assertEquals(4, d.dime());
        assertEquals(4, d.quarter());
    }

    @Test
    public void removeBills() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);

        // Test - change d bills amount, check each amount

        d.removeBills(1,2,3,4,5,6);

        assertEquals(4, d.one());
        assertEquals(4, d.five());
        assertEquals(4, d.ten());
        assertEquals(4, d.twenty());
        assertEquals(4, d.fifty());
        assertEquals(4, d.hundred());
    }

    @Test
    public void removeBills_Invalid() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);

        // Test - check if d has sufficient amount

        d.removeBills(6,7,8,9,10,11);
    }

    @Test
    public void removeBills_viaBillPack() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer(cp,bp);
        BillPack bp2 = new BillPack(1,2,3,4,5,6);

        // Test - change d bills amount, check each amount

        d.removeBills(bp2);

        assertEquals(4, d.one());
        assertEquals(4, d.five());
        assertEquals(4, d.ten());
        assertEquals(4, d.twenty());
        assertEquals(4, d.fifty());
        assertEquals(4, d.hundred());
    }

    @Test
    public void centValueFromCoins() {
        //CoinPack cp = new CoinPack(1,2,3,4);

        Drawer d = new Drawer();

        // Test - check cp amount
        //          cp = 141 total

        assertEquals(141, d.centValueFromCoins(1,2,3,4));
    }

    @Test
    public void centValueFromCoins_viaCoinPack() {
        CoinPack cp = new CoinPack(1,2,3,4);

        Drawer d = new Drawer();

        // Test - check cp amount
        //          cp = 141 total

        assertEquals(141, d.centValueFromCoins(cp));
    }

    @Test
    public void centValueFromBills() {
        // BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer();

        // Test - check dp amount
        //          bp = 171500 total

        assertEquals(171500, d.centValueFromBills(5,6,7,8,9,10));
    }

    @Test
    public void centValueFromBills_viaBillPack() {
        BillPack bp = new BillPack(5,6,7,8,9,10);

        Drawer d = new Drawer();

        // Test - check dp amount
        //          bp = 171500 total

        assertEquals(171500, d.centValueFromBills(bp));
    }
}