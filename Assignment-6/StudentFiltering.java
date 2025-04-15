import java.util.*;
import java.util.stream.Collectors;

class Student {
    private String name;
    private double marks;

    // Constructor
    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    // Getters
    public String getName() { return name; }
    public double getMarks() { return marks; }

    @Override
    public String toString() {
        return name + " - " + marks + "%";
    }
}

public class StudentFiltering {
    public static void main(String[] args) {
        // List of students
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 82));
        students.add(new Student("Bob", 70));
        students.add(new Student("Charlie", 85));
        students.add(new Student("David", 65));
        students.add(new Student("Eve", 90));

        // Use Stream API to filter, sort, and display names of students with marks > 75
        System.out.println("Students with marks above 75%, sorted by marks:");

        students.stream()
                // Filter students with marks greater than 75%
                .filter(student -> student.getMarks() > 75)
                // Sort students by marks in descending order
                .sorted((s1, s2) -> Double.compare(s2.getMarks(), s1.getMarks()))
                // Map to get just the names
                .map(Student::getName)
                // Collect and print the result
                .forEach(System.out::println);
    }
}
