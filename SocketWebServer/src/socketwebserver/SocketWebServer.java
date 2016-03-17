package socketwebserver;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.in;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

public class SocketWebServer{
    public static void main(String[] args) throws IOException {
        System.out.println("The chat server is running.");
        ServerSocket ss = new ServerSocket(8080);
        try {
            while (true) {
                new Handler(ss.accept()).start();
            }
        } finally {
            ss.close();
        }
    }
    
    private static class Handler extends Thread{
        private Socket client;
        
        public Handler(Socket socket) {
            this.client = socket;
        }
        
        @Override
        public void run() {
            try {
                System.out.println("SocketWebServer Made");
                while (true) {
                    System.out.println("\nStart while loop");

                    System.out.println("End read buffered reader");

                    PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                    out.println("HTTP/1.1 200 OK");
                    out.println("Content-Type: image/png");
                    out.println("SocketWebServer: Bot");
                    out.println("");

                    //int i;

        //            String fileName = "C:\\Users\\Sammy Caychingco\\Documents\\NetBeansProjects\\SocketWeb\\SocketWebServer\\src\\Assets\\HighMufasa.png";
        //            String fileName = "C:\\Users\\Sammy Caychingco\\Desktop\\Reaction Photos\\Whoa.JPG";
                    String fileName = "src/Assets/HighMufasa.png";

                    File file = new File(fileName);

                    ImageInputStream imageInputStream = ImageIO.createImageInputStream(file);
                    long size = imageInputStream.length();
                    out.write("Content-Length: " + size + "\r\n");
                    out.write("\r\n");

                    BufferedImage bufferedImage = ImageIO.read(file);
                    boolean success = ImageIO.write(bufferedImage, "png", client.getOutputStream());
                    System.out.println("Success: " + success); //true
                    out.flush();
                    System.out.println("Done printing output");

                    out.close();
                    in.close();
                    client.close();
                }
            } catch (IOException ex) {
                System.out.println("ERROR");
                //Logger.getLogger(SocketWebServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
