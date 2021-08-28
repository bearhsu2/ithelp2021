package idv.kuma.itehlp2021.scholarship.register;

public class StudentNotExistException extends Exception {
    public StudentNotExistException(String message) {
        super(message);
    }
}
