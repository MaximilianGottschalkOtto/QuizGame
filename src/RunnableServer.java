import java.io.*;
import java.net.Socket;
import java.util.*;


public class RunnableServer implements Runnable {

    protected Socket clientSocket = null;
    InputStreamReader inputStreamReader = null;
    OutputStreamWriter outputStreamWriter = null;
    BufferedReader bufferedReader = null;
    BufferedWriter bufferedWriter = null;


    public RunnableServer(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }


    /**Thread with logic*/

    public void run() {

        while (true) {
            try {
                inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(clientSocket.getOutputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);



                //---- Questions ----
                String[][] listMulti = new String[4][5];
                listMulti[0][0] = "Wann endete die Herrschaft der Habsburger? ";
                listMulti[0][1] = "a) 1918";
                listMulti[0][2] = " b) 1923";
                listMulti[0][3] = " c) 1933";
                listMulti[0][4] = "a";
                listMulti[1][0] = "Wie alt ist die Königin von England? ";
                listMulti[1][1] = "a) 95";
                listMulti[1][2] = " b) 99";
                listMulti[1][3] = " c) 93";
                listMulti[1][4] = "a";
                listMulti[2][0] = "Welches war das größte Königreich der Welt? ";
                listMulti[2][1] = "a) Das osmanische Reich ";
                listMulti[2][2] = " b) Das britische Imperium";
                listMulti[2][3] = " c) Das deutsche Kaiserreich";
                listMulti[2][4] = "b";
                listMulti[3][0] = "Wie groß ist Dresden? ";
                listMulti[3][1] = "a) 287,7 km²";
                listMulti[3][2] = " b) 200,2 km²";
                listMulti[3][3] = " c) 328,8 km²";
                listMulti[3][4] = "c";

                //Helpingvar
                boolean firstRun = true;

                int i = 0; //number of rounds
                int nextQ = 0;
                int nextAnswerInArray = 0;
                int score = 0;

                int count = 3;
                List<Integer> list = new ArrayList<>();
                for (int j = 0; j < 4; j++) {
                    list.add(j);
                }
                Collections.shuffle(list);

                while (true) {

                    // Client Input to server console
                    String msgFromClient = bufferedReader.readLine();
                    System.out.println("Client: " + msgFromClient);
                    String input = msgFromClient.toLowerCase(Locale.ROOT).trim();


                    if (firstRun == true) {
                        firstRun = false;
                        if (input.equals("yes")) {
                        } else {
                            break;
                        }
                    } // end of start loop yes/bye


                    // ==========================first run(permanent loop)=====================================
                    if(i == 0) {
                        i++;

                        nextQ = list.get(count);
                        list.remove(count);
                        nextAnswerInArray = nextQ;
                        count--;

                        bufferedWriter.write(listMulti[nextQ][0]);
                        bufferedWriter.write(listMulti[nextQ][1]);
                        bufferedWriter.write(listMulti[nextQ][2]);
                        bufferedWriter.write(listMulti[nextQ][3]);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }

                    // ==================================================================

                    if ( i > 0 ) {
                        /** Checking for right answer */
                        if(i == listMulti.length){
                            if (input.equals(listMulti[nextAnswerInArray][4])){
                                score = score + 5;
                                bufferedWriter.write("Yes, the correct answer was " + listMulti[nextAnswerInArray][4] + ". You earned 5 points and your final score is:  " + score + " - BYE");
                                bufferedWriter.newLine();
                                bufferedWriter.flush();
                                break;
                            } else {
                                bufferedWriter.write("No, the correct answer was " + listMulti[nextAnswerInArray][4] + ". your final score is:  " + score + " - BYE");
                                bufferedWriter.newLine();
                                bufferedWriter.flush();
                                break;
                            }

                        } else if(input.equals("yes")){
                            continue;
                        } else if(input.equals(listMulti[nextAnswerInArray][4])){
                            i++;
                            score = score + 5;

                            nextQ = list.get(count);
                            list.remove(count);
                            nextAnswerInArray = nextQ;
                            count--;

                            bufferedWriter.write("Yes, the correct answer was " + listMulti[nextAnswerInArray][4] + ". You earned 5 points. ");
                            bufferedWriter.write(listMulti[nextQ][0] + listMulti[nextQ][1] + listMulti[nextQ][2] +listMulti[nextQ][3]);
                            bufferedWriter.newLine();

                            bufferedWriter.flush();

                            /** Checking for wrong answer, but being a, b, or c */
                        } else if (input.equals("a") || input.equals("b") || input.equals("c")) {
                            i++;

                            nextQ = list.get(count);
                            list.remove(count);
                            nextAnswerInArray = nextQ;
                            count--;

                            bufferedWriter.write("No, the correct answer is " + listMulti[nextAnswerInArray][4] + ". You earned 0 points. " );
                            bufferedWriter.write(listMulti[nextQ][0] + listMulti[nextQ][1] + listMulti[nextQ][2] +listMulti[nextQ][3]);
                            bufferedWriter.newLine();

                            bufferedWriter.flush();
                            continue;
                        }
                        else {
                            bufferedWriter.write("Invalid input, try again");
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        }
                    } // end if loop for msgFromClient.equals

                } // end of while(true) loop of the game

            } catch (IOException e) {
                e.printStackTrace();
            }break;

        }

    }

}