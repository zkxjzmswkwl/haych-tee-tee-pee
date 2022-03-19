package software.carter;

import java.io.IOException;

public class Request {
    private String requestRoute = "/";
    private String domain = "127.0.0.1";
    private int port = 8080;
    private Connection connection;
    private Response response;
    private RequestType requestType;

    public Request send() {
        if (connection == null) {
            connection = new Connection(this.domain, this.port);
        }
        
        connection.getWriter().println(String.format("%s %s HTTP/1.1", requestType, requestRoute));
        connection.getWriter().println("Host: " + domain);
        connection.getWriter().println("User-Agent: I'm gay");
        connection.getWriter().println("Accept: text/html");
        connection.getWriter().println("Connection: close");
        connection.getWriter().println();

        this.response = new Response().parse(connection.getReader());
        return this;
    }

    public void cleanup() {
        try {
            connection.getOutput().close();
            connection.getInput().close();
            connection.getWriter().close();
            connection.getReader().close();
            connection.getSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RequestType getRequestType() {
        return this.requestType;
    }

    public Request setRequestType(RequestType requestType) {
        this.requestType = requestType;
        return this;
    }

    public String getRequestRoute() {
        return this.requestRoute;
    }

    public Request setRequestRoute(String requestRoute) {
        this.requestRoute = requestRoute;
        return this;
    }

    public String getDomain() {
        return this.domain;
    }

    public Request setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public int getPort() {
        return this.port;
    }

    public Request setPort(int port) {
        this.port = port;
        return this;
    }

    public Response getResponse() {
        if (response != null)
            return this.response;
        throw new NullPointerException("You cannot get the response of a request you've yet to make.");
    }
}
