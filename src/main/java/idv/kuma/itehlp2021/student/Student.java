package idv.kuma.itehlp2021.student;

import lombok.Data;

@Data
public class Student {

    String lastName;
    String firstName;

    public Student(String firstName, String lastName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

}







