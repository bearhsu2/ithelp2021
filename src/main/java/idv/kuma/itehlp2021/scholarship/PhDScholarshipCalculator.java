package idv.kuma.itehlp2021.scholarship;

import java.util.List;

public class PhDScholarshipCalculator implements Calculator {


    @Override
    public int calculate(Transcript transcript) {
        List<Course> courses = transcript.getCourses();
        if (courses.isEmpty()) return 0; // 不修課跟人家領什麼獎學金！


        for (Course course : courses) {
            if (course.getScore() < 80) {
                return 0;
            }
            if (course.getScore() < 90) {
                return 20_000;
            }
        }
        return 40_000;
    }
}