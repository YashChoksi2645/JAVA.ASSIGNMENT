import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Course {
    private String courseID;
    private String courseName;
    private int credits;

    public Course(String courseID, String courseName, int credits) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.credits = credits;
    }
    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
    @Override
    public String toString() {
        return "Course ID: " + courseID + ", Course Name: " + courseName + ", Credits: " + credits;
    }
}
public class Enrollment {
    private Map<String, Set<String>> studentEnrollments; 

    public Enrollment() {
        studentEnrollments = new HashMap<>();
    }
    public void enrollStudent(String studentID, String courseID) {
        studentEnrollments.computeIfAbsent(studentID, k -> new HashSet<>()).add(courseID);
    }
    public void dropCourse(String studentID, String courseID) {
        Set<String> courses = studentEnrollments.get(studentID);
        if (courses != null) {
            courses.remove(courseID);
            if (courses.isEmpty()) {
                studentEnrollments.remove(studentID);
            }
        }
    }
    public Set<String> viewCourses(String studentID) {
        return studentEnrollments.getOrDefault(studentID, new HashSet<>());
    }
}
public class EnrollmentSystem {
    private Map<String, Course> courses;
    private Enrollment enrollment;
    public EnrollmentSystem() {
        courses = new HashMap<>();
        enrollment = new Enrollment();
    }

    public void addCourse(Course course) {
        courses.put(course.getCourseID(), course);
    }

    public Course getCourse(String courseID) {
        return courses.get(courseID);
    }

    public void enrollStudent(String studentID, String courseID) {
        if (courses.containsKey(courseID)) {
            enrollment.enrollStudent(studentID, courseID);
        } else {
            System.out.println("Course not found.");
        }
    }

    public void dropCourse(String studentID, String courseID) {
        enrollment.dropCourse(studentID, courseID);
    }

    public void viewStudentCourses(String studentID) {
        Set<String> courseIDs = enrollment.viewCourses(studentID);
        if (courseIDs.isEmpty()) {
            System.out.println("No courses enrolled.");
        } else {
            for (String courseID : courseIDs) {
                Course course = courses.get(courseID);
                System.out.println(course);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        EnrollmentSystem system = new EnrollmentSystem();
        Scanner scanner = new Scanner(System.in);

        system.addCourse(new Course("CS101", "Introduction to Computer Science", 4));
        system.addCourse(new Course("MATH101", "Calculus I", 3));
        system.addCourse(new Course("PHYS101", "Physics I", 4));

        while (true) {
            System.out.println("Enrollment System");
            System.out.println("1. Enroll Student in Course");
            System.out.println("2. Drop Course for Student");
            System.out.println("3. View All Courses of Student");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String studentID = scanner.nextLine();
                    System.out.print("Enter Course ID: ");
                    String courseID = scanner.nextLine();
                    system.enrollStudent(studentID, courseID);
                    System.out.println("Enrollment successful.");
                    break;

                case 2:
                    System.out.print("Enter Student ID: ");
                    String dropStudentID = scanner.nextLine();
                    System.out.print("Enter Course ID: ");
                    String dropCourseID = scanner.nextLine();
                    system.dropCourse(dropStudentID, dropCourseID);
                    System.out.println("Course dropped.");
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    String viewStudentID = scanner.nextLine();
                    System.out.println("Courses for student " + viewStudentID + ":");
                    system.viewStudentCourses(viewStudentID);
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


