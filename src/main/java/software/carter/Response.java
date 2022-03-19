package software.carter;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.List;

// TODO: Separate the building blocks of this into `ResponseBuilder`. 
public class Response {
    private HashMap<String, String> headers;
    private String body;
    private String statusCode;

    public Response parse(BufferedReader reader) {
        StringBuilder bodyBuilder = new StringBuilder();
        List<String> data;

        if (this.headers == null)
            this.headers = new HashMap<>();

        data = reader.lines().toList();

        for (String line : data) {
            if (line.contains("HTTP/1")) {
                this.statusCode = line;
                continue;
            }

            if (!line.contains(":")) {
                bodyBuilder.append(line + "\n");
            } else {
                String[] keyValue = line.split(":");
                putHeader(keyValue[0], keyValue[1]);
            }
        }

        this.body = bodyBuilder.toString();
        return this;
    }

    public HashMap<String,String> getHeaders() {
        return this.headers;
    }

    public Response setHeaders(HashMap<String,String> headers) {
        this.headers = headers;
        return this;
    }

    public Response putHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public String getBody() {
        return this.body;
    }

    public Response setBody(String body) {
        this.body = body;
        return this;
    }

    // TODO: Just return the code as an integer later on.
    public String getStatusCode() {
        return this.statusCode;
    }
}
