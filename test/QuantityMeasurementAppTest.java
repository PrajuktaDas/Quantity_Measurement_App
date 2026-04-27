// ===================== TEST FILE =====================

public class QuantityMeasurementTest {

    public static void main(String[] args) {

        // ---------- LENGTH TESTS ----------
        QuantityLength l1 = new QuantityLength(1, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12, LengthUnit.INCHES);

        assert l1.equals(l2);
        assert l1.add(l2, LengthUnit.FEET).equals(new QuantityLength(2, LengthUnit.FEET));

        // ---------- WEIGHT TESTS ----------
        QuantityWeight w1 = new QuantityWeight(1, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000, WeightUnit.GRAM);

        assert w1.equals(w2);
        assert w1.add(w2, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(2, WeightUnit.KILOGRAM));

        // Conversion tests
        QuantityWeight pound = new QuantityWeight(2.20462, WeightUnit.POUND);
        assert pound.convertTo(WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(1, WeightUnit.KILOGRAM));

        // Negative + zero
        QuantityWeight w3 = new QuantityWeight(5, WeightUnit.KILOGRAM);
        QuantityWeight w4 = new QuantityWeight(-2, WeightUnit.KILOGRAM);

        assert w3.add(w4, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(3, WeightUnit.KILOGRAM));

        System.out.println("ALL TESTS PASSED ✅");
    }
}