import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPS = 1e-3;

    @Test
    void testAddition_TargetUnit_Feet() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        var result = QuantityMeasurementApp.Quantity.add(q1, q2,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_TargetUnit_Inches() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        var result = QuantityMeasurementApp.Quantity.add(q1, q2,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(24.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_TargetUnit_Yards() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        var result = QuantityMeasurementApp.Quantity.add(q1, q2,
                QuantityMeasurementApp.LengthUnit.YARD);

        assertEquals(0.667, result.getValue(), 0.01);
    }

    @Test
    void testAddition_TargetUnit_Centimeters() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.INCH);
        var q2 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.INCH);

        var result = QuantityMeasurementApp.Quantity.add(q1, q2,
                QuantityMeasurementApp.LengthUnit.CM);

        assertEquals(5.08, result.getValue(), 0.01);
    }

    @Test
    void testAddition_Commutativity() {
        var a = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        var r1 = QuantityMeasurementApp.Quantity.add(a, b, QuantityMeasurementApp.LengthUnit.YARD);
        var r2 = QuantityMeasurementApp.Quantity.add(b, a, QuantityMeasurementApp.LengthUnit.YARD);

        assertEquals(r1.getValue(), r2.getValue(), EPS);
    }

    @Test
    void testAddition_NullTargetUnit() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.Quantity.add(q1, q2, null));
    }

    @Test
    void testAddition_WithZero() {
        var q1 = new QuantityMeasurementApp.Quantity(5.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(0.0, QuantityMeasurementApp.LengthUnit.INCH);

        var result = QuantityMeasurementApp.Quantity.add(q1, q2,
                QuantityMeasurementApp.LengthUnit.YARD);

        assertEquals(1.667, result.getValue(), 0.01);
    }

    @Test
    void testAddition_NegativeValues() {
        var q1 = new QuantityMeasurementApp.Quantity(5.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(-2.0, QuantityMeasurementApp.LengthUnit.FEET);

        var result = QuantityMeasurementApp.Quantity.add(q1, q2,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(36.0, result.getValue(), EPS);
    }
}