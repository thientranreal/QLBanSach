package GUI;

import BUS.PhanQuyen_BUS;
import DTO.NhanVien_DTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;

public class MainFrame extends JFrame {
    private static MainFrame instance;
    private static NhanVien_DTO account;
    private static List<String> listchucnang;
    public static final int CONTENT_WIDTH = (1366-250);
    public static final int CONTENT_HEIGHT = (768-100);
    JButton QLSach;
    JButton QLHD;
    JButton QLNhapHang;
    JButton QLKhachHang;
    JButton QLThongKe;
    JButton QLNhanVien;
    JButton PhanQuyen;
    public static MainFrame getInstance(NhanVien_DTO account){
        if(instance == null){
            instance = new MainFrame(account);
        }
        return instance;
    }
    JPanel content;
    public MainFrame(NhanVien_DTO account){
        Init();this.account = account;
        listchucnang = PhanQuyen_BUS.getInstance().getChucNang(this.account.getMaquyen());
    }
    public void changetoCTHD(CT_HD_GUI ctHdGui){

        content.removeAll();
        content.add(ctHdGui.getPanel());
        content.revalidate();
        content.repaint();
    }
    public void changetoCTNhapHang(CT_NH_GUI ctnhGui){
        content.removeAll();
        content.add(ctnhGui.getPanel());
        content.revalidate();
        content.repaint();
    }
    public void changetoNhapHang(JFrame frame){
        content.removeAll();
        content.add(new NhapHang_GUI(MainFrame.getInstance(account)).getNhapHang_pnl());
        content.revalidate();
        content.repaint();
        frame.dispose();
    }
    public void changetoHoaDon(JFrame cthdframe){
        content.removeAll();
        content.add(new HoaDon_GUI(MainFrame.getInstance(account)).getHoaDon_pnl());
        content.revalidate();
        content.repaint();
        cthdframe.dispose();
    }
    public void clearColor(){
        QLSach.setBackground(new Color(250, 238, 232));
        QLSach.setForeground(Color.BLACK);
        QLHD.setBackground(new Color(250, 238, 232));
        QLHD.setForeground(Color.BLACK);
        QLNhapHang.setBackground(new Color(250, 238, 232));
        QLNhapHang.setForeground(Color.BLACK);
        QLKhachHang.setBackground(new Color(250, 238, 232));
        QLKhachHang.setForeground(Color.BLACK);
        QLThongKe.setBackground(new Color(250, 238, 232));
        QLThongKe.setForeground(Color.BLACK);
        QLNhanVien.setBackground(new Color(250, 238, 232));
        QLNhanVien.setForeground(Color.BLACK);
        PhanQuyen.setBackground(new Color(250, 238, 232));
        PhanQuyen.setForeground(Color.BLACK);
    }
    public void Init(){
        Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        setIconImage(image);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1,1));
        setUndecorated(true);
        JPanel mainpanel = new JPanel();
        add(mainpanel);
        mainpanel.setLayout(new BorderLayout());
        JPanel header = new JPanel();
        header.setLayout(new BorderLayout());
        header.setPreferredSize(new Dimension(0,100));
        header.setBackground(new Color(250, 238, 232));
        JPanel eastheader = new JPanel();
        eastheader.setLayout(new FlowLayout(FlowLayout.RIGHT));


        JButton btnclose = new JButton("X");

        btnclose.setFocusable(false);
        btnclose.setBackground(new Color(250, 238, 232));

        eastheader.add(btnclose);
        eastheader.setBackground(new Color(250, 238, 232));
        btnclose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getInstance(account).dispose();


            }
        });

        JLabel lbtitle = new JLabel("             Quản lý bán sách");
        lbtitle.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
        lbtitle.setHorizontalAlignment(JLabel.CENTER);
        header.add(eastheader,BorderLayout.EAST);
        header.add(lbtitle,BorderLayout.CENTER);
        JPanel chucnang = new JPanel();
        chucnang.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        chucnang.setBackground(new Color(250, 238, 232));
        chucnang.setPreferredSize(new Dimension(250,0));

        chucnang.setLayout(new GridLayout(0,1));

         QLSach = new JButton("Quản lý Sách");
        QLSach.setBackground(new Color(250, 238, 232));
        QLSach.setFocusable(false);


        QLSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listchucnang.contains("QLS")){
                   content.removeAll();
                   content.add(new PanelSach());
                   content.revalidate();
                   content.repaint();
                   clearColor();
                   QLSach.setBackground(new Color(141, 109, 99));
                   QLSach.setForeground(Color.WHITE);
                }else{
                    JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào chức năng này");
                }
            }
        });

        QLHD =  new JButton("Hoá đơn");
        QLHD.setBackground(new Color(250, 238, 232));
        QLHD.setFocusable(false);
        QLHD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if(listchucnang.contains("QLHD")) {
                    content.removeAll();
                    content.add(new HoaDon_GUI(MainFrame.getInstance(account)).getHoaDon_panel());
                    content.revalidate();
                    content.repaint();
                    clearColor();
                    QLHD.setBackground(new Color(141, 109, 99));
                    QLHD.setForeground(Color.WHITE);
                }else{
                    JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào chức năng này");
                }
            }
        });
        QLNhapHang = new JButton("Nhập hàng");

        QLNhapHang.setBackground(new Color(250, 238, 232));
        QLNhapHang.setFocusable(false);
        QLNhapHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listchucnang.contains("QLNH")) {
                    content.removeAll();
                    content.add(new NhapHang_GUI(MainFrame.getInstance(account)).getNhapHang_pnl());
                    content.revalidate();
                    content.repaint();
                    clearColor();
                    QLNhapHang.setBackground(new Color(141, 109, 99));
                    QLNhapHang.setForeground(Color.WHITE);
                }else{
                    JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào chức năng này");
                }

            }
        });
        QLKhachHang = new JButton("Khách hàng");

        QLKhachHang.setBackground(new Color(250, 238, 232));
        QLKhachHang.setFocusable(false);
        QLKhachHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listchucnang.contains("QLKH")) {
                    content.removeAll();
                    content.add(new KhachHangPanel());
                    content.revalidate();
                    content.repaint();
                    clearColor();
                    QLKhachHang.setBackground(new Color(141, 109, 99));
                    QLKhachHang.setForeground(Color.WHITE);
                }else{
                    JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào chức năng này");
                }

            }
        });
        QLThongKe = new JButton("Thống kê");

        QLThongKe.setBackground(new Color(250, 238, 232));
        QLThongKe.setFocusable(false);
        QLThongKe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listchucnang.contains("TK")) {
                    content.removeAll();
                    content.add(new ThongKe_GUI(MainFrame.getInstance(account)).getThongKe_pnl());
                    content.revalidate();
                    content.repaint();
                    clearColor();
                    QLThongKe.setBackground(new Color(141, 109, 99));
                    QLThongKe.setForeground(Color.WHITE);
                }else{
                    JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào chức năng này");
                }

            }
        });
        QLNhanVien = new JButton("Nhân viên");

        QLNhanVien.setBackground(new Color(250, 238, 232));
        QLNhanVien.setFocusable(false);
        QLNhanVien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listchucnang.contains("QLNV")) {
                    content.removeAll();
                    content.add(new NhanVienPanel(account));
                    content.revalidate();
                    content.repaint();
                    clearColor();
                    QLNhanVien.setBackground(new Color(141, 109, 99));
                    QLNhanVien.setForeground(Color.WHITE);
                }else{
                    JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào chức năng này");
                }

            }
        });

        PhanQuyen = new JButton("Phân quyền");

        PhanQuyen.setBackground(new Color(250, 238, 232));
        PhanQuyen.setFocusable(false);
        PhanQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listchucnang.contains("PQ")) {
                    content.removeAll();
                    content.add(new QuyenPanel());
                    content.revalidate();
                    content.repaint();
                    clearColor();
                    PhanQuyen.setBackground(new Color(141, 109, 99));
                    PhanQuyen.setForeground(Color.WHITE);
                }else{
                    JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào chức năng này");
                }

            }
        });


        chucnang.add(QLSach);
        chucnang.add(QLHD);
        chucnang.add(QLNhapHang);
        chucnang.add(QLKhachHang);
        chucnang.add(QLThongKe);
        chucnang.add(QLNhanVien);

        chucnang.add(PhanQuyen);



        content = new JPanel();
        content.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(10,10,10,10)));
        content.setBackground(new Color(250, 238, 232));
        content.setLayout(new GridLayout(1,1));

        mainpanel.add(header,BorderLayout.NORTH);
        mainpanel.add(chucnang,BorderLayout.WEST);
        mainpanel.add(content,BorderLayout.CENTER);




    }



}
