package cm;

import java.math.BigDecimal;

public interface IRateCalculator {
    BigDecimal calculate(Period periodStay, int normalRateHours, int reducedRateHours, BigDecimal normalRate, BigDecimal reducedRate);
}
