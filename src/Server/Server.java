import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
   //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 9876;
    private static String[] words = {
            "godfather","interstellar","inception","avatar","avengers","batman","brave","cars","cinderella","coco","deadpool"," gladiator",
            "dunkirk","frozen","gravity", "halloween","toystory","interstellar","Jaws","jumanji","jurassic","life","logan","forrestGump","memento","antman",
           };
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        //create the socket server object
        server = new ServerSocket(port);
        try {
            //keep listens indefinitely until receives 'exit' call or program terminates
            while (true) {
                System.out.println("Waiting for the client request");
                //creating socket and waiting for client connection
                Socket socket = server.accept();
                //create ObjectOutputStream object
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                //write object to Socket
                String word = words[(int) (Math.random() * words.length)];
                oos.writeObject(word);
                //read from socket to ObjectInputStream object
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //convert ObjectInputStream object to String
                String message = (String) ois.readObject();
                System.out.println("Message Received: " + message);
                //close resources
                ois.close();
                oos.close();
                socket.close();
                //terminate the server if client sends exit request
                if (message.equalsIgnoreCase("y")) break;
        }
            System.out.println("Shutting down Socket server!!");
            //close the ServerSocket object
            server.close();
        } catch (IOException e) {
            System.err.println("Client has been shutdown.");
            server.close();
        }
    }
}

