//Уровень 3
//Напишите класс Student, предоставляющий информацию об имени студента
//методом getName() и о его курсе методом getCourse(). Напишите метод
//printStudents(LinkedList students, int course), который получает список
//студентов и номер курса и печатает имена тех студентов из списка, которые
//обучаются на данном курсе. Протестируйте метод (для этого предварительно
//придется создать десяток объектов класса Student и поместить их в список).
//Напишите методы union(LinkedList set1, LinkedList set2) и intersect(LinkedListset1, LinkedList set2), реализующие операции объединения и пересечения двух
//множеств. Протестируйте работу этих методов на двух предварительно
//заполненных множествах (понадобится написать вспомогательный метод,
//выводящий все элементы множества на консоль.) Реализуйте интерфейс
//Comparable так, чтобы студенты сортировались по номеру курса. Проверьте
//работу, используя класс TreeSet.
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Comparable<Student> {
    private String name;
    private int course;

    public static void printStudents(LinkedList<Student> students, int course) {
        System.out.print("Students of " + course + " course:\n");
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println(student.getName());
            }
        }
    }
    public static LinkedList<Student> union(LinkedList set1, LinkedList set2){
        if (set1 == null || set2 == null) {
            throw new NullPointerException("Input sets cannot be null");
        }
        LinkedList<Student> result = new LinkedList<>(set1);
        result.addAll(set2);
        return result;
    }

    public static LinkedList<Student> intersect(LinkedList set1, LinkedList set2){
        if (set1 == null || set2 == null) {
            throw new NullPointerException("Input sets cannot be null");
        }
        LinkedList<Student> result = new LinkedList<>(set1);
        result.retainAll(set2);
        return result;
    }


    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.course, other.course);
    }
}

