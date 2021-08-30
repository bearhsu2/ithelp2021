package idv.kuma.itehlp2021.scholarship;

public class ScholarshipService {

    private final BachelorScholarshipCalculator bachelorScholarshipCalculator = new BachelorScholarshipCalculator();
    private final MasterScholarshipCalculator masterScholarshipCalculator = new MasterScholarshipCalculator();
    private final PhDScholarshipCalculator phDScholarshipCalculator = new PhDScholarshipCalculator();

    public int calculate(Transcript transcript) throws UnknownProgramTypeException {

        String programType = transcript.getProgramType();

        if (programType.equals("Bachelor")) {
            return bachelorScholarshipCalculator.calculateBachelor(transcript);
        }

        if (programType.equals("Master")) {
            return masterScholarshipCalculator.calculateMaster(transcript);
        }

        if (programType.equals("PhD")) {
            return phDScholarshipCalculator.calculatePhD(transcript);
        }

        throw new UnknownProgramTypeException(programType);

    }


    public static class UnknownProgramTypeException extends Throwable {
        public UnknownProgramTypeException(String programType) {
            super("Unknown program type: " + programType);
        }
    }
}
