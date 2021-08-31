package idv.kuma.itehlp2021.scholarship;

import java.util.List;

public class MasterScholarshipCalculator implements Calculator {

    @Override
    public int calculate(Transcript transcript) {

        List<Course> courses = transcript.getCourses();

        if (hasNoCourses(courses)) return 0; // 不修課跟人家領什麼獎學金！

        double weightedAverage = calculateWeightedAverage(courses);

        if (weightedAverage >= 90D) {
            return 15_000;
        } else if (weightedAverage >= 80D) {
            return 7_500;
        } else {
            return 0;
        }
    }

    private double calculateWeightedAverage(List<Course> courses) {
        double totalCredit = 0.001D;
        double totalWeightedScore = 0D;

        for (Course course : courses) {
            totalCredit += course.getCredit();
            totalWeightedScore += course.getScore() * course.getCredit();
        }

        double weightedAverage = totalWeightedScore / totalCredit;
        return weightedAverage;
    }

    private boolean hasNoCourses(List<Course> courses) {
        return courses.isEmpty();
    }
}