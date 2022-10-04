import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class Dialog {
    JDialog jd;
    JButton ok;
    JLabel l1;
    JFrame jf;
    JButton b1,b2,b3;
    Dialog()
    {
        jf=new JFrame("frame窗口");
        l1=new JLabel("第一个对话框");
        ok=new JButton("确认");
        jd=new JDialog(jf,"对话框",true);
        b1=new JButton("第一个对话框");
        b2=new JButton("第二个对话框");
        b3=new JButton("第三个对话框");
        jf.setLayout(new FlowLayout());
        jf.add(b1);
        jf.add(b2);
        jf.add(b3);

        jd.setLayout(new BorderLayout());
        jd.add(BorderLayout.NORTH,l1);
        jd.add(ok);
        jd.setLayout(new FlowLayout());
        jd.setSize(300, 200);
        jd.setLocationRelativeTo(null);
        //jd.setVisible(true);
        jf.setSize(300, 200);
        //JOptionPane.showConfirmDialog(jf,"第二个对话框",null, JOptionPane.INFORMATION_MESSAGE);//1
        //JOptionPane.showMessageDialog(jf, "第三个对话框",null, 2);
        jf.setDefaultCloseOperation(3);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        b1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            jd.setVisible(true);
        }
        });
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jd.setVisible(false);
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(jf,"第二个对话框",null, JOptionPane.INFORMATION_MESSAGE);//1
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(jf, "第三个对话框",null, 2);
            }
        });
    }
    public static void main(String []args) {
        new Dialog();
    }
}