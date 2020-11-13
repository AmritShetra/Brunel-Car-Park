import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CarParkThread extends Thread {

    private Socket socket = null;

    CarParkThread(Socket socket) {
        super("CarParkThread");
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                // Read in the input from the socket
                String message = in.readLine();

                // Send a message back
                out.println("Message received - you said " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
