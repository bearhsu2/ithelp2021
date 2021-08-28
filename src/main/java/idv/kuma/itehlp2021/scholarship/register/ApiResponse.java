package idv.kuma.itehlp2021.scholarship.register;

public class ApiResponse {
    private Integer errorCode;

    public ApiResponse() {
    }

    public ApiResponse(int errorCode) {

        this.errorCode = errorCode;
    }

    public static ApiResponse empty() {
        return new ApiResponse();
    }

    public static ApiResponse bad(int errorCode) {
        return new ApiResponse(errorCode);
    }
}
