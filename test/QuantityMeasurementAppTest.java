import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(2.0, QuantityMeasurementApp.LengthUnit.FEET);

        var result = q1.add(q2);

        assertEquals(3.0, resultValue(result), EPS);
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        var result = q1.add(q2);

        assertEquals(2.0, resultValue(result), EPS);
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {
        var q1 = new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);
        var q2 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        var result = q1.add(q2);

        assertEquals(24.0, resultValue(result), EPS);
    }

    @Test
    void testAddition_YardPlusFeet() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        var q2 = new QuantityMeasurementApp.Quantity(3.0, QuantityMeasurementApp.LengthUnit.FEET);

        var result = q1.add(q2);

        assertEquals(2.0, resultValue(result), EPS);
    }

    @Test
    void testAddition_CMPlusInch() {
        var q1 = new QuantityMeasurementApp.Quantity(2.54, QuantityMeasurementApp.LengthUnit.CM);
        var q2 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.INCH);

        var result = q1.add(q2);

        assertEquals(5.08, resultValue(result), 1e-2);
    }

    @Test
    void testAddition_Commutativity() {
        var a = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        var r1 = a.add(b);
        var r2 = b.add(a);

        assertEquals(r1.toString(), r2.toString());
    }

    @Test
    void testAddition_WithZero() {
        var q1 = new QuantityMeasurementApp.Quantity(5.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(0.0, QuantityMeasurementApp.LengthUnit.INCH);

        var result = q1.add(q2);

        assertEquals(5.0, resultValue(result), EPS);
    }

    @Test
    void testAddition_NegativeValues() {
        var q1 = new QuantityMeasurementApp.Quantity(5.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(-2.0, QuantityMeasurementApp.LengthUnit.FEET);

        var result = q1.add(q2);

        assertEquals(3.0, resultValue(result), EPS);
    }

    @Test
    void testAddition_Null() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> q1.add(null));
    }

    // helper to extract value (since field is private)
    private double resultValue(QuantityMeasurementApp.Quantity q) {
        return Double.parseDouble(q.toString().split(" ")[0]);
    }
}