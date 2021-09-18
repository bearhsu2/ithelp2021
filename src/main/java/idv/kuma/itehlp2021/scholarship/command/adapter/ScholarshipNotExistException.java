package idv.kuma.itehlp2021.scholarship.command.adapter;

public class ScholarshipNotExistException extends Exception {
    public ScholarshipNotExistException(String message) {
        super(message);
    }
}
