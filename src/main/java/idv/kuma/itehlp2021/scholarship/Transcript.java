package idv.kuma.itehlp2021.scholarship;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class Transcript {


    private String programType;
    private List<Course> courses;

    public Transcript(String programType, Course... courses) {
        this.programType = programType;
        this.courses = Arrays.asList(courses);
    }

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }
}



