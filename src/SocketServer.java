import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    int portNumber = 44444;
    ServerSocket serverSocket = null;
    Socket socket = null;


    public void runServer(){
        try{
            serverSocket = new ServerSocket(portNumber);
        }catch (IOException e){
            e.printStackTrace();
        }


        while (true){
            try{
                socket = serverSocket.accept();
                RunnableServer runnableServer = new RunnableServer(socket);
                new Thread (runnableServer).start();
            }catch (IOException e){
                e.printStackTrace();

            }
        }
    }
}
