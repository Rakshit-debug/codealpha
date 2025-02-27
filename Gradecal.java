import java.util.Scanner;

public class Gradecal {
    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);

        // ENTER TOTAL NUMBER OF SUBJECTS
        System.out.print("Enter total number of subjects: ");
        int numSubjects = scann.nextInt();

        int totalMarks = 0;

        for (int i = 1; i <= numSubjects; i++) {
            System.out.print("Enter marks for subject " + i + " (out of 100): ");
            int marks = scann.nextInt();

            // INPUT VALIDATION
            while (marks < 0 || marks > 100) {
                System.out.println("Invalid marks! Please enter a value between 0 and 100.");
                System.out.print("Enter marks for subject " + i + " (out of 100): ");
                marks = scann.nextInt();
            }
            totalMarks += marks;
        }

        // Display student details
        System.out.println("\nStudent Details:");
        System.out.println("Total marks obtained in all subjects: " + totalMarks);

        // Calculate average percentage
        int averagePercentage = totalMarks / numSubjects;
        System.out.println("Average percentage: " + averagePercentage + "%");

        // Assign grade based on the average percentage
        if (averagePercentage >= 90) {
            System.out.println("Your grade is: A+");
        } else if (averagePercentage >= 80) {
            System.out.println("Your grade is: B+");
        } else if (averagePercentage >= 70) {
            System.out.println("Your grade is: B");
        } else if (averagePercentage >= 60) {
            System.out.println("Your grade is: C+");
        } else if (averagePercentage >= 50) {
            System.out.println("Your grade is: C");
        } else if (averagePercentage >= 40) {
            System.out.println("Your grade is: E+");
        } else if (averagePercentage >= 30) {
            System.out.println("Your grade is: E");
        } else {
            System.out.println("Your grade is: FAIL");
        }

        scann.close();
    }
}
 