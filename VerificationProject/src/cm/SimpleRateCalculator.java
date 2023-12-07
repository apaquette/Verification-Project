package cm;

import java.math.BigDecimal;

public abstract class SimpleRateCalculator implements IRateCalculator{


    @Override
    public BigDecimal calculate(Period periodStay, int normalRateHours, int reducedRateHours, BigDecimal normalRate, BigDecimal reducedRate) {
        return (normalRate.multiply(BigDecimal.valueOf(normalRateHours)))
                .add(reducedRate.multiply(BigDecimal.valueOf(reducedRateHours)))
                .stripTrailingZeros();
    }
}
