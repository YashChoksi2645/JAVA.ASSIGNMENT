
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
public class StudentRecordSystem {
    private Map<String, Student> studentRecords;

    public StudentRecordSystem() {
        studentRecords = new HashMap<>();
    }

    public void addStudent(Student student) {
        studentRecords.put(student.getStudentID(), student);
    }

    public Student viewStudent(String studentID) {
        return studentRecords.get(studentID);
    }

    public void displayAllStudents() {
        if (studentRecords.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        for (Student student : studentRecords.values()) {
            System.out.println(student);
        }
    }
}
public class Main {
    public static void main(String[] args) {
        StudentRecordSystem system = new StudentRecordSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Student Record System");
            System.out.println("1. Add New Student");
            System.out.println("2. View Student by ID");
            System.out.println("3. Display All Students");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();  
                    System.out.print("Enter Department: ");
                    String department = scanner.nextLine();

                    Student newStudent = new Student(id, name, age, department);
                    system.addStudent(newStudent);
                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    System.out.print("Enter Student ID: ");
                    String searchID = scanner.nextLine();
                    Student student = system.viewStudent(searchID);
                    if (student != null) {
                        System.out.println(student);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 3:
                    system.displayAllStudents();
                    break;

                case 4:
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

