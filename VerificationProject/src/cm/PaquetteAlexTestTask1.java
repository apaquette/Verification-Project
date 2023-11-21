package cm;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaquetteAlexTestTask1 {
    /*PERIOD TEST START*/
    @Test
    @DisplayName("Period instantiation")
    void Period(){
        assertInstanceOf(Period.class, new Period(5, 10));
        assertInstanceOf(Period.class, new Period(1, 12));
        assertInstanceOf(Period.class, new Period(5, 6));
        assertInstanceOf(Period.class, new Period(1, 20));
        assertInstanceOf(Period.class, new Period(0, 20));

        assertThrows(IllegalArgumentException.class,() -> new Period(-1, 20));
        assertThrows(IllegalArgumentException.class,() -> new Period(25, 24));
        assertThrows(IllegalArgumentException.class,() -> new Period(1, -1));
        assertThrows(IllegalArgumentException.class,() -> new Period(1, 25));
        assertThrows(IllegalArgumentException.class,() -> new Period(5, 25));
        assertThrows(IllegalArgumentException.class,() -> new Period(5, 2));
        assertThrows(IllegalArgumentException.class,() -> new Period(10, 5));
    }

    @Test
    @DisplayName("Period overlap")
    void overlaps() {
        assertTrue(new Period(1, 10).overlaps(new Period(5, 12)));
        assertFalse(new Period(1, 10).overlaps(new Period(11, 12)));
        assertThrows(IllegalArgumentException.class, () -> new Period(1, 10).overlaps(new Period(-1, 12)));
        assertTrue(new Period(1, 5).overlaps(new Period(2, 6)));
        assertFalse(new Period(0, 5).overlaps(new Period(10, 20)));
    }

    @Test
    @DisplayName("Period duration")
    void duration() {
        assertEquals(1, new Period(1, 2).duration());
        assertEquals(1, new Period(23, 24).duration());
        assertEquals(14, new Period(10, 24).duration());
        assertEquals(10, new Period(5, 15).duration());

        assertEquals(1, new Period(23, 24).duration());
        assertEquals(24, new Period(0, 24).duration());
        assertEquals(1, new Period(0, 1).duration());
    }
    /*PERIOD TEST END*/

    /*RATE TEST START*/
    @Test
    @DisplayName("Rate instantiation")
    void Rate(){
        assertInstanceOf(Rate.class, new Rate(CarParkKind.STUDENT, new BigDecimal("2.0"), new BigDecimal("1.0"), new ArrayList<Period>(Arrays.asList(new Period(0,5), new Period(6,10))), new ArrayList<Period>(Arrays.asList(new Period(11,15), new Period(16,20)))));
        assertInstanceOf(Rate.class, new Rate(CarParkKind.STUDENT, new BigDecimal("5.0"), new BigDecimal("3.0"), new ArrayList<Period>(Arrays.asList(new Period(0,5), new Period(6,10))), new ArrayList<Period>(Arrays.asList(new Period(11,15), new Period(16,20)))));
        assertInstanceOf(Rate.class, new Rate(CarParkKind.STUDENT, new BigDecimal("10.0"), new BigDecimal("1.0"), new ArrayList<Period>(Arrays.asList(new Period(0,5), new Period(6,10))), new ArrayList<Period>(Arrays.asList(new Period(11,15), new Period(16,20)))));
        assertInstanceOf(Rate.class, new Rate(CarParkKind.STUDENT, new BigDecimal("1.0"), new BigDecimal("1.0"), new ArrayList<Period>(Arrays.asList(new Period(0,5), new Period(7,10))), new ArrayList<Period>(Arrays.asList(new Period(11,15), new Period(16,20)))));
        assertInstanceOf(Rate.class, new Rate(CarParkKind.STUDENT, new BigDecimal("1.0"), new BigDecimal("1.0"), new ArrayList<Period>(Arrays.asList(new Period(0,5), new Period(6,10))), new ArrayList<Period>(Arrays.asList(new Period(12,15), new Period(17,20)))));
        assertInstanceOf(Rate.class, new Rate(CarParkKind.STUDENT, new BigDecimal("1.0"), new BigDecimal("1.0"), new ArrayList<Period>(List.of(new Period(0, 10))), new ArrayList<Period>(Arrays.asList(new Period(11,15), new Period(16,20)))));
        assertInstanceOf(Rate.class, new Rate(CarParkKind.STUDENT, new BigDecimal("1.0"), new BigDecimal("1.0"), new ArrayList<Period>(Arrays.asList(new Period(0,5), new Period(6,10))), new ArrayList<Period>(List.of(new Period(11, 24)))));

        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STUDENT, new BigDecimal("0.0"), new BigDecimal("1.0"), new ArrayList<Period>(Arrays.asList(new Period(0,5), new Period(6,10))), new ArrayList<Period>(Arrays.asList(new Period(11,15), new Period(16,20)))));
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STUDENT, new BigDecimal("1.0"), new BigDecimal("0.0"), new ArrayList<Period>(Arrays.asList(new Period(0,5), new Period(6,10))), new ArrayList<Period>(Arrays.asList(new Period(11,15), new Period(16,20)))));
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STUDENT, new BigDecimal("1.0"), new BigDecimal("10.0"), new ArrayList<Period>(Arrays.asList(new Period(0,5), new Period(6,10))), new ArrayList<Period>(Arrays.asList(new Period(11,15), new Period(16,20)))));
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STUDENT, new BigDecimal("1.0"), new BigDecimal("1.0"), new ArrayList<Period>(Arrays.asList(new Period(0,10), new Period(5,10))), new ArrayList<Period>(Arrays.asList(new Period(11,15), new Period(16,20)))));
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STUDENT, new BigDecimal("1.0"), new BigDecimal("1.0"), new ArrayList<Period>(Arrays.asList(new Period(0,5), new Period(6,10))), new ArrayList<Period>(Arrays.asList(new Period(11,20), new Period(16,20)))));
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STUDENT, new BigDecimal("1.0"), new BigDecimal("1.0"), new ArrayList<Period>(Arrays.asList(new Period(0,5), new Period(6,10))), new ArrayList<Period>(Arrays.asList(new Period(11,15), new Period(16,20)))));
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STUDENT, new BigDecimal("1.0"), new BigDecimal("1.0"), new ArrayList<Period>(Arrays.asList(new Period(0,5), new Period(6,10))), new ArrayList<Period>(Arrays.asList(new Period(6,15), new Period(16,20)))));
    }

    @Test
    @DisplayName("Rate calculate")
    void calculate(){
        Rate testRate = new Rate(CarParkKind.STUDENT, new BigDecimal(1), new BigDecimal("0.5"), new ArrayList<Period>(Arrays.asList(new Period(8, 10), new Period(12,14))), new ArrayList<Period>(Arrays.asList(new Period(15, 18), new Period(20,22))));

        assertEquals(new BigDecimal("0.0"), testRate.calculate(new Period(0, 7)));
        assertEquals(new BigDecimal("2.0"), testRate.calculate(new Period(8, 10)));
        assertEquals(new BigDecimal("1.5"), testRate.calculate(new Period(15, 18)));
        assertEquals(new BigDecimal("2.0"), testRate.calculate(new Period(6, 10)));
        assertEquals(new BigDecimal("1.0"), testRate.calculate(new Period(20, 24)));
        assertEquals(new BigDecimal("2.5"), testRate.calculate(new Period(12, 16)));
        assertEquals(new BigDecimal("4.5"), testRate.calculate(new Period(12, 22)));
        assertEquals(new BigDecimal("6.5"), testRate.calculate(new Period(0, 24)));
        assertEquals(new BigDecimal("0.0"), testRate.calculate(new Period(23, 24)));
        assertEquals(new BigDecimal("0.0"), testRate.calculate(new Period(0, 1)));

        assertThrows(IllegalArgumentException.class, () -> testRate.calculate(new Period(-1, -1)));

        assertEquals(new BigDecimal("2.0"), testRate.calculate(new Period(8, 10)));
        assertEquals(new BigDecimal("1.0"), testRate.calculate(new Period(20, 22)));
    }
    /*RATE TEST END*/
}