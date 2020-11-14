import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Entrance {

    public static void main(String[] args) throws IOException {

        // Set up the socket, in and out variables
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        int port = 4444;
        try {
            socket = new Socket("localhost", port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Couldn't get I/O for the connection to: " + port);
            System.exit(1);
        }

        System.out.println("Welcome to the car park. You can enter 'e' to enter.");

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        String fromServer;

        // Get the user input and send it to the server - then print out the reply
        while (true) {

            // TODO: Check messages from the server - if it says the car park is full, we should start a queue
            //       If the car park is no longer full, send a queued "e" message to the server

            userInput = stdIn.readLine();
            if (userInput.equals("e")) {
                out.println(userInput);

                // Read in messages from the server and display
                fromServer = in.readLine();
                System.out.println("Car Park: " + fromServer);
            }
            else {
                System.out.println("You have to press 'e' to let the entrance know you're there!");
            }
        }

    }

}
