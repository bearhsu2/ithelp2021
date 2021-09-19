package idv.kuma.itehlp2021.scholarship.command.adapter;

public class ServerSideErrorException extends Exception {
    private int code;

    public ServerSideErrorException(String message) {
        super(message);
    }

    public ServerSideErrorException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
