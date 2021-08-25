package idv.kuma.itehlp2021.scholarship;

import java.util.List;

public class ScholarshipService {

    public int calculate(Transcript transcript) {

        String programType = transcript.getProgramType();

        if (programType.equals("Bachelor")) {


            List<Course> courses = transcript.getCourses();
            if (courses.isEmpty()) return 0; // 不修課跟人家領什麼獎學金！

            int total = courses.size();
            int achieved = 0;
            for (Course course : courses) {
                if (course.getScore() >= 80) {
                    achieved++;
                }
            }
            double rate = (double) achieved / total;

            if (rate >= (double) 1 / 2) {
                return 10_000;
            } else if (rate >= (double) 1 / 3) {
                return 5_000;
            } else {
                return 0;
            }
        }

        return 0;
    }
}
