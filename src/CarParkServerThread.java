import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CarParkServerThread extends Thread {

    private Socket socket = null;
    private SharedCarParkState carParkState;

    CarParkServerThread(Socket socket, String threadName, SharedCarParkState carParkState) {
        super(threadName);
        this.socket = socket;
        this.carParkState = carParkState;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                // Read in the input from the socket
                String message = in.readLine();
                printToConsole(message);

                // Complete the action and send the output back to the client
                try {
                    carParkState.acquireLock();
                    String output = carParkState.processInput(message);
                    out.println(output);
                    carParkState.releaseLock();
                }
                catch (InterruptedException e) {
                    System.err.println("Failed to get lock when reading: "+ e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Print to server console for logging purposes.
    private void printToConsole(String message) {
        if (message.equals("e")) {
            System.out.println("[*] Message received from Entrance - a car is trying to enter.");
        }
        else {
            System.out.println("[*] Message received from Exit - a car has left.");
        }
    }

}
