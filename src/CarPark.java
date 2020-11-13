import java.io.IOException;
import java.net.ServerSocket;

public class CarPark {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        int serverPort = 4444;

        // Create a serverSocket and bind it to the specified port
        try {
            serverSocket = new ServerSocket(serverPort);
            System.out.println("Car park server started on localhost:" + serverPort);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // As new clients connect, make a new Thread to deal with them
        while(true) {
            new CarParkThread(serverSocket.accept()).start();
            System.out.println("New thread started.");
        }

    }

}
