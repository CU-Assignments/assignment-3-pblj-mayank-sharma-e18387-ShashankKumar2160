import java.util.HashMap;
import java.util.Scanner;

class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}

public class UniversityEnrollment {
    private static final int MAX_STUDENTS = 2;
    private static HashMap<String, Integer> courseEnrollment = new HashMap<>();
    private static HashMap<String, String> prerequisites = new HashMap<>();
    
    static {
        prerequisites.put("Advanced Java", "Core Java");
    }

    public static void enrollStudent(String course, String completedCourse) throws CourseFullException, PrerequisiteNotMetException {
        if (prerequisites.containsKey(course) && !prerequisites.get(course).equals(completedCourse)) {
            throw new PrerequisiteNotMetException("Error: Complete " + prerequisites.get(course) + " before enrolling in " + course + ".");
        }

        int enrolled = courseEnrollment.getOrDefault(course, 0);
        if (enrolled >= MAX_STUDENTS) {
            throw new CourseFullException("Error: " + course + " is full.");
        }

        courseEnrollment.put(course, enrolled + 1);
        System.out.println("Enrollment successful in " + course + ".");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Enroll in Course: ");
            String course = scanner.nextLine();
            
            System.out.print("Prerequisite Completed: ");
            String completedCourse = scanner.nextLine();
            
            enrollStudent(course, completedCourse);
        } catch (CourseFullException | PrerequisiteNotMetException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
