package software.carter;

public class App 
{
    public static void main( String[] args )
    {
        Request request = new Request()
            .setRequestType(RequestType.GET)
            .setDomain("example.com")
            .setRequestRoute("/")
            .setPort(80);

        request.send();

        System.out.println(request.getResponse().getBody());

        request.cleanup();
    }
}
