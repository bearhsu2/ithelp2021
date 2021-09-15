package idv.kuma.itehlp2021.scholarship.apply.adapter;

public class ClientSideErrorException extends Exception {
    private final int code;

    public ClientSideErrorException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
