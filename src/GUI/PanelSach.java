package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BUS.Sach_BUS;
import DTO.Sach_DTO;
import DAO.JDBC;
import DAO.Sach_DAO;

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
	private JSpinner txtGia;
	private JSpinner txtGiaNHap;
	private JTextField txtTheLoai;
	private JSpinner txtSL;

	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnSave;
	private JButton btnDelete;
	private JButton btnReload;
	private JPanel pnTextbox;
	private Sach_BUS sachBus;
	private boolean isAdd = false;

	public void initTextbox() {
		JPanel pnSearch = new JPanel(new BorderLayout(0, 0));
		pnSearch.setBackground(new Color(250, 238, 232));
		pnSearch.setPreferredSize(new Dimension(MainFrame.CONTENT_WIDTH - 280, 70));
		pnSearch.setBorder(new EmptyBorder(16, 16, 16, 16));
		groupTextbox.add(pnSearch, BorderLayout.NORTH);
		
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
		btnSearch.addActionListener(e -> {
			try {
				String nameBook = txtSearch.getText().trim();
				loadData(sachBus.findByName(nameBook));
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		});

		JPanel tmp = new JPanel();
		tmp.setBackground(new Color(250, 238, 232));
		tmp.setPreferredSize(new Dimension(MainFrame.CONTENT_WIDTH - 280, 3));
		
		pnSearch.add(tmp, BorderLayout.SOUTH);

		pnTextbox = new JPanel(new GridLayout(8, 2, 4, 4));
		pnTextbox.setBackground(new Color(250, 238, 232));
		pnTextbox.setBorder(new EmptyBorder(12, 12, 12, 12));
		pnTextbox.setPreferredSize(new Dimension(MainFrame.CONTENT_WIDTH - 280, 250));
		
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

	void setOff() {
		for (Component item : pnTextbox.getComponents()) {
			item.setEnabled(false);
		}
		btnEdit.setEnabled(false);
		btnDelete.setEnabled(false);
		btnSave.setEnabled(false);

		btnAdd.setEnabled(true);
	}

	void setOn() {
		for (Component item : pnTextbox.getComponents()) {
			item.setEnabled(true);
		}
		btnSave.setEnabled(true);

		btnAdd.setEnabled(false);
		btnEdit.setEnabled(false);
		btnDelete.setEnabled(false);
	}

	void resetAllTextBox() {
		txtMaSach.setText("");
		txtTenSach.setText("");
		txtTacGia.setText("");
		txtSearch.setText("");
		txtTheLoai.setText("");
		txtNXB.setText("");
		txtGia.getModel().setValue(1);
		txtGiaNHap.getModel().setValue(1);
		txtSL.getModel().setValue(1);
	}

	public PanelSach() {
		sachBus = new Sach_BUS();
		model  = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		txtMaSach = new JTextField();
		txtTenSach = new JTextField();
		txtTacGia = new JTextField();

		SpinnerModel spModelGia = new SpinnerNumberModel(1, // initial value
				0, // min
				1000000000, // max
				1);// step
		txtGia = new JSpinner(spModelGia);
		SpinnerModel spModelGiaNhap = new SpinnerNumberModel(1, // initial value
				0, // min
				1000000000, // max
				1);// step
		txtGiaNHap = new JSpinner(spModelGiaNhap);
		SpinnerModel spModelSL = new SpinnerNumberModel(1, // initial value
				0, // min
				10000, // max
				1);// step
		txtSL = new JSpinner(spModelSL);
		txtNXB = new JTextField();
		txtTheLoai = new JTextField();

		txtSearch = new JTextField();
		groupTextbox = new JPanel();
		groupTextbox.setBackground(new Color(250, 238, 232));
		groupTextbox.setLayout(new BorderLayout(0, 0));
		groupTextbox.setPreferredSize(new Dimension(MainFrame.CONTENT_WIDTH - 280, 350));
		

		ImageIcon iconAdd = new ImageIcon(MainFrame.class.getResource("/images/add.png"));
		Image iconImage = iconAdd.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		iconAdd = new ImageIcon(iconImage);
		btnAdd = new JButton("Thêm", iconAdd);
		btnAdd.setIconTextGap(12);

		ImageIcon iconEdit = new ImageIcon(MainFrame.class.getResource("/images/edit.png"));
		iconImage = iconEdit.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		iconEdit = new ImageIcon(iconImage);
		btnEdit = new JButton("Sửa", iconEdit);
		btnEdit.setIconTextGap(12);

		ImageIcon iconSave = new ImageIcon(MainFrame.class.getResource("/images/save.png"));
		iconImage = iconSave.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		iconSave = new ImageIcon(iconImage);
		btnSave = new JButton("Lưu", iconSave);
		btnSave.setIconTextGap(12);

		ImageIcon iconDelete = new ImageIcon(MainFrame.class.getResource("/images/delete.png"));
		iconImage = iconDelete.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		iconDelete = new ImageIcon(iconImage);
		btnDelete = new JButton("Xóa", iconDelete);
		btnDelete.setIconTextGap(12);

		ImageIcon iconReload = new ImageIcon(MainFrame.class.getResource("/images/reload.png"));
		iconImage = iconReload.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		iconReload = new ImageIcon(iconImage);
		btnReload = new JButton("Làm mới", iconReload);
		btnReload.setIconTextGap(12);

		initGroupButton();
		groupButton.setPreferredSize(new Dimension(180, 350));

		groupTop = new JPanel();
		groupTop.setBackground(new Color(250, 238, 232));
		groupTop.setLayout(new BorderLayout(0, 0));
		groupTop.setPreferredSize(new Dimension(MainFrame.CONTENT_WIDTH, 350));
		groupTop.setBorder(new EmptyBorder(0, 0, 14, 0));
		groupBottom = new JPanel();
		groupBottom.setBackground(new Color(250, 238, 232));
		groupBottom.setPreferredSize(new Dimension(MainFrame.CONTENT_WIDTH, 350));
		groupBottom.setBorder(new EmptyBorder(14, 0, 0, 0));
		initTextbox();
		groupBottom.setLayout(new BorderLayout(0, 0));

		String column[] = { "Mã Sách", "Tên Sách", "Tác Giả", "Nhà Xuất Bản", "Giá", "Giá Nhập", "Thể loại",
				"Số lượng" };

		model.setColumnIdentifiers(column);
		table = new JTable(model);
		table.getSelectionModel()
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int pos = table.rowAtPoint(e.getPoint());
					txtMaSach.setText(table.getValueAt(pos, 0).toString());
					txtTenSach.setText(table.getValueAt(pos, 1).toString());
					txtTacGia.setText(table.getValueAt(pos, 2).toString());
					txtNXB.setText(table.getValueAt(pos, 3).toString());
					txtGia.getModel().setValue(Integer.parseInt(table.getValueAt(pos, 4).toString()));
					txtGiaNHap.getModel().setValue(Integer.parseInt(table.getValueAt(pos, 5).toString()));
					txtTheLoai.setText(table.getValueAt(pos, 6).toString());
					txtSL.getModel().setValue(Integer.parseInt(table.getValueAt(pos, 7).toString()));

					btnEdit.setEnabled(true);
					btnDelete.setEnabled(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		scrollPane = new JScrollPane(table);
		groupBottom.add(scrollPane, BorderLayout.CENTER);
		groupTop.add(groupTextbox, BorderLayout.WEST);
		groupTop.add(groupButton, BorderLayout.EAST);
		setBorder(new EmptyBorder(14, 14, 14, 14));
		setPreferredSize(new Dimension(MainFrame.CONTENT_WIDTH, MainFrame.CONTENT_HEIGHT));
		setLayout(new BorderLayout(0, 0));
		add(groupTop, BorderLayout.NORTH);
		add(groupBottom, BorderLayout.CENTER);
         setBackground(new Color(250, 238, 232));
		btnAdd.addActionListener(e -> {
			isAdd = true;
			resetAllTextBox();
			setOn();
			txtMaSach.requestFocus();
		});
		btnEdit.addActionListener(e -> {
			isAdd = false;
			setOn();
			txtMaSach.setEnabled(false);
			txtTenSach.requestFocus();
		});
		btnReload.addActionListener(e -> {
			resetAllTextBox();
			setOff();
			loadData(sachBus.findAll());
			isAdd = false;
		});
		btnSave.addActionListener(e -> {
			if (isAdd) {
				try {
					String maSach = txtMaSach.getText().trim();
					if(maSach.trim().equals("")){
						JOptionPane.showMessageDialog(null, "Mã sách không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (sachBus.isExist(maSach)) {
						JOptionPane.showMessageDialog(null, "Mã sách đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					} else {
						String tenS = txtTenSach.getText().trim();
						String tacGia = txtTacGia.getText().trim();
						String NXB = txtNXB.getText().trim();
						int gia = Integer.parseInt(spModelGia.getValue().toString());
						int giaNhap = Integer.parseInt(spModelGiaNhap.getValue().toString());
						String theLoai = txtTheLoai.getText().trim();
						int sl = Integer.parseInt(spModelSL.getValue().toString());

						Sach_DTO item = new Sach_DTO(maSach, tenS, tacGia, NXB, gia, giaNhap, theLoai, sl);
						sachBus.add(item);
						btnReload.doClick();
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			} else {
				try {
					String maSach = txtMaSach.getText().trim();
					String tenS = txtTenSach.getText().trim();
					String tacGia = txtTacGia.getText().trim();
					String NXB = txtNXB.getText().trim();
					int gia = Integer.parseInt(spModelGia.getValue().toString());
					int giaNhap = Integer.parseInt(spModelGiaNhap.getValue().toString());
					String theLoai = txtTheLoai.getText().trim();
					int sl = Integer.parseInt(spModelSL.getValue().toString());

					Sach_DTO item = new Sach_DTO(maSach, tenS, tacGia, NXB, gia, giaNhap, theLoai, sl);
					sachBus.update(item);
					btnReload.doClick();
				} catch (Exception e3) {
					// TODO: handle exception
					e3.printStackTrace();
				}

			}
		});
		btnDelete.addActionListener(e -> {
			try {
				String maSach = txtMaSach.getText().trim();
				sachBus.delete(maSach);
				btnReload.doClick();
			} catch (Exception e4) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null,
						"Không thể xóa sách này. Vui lòng xóa hơn chứa cuốn sách này trước!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}

		});

		loadData(sachBus.findAll());
		setOff();
	}

	public void initGroupButton() {
		groupButton = new JPanel(new GridLayout(5, 1, 8, 8));
		groupButton.setBackground(new Color(250, 238, 232));
		groupButton.setBorder(new EmptyBorder(12, 12, 12, 12));
		groupButton.add(btnAdd);
		groupButton.add(btnEdit);
		groupButton.add(btnSave);
		groupButton.add(btnDelete);
		groupButton.add(btnReload);
	}

	private void loadData(List<Sach_DTO> list) {
		model.setRowCount(0);
		try {
			for (Sach_DTO b : list) {
				Object data[] = new Object[8];
				data[0] = b.getMaSach();
				data[1] = b.getTenSach();
				data[2] = b.getTacGia();
				data[3] = b.getNXB();
				data[4] = b.getGia();
				data[5] = b.getGiaNhap();
				data[6] = b.getTheLoai();
				data[7] = b.getSl();
				model.addRow(data);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

}