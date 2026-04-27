// ===================== MAIN FILE =====================

enum LengthUnit {
    FEET(1.0),
    INCHES(1.0 / 12),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double toBase(double value) {
        return value * factor;
    }

    public double fromBase(double baseValue) {
        return baseValue / factor;
    }
}

// ---------------- LENGTH CLASS ----------------
final class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null || !Double.isFinite(value))
            throw new IllegalArgumentException("Invalid input");
        this.value = value;
        this.unit = unit;
    }

    public QuantityLength convertTo(LengthUnit target) {
        double base = unit.toBase(value);
        return new QuantityLength(target.fromBase(base), target);
    }

    public QuantityLength add(QuantityLength other, LengthUnit target) {
        if (other == null || target == null)
            throw new IllegalArgumentException("Invalid input");

        double sumBase = this.unit.toBase(this.value)
                + other.unit.toBase(other.value);

        return new QuantityLength(target.fromBase(sumBase), target);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;
        double a = this.unit.toBase(this.value);
        double b = other.unit.toBase(other.value);

        return Math.abs(a - b) < 1e-6;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}


// ===================== WEIGHT =====================

enum WeightUnit {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    public double toBase(double value) {
        return value * factor;
    }

    public double fromBase(double baseValue) {
        return baseValue / factor;
    }
}

// ---------------- WEIGHT CLASS ----------------
final class QuantityWeight {
    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
        if (unit == null || !Double.isFinite(value))
            throw new IllegalArgumentException("Invalid input");
        this.value = value;
        this.unit = unit;
    }

    public QuantityWeight convertTo(WeightUnit target) {
        double base = unit.toBase(value);
        return new QuantityWeight(target.fromBase(base), target);
    }

    public QuantityWeight add(QuantityWeight other, WeightUnit target) {
        if (other == null || target == null)
            throw new IllegalArgumentException("Invalid input");

        double sumBase = this.unit.toBase(this.value)
                + other.unit.toBase(other.value);

        return new QuantityWeight(target.fromBase(sumBase), target);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityWeight)) return false;

        QuantityWeight other = (QuantityWeight) obj;
        double a = this.unit.toBase(this.value);
        double b = other.unit.toBase(other.value);

        return Math.abs(a - b) < 1e-6;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}


// ---------------- MAIN APP ----------------
public class QuantityMeasurementApp {
    public static void main(String[] args) {

        // LENGTH
        QuantityLength l1 = new QuantityLength(1, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12, LengthUnit.INCHES);

        System.out.println(l1.add(l2, LengthUnit.FEET));   // 2 FEET
        System.out.println(l1.equals(l2));                 // true

        // WEIGHT
        QuantityWeight w1 = new QuantityWeight(1, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000, WeightUnit.GRAM);

        System.out.println(w1.add(w2, WeightUnit.KILOGRAM)); // 2 KG
        System.out.println(w1.equals(w2));                   // true
    }
}