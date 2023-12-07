package cm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Rate {
    private CarParkKind kind;
    private IRateCalculator rateCalculator;
    private BigDecimal hourlyNormalRate;
    private BigDecimal hourlyReducedRate;
    private ArrayList<Period> reduced;
    private ArrayList<Period> normal;

    public Rate(CarParkKind kind, BigDecimal normalRate, BigDecimal reducedRate, ArrayList<Period> normalPeriods, ArrayList<Period> reducedPeriods) {
        if (reducedPeriods == null || normalPeriods == null) {
            throw new IllegalArgumentException("periods cannot be null");
        }
        if (normalRate == null || reducedRate == null) {
            throw new IllegalArgumentException("The rates cannot be null");
        }
        if (normalRate.compareTo(BigDecimal.ZERO) < 0 || reducedRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("A rate cannot be negative");
        }
        if (normalRate.compareTo(reducedRate) < 0) {
            throw new IllegalArgumentException("The normal rate cannot be less than the reduced rate");
        }
        if (!isValidPeriods(reducedPeriods) || !isValidPeriods(normalPeriods)) {
            throw new IllegalArgumentException("The periods are not valid individually");
        }
        if (!isValidPeriods(reducedPeriods, normalPeriods)) {
            throw new IllegalArgumentException("The periods overlaps");
        }
        this.kind = kind;
        this.rateCalculator = RateCalculatorFactory.CreateRateCalculator(kind);
        this.hourlyNormalRate = normalRate;
        this.hourlyReducedRate = reducedRate;
        this.reduced = reducedPeriods;
        this.normal = normalPeriods;
    }

    /**
     * Checks if two collections of periods are valid together
     * @param periods1
     * @param periods2
     * @return true if the two collections of periods are valid together
     */
    private boolean isValidPeriods(ArrayList<Period> periods1, ArrayList<Period> periods2) {
        Boolean isValid = true;
        int i = 0;
        while (i < periods1.size() && isValid) {
            isValid = isValidPeriod(periods1.get(i), periods2);
            i++;
        }
        return isValid;
    }

    /**
     * checks if a collection of periods is valid
     * @param list the collection of periods to check
     * @return true if the periods do not overlap
     */
    private Boolean isValidPeriods(ArrayList<Period> list) {
        Boolean isValid = true;
        if (list.size() >= 2) {
            Period secondPeriod;
            int i = 0;
            int lastIndex = list.size()-1;
            while (i < lastIndex && isValid) {
                isValid = isValidPeriod(list.get(i), ((List<Period>)list).subList(i + 1, lastIndex+1));
                i++;
            }
        }
        return isValid;
    }

    /**
     * checks if a period is a valid addition to a collection of periods
     * @param period the Period addition
     * @param list the collection of periods to check
     * @return true if the period does not overlap in the collecton of periods
     */
    private Boolean isValidPeriod(Period period, List<Period> list) {
        Boolean isValid = true;
        int i = 0;
        while (i < list.size() && isValid) {
            isValid = !period.overlaps(list.get(i));
            i++;
        }
        return isValid;
    }
    public BigDecimal calculate(Period periodStay) {
        int normalRateHours = periodStay.occurences(normal);
        int reducedRateHours = periodStay.occurences(reduced);

        //return rateCalculator.calculate(periodStay, normalRateHours, reducedRateHours, this.hourlyNormalRate, this.hourlyNormalRate).stripTrailingZeros();

        //normal calculation
        BigDecimal total = (this.hourlyNormalRate.multiply(BigDecimal.valueOf(normalRateHours)))
                            .add(this.hourlyReducedRate.multiply(BigDecimal.valueOf(reducedRateHours)));

        switch(this.kind){
            case CarParkKind.VISITOR:
                return rateCalculator.calculate(periodStay, normalRateHours, reducedRateHours, this.hourlyNormalRate, this.hourlyReducedRate);
                /*if(total.compareTo(BigDecimal.valueOf(10)) <= 0){
                    return BigDecimal.ZERO;
                }
                return total.subtract(BigDecimal.valueOf(10)).multiply(BigDecimal.valueOf(0.5)).stripTrailingZeros();*/
            case CarParkKind.STUDENT:
                return rateCalculator.calculate(periodStay, normalRateHours, reducedRateHours, this.hourlyNormalRate, this.hourlyReducedRate);
                /*if(total.compareTo(BigDecimal.valueOf(5.5)) > 0){
                    return total.subtract(BigDecimal.valueOf(5.5)).multiply(BigDecimal.valueOf(0.66)).add(BigDecimal.valueOf(5.5)).stripTrailingZeros();
                }
                break;*/
            case CarParkKind.STAFF:
                return rateCalculator.calculate(periodStay, normalRateHours, reducedRateHours, this.hourlyNormalRate, this.hourlyReducedRate);
                /*if(total.compareTo(BigDecimal.valueOf(10)) > 0){
                    return BigDecimal.valueOf(10);
                }
                break;*/
            case CarParkKind.MANAGEMENT:
                return rateCalculator.calculate(periodStay, normalRateHours, reducedRateHours, this.hourlyNormalRate, this.hourlyReducedRate);
                /*if(total.compareTo(BigDecimal.valueOf(5)) <= 0){
                    return BigDecimal.valueOf(5);
                }
                break;*/
        }
        return total.stripTrailingZeros();
    }

}
