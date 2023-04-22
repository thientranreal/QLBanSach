package GUI;


import DTO.NhanVien_DTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;

public class Login_GUI extends JFrame {
        public Login_GUI(){
            Init();
        }
        public void Init(){
            Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
            setIconImage(image);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400,220);
            setResizable(false);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(1,1));
            JPanel panel = new  JPanel();
            panel.setBackground(new Color(250, 238, 232));
            panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
            setBackground(Color.PINK);
            add(panel);
            JLabel lbtitle = new JLabel("Login");
            lbtitle.setFont(new Font(Font.SANS_SERIF,Font.BOLD,40));
            lbtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel lbusername = new JLabel("Username:");
            lbusername.setAlignmentX(Component.CENTER_ALIGNMENT);
            JTextField txtusername = new JTextField();
            txtusername.setPreferredSize(new Dimension(200,20));
            txtusername.setMaximumSize(new Dimension(200,20));
            txtusername.setMinimumSize(new Dimension(200,20));
            txtusername.setAlignmentX(Component.CENTER_ALIGNMENT);
            txtusername.setHorizontalAlignment(JTextField.CENTER);
            JLabel lbpassword = new JLabel("Password:");
            lbpassword.setAlignmentX(Component.CENTER_ALIGNMENT);
            JPasswordField txtpassword = new JPasswordField();
            txtpassword.setAlignmentX(Component.CENTER_ALIGNMENT);
            txtpassword.setMinimumSize(new Dimension(200,20));
            txtpassword.setMaximumSize(new Dimension(200,20));
            txtpassword.setPreferredSize(new Dimension(200,20));
            txtpassword.setEchoChar('*');

             txtpassword.setHorizontalAlignment(JPasswordField.CENTER);
             txtpassword.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     String username = txtusername.getText();
                     String password  = new String(txtpassword.getPassword());
                     NhanVien_DTO dto =BUS.NhanVien_BUS.getInstance().getNhanVienByUsernameAndPassword(username,password);
                     if(dto==null){
                         JOptionPane.showMessageDialog(null, "username hoặc password không hợp lệ");

                     }else{
                         setVisible(false);
                         MainFrame.getInstance(dto).setVisible(true);
                     }
                 }
             });
            JCheckBox chkremember = new JCheckBox("Show password");
            chkremember.setFocusable(false);
            chkremember.setAlignmentX(Component.CENTER_ALIGNMENT);
            chkremember.setBackground(new Color(250, 238, 232));
            chkremember.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange() == ItemEvent.SELECTED){
                        txtpassword.setEchoChar('\0');
                    }else{
                        txtpassword.setEchoChar('*');
                    }
                }
            });
            JButton btnLogin = new JButton("Login");
            btnLogin.setBackground(Color.WHITE);
            btnLogin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = txtusername.getText().trim();
                    String password  = new String(txtpassword.getPassword());
                    if(username.equals("")||password.equals("")){
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
                    return;
                    }
                    NhanVien_DTO dto =BUS.NhanVien_BUS.getInstance().getNhanVienByUsernameAndPassword(username,password);
                    if(dto==null){
                        JOptionPane.showMessageDialog(null, "username hoặc password không hợp lệ");
                    }else{
                        setVisible(false);
                        MainFrame.getInstance(dto).setVisible(true);
                    }
                }
            });
            btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnLogin.setFocusable(false);
            panel.add(lbtitle);
            panel.add(lbusername);
            panel.add(txtusername);
            panel.add(lbpassword);
            panel.add(txtpassword);
            panel.add(chkremember);
            panel.add(btnLogin);
        }



}
