package software.carter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection {
    private Socket socket;
    private OutputStream output;
    private PrintWriter writer;
    private InputStream input;
    private BufferedReader reader;

    /**
     * <p>When a `Connection` instance falls out of scope, anything instantiated in the
     * try() call will be cleaned up for us.
     * That being said - I feel that it's likely worth it if I were to add each as a
     * class member and simply ensure we clean
     * up after ourselves properly, rather than either</p>
     * 
     * <p>
     * A - Not having access to them via encapsulation elsewhere
     * </p>
     * 
     * <p>
     * B - Adding duplicate class members and setting them once within the scope of
     * the try-catch.
     * </p>
     * 
     * <p>
     * Both of the above options seem stupid when compared to just cleaning up after
     * ourselves.
     * </p>
     * 
     * <p>
     * Edit: Just did it the right(?) way. Was previously using try-with-resource to
     * instantiate all members of this class.
     * </p>
     * 
     * @param inetAddress
     * @param port
     */
    public Connection(String inetAddress, int port) {
        try {
            socket = new Socket(inetAddress, port);
            output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
            input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return this.socket;
    }

    public OutputStream getOutput() {
        return this.output;
    }

    public PrintWriter getWriter() {
        return this.writer;
    }

    public InputStream getInput() {
        return this.input;
    }

    public BufferedReader getReader() {
        return this.reader;
    }
}
