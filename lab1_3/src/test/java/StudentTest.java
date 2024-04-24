

import java.io.IOException;
import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;


public class StudentTest {
    private LinkedList<Student> students;

    @BeforeEach
    public void setUp() {
        students = new LinkedList<>();
        students.add(new Student("Anton", 1));
        students.add(new Student("Pizza", 2));
        students.add(new Student("Sergey", 3));
        students.add(new Student("Nikita", 3));
    }
    @AfterEach
    public void tearDown() {
        students = null;
    }

    @Test
    public void testCompareToPositive() {
        assertTrue(students.get(0).compareTo(students.get(1)) < 0);
    }

    @Test
    public void testPrintStudents() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Student.printStudents(students, 4);
        String expectedOutput = "Students of 4 course:\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testUnionPositive() {
        LinkedList<Student> set1 = new LinkedList<>();
        set1.add(students.get(0));
        LinkedList<Student> set2 = new LinkedList<>();
        set2.add(students.get(1));
        LinkedList<Student> result = Student.union(set1, set2);
        assertTrue(result.containsAll(set1));
        assertTrue(result.containsAll(set2));
    }

    @Test
    public void testIntersectPositive() {

        LinkedList<Student> set1 = new LinkedList<>();
        set1.add(students.get(0));
        set1.add(students.get(1));

        LinkedList<Student> set2 = new LinkedList<>();
        set2.add(students.get(0));
        set2.add(students.get(2));

        Set<Student> expected = new HashSet<>();
        expected.add(students.get(0));

        Set<Student> intersectResult = new HashSet<>(Student.intersect(set1, set2));
        assertEquals(expected, intersectResult);
    }
    @ParameterizedTest
    @MethodSource("provideNullInputData")
    public void testUnionNullInput(LinkedList<Student> set1, LinkedList<Student> set2) {
        assertThrows(NullPointerException.class, () -> Student.union(set1, set2));
    }

    @ParameterizedTest
    @MethodSource("provideNullInputData")
    public void testIntersectNullInput(LinkedList<Student> set1, LinkedList<Student> set2) {
        assertThrows(NullPointerException.class, () -> Student.intersect(set1, set2));
    }

    private static Stream<Arguments> provideNullInputData() {
        return Stream.of(
                Arguments.of(null, new LinkedList<>(List.of(new Student("Null", 1)))),
                Arguments.of(new LinkedList<>(List.of(new Student("Null", 1))), null)
        );
    }

}

