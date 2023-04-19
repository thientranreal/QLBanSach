package GUI;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;


public class NhanVienPanel extends JPanel {
	DefaultTableModel tablemodel;
	JTextField txtma;
    	JTextField txtname;
    	JComboBox combogender;
    	JDateChooser birthday;
		JTextField txtdiachi;
		JTextField txtsdt;
		JComboBox comboquyen;
    	JTextField txtusername;
    	JTextField txtpassword;
	JTextField txtfind;
	private DTO.NhanVien_DTO account;
    public NhanVienPanel(DTO.NhanVien_DTO account){
		this.account =account;
    	Init();
		loadData();
    }
	public void loadData(){
		tablemodel.setRowCount(0);
		java.util.List<DTO.NhanVien_DTO> list = BUS.NhanVien_BUS.getInstance().getAllNhanVien();
		for(DTO.NhanVien_DTO dto :list){
			tablemodel.addRow(new Object[]{dto.getMaNV(),dto.getName(),dto.getGender(),dto.getBirth(),dto.getDiachi(),dto.getSdt(),dto.getTenQuyen()});
		}
        txtma.setText("");
		txtname.setText("");
		combogender.setSelectedIndex(-1);
		birthday.setDate(null);
		txtdiachi.setText("");
		txtsdt.setText("");
	    comboquyen.setSelectedIndex(-1);
		txtusername.setText("");
		txtpassword.setText("");
	}
	public void loadDataFind(){
		if(txtfind.getText().trim().equals("")){
			loadData();
			return;
		}
		tablemodel.setRowCount(0);
		java.util.List<DTO.NhanVien_DTO> list = BUS.NhanVien_BUS.getInstance().getNhanVienLikeNameOrID(txtfind.getText().trim());
		for(DTO.NhanVien_DTO dto :list){
			tablemodel.addRow(new Object[]{dto.getMaNV(),dto.getName(),dto.getGender(),dto.getBirth(),dto.getDiachi(),dto.getSdt(),dto.getTenQuyen()});
		}
		txtma.setText("");
		txtname.setText("");
		combogender.setSelectedIndex(-1);
		birthday.setDate(null);
		txtdiachi.setText("");
		txtsdt.setText("");
	    comboquyen.setSelectedIndex(-1);
		txtusername.setText("");
		txtpassword.setText("");
	}
    public void Init(){
    	setLayout(new GridLayout(1,2));
    	JPanel left = new JPanel();
		left.setBackground(new Color(250, 238, 232));
    	left.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(80,10,0,30)));
    	left.setLayout(new BorderLayout(0,10));
    	JPanel northleft = new JPanel();
		northleft.setBackground(new Color(250, 238, 232));
    	northleft.setPreferredSize(new Dimension(0,300));
    	northleft.setLayout(new BorderLayout());
    	
    	JPanel storelb = new JPanel();
		storelb.setBackground(new Color(250, 238, 232));
    	storelb.setPreferredSize(new Dimension(70,0));
    	storelb.setLayout(new GridLayout(0,1,0,5));
        JLabel lbma = new JLabel("Mã:");
        lbma.setHorizontalAlignment(JLabel.CENTER);
        JLabel lbname = new JLabel("Họ tên:");
        lbname.setHorizontalAlignment(JLabel.CENTER);
        JLabel lbgender = new  JLabel("Giới tính:");
        lbgender.setHorizontalAlignment(JLabel.CENTER);
        JLabel lbbirth = new JLabel("Ngày sinh:");
        lbbirth.setHorizontalAlignment(JLabel.CENTER);
		JLabel lbdiachi = new JLabel("Địa chỉ:");
		lbdiachi.setHorizontalAlignment(JLabel.CENTER);
		JLabel lbsdt = new JLabel("SDT:");
		lbsdt.setHorizontalAlignment(JLabel.CENTER);
		JLabel lbquyen = new JLabel("Quyền:");
		lbquyen.setHorizontalAlignment(JLabel.CENTER);
        JLabel username = new JLabel("Username:");
        username.setHorizontalAlignment(JLabel.CENTER);
        JLabel password = new JLabel("Mật khẩu:");
        password.setHorizontalAlignment(JLabel.CENTER);

        storelb.add(lbma);
        storelb.add(lbname);
        storelb.add(lbgender);
        storelb.add(lbbirth);
		storelb.add(lbdiachi);
		storelb.add(lbsdt);
		storelb.add(lbquyen);
        storelb.add(username);
        storelb.add(password);
        
    	
    	
    	JPanel storetext = new JPanel();
		storetext.setBackground(new Color(250, 238, 232));
    	storetext.setLayout(new GridLayout(0,1,0,5));
    	storetext.setBorder(BorderFactory.createEmptyBorder(0,10,0,30));
    	txtma = new JTextField();
		txtname = new JTextField();
		combogender = new JComboBox();
		combogender.addItem("Nam");
		combogender.addItem("Nữ");
		combogender.setSelectedIndex(-1);
        birthday = new JDateChooser();
		txtdiachi = new JTextField();
		 txtsdt = new JTextField();
		comboquyen = new JComboBox();
        java.util.List<String> listquyen = BUS.NhanVien_BUS.getInstance().getListQuyen();
		for(String item :listquyen){
			comboquyen.addItem(item);
		}
		comboquyen.setSelectedIndex(-1);

		 txtusername = new JTextField();
	     txtpassword = new JTextField();
    	
    	storetext.add(txtma);
    	storetext.add(txtname);
    	storetext.add(combogender);
    	storetext.add(birthday);
		storetext.add(txtdiachi);
		storetext.add(txtsdt);
		storetext.add(comboquyen);
    	storetext.add(txtusername);
    	storetext.add(txtpassword);
    	
    	northleft.add(storelb,BorderLayout.WEST);
    	northleft.add(storetext,BorderLayout.CENTER);
    	
    	JPanel storebutton = new JPanel();

		storebutton.setBackground(new Color(250, 238, 232));
    	storebutton.setLayout(new FlowLayout(FlowLayout.CENTER));
    	JButton btnadd = new JButton("Thêm");
    	btnadd.setFocusable(false);
    	btnadd.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
    	btnadd.setBackground(Color.WHITE);
        btnadd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				 if(txtma.getText().trim().equals("") || txtname.getText().trim().equals("")|| txtdiachi.getText().trim().equals("") || txtsdt.getText().trim().equals("")||birthday.getDate()==null || combogender.getSelectedIndex()==-1||txtusername.getText().trim().equals("")||txtpassword.getText().trim().equals("")||comboquyen.getSelectedIndex()==-1){
					JOptionPane.showMessageDialog(null, "Vui lòng chọn đầy đủ thông tin");
				 }else{
					  SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
					  if (BUS.NhanVien_BUS.getInstance().isExists(txtma.getText().trim())){
						  JOptionPane.showMessageDialog(null, "Đã tồn tại nhân viên");
						  return;
					  }
					  if(BUS.NhanVien_BUS.getInstance().isExistsUsername(txtusername.getText().trim())){
						  JOptionPane.showMessageDialog(null, "Đã tồn tại username");
						  return;
					  }
					  if(txtsdt.getText().trim().length()!=10){
						  JOptionPane.showMessageDialog(null, "SDT phải 10 chữ số");
						  return;
					  }
					 if(txtsdt.getText().trim().charAt(0)!='0'){
						 JOptionPane.showMessageDialog(null, "SDT phải bắt đầu bằng số 0");
						 return;
					 }
					  String maquyen = BUS.NhanVien_BUS.getInstance().getMaQuyenByTenQuyen(comboquyen.getSelectedItem().toString());
					  if(!BUS.NhanVien_BUS.getInstance().insert(new DTO.NhanVien_DTO(txtma.getText().trim(),txtname.getText().trim(),f.format(birthday.getDate()),txtdiachi.getText().trim(),txtsdt.getText().trim(),combogender.getSelectedItem().toString(),txtusername.getText().trim(),txtpassword.getText().trim(),maquyen,comboquyen.getSelectedItem().toString()))){
						  JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại");
					  }
					  loadData();

				 }
			}
	  });

    	JButton btnedit = new JButton("Cập nhật");
    	btnedit.setFocusable(false);
    	btnedit.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
    	btnedit.setBackground(Color.WHITE);
		btnedit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(txtma.getText().trim().equals("") || txtname.getText().trim().equals("")|| txtdiachi.getText().trim().equals("") || txtsdt.getText().trim().equals("")||birthday.getDate()==null || combogender.getSelectedIndex()==-1||txtusername.getText().trim().equals("")||txtpassword.getText().trim().equals("")||comboquyen.getSelectedIndex()==-1){
					JOptionPane.showMessageDialog(null, "Vui lòng chọn đầy đủ thông tin");
				 }else{
					  SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
					  if (!BUS.NhanVien_BUS.getInstance().isExists(txtma.getText().trim())){
						  JOptionPane.showMessageDialog(null,"Không tồn tại nhân viên");
						  return;
					  }
					  if(!BUS.NhanVien_BUS.getInstance().getNhanVienByID(txtma.getText().trim()).getUsername().equals(txtusername.getText().trim())){
						  JOptionPane.showMessageDialog(null,"Không được thay đổi username");
						  return;
					  }
					if(txtsdt.getText().trim().length()!=10){
						JOptionPane.showMessageDialog(null, "SDT phải 10 chữ số");
						return;
					}

					if(txtsdt.getText().trim().charAt(0)!='0'){
						JOptionPane.showMessageDialog(null, "SDT phải bắt đầu bằng số 0");
						return;
					}
					  String maquyen = BUS.NhanVien_BUS.getInstance().getMaQuyenByTenQuyen(comboquyen.getSelectedItem().toString());
					  if(!BUS.NhanVien_BUS.getInstance().update(new DTO.NhanVien_DTO(txtma.getText().trim(),txtname.getText().trim(),f.format(birthday.getDate()),txtdiachi.getText().trim(),txtsdt.getText().trim(),combogender.getSelectedItem().toString(),txtusername.getText().trim(),txtpassword.getText().trim(),maquyen,comboquyen.getSelectedItem().toString()))){
						  JOptionPane.showMessageDialog(null, "Cập nhật nhân viên thất bại");
					  }
					  loadData();

				 }
			}
		});
    	JButton btnremove = new JButton("Xóa");
    	btnremove.setFocusable(false);
    	btnremove.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
    	btnremove.setBackground(Color.WHITE);
		btnremove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(txtma.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "Vui lòng nhập mã nhân viên cần xóa");
				}else{
					if(!BUS.NhanVien_BUS.getInstance().isExists(txtma.getText().trim())){
						JOptionPane.showMessageDialog(null,"Không tồn tại nhân viên");
						return;
					}
					if(BUS.NhanVien_BUS.getInstance().getNhanVienByID(txtma.getText().trim()).getMaNV().equals(account.getMaNV())){
						JOptionPane.showMessageDialog(null,"Bạn không thể xoá chính bạn");
						return;
					}
					if(!BUS.NhanVien_BUS.getInstance().remove(txtma.getText().trim())){
						JOptionPane.showMessageDialog(null, "Xóa nhân viên thất bại");
					}
					loadData();
				}
			}
		});
    	storebutton.add(btnadd);
    	storebutton.add(btnedit);
    	storebutton.add(btnremove);
    	
    	
    	
    	left.add(northleft,BorderLayout.NORTH);
    	left.add(storebutton,BorderLayout.CENTER);
    	JPanel right = new JPanel();
		right.setBackground(new Color(250, 238, 232));
    	right.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	right.setLayout(new BorderLayout());
    	JPanel panelfind = new JPanel();
		panelfind.setBackground(new Color(250, 238, 232));
    	panelfind.setPreferredSize(new Dimension(0,100));
    	panelfind.setLayout(new BorderLayout());
    	JPanel southfind = new JPanel();
		southfind.setBackground(new Color(250, 238, 232));
		southfind.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
    	southfind.setPreferredSize(new Dimension(0,60));
    	southfind.setLayout(new FlowLayout(FlowLayout.LEFT));
    	southfind.setBorder(BorderFactory.createTitledBorder("Tìm nhân viên"));

    	 txtfind = new JTextField();
		txtfind.getDocument().addDocumentListener(new DocumentListener(){
			@Override
			public void insertUpdate(DocumentEvent e) {
				loadDataFind();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
                  loadDataFind();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
                 loadDataFind();
			}
		});
    	txtfind.setPreferredSize(new Dimension(200,27));

    	southfind.add(txtfind);
    	JPanel centerfind = new JPanel();
		centerfind.setBackground(new Color(250, 238, 232));
    	panelfind.add(southfind,BorderLayout.SOUTH);
    	panelfind.add(centerfind,BorderLayout.CENTER);

    	JPanel paneltable = new JPanel();
		paneltable.setBackground(new Color(250, 238, 232));
		paneltable.setLayout(new GridLayout(1,1));
		paneltable.setBorder(BorderFactory.createEmptyBorder(10,10,200,10));
		 tablemodel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String column[] = {"Mã nhân viên","Họ tên","Giới tính","Ngày sinh","Địa chỉ","SDT","Quyền"};

		tablemodel.setColumnIdentifiers(column);
		JTable table = new JTable(tablemodel);
		table.addMouseListener(
				new MouseAdapter() {

					@Override
					public void mouseClicked(java.awt.event.MouseEvent e
					) {
						int row = table.getSelectedRow();
						if(row!=-1){
							DTO.NhanVien_DTO dto = BUS.NhanVien_BUS.getInstance().getNhanVienByID(table.getValueAt(row,0).toString());
							txtma.setText(dto.getMaNV());
							txtname.setText(dto.getName());
							txtdiachi.setText(dto.getDiachi());
							txtsdt.setText(dto.getSdt());
							SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
							try {
								birthday.setDate(f.parse(dto.getBirth()));
							} catch (ParseException ex) {
								throw new RuntimeException(ex);
							}
                            combogender.setSelectedItem(dto.getGender());
						    comboquyen.setSelectedItem(dto.getTenQuyen());
							txtusername.setText(dto.getUsername());
							txtpassword.setText(dto.getPassword());

						}
					}
				}
		);
		table.getSelectionModel()
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane sp = new JScrollPane(table);
		paneltable.add(sp);
    	right.add(panelfind,BorderLayout.NORTH);
    	right.add(paneltable,BorderLayout.CENTER);
    	add(left);
    	add(right);
    	
    	
    	
    }

    
}
