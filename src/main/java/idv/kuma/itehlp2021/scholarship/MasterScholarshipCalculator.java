package idv.kuma.itehlp2021.scholarship;

import java.util.List;

public class MasterScholarshipCalculator {
    public MasterScholarshipCalculator() {
    }

    int calculateMaster(Transcript transcript) {
        List<Course> courses = transcript.getCourses();
        if (courses.isEmpty()) return 0; // 不修課跟人家領什麼獎學金！

        double totalCredit = 0.001D;
        double totalWeightedScore = 0D;

        for (Course course : courses) {
            totalCredit += course.getCredit();
            totalWeightedScore += course.getScore() * course.getCredit();
        }

        double weightedAverage = totalWeightedScore / totalCredit;


        if (weightedAverage >= 90D) {
            return 15_000;
        } else if (weightedAverage >= 80D) {
            return 7_500;
        } else {
            return 0;
        }
    }
}