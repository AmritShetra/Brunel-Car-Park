import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CarParkThread extends Thread {

    private Socket socket = null;
    private SharedCarParkState carParkState;

    CarParkThread(Socket socket, SharedCarParkState carParkState) {
        super("CarParkThread");
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

                // TODO: If the car park is full, we should tell the client (so they can queue messages)
                //       When the car park is no longer full, we should tell the client (so they can send us messages)

                if (message.equals("e")) {
                    System.out.println("[*] Message received from Entrance - car is trying to enter.");
                }
                else {
                    System.out.println("[*] Message received from Exit - a car has left.");
                }

                carParkState.acquireLock();
                String output = carParkState.processInput(message);
                carParkState.releaseLock();

                out.println(output);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
