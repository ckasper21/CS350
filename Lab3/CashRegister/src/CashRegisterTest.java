import org.junit.Test;

import static org.junit.Assert.*;

public class CashRegisterTest {

    @Test
    public void createCashRegister() {
        CashRegister c = new CashRegister();
        assertNotNull(c);
    }

    @Test
    public void createCashRegister_Drawer() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);
        Drawer d = new Drawer(cp,bp);

        CashRegister c = new CashRegister(d);
        assertNotNull(c);
    }

    @Test
    public void createCashRegister_Params() {
        CashRegister c = new CashRegister(1,2,3,4,5,6,7,8,9,10);
        assertNotNull(c);
    }

    @Test
    public void createCashRegister_ParamsWithTax() {
        PennsylvaniaTax paTax = new PennsylvaniaTax();

        CashRegister c = new CashRegister(1,2,3,4,5,6,7,8,9,10,paTax);
        assertNotNull(c);
    }

    @Test
    public void createCashRegister_BillPackCoinPack() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        CashRegister c = new CashRegister(bp,cp);
        assertNotNull(c);
    }

    @Test
    public void createCashRegister_BillPackCoinPackTax() {
        PennsylvaniaTax paTax = new PennsylvaniaTax();
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        CashRegister c = new CashRegister(bp,cp,paTax);
        assertNotNull(c);
    }

    @Test
    public void drawerValue() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        CashRegister c = new CashRegister(bp,cp);

        // Test - Check drawer value in dollars
        //          d = 171641 (in cents)
        //          cp = 141 total
        //          bp = 171500 total

        assertEquals(1716.41, c.drawerValue(),0);
    }

    @Test
    public void coinsInDrawer() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        CashRegister c = new CashRegister(bp,cp);

        // Test - get c's CoinPack, should be the same as cp

        assertSame(cp, c.coinsInDrawer());
    }

    @Test
    public void billsInDrawer() {
        CoinPack cp = new CoinPack(1,2,3,4);
        BillPack bp = new BillPack(5,6,7,8,9,10);

        CashRegister c = new CashRegister(bp,cp);

        // Test - get c's BillPack, should be the same as bp

        assertSame(bp, c.billsInDrawer());
    }

    @Test
    public void purchaseItem() {
        PennsylvaniaTax paTax = new PennsylvaniaTax();
        CoinPack cp = new CoinPack(3,2,3,4);
        BillPack bp = new BillPack(2,2,2,2,2,2);

        CashRegister c = new CashRegister(bp,cp,paTax);

        double itemPrice = 13.99;

        // Test - change should be $0.88
        //      Cash Register has $373.43
        //      Purchase = $14.82
        //          Item = $13.99
        //          Tax (6%) = $0.83
        //      Customer has $15.70

        assertEquals(0.88, c.purchaseItem(itemPrice,5,0,1,0,0,0,0,0,2,2),0.05);
    }

    @Test
    public void purchaseItem_BillPackCoinPack() {
        PennsylvaniaTax paTax = new PennsylvaniaTax();
        CoinPack cp = new CoinPack(3,2,3,4);
        BillPack bp = new BillPack(2,2,2,2,2,2);

        CashRegister c = new CashRegister(bp,cp,paTax);

        double itemPrice = 13.99;
        CoinPack custCP = new CoinPack(0,0,2,2);
        BillPack custBP = new BillPack(5,0,1,0,0,0);

        // Test 1 - change should be $0.88
        //      Cash Register has $373.43
        //      Purchase = $14.82
        //          Item = $13.99
        //          Tax (6%) = $0.83
        //      Customer has $15.70

       assertEquals(0.88, c.purchaseItem(itemPrice,custBP,custCP),0.05);


        cp = new CoinPack(9,9,9,9);
        bp = new BillPack(9,9,9,9,9,9);

        c = new CashRegister(bp,cp);

        itemPrice = 0.01;
        custCP = new CoinPack(5,1,1,1);
        custBP = new BillPack(1,1,1,1,1,1);

        // Test 2 - change should be $0.00
        //      Purchase = $0.01
        //          Item = $0.01
        //          Tax (6%) = $0.00
        //      Customer has $186.45

        assertEquals(186.44, c.purchaseItem(itemPrice,custBP,custCP),0.05);

        cp = new CoinPack(9,9,9,9);
        bp = new BillPack(9,9,9,9,9,9);

        c = new CashRegister(bp,cp);

        itemPrice = 13.99;
        custCP = new CoinPack(2,1,0,3);
        custBP = new BillPack(4,2,0,0,0,0);

        // Test 3 - customer has exact change
        //      Purchase = $14.82
        //          Item = $13.99
        //          Tax (6%) = $0.83
        //      Customer has $14.82

        assertEquals(0, c.purchaseItem(itemPrice,custBP,custCP),0.05);
    }

    @Test (expected = IllegalStateException.class)
    public void purchaseItem_CustomerNotEnough() {
        PennsylvaniaTax paTax = new PennsylvaniaTax();
        CoinPack cp = new CoinPack(3,2,3,4);
        BillPack bp = new BillPack(2,2,2,2,2,2);

        CashRegister c = new CashRegister(bp,cp,paTax);

        double itemPrice = 13.99;
        CoinPack custCP = new CoinPack(0,0,2,2);
        BillPack custBP = new BillPack(4,0,1,0,0,0);

        // Test - Customer doesn't have enough money
        //      Cash Register has $373.43
        //      Purchase = $14.82
        //          Item = $13.99
        //          Tax (6%) = $0.83
        //      Customer has $14.70 (needs $0.12)

        c.purchaseItem(itemPrice,custBP,custCP);
    }

    @Test (expected = IllegalStateException.class)
    public void purchaseItem_RegisterNotEnough() {
        PennsylvaniaTax paTax = new PennsylvaniaTax();
        CoinPack cp = new CoinPack(0,0,3,4);
        BillPack bp = new BillPack(2,2,2,2,2,2);

        CashRegister c = new CashRegister(bp,cp,paTax);

        double itemPrice = 13.93;
        CoinPack custCP = new CoinPack(0,0,1,2);
        BillPack custBP = new BillPack(5,0,1,0,0,0);

        // Test - Register doesn't have exact change
        //      Cash Register has $373.40
        //      Purchase = $14.82
        //          Item = $13.99
        //          Tax (6%) = $0.83
        //      Customer has $15.70
        //      Change = $0.88 (needs $0.13)

        c.purchaseItem(itemPrice,custBP,custCP);
    }

    @Test
    public void scanItem() {
        PennsylvaniaTax paTax = new PennsylvaniaTax();
        CoinPack cp = new CoinPack(0,2,3,4);
        BillPack bp = new BillPack(2,2,2,2,2,2);

        CashRegister c = new CashRegister(bp,cp,paTax);

        // Test - get total price from items
        assertEquals(2.30,c.scanItem(2.30,"CokeCola"),0.05);

        // Test - sum should price of CokeCola + Doritos
        assertEquals(7.80,c.scanItem(5.50,"Doritos"),0.05);
    }

    @Test
    public void finalizePurchase() {
        PennsylvaniaTax paTax = new PennsylvaniaTax();
        CoinPack cp = new CoinPack(3,2,3,4);
        BillPack bp = new BillPack(2,2,2,2,2,2);

        CashRegister c = new CashRegister(bp,cp,paTax);
        CoinPack custCP = new CoinPack(0,0,2,2);
        BillPack custBP = new BillPack(5,1,0,0,0,0);
        c.scanItem(2.30,"CokeCola");
        c.scanItem(5.50,"Doritos");

        // Test - get change
        assertEquals(2.45,c.finalizePurchase(custBP,custCP),0.05);
    }

    @Test (expected = IllegalStateException.class)
    public void finalizePurchase_CustomerNotEnough() {
        PennsylvaniaTax paTax = new PennsylvaniaTax();
        CoinPack cp = new CoinPack(3,2,3,4);
        BillPack bp = new BillPack(2,2,2,2,2,2);

        CashRegister c = new CashRegister(bp,cp,paTax);
        CoinPack custCP = new CoinPack(0,0,2,2);
        BillPack custBP = new BillPack(5,0,0,0,0,0);
        c.scanItem(2.30,"CokeCola");
        c.scanItem(5.50,"Doritos");

        // Test - try to get change when customer doesn't have enough
        assertEquals(2.45,c.finalizePurchase(custBP,custCP),0.05);
    }

}