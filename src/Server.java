import java.io.IOException;

public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println("!QUIZ-GAME!");
        SocketServer socketServer = new SocketServer();
        socketServer.runServer();
    }
}
