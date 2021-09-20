package idv.kuma.itehlp2021.student;

import lombok.Data;

@Data
public class Student {

    String lastName;
    String firstName;
    private String degree = "unassigned";
    private Student student;

    public Student(String firstName, String lastName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Student(String firstName, String lastName, String degree) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.degree = degree;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

}







