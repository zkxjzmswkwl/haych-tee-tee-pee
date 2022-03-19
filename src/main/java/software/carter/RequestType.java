package software.carter;

public enum RequestType
{
    HEAD("HEAD"),
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE"),
    CREATE("CREATE");

    private final String requestType;

    RequestType(String requestType) {
        this.requestType = requestType;
    }

    @Override
    public String toString() {
        return this.requestType;
    }
}
