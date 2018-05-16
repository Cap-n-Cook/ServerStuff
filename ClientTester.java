import java.net.*;
import java.io.*;


public class ClientTester {

    // Init Socket & IO
    private Socket socket = null;
    private DataOutputStream output = null;

    // Stores data to send.
    String dataToSend = "THis is XML.";
    // This code is for a client. We are opening a socket on the 
    // specified device.
    public ClientTester(String address, int port){

        // Establish the connection.
        try {
            // Create socket object. This opens the socket.
            socket = new Socket(address, port);
            // Direct output to the Ostream of the socket.
            output = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException unknown) {
            System.out.println("ERROR: " + unknown);
        }
        catch(IOException err){
            System.out.println("ERROR (Client try 1): " + err);
        }

        // Gather data.
        getDataFromClient(dataToSend);

        // Close the connection.
        closeConnection();

    }

    public static void main(String[] args){

        System.out.println("Testing Socket Stuff...\n");
        ClientTester client = new ClientTester("127.0.0.1", 5000);
    }

    private void closeConnection(){
        
        try{
            output.close();
            socket.close();
        }
        catch(IOException err){
            System.out.println(err);
        }
    }

    private void getDataFromClient(String Data){
        
        try{
            output.writeUTF(dataToSend);
        }
        catch(IOException err){
            System.out.println(err);
        }
    }
}