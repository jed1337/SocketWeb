package socketwebbrowser;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

class SocketWebBrowser extends Thread {
   private int number;
   private Socket socket;

   public SocketWebBrowser(int number) throws IOException {
      System.out.println("Number: " + number);
      this.number = number;

      socket = new Socket("localhost", 8080);
      System.out.println(socket);
   }

   @Override
   public void run() {
      int i;

      try {
//         DataInputStream din = new DataInputStream(socket.getInputStream());
          BufferedInputStream b = new BufferedInputStream(socket.getInputStream());
          
          int count;
          while((count = b.read())!=-1){}
          
//         BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//         while (br.readLine() != null){}
//            System.out.println(sb);
         System.out.println("File received, number "+number);            
        } catch (IOException ex) {
           System.err.println("ERROR File: " + ex + " Number: "+number);
        }
   }

    public static void main(String ar[]) throws IOException {
        int i = 0;

        //while(true){
            new SocketWebBrowser(++i).start();
        //}
    }
}
