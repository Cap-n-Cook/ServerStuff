import java.net.*;
import java.io.*;


public class ClientTester {

    // Init Socket & IO
    private Socket socket = null;
    private BufferedReader input = null;
    private DataOutputStream output = null;

    // This code is for a client. We are opening a socket on the 
    // specified device.
    public ClientTester(String address, int port){
 
        // String data we want to send.
        String dataToSend = "";

        // Establish the connection.
        try {
            // Create socket object. This opens the socket.
            socket = new Socket(address, port);
            // Print status of socket.
            System.out.println("Socket");

            // This is what we want to send over the socket.
            input = new BufferedReader(new InputStreamReader(System.in));
            output = new DataOutputStream(System.out);
        }
        catch(UnknownHostException unknown) {
            System.out.println("ERROR: " + unknown);
        }
        catch(IOException err){
            System.out.println("ERROR (Client try 1): " + err);
        }

        // Gather data.
        while(!dataToSend.equals("Over")) {

            try {
                dataToSend = input.readLine();
                output.writeUTF(dataToSend);
            }
            catch(IOException err){
                System.out.println("Client based error. Bad IO. " + err);
            }
        }

        // Close the connection.
        try{
            input.close();
            output.close();
            socket.close();
        }
        catch(IOException err){
            System.out.println(err);
        }

    }

    public static void main(String[] args) {

        System.out.println("Testing Java stuff.\n\n");
        
        // Starting the client.
        ClientTester client = new ClientTester("121.0.0.1", 5000);
    }
}