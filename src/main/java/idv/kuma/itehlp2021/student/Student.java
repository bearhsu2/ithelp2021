package idv.kuma.itehlp2021.student;

import lombok.Data;

@Data
public class Student {

    String lastName;
    String firstName;
    private String program = "unassigned";
    private Student student;

    public Student(String firstName, String lastName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Student(String firstName, String lastName, String program) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.program = program;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

}







