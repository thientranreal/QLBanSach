import GUI.HoaDon_GUI;
import GUI.NhapHang_GUI;
import GUI.ThongKe_GUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        new ThongKe_GUI();
//        new HoaDon_GUI();
        JFrame frame = new JFrame();
        frame.add(new ThongKe_GUI(frame).getThongKe_pnl());
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
