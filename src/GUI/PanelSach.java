package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class PanelSach extends JPanel {

	private JPanel groupTop;
	private JPanel groupBottom;

	private JPanel groupTextbox;
	private JPanel groupButton;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scrollPane;

	private JTextField txtSearch;
	private JButton btnSearch;

	private JTextField txtMaSach;
	private JTextField txtTenSach;
	private JTextField txtTacGia;
	private JTextField txtNXB;
	private JTextField txtGia;
	private JTextField txtGiaNHap;
	private JTextField txtTheLoai;
	private JTextField txtSL;

	public void initTextbox() {
		JPanel pnSearch = new JPanel(new BorderLayout(0, 0));
		pnSearch.setPreferredSize(new Dimension(MainFrame.CONTENT_WIDTH - 200, 70));
		pnSearch.setBorder(new EmptyBorder(16, 16, 16, 16));
		groupTextbox.add(pnSearch, BorderLayout.NORTH);
		pnSearch.setBackground(Color.LIGHT_GRAY);
		JLabel lbSearch = new JLabel("Tìm kiếm: ", JLabel.RIGHT);
		lbSearch.setPreferredSize(new Dimension(150, 70));
		lbSearch.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbSearch.setForeground(Color.blue);
		pnSearch.add(lbSearch, BorderLayout.WEST);
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSearch.setPreferredSize(new Dimension(100, 50));
		pnSearch.add(txtSearch, BorderLayout.CENTER);

		ImageIcon iconSearch = new ImageIcon(MainFrame.class.getResource("/images/search.png"));
		Image iconImage = iconSearch.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		iconSearch = new ImageIcon(iconImage);
		btnSearch = new JButton("", iconSearch);
		pnSearch.add(btnSearch, BorderLayout.EAST);

		JPanel tmp = new JPanel();
		tmp.setPreferredSize(new Dimension(MainFrame.CONTENT_WIDTH - 200, 3));
		tmp.setBackground(Color.BLACK);
		pnSearch.add(tmp, BorderLayout.SOUTH);

		JPanel pnTextbox = new JPanel(new GridLayout(8, 2, 4, 4));
		pnTextbox.setBorder(new EmptyBorder(12, 12, 12, 12));
		pnTextbox.setPreferredSize(new Dimension(MainFrame.CONTENT_WIDTH - 200, 250));
		pnTextbox.setBackground(Color.lightGray);
		groupTextbox.add(pnTextbox, BorderLayout.CENTER);

		JLabel lbMaSach = new JLabel("Mã sách", JLabel.LEADING);
		lbMaSach.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnTextbox.add(lbMaSach);
		pnTextbox.add(txtMaSach);

		JLabel lbTenSach = new JLabel("Tên sách", JLabel.LEADING);
		lbTenSach.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnTextbox.add(lbTenSach);
		pnTextbox.add(txtTenSach);

		JLabel lbTacGia = new JLabel("Tác giả", JLabel.LEADING);
		lbTacGia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnTextbox.add(lbTacGia);
		pnTextbox.add(txtTacGia);

		JLabel lbNXB = new JLabel("Nhà xuất bản", JLabel.LEADING);
		lbNXB.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnTextbox.add(lbNXB);
		pnTextbox.add(txtNXB);

		JLabel lbGia = new JLabel("Giá", JLabel.LEADING);
		lbGia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnTextbox.add(lbGia);
		pnTextbox.add(txtGia);

		JLabel lbGiaNhap = new JLabel("Giá nhập", JLabel.LEADING);
		lbGiaNhap.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnTextbox.add(lbGiaNhap);
		pnTextbox.add(txtGiaNHap);

		JLabel lbTheLoai = new JLabel("Thể loại", JLabel.LEADING);
		lbTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnTextbox.add(lbTheLoai);
		pnTextbox.add(txtTheLoai);

		JLabel lbSL = new JLabel("Số lượng", JLabel.LEADING);
		lbSL.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnTextbox.add(lbSL);
		pnTextbox.add(txtSL);

	}

	public PanelSach() {
		txtMaSach = new JTextField();
		txtTenSach = new JTextField();
		txtTacGia = new JTextField();
		txtGia = new JTextField();
		txtGiaNHap = new JTextField();
		txtSL = new JTextField();
		txtNXB = new JTextField();
		txtTheLoai = new JTextField();

		txtSearch = new JTextField();
		groupTextbox = new JPanel();
		groupTextbox.setLayout(new BorderLayout(0, 0));
		groupTextbox.setPreferredSize(new Dimension(MainFrame.CONTENT_WIDTH - 200, 350));
		groupTextbox.setBackground(Color.LIGHT_GRAY);

		groupButton = new PanelGroupButton().getGroupButton();
		groupButton.setPreferredSize(new Dimension(150, 350));

		model = new DefaultTableModel();
		groupTop = new JPanel();
		groupTop.setLayout(new BorderLayout(0, 0));
		groupTop.setPreferredSize(new Dimension(MainFrame.CONTENT_WIDTH, 350));
		groupTop.setBorder(new EmptyBorder(0, 0, 14, 0));
		groupBottom = new JPanel();
		groupBottom.setPreferredSize(new Dimension(MainFrame.CONTENT_WIDTH, 350));
		groupBottom.setBorder(new EmptyBorder(14, 0, 0, 0));

		initTextbox();
		groupBottom.setLayout(new BorderLayout(0, 0));
		model = new DefaultTableModel();
		model.addColumn("Mã sách");
		model.addColumn("Tên sách");
		model.addColumn("Tác giả");
		model.addColumn("Nhà xuất bản");
		model.addColumn("Giá");
		model.addColumn("Giá nhập");
		model.addColumn("Thể loại");
		model.addColumn("Số lượng");

		for (int i = 0; i < 5; i++) {
			Object data[] = new Object[8];
			data[0] = "S0" + i;
			data[1] = "Sách " + i;
			data[2] = "Tác giả " + i;
			data[3] = "Hà Nội " + i;
			data[4] = "112,000 VND";
			data[5] = "35,000 VND";
			data[6] = "Tiểu thuyết";
			data[7] = "12";
			model.addRow(data);
		}

		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		groupBottom.add(scrollPane, BorderLayout.CENTER);
		groupTop.add(groupTextbox, BorderLayout.WEST);
		groupTop.add(groupButton, BorderLayout.EAST);

	}

	public JPanel getPanelSach() {
		JPanel pnResult = new JPanel();
		pnResult.setBorder(new EmptyBorder(14, 14, 14, 14));
		pnResult.setPreferredSize(new Dimension(MainFrame.CONTENT_WIDTH, MainFrame.CONTENT_HEIGHT));
		pnResult.setLayout(new BorderLayout(0, 0));
		pnResult.add(groupTop, BorderLayout.NORTH);
		pnResult.add(groupBottom, BorderLayout.CENTER);
		return pnResult;
	}
}
