import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of subjects:");
        int numOfSubjects = scanner.nextInt();

        // Input marks for each subject
        int[] subjectMarks = new int[numOfSubjects];
        for (int i = 0; i < numOfSubjects; i++) {
            System.out.println("Enter marks for subject " + (i + 1) + " out of 100:");
            subjectMarks[i] = scanner.nextInt();
        }

        // Calculate total marks
        int totalMarks = 0;
        for (int marks : subjectMarks) {
            totalMarks += marks;
        }

        // Calculate average percentage
        double averagePercentage = (double) totalMarks / numOfSubjects;

        // Grade calculation
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // Display results
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
