import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Sever {
    int port=10001;
    GUI gui;
    InputStream inputStream;
    OutputStream outputStream;
    Sever()
    {
        gui=new GUI("服务端");
        gui.jTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data=gui.jTextField.getText();
                gui.jTextField.setText(null);
                byte[] buf=new byte[1024];
                buf=data.getBytes();
                try {
                    outputStream.write(buf);
                    gui.jTextArea.append("我说:"+data);
                    gui.jTextArea.append("\n");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        try {
            ServerSocket serverSocket=new ServerSocket(port);
            Socket client=serverSocket.accept();
            gui.jTextArea.append("连接的客户端"+client.getInetAddress().getHostName()+"\n\n");

            inputStream=client.getInputStream();
            outputStream=client.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true)
        {
            try {
                byte[] buf=new byte[1024];
                int a=inputStream.read(buf);
                String data1=new String(buf);
                System.out.println("int a"+a+" data"+data1);
                gui.jTextArea.append("客户端："+data1);
                gui.jTextArea.append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[]args)
    {
        new Sever();
    }
}
class GUI
{
    JFrame jf;
    JTextArea jTextArea;
    JScrollPane jScrollPane;
    JLabel jLabel;
    JTextField jTextField;
    GUI(String s)
    {
        jf=new JFrame(s);
        jf.setSize(700,500);
        jTextArea=new JTextArea("");
        jScrollPane=new JScrollPane(jTextArea);
        jLabel=new JLabel("发送内容");
        jTextField=new JTextField(9);
        jf.setLayout(new BorderLayout());
        JPanel jPanel=new JPanel();
        jPanel.setLayout(new FlowLayout());
        jPanel.add(jLabel);
        jPanel.add(jTextField);
        jf.add(BorderLayout.CENTER,jScrollPane);
        jf.add(BorderLayout.SOUTH,jPanel);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(3);

    }
}