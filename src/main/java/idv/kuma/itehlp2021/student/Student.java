package idv.kuma.itehlp2021.student;

import lombok.Data;

@Data
public class Student {

    String lastName;
    String firstName;

    public Student(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

}
