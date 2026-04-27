import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPS = 1e-3;

    @Test
    void testConvertToBaseUnit() {
        assertEquals(1.0,
                QuantityMeasurementApp.LengthUnit.INCHES.convertToBaseUnit(12.0),
                EPS);
    }

    @Test
    void testConvertFromBaseUnit() {
        assertEquals(12.0,
                QuantityMeasurementApp.LengthUnit.INCHES.convertFromBaseUnit(1.0),
                EPS);
    }

    @Test
    void testEquality() {
        var f = new QuantityMeasurementApp.Quantity(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var i = new QuantityMeasurementApp.Quantity(12.0,
                QuantityMeasurementApp.LengthUnit.INCHES);

        assertTrue(f.equals(i));
    }

    @Test
    void testConvertTo() {
        var f = new QuantityMeasurementApp.Quantity(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var result = f.convertTo(QuantityMeasurementApp.LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), EPS);
    }

    @Test
    void testAddition() {
        var f = new QuantityMeasurementApp.Quantity(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var i = new QuantityMeasurementApp.Quantity(12.0,
                QuantityMeasurementApp.LengthUnit.INCHES);

        var result = QuantityMeasurementApp.Quantity.add(
                f, i, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPS);
    }

    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityMeasurementApp.Quantity(1.0, null));
    }

    @Test
    void testInvalidValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityMeasurementApp.Quantity(Double.NaN,
                        QuantityMeasurementApp.LengthUnit.FEET));
    }
}