//Определите класс "Дробь" в виде пары (m, n). Класс должен содержать
//несколько конструкторов. Реализуйте методы для сложения, вычитания,
//умножения, деления и сокращения дробей. Методы для сложения и
//умножения дробей сделайте с переменным числом параметров. Объявите
//массив из k дробей, введите/выведите значения для массива дробей. Создайте
//массив объектов и передайте его в метод, который будет изменять каждый
//элемент массива с четным индексом путем добавления, следующего за ним
//элемента массива.
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fraction {
    public static final String DIV_BY_ZERO_EXCEPTION = "Div By Zero";
    private int m;
    private int n;

    public Fraction(int wholeNumber) {
        this.m = wholeNumber;
        this.n = 1;
    }

    public Fraction add(Fraction... fractions)  {
        int resultm = this.m;
        int resultn = this.n;

        for (Fraction fraction : fractions) {
            resultm = resultm * fraction.n + fraction.m * resultn;
            resultn *= fraction.n;
        }

        return new Fraction(resultm, resultn).reduce();
    }

    public Fraction subtract(Fraction fraction) {
        int resultm = this.m * fraction.n - fraction.m * this.n;
        int resultn = this.n * fraction.n;
        return new Fraction(resultm, resultn).reduce();
    }

    public Fraction multiply(Fraction... fractions) {
        int resultm = this.m;
        int resultn= this.n;

        for (Fraction fraction : fractions) {
            resultm *= fraction.m;
            resultn *= fraction.n;
        }

        return new Fraction(resultm, resultn).reduce();
    }

    public Fraction divide(Fraction fraction) throws DivByZeroException {
        if (this.m ==0 || fraction.m ==0) throw new DivByZeroException(DIV_BY_ZERO_EXCEPTION);
        int resultm = this.m * fraction.n;
        int resultn = this.n * fraction.m;
        return new Fraction(resultm, resultn).reduce();
    }

    public Fraction reduce() {
        int gcd = gcd(this.m, this.n);
        return new Fraction(this.m / gcd, this.n / gcd);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void printFractionArray(Fraction[] fractions) {
        System.out.println(Arrays.toString(fractions));
    }

    public static void modifyIndexElements(Fraction[] fractions) {
        for (int i = 2; i < fractions.length; i += 2) {
            if (i + 1 < fractions.length) {
                fractions[i] = fractions[i].add(fractions[i + 1]);
            }
        }
    }

    @Override
    public String toString() {
        return m + "/" + n;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Fraction other = (Fraction) obj;
        return this.m * other.n == other.m * this.n;
    }

}
