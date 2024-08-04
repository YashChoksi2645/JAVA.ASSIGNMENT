import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Student {
    private String studentID;
    private String name;
    private int age;
    private String department;

    public Student(String studentID, String name, int age, String department) {
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.department = department;
    }
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    @Override
    public String toString() {
        return "Student ID: " + studentID + ", Name: " + name + ", Age: " + age + ", Department: " + department;
    }
}
public class Grade {
    private String studentID;
    private String courseID;
    private double grade;

    public Grade(String studentID, String courseID, double grade) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.grade = grade;
    }
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
public class GradingSystem {
    private Map<String, Student> students;
    private Map<String, Grade> grades;

    public GradingSystem() {
        students = new HashMap<>();
        grades = new HashMap<>();
    }
    public void addStudent(Student student) {
        students.put(student.getStudentID(), student);
    }
    public void addGrade(Grade grade) {
        grades.put(grade.getStudentID() + "-" + grade.getCourseID(), grade);
    }
    public double calculateGPA(String studentID) {
        double totalGrades = 0;
        int count = 0;

        for (Grade grade : grades.values()) {
            if (grade.getStudentID().equals(studentID)) {
                totalGrades += grade.getGrade();
                count++;
            }
        }

        return count > 0 ? totalGrades / count : 0;
    }
    public void generateGradeReport(String studentID) {
        Student student = students.get(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Grade Report for " + student);
        System.out.println("Course ID | Grade");
        System.out.println("--------------------");

        for (Grade grade : grades.values()) {
            if (grade.getStudentID().equals(studentID)) {
                System.out.println(grade.getCourseID() + " | " + grade.getGrade());
            }
        }

        System.out.println("GPA: " + calculateGPA(studentID));
    }
}
public class Main {
    public static void main(String[] args) {
        GradingSystem system = new GradingSystem();
        Scanner scanner = new Scanner(System.in);

        system.addStudent(new Student("S001", "Alice Smith", 20, "Computer Science"));
        system.addStudent(new Student("S002", "Bob Johnson", 21, "Mathematics"));

        while (true) {
            System.out.println("Grading and Result Declaration System");
            System.out.println("1. Add Grade");
            System.out.println("2. Generate Grade Report");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String studentID = scanner.nextLine();
                    System.out.print("Enter Course ID: ");
                    String courseID = scanner.nextLine();
                    System.out.print("Enter Grade: ");
                    double grade = scanner.nextDouble();
                    scanner.nextLine();

                    system.addGrade(new Grade(studentID, courseID, grade));
                    System.out.println("Grade added successfully.");
                    break;

                case 2:
                    System.out.print("Enter Student ID: ");
                    String reportStudentID = scanner.nextLine();
                    system.generateGradeReport(reportStudentID);
                    break;

                case 3:
                    System.out.println("Exiting the system...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}


