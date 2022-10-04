import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Calendar;

public class Client {
    GUI gui;
    InputStream inputStream;
    OutputStream outputStream;
    Socket client;
    int port=10001;
    Client()
    {
        gui=new GUI("客户端");
        gui.jTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data=gui.jTextField.getText();
                gui.jTextField.setText(null);
                byte[] buf=new byte[1024];//将界面中的数据传给buf 之后传给输出流
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
            client=new Socket(InetAddress.getLocalHost(),port);
            gui.jTextArea.append("连接的服务器"+client.getInetAddress().getHostName()+"\n\n");
            inputStream=client.getInputStream();
            outputStream=client.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true)
        {
            byte[] buf=new byte[1024];
            try {
                inputStream.read(buf);
                String data=new String(buf);
                gui.jTextArea.append("服务器："+data);
                gui.jTextArea.append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String []args)
    {
        new Client();
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
        jTextArea=new JTextArea();
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
        jf.setDefaultCloseOperation(3);

    }
}