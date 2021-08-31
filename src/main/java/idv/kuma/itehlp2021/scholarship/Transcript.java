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

    public double calculateWeightedAverage() {


        double totalCredit = 0.001D;
        double totalWeightedScore = 0D;

        for (Course course : courses) {
            totalCredit += course.getCredit();
            totalWeightedScore += course.getScore() * course.getCredit();
        }

        double weightedAverage = totalWeightedScore / totalCredit;
        return weightedAverage;
    }

    boolean hasNoCourses() {
        return this.courses.isEmpty();
    }

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }
}



