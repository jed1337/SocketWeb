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

public class SocketWebServer {
   public static void main(String[] args) {
      
      try {
         ServerSocket ss = new ServerSocket(8080);
         System.out.println("SocketWebServer Made");

         while (true) {
            System.out.println("\nStart while loop");
            Socket client = ss.accept();
//<editor-fold defaultstate="collapsed" desc="Copy-paste not-working">
            
//            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
//            output.writeChars("HTTP/1.1 200 OK");
//            output.writeChars("Content-Type: text/html");
//            output.writeChars("SocketWebServer: Bot");
//            output.writeChars("");
//            output.writeChars("<H1>Hi World</H1>");
//            output.flush();
//            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
//
//            while (!socket.isClosed()) {
//               try {
////                  action = (String) in.readObject();
////                  if (azione.equals("screenshot")) {
//                     BufferedImage screenshot = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
//                     ImageIO.write(screenshot, "jpg", output);
//                  }catch(AWTException | HeadlessException | IOException e){
//
//                    }
//               }
//</editor-fold>

            System.out.println("End read buffered reader");

            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: image/png");
            out.println("SocketWebServer: Bot");
            out.println("");
//            out.println("<H1>Hi World</H1>");

            int i;
            
//            String fileName = "C:\\Users\\Sammy Caychingco\\Documents\\NetBeansProjects\\SocketWeb\\SocketWebServer\\src\\Assets\\Untitled.png";
            String fileName = "C:\\Users\\Sammy Caychingco\\Documents\\NetBeansProjects\\SocketWeb\\SocketWebServer\\src\\Assets\\HighMufasa.png";

            out.write("Server: Apache/0.8.4\r\n");
            out.write("Content-type: image/png\r\n");

            File file = new File(fileName);

            if (file.exists()) {
               System.out.println("file exists");
               ImageInputStream imageInputStream = ImageIO.createImageInputStream(file);
               long size = imageInputStream.length();
               out.write("Content-Length: " + size + "\r\n");
               out.write("\r\n");

               BufferedImage bufferedImage = ImageIO.read(file);
               boolean success = ImageIO.write(bufferedImage, "jpeg", client.getOutputStream());
               System.out.println("Success: " + success); //true
               out.close();
               in.close();
               client.close();
               break;
            }
//<editor-fold defaultstate="collapsed" desc="Previous Implementation 2">
            
//            try {
//               DataOutputStream output = new DataOutputStream(socket.getOutputStream());
////               output.writeBoolean(true);
//
//               File file = new File(fileName);
//               FileInputStream fin = new FileInputStream(file);
//
//               output.writeUTF(fileName);
//               System.out.println("Sending image...");
//               byte[] readData = new byte[1024];
//
//               while ((i = fin.read(readData)) != -1) {
//                  output.write(readData, 0, i);
//               }
//               System.out.println("Image sent");
//
//               output.flush();
//               fin.close();
//            } catch (IOException ex) {
//               System.out.println("ERROR");
//               System.out.println("Image ::" + ex);
//
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Previous implementation">
//            BufferedReader br = new BufferedReader(
//                    new InputStreamReader(socket.getInputStream()));
//
//
//            String line;
//            while (!(line = br.readLine()).equals("")) {
//               System.out.println(line);
//            }
//
//
//            output.println("<img>");
//            BufferedImage image = ImageIO.read(new File("src/assets/HighMufasa.png"));
//            ImageIO.write(image, "PNG", socket.getOutputStream());
//            output.println("</img>");
//            
//            
//            String str;
//            FileReader f = new FileReader("src/assets/test.html");
//            BufferedReader b = new BufferedReader(f);
//            /*while((str = b.readLine()) != null){
//                System.out.println(str);
//             }*/
//            while((str=b.readLine()) != null){
//                output.println(str);
//            }
//</editor-fold>
            System.out.println("Done printing output");
            out.flush();
            client.close();
         }
      } catch (IOException ex) {
         Logger.getLogger(SocketWebServer.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
}

//<editor-fold defaultstate="collapsed" desc="Working copy-paste">
//import java.io.*;
//import java.net.*;
//
//class SocketWebServer {
//   static int i = 0;
//   private static int maxcon = 0;
//
//   public static void main(String args[]) {
//      try {
//         ServerSocket ss;
//         Socket s;
//
//         System.out.println("Server Started");
//         ss = new ServerSocket(8080);
//
//         while ((i++ < maxcon) || (maxcon == 0)) {
//            doComms connection;
//            s = ss.accept();
//            System.out.println(s);
//            System.out.println("Client " + i + "  Connected");
//            doComms conn_c = new doComms(s);
//            Thread t = new Thread(conn_c);
//            t.start();
//         }
//      } catch (IOException ioe) {
//         System.out.println("IOException on socket listen: " + ioe);
//         ioe.printStackTrace();
//      }
//
//   }
//}
//
//class doComms implements Runnable {
//   private Socket s;
//
//   doComms(Socket s) {
//      this.s = s;
//   }
//
//   public void run() {
//
//      try {
//         // Get input from the client
//         DataInputStream dis = new DataInputStream(s.getInputStream());
//         PrintStream out1 = new PrintStream(s.getOutputStream());
//
//         String str, extn = "";
//         str = dis.readUTF();
//         System.out.println("\n" + str);
//         int flag = 0, i;
//
//         for (i = 0; i < str.length(); i++) {
//
//            if (str.charAt(i) == '.' || flag == 1) {
//               flag = 1;
//               extn += str.charAt(i);
//            }
//         }
//
////**********************reading image*********************************//
//         if (extn.equals(".jpg") || extn.equals(".png")) {
//            File file = new File("RecievedImage" + str);
//            FileOutputStream fout = new FileOutputStream(file);
//
//            //receive and save image from client
//            byte[] readData = new byte[1024];
//            while ((i = dis.read(readData)) != -1) {
//               fout.write(readData, 0, i);
//               if (flag == 1) {
//                  System.out.println("Image Has Been Received");
//                  flag = 0;
//               }
//            }
//            fout.flush();
//            fout.close();
//
////****************************Reading Other Files******************//
//         } else {
//            FileWriter fstream = new FileWriter("ReceivedFile" + str);
//            PrintWriter out = new PrintWriter(fstream);
//
//            do {
//               str = dis.readUTF();
//               System.out.println(" " + str);
//               out.println(str);
//               out.flush();
//               if (str == null) {
//                  break;
//               }
//
//            } while (true);
//
//            System.out.println("One File Received");
//            out.close();
//         }
//      } catch (IOException ioe) {
//         System.out.println("");
//      }
//   }
//
//}
//</editor-fold>
