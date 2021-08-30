package idv.kuma.itehlp2021.scholarship;

public class ScholarshipService {

    public int calculate(Transcript transcript) throws UnknownProgramTypeException {

        Calculator calculator = findCalculator(transcript.getProgramType());
        return calculator.calculate(transcript);

    }

    private Calculator findCalculator(String programType) throws UnknownProgramTypeException {
        switch (programType) {
            case "Bachelor":
                return new BachelorScholarshipCalculator();
            case "Master":
                return new MasterScholarshipCalculator();
            case "PhD":
                return new PhDScholarshipCalculator();
            default:
                throw new UnknownProgramTypeException(programType);
        }
    }


    public static class UnknownProgramTypeException extends Throwable {
        public UnknownProgramTypeException(String programType) {
            super("Unknown program type: " + programType);
        }
    }
}
