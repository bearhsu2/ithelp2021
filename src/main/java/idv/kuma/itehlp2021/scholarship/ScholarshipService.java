package idv.kuma.itehlp2021.scholarship;

import java.util.List;

public class ScholarshipService {

    public int calculate(Transcript transcript) throws UnknownProgramTypeException {

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


        if (programType.equals("Master")) {

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


        if (programType.equals("PhD")) {

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

        throw new UnknownProgramTypeException(programType);
    }

    public static class UnknownProgramTypeException extends Throwable {
        public UnknownProgramTypeException(String programType) {
            super("Unknown program type: " + programType);
        }
    }
}
