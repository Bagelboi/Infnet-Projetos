import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatePriceTest {

    public enum CUSTOMER_TYPE {
        PREMIUM_1,
        PREMIUM_2
    }

    public double calculatePrice(double basePrice, CUSTOMER_TYPE customerType, boolean isHoliday) {
        if (basePrice < 0)
            throw new IllegalArgumentException("Preço negativo!");

        double discount = 0;

        if (customerType == CUSTOMER_TYPE.PREMIUM_1)
            discount = 0.1;
        else if (customerType == CUSTOMER_TYPE.PREMIUM_2)
            discount = 0.15;

        if (isHoliday)
            discount += 0.05;

        double toDiscountOff = basePrice * discount;

        return Math.max(0.01, basePrice - toDiscountOff);
    }

    @Test
    public void test() {
        assertEquals( calculatePrice(100, CUSTOMER_TYPE.PREMIUM_1, false), 90, 1 );
        assertEquals( calculatePrice(100, CUSTOMER_TYPE.PREMIUM_2, false), 85, 1 );
    }

    @Test
    public void holidayTest() {
        assertEquals( calculatePrice(100, CUSTOMER_TYPE.PREMIUM_1, true), 85, 1);
        assertEquals( calculatePrice(100, CUSTOMER_TYPE.PREMIUM_2, true), 80, 1 );
    }


}
