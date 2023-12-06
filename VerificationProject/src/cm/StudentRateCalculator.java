package cm;

import java.math.BigDecimal;

public class StudentRateCalculator extends SimpleRateCalculator{
    @Override
    public BigDecimal calculate(Period periodStay, int normalRateHours, int reducedRateHours, BigDecimal normalRate, BigDecimal reducedRate) {
        BigDecimal total = super.calculate(periodStay, normalRateHours, reducedRateHours, normalRate, reducedRate);
        if(total.compareTo(BigDecimal.valueOf(5.5)) > 0){
            return total.subtract(BigDecimal.valueOf(5.5)).multiply(BigDecimal.valueOf(0.66)).add(BigDecimal.valueOf(5.5)).stripTrailingZeros();
        }
        return total;
    }
}
