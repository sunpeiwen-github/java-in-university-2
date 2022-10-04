import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class Sever {
    int port=10000;
    Sever()
    {
        try {
            ServerSocket serverSocket=new ServerSocket(port);
            while(true)
            {
            Socket socket=serverSocket.accept();
                InputStream inputStream=socket.getInputStream();
                OutputStream outputStream=socket.getOutputStream();
                DataInputStream dataInputStream=new DataInputStream(inputStream);
                DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
                Scanner sc=new Scanner(System.in);
                System.out.print("我:");
                String s=sc.next();
                dataOutputStream.writeUTF(s);
                System.out.print("客户端:");
                System.out.println(dataInputStream.readUTF());
               /* serverSocket.close();
                socket.close();
                dataInputStream.close();
                dataOutputStream.close();*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String []args)
    {
        new Sever();
    }
}
