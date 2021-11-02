import java.io.*;
import java.net.Socket;
import java.util.Scanner;


import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    /**helping Var*/
    private static boolean firstRun = true;
    private static boolean secondRun = true;

    public static void main(String[] args) {

        String hostName = "127.0.0.1";
        int portNumber = 44444;
        Socket clientSocket;

        InputStreamReader inputStreamReader;
        OutputStreamWriter outputStreamWriter;
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;



        try {
            clientSocket = new Socket(hostName, portNumber);

            inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(clientSocket.getOutputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            Scanner scanner = new Scanner(System.in);


            while( true ){

                if(firstRun == true){
                    System.out.println("Welcome to game");
                    System.out.println("===========================================================================");
                    System.out.println("Enter >>yes<< to start the game.");
                    System.out.println("Type >>BYE<< if you want to exit the game.");
                    firstRun = false;
                }


                String msgToSend = scanner.nextLine();
                bufferedWriter.write(msgToSend);
                bufferedWriter.newLine();
                if (msgToSend.equalsIgnoreCase("BYE")){
                    break;
                }
                bufferedWriter.flush();
                System.out.println("Server: " + bufferedReader.readLine());

            }


        }catch (UnknownHostException e){
            System.exit(1);
        }catch (IOException e){
            System.exit(1);
        } // end try catch


    } // end main

} // end class
