import org.junit.Test;
import static org.junit.Assert.*;

public class PennsylvaniaTaxTest {

    @Test
    public void calculateTax() {
        PennsylvaniaTax paTax = new PennsylvaniaTax();

        // Test 1
        long purchasePrice = 500;
        // Tax calculation... 500 * 6 / 100 = 30
        assertEquals(30, paTax.calculateTax(purchasePrice));

        // Test 2
        purchasePrice = 754;
        // Tax calculation... 754 * 6 / 100 = 45.25.. (45 since long)
        assertEquals(45, paTax.calculateTax(purchasePrice));
    }

    @Test
    public void applyTaxToPurchase() {
        PennsylvaniaTax paTax = new PennsylvaniaTax();

        // Test 1
        long purchasePrice = 500;
        // Apply tax to purchase... 30 + 500 = 530
        assertEquals(530, paTax.applyTaxToPurchase(purchasePrice));

        // Test 2
        purchasePrice = 754;
        // Tax calculation... 45 + 754 = 799
        assertEquals(799, paTax.applyTaxToPurchase(purchasePrice));

    }
}

