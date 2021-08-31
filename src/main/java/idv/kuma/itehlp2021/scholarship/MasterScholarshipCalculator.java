package idv.kuma.itehlp2021.scholarship;

public class MasterScholarshipCalculator implements Calculator {

    @Override
    public int calculate(Transcript transcript) {

        if (transcript.hasNoCourses()) return 0; // 不修課跟人家領什麼獎學金！

        double weightedAverage = transcript.calculateWeightedAverage();

        if (weightedAverage >= 90D) {
            return 15_000;
        } else if (weightedAverage >= 80D) {
            return 7_500;
        } else {
            return 0;
        }
    }

}