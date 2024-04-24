import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FractionTest {
    private Fraction fraction1;
    private Fraction fraction2;

    @BeforeEach
    void SetUp() {
        fraction1 = new Fraction(6, 3);
        fraction2 = new Fraction(3, 4);
    }

    @AfterEach
    void tearDown() {
        fraction1 = null;
        fraction2 = null;
    }

    @Test
    public void testAddFractionsPositive() {
        assertEquals(new Fraction(11, 4), fraction1.add(fraction2));
    }

    @Test
    public void testSubtractFractionsPositive() {
        assertEquals(new Fraction(5, 4), fraction1.subtract(fraction2));
    }

    @Test
    public void testMultiplyFractionsPositive() {
        assertEquals(new Fraction(3, 2), fraction1.multiply(fraction2));
    }

    @Test
    public void testDivideFractionsPositive() throws DivByZeroException {
        assertEquals(new Fraction(8, 3), fraction1.divide(fraction2));
    }

    @ParameterizedTest
    @MethodSource("provideWrongFractions")
    public void testDivideByZeroException(Fraction genFraction1, Fraction getFraction2) {
        assertThrows(DivByZeroException.class, () -> genFraction1.divide(getFraction2));
    }

    @Test
    public void testReduceFractionPositive() {
        assertEquals(new Fraction(2, 1), fraction1.reduce());
    }

    @Test
    public void testModifyEvenIndexElementsPositive() {
        Fraction[] fractions = {new Fraction(1, 2), new Fraction(1, 3), new Fraction(1, 4), new Fraction(1, 5)};
        Fraction[] expected = {new Fraction(1, 2), new Fraction(1, 3), new Fraction(9, 20), new Fraction(1, 5)};
        Fraction.modifyIndexElements(fractions);
        assertArrayEquals(expected, fractions);
    }

    private static List<Arguments> provideWrongFractions() {
        return Arrays.asList(
                Arguments.of(new Fraction(1, 2), new Fraction(0, 5)),
                Arguments.of(new Fraction(0, 5), new Fraction(1, 2))
        );
    }
}
