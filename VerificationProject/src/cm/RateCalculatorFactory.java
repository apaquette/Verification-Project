package cm;

public class RateCalculatorFactory {
    public static SimpleRateCalculator CreateRateCalculator(CarParkKind kind){
        return switch (kind) {
            case CarParkKind.VISITOR -> new VisitorRateCalculator();
            case CarParkKind.STUDENT -> new StudentRateCalculator();
            case CarParkKind.STAFF -> new StaffRateCalculator();
            case CarParkKind.MANAGEMENT -> new ManagementRateCalculator();
            default -> throw new IllegalArgumentException("Unsupported CarParkKind: " + kind);
        };
    }
}
