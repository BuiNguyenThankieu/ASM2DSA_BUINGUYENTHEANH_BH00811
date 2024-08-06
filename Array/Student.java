package Exercise.Array;

public class Student {
    private String studentId;  // Change from int to String
    private String fullName;
    private double marks;
    private String rank;

    public Student(String studentId, String fullName, double marks) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.marks = marks;
        this.rank = calculateRank(marks);
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
        this.rank = calculateRank(marks);
    }

    public String getRank() {
        return rank;
    }

    private String calculateRank(double marks) {
        if (marks >= 0 && marks < 5.0) {
            return "Fail";
        } else if (marks >= 5.0 && marks < 6.5) {
            return "Medium";
        } else if (marks >= 6.5 && marks < 7.5) {
            return "Good";
        } else if (marks >= 7.5 && marks < 9.0) {
            return "Very Good";
        } else if (marks >= 9.0 && marks <= 10.0) {
            return "Excellent";
        } else {
            return "Invalid Marks";
        }
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + ", Full Name: " + fullName + ", Marks: " + marks + ", Rank: " + rank;
    }
}


