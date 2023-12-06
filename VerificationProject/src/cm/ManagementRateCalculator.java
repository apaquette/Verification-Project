package cm;

import java.math.BigDecimal;

public class ManagementRateCalculator extends SimpleRateCalculator{
    @Override
    public BigDecimal calculate(Period periodStay, int normalRateHours, int reducedRateHours, BigDecimal normalRate, BigDecimal reducedRate) {
        BigDecimal total = super.calculate(periodStay, normalRateHours, reducedRateHours, normalRate, reducedRate);
        if(total.compareTo(BigDecimal.valueOf(5)) <= 0){
            return BigDecimal.valueOf(5);
        }
        return total;
    }
}
