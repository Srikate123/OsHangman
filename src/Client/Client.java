import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class Client {

      private static String word;
    private static String answer;
    private static int count = 0;

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
        System.out.println(" - Hangman- \n For exit type \"exit\"");

        Scanner sc = new Scanner(System.in);
        String host = "server";
        Socket socket;

        while (true) {
            socket = new Socket(host, 9876);

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            word = (String) ois.readObject();
            answer = new String(new char[word.length()]).replace("\0", "*");
            System.out.println("Answer: " + word);

            while (count < 7 && answer.contains("*")) {
                System.out.println("Guess any letter in the word");
                System.out.println(answer);
                String guess = sc.next();
                hang(guess);
            }

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            System.out.print("Exit? [y/n]: ");
            String text = sc.next();
            oos.writeObject(text);
            if (text.equalsIgnoreCase("y")) break;
            count = 0;

            ois.close();
            oos.close();
            Thread.sleep(1000);
        }
        System.out.println("Shutting down Socket Client!!");
    }

    public static void hang(String guess) {
        String newAnswer = "";
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == Character.toLowerCase(guess.charAt(0))) {
                newAnswer += guess.charAt(0);
            } else if (answer.charAt(i) != '*') {
                newAnswer += word.charAt(i);
            } else {
                newAnswer += "*";
            }
        }

        if (answer.equalsIgnoreCase(newAnswer)) {
            count++;
            hangmanImage();
        } else {
            answer = newAnswer;
        }
        if (answer.equalsIgnoreCase(word)) {
            System.out.println("Correct! You win! The word was " + word);
        }
    }

    public static void hangmanImage() {
        cls();
        if (count == 1) {
            System.out.println("Wrong guess, try again");
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("___|___");
            System.out.println();
        }
        if (count == 2) {
            System.out.println("Wrong guess, try again");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("___|___");
        }
        if (count == 3) {
            System.out.println("Wrong guess, try again");
            System.out.println("   ____________");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   | ");
            System.out.println("___|___");
        }
        if (count == 4) {
            System.out.println("Wrong guess, try again");
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        |     |");
            System.out.println("   |         \\_ _/");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("___|___");
        }
        if (count == 5) {
            System.out.println("Wrong guess, try again");
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        |     |");
            System.out.println("   |         \\_ _/");
            System.out.println("   |           |");
            System.out.println("   |           |");
            System.out.println("   |");
            System.out.println("___|___");
        }
        if (count == 6) {
            System.out.println("Wrong guess, try again");
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        |     |");
            System.out.println("   |         \\_ _/");
            System.out.println("   |           |");
            System.out.println("   |           |");
            System.out.println("   |          / \\ ");
            System.out.println("___|___      /   \\");
        }
        if (count == 7) {
            System.out.println("GAME OVER!");
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        |     |");
            System.out.println("   |         \\_ _/");
            System.out.println("   |          _|_");
            System.out.println("   |         / | \\");
            System.out.println("   |          / \\ ");
            System.out.println("___|___      /   \\");
            System.out.println("GAME OVER! The word was " + word);
        }
    }

    public static void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException E) {
            System.out.println(E);
        } catch (InterruptedException E) {
            System.out.println(E);
          }
    }
}
