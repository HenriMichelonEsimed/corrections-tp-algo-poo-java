package myapp.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import myapp.collections.EndOfListException;
import myapp.collections.Iterator;
import myapp.searchengine.Index;
import myapp.searchengine.TreeNode;
import myapp.searchengine.WordWeigth;

public class WebSearchEngine {

    private final Index index;
    private final ServerSocket serverSocket;

    public WebSearchEngine(String path, int tcpPort) throws IOException, EndOfListException {
        this.index = new Index();
        this.index.build(path);
        serverSocket = new ServerSocket(tcpPort);
        while(true) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        processRequest(serverSocket.accept());
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                };
            }).start();
        }
    } 

    private void sendError(PrintWriter out, int status) {
        out.format("HTTP/1.1 %s ERROR\r\n", status);
        out.write("Content-Type: text/plain; charset=UTF-8\r\n");
        out.write("\r\n");
        out.format("HTTP Error %s\r\n", status);
    }

    private void routeSearch(BufferedReader in, PrintWriter out, String request) {
        String parts[] = request.split("=");
        if (parts.length != 2) {
            sendError(out, 400);
            return;
        }
        switch (parts[0]) {
            case "q": {
                    out.write("HTTP/1.1 200 OK\r\n");
                    out.write("Content-Type: application/json; charset=UTF-8\r\n");
                    out.write("\r\n[\r\n");
                    TreeNode node = index.find(parts[1]);
                    if (node != null) {
                        Iterator<WordWeigth> it = new Iterator<>(node.getFilesList());
                        while (it.hasNext()) {
                            try {
                                WordWeigth wordWeigth = it.next();
                                out.format("{\"fileName\":\"%s\", \"weight\":%d}", 
                                    wordWeigth.getFileName().replace("\\", "\\\\"), 
                                    wordWeigth.getWeight());
                                if (it.hasNext()) out.write(",");
                                out.write("\r\n");
                            } catch (EndOfListException e) {
                                break;
                            }
                        }
                    }
                    out.write("]\r\n");
                }
                break;
            default: 
            sendError(out, 400);
                break;
        }
    }

    private void routerGet(BufferedReader in, PrintWriter out, String request) {
        String parts[] = request.split("\\?");
        if (parts.length != 2) {
            sendError(out, 400);
            return;
        }
        switch (parts[0]) {
            case "/search": 
                routeSearch(in, out, parts[1]);
                break;
            default: 
                sendError(out, 404);
                break;
        }
    }
    
    public void processRequest(Socket socket) throws IOException, InterruptedException {
        System.out.format("New connexion from %s : %s\n", socket.getRemoteSocketAddress().toString(), socket.hashCode());
        //Thread.sleep((long) (Math.random() * 5000 + 10));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        String request = in.readLine();

        String[] parts = request.split(" ");
        if ((parts.length != 3) || (!parts[2].equals("HTTP/1.1"))) {
            sendError(out, 400);
            socket.close();
            return;
        }
        switch (parts[0]) {
            case "GET": 
                routerGet(in, out, parts[1]);
                break;
            default:
                sendError(out, 404);
                break;
        }
        out.flush();
        socket.close();
        System.out.format("End of connexion %s\n", socket.hashCode());
    }

}
