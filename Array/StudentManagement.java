package Exercise.Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import java.io.IOException;

public class StudentManagement {
    private Node head;

    public StudentManagement() {
        this.head = null;
    }

    private String readLine() {
        byte[] buffer = new byte[100];
        int count = 0;
        try {
            while (true) {
                int read = System.in.read();
                if (read == '\n') {
                    break;
                }
                buffer[count++] = (byte) read;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(buffer, 0, count).trim();
    }

    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(readLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid integer: ");
            }
        }
    }

    private double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(readLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }

    private boolean isIdExists(String studentId) {
        Node current = head;
        while (current != null) {
            if (current.student.getStudentId().equals(studentId)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void addStudent() {
        String studentId;
        String fullName;
        double marks;

        // Prompt for student ID and ensure it is not empty
        while (true) {
            System.out.print("Enter Student ID: ");
            studentId = readLine().trim();
            if (studentId.isEmpty()) {
                System.out.println("Invalid input. Please enter a valid Student ID.");
            } else if (isIdExists(studentId)) {
                System.out.println("There are already students using this ID.");
                return;
            } else {
                break;
            }
        }

        // Prompt for full name and ensure it is not empty or invalid
        while (true) {
            System.out.print("Enter Student Full Name: ");
            fullName = readLine().trim();
            if (fullName.isEmpty()) {
                System.out.println("Invalid input. Please enter a valid name.");
            } else if (fullName.matches(".*\\d.*")) {
                System.out.println("Invalid name. Name cannot contain numbers. Please enter a valid name.");
            } else {
                break;
            }
        }

        // Prompt for marks and ensure they are within the valid range
        while (true) {
            System.out.print("Enter Student Marks: ");
            marks = readDouble();
            if (marks < 0 || marks > 10) {
                System.out.println("Invalid marks. Please enter marks between 0 and 10.");
            } else {
                break;
            }
        }

        // Create new student and add to the list
        Student student = new Student(studentId, fullName, marks);
        Node newNode = new Node(student);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("Student added successfully!");
    }


    public void editStudent() {
        System.out.print("Enter Student ID to edit: ");
        String studentId = readLine().trim();
        Node current = head;

        while (current != null && !current.student.getStudentId().equals(studentId)) {
            current = current.next;
        }

        if (current != null) {
            // Prompt for full name and ensure it is not empty or invalid
            String fullName;
            while (true) {
                System.out.print("Enter new Full Name: ");
                fullName = readLine().trim();
                if (fullName.isEmpty()) {
                    System.out.println("Invalid input. Please enter a valid name.");
                } else if (fullName.matches(".*\\d.*")) {
                    System.out.println("Invalid name. Name cannot contain numbers. Please enter a valid name.");
                } else {
                    break;
                }
            }

            // Prompt for marks and ensure they are within the valid range
            double marks;
            while (true) {
                System.out.print("Enter new Marks: ");
                marks = readDouble();
                if (marks < 0 || marks > 10) {
                    System.out.println("Invalid marks! Please enter marks between 0 and 10.");
                } else {
                    break;
                }
            }

            current.student.setFullName(fullName);
            current.student.setMarks(marks);
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }


    public void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        String studentId = readLine().trim();

        if (head == null) {
            System.out.println("No students to delete.");
            return;
        }

        if (head.student.getStudentId().equals(studentId)) {
            head = head.next;
            System.out.println("Student deleted successfully!");
            return;
        }

        Node current = head;
        Node previous = null;

        while (current != null && !current.student.getStudentId().equals(studentId)) {
            previous = current;
            current = current.next;
        }

        if (current != null) {
            previous.next = current.next;
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    public void sortStudentsById() {
        if (head == null || head.next == null) {
            System.out.println("No need to sort, insufficient number of students.");
            return;
        }

        boolean swapped;
        do {
            swapped = false;
            Node current = head;
            while (current.next != null) {
                if (current.student.getStudentId().compareTo(current.next.student.getStudentId()) > 0) {
                    Student temp = current.student;
                    current.student = current.next.student;
                    current.next.student = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);

        System.out.println("Students sorted by ID using Bubble Sort!");
    }

    public void sortStudentsByMarks() {
        if (head == null || head.next == null) {
            System.out.println("No need to sort, insufficient number of students.");
            return;
        }

        Node current = head;

        while (current != null) {
            Node min = current;
            Node nextNode = current.next;

            while (nextNode != null) {
                if (nextNode.student.getMarks() < min.student.getMarks()) {
                    min = nextNode;
                }
                nextNode = nextNode.next;
            }

            if (min != current) {
                Student temp = current.student;
                current.student = min.student;
                min.student = temp;
            }

            current = current.next;
        }

        System.out.println("Students sorted by Marks using Selection Sort!");
    }

    public void searchStudent() {
        System.out.print("Enter Student ID to search: ");
        String studentId = readLine().trim();
        Node current = head;

        while (current != null && !current.student.getStudentId().equals(studentId)) {
            current = current.next;
        }

        if (current != null) {
            System.out.println(current.student);
        } else {
            System.out.println("Student not found!");
        }
    }

    public void displayAllStudents() {
        if (head == null) {
            System.out.println("No students to display.");
            return;
        }

        Node current = head;
        while (current != null) {
            System.out.println(current.student);
            current = current.next;
        }
    }

    public static void main(String[] args) {
        StudentManagement sm = new StudentManagement();

        while (true) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Sort Students by ID (Bubble Sort)");
            System.out.println("5. Sort Students by Marks (Selection Sort)");
            System.out.println("6. Search Student by ID");
            System.out.println("7. Display All Students");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = sm.readInt();

            switch (choice) {
                case 1:
                    sm.addStudent();
                    break;
                case 2:
                    sm.editStudent();
                    break;
                case 3:
                    sm.deleteStudent();
                    break;
                case 4:
                    sm.sortStudentsById();
                    break;
                case 5:
                    sm.sortStudentsByMarks();
                    break;
                case 6:
                    sm.searchStudent();
                    break;
                case 7:
                    sm.displayAllStudents();
                    break;
                case 8:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

