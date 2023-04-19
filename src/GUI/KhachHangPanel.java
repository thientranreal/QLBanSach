package GUI;

import BUS.KhachHang_BUS;
import DTO.KhachHang_DTO;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


public class KhachHangPanel extends JPanel {
	DefaultTableModel tablemodel;
	JTextField txtma;
	JTextField txtname;
	JComboBox combogender;
	JDateChooser birthday;
	JTextField txtdiachi;
	JTextField txtsdt;
	JTextField txtfind;
    public KhachHangPanel(){
    	Init();
		loadData();
    }
	public void loadDataFind(){
		if(txtfind.getText().trim().equals("")){
			loadData();
			return;
		}
		java.util.List<KhachHang_DTO> list = BUS.KhachHang_BUS.getInstance().getKhachHangLikeNameOrID(txtfind.getText().trim());
        tablemodel.setRowCount(0);
		for(KhachHang_DTO dto :list ){
			tablemodel.addRow(new Object[]{dto.getMaKH(),dto.getName(),dto.getGender(),dto.getBirth(),dto.getDiachi(),dto.getSdt()});
		}
		txtma.setText("");
		txtname.setText("");
		txtdiachi.setText("");
		txtsdt.setText("");
		combogender.setSelectedIndex(-1);
		birthday.setDate(null);
	}
	public void loadData(){
		java.util.List<DTO.KhachHang_DTO>list  =BUS.KhachHang_BUS.getInstance().getAllKhachHang();
		tablemodel.setRowCount(0);
		for(DTO.KhachHang_DTO dto : list){
			tablemodel.addRow(new Object[]{dto.getMaKH(),dto.getName(),dto.getGender(),dto.getBirth(),dto.getDiachi(),dto.getSdt()});
		}
		txtma.setText("");
		txtname.setText("");
		txtdiachi.setText("");
		txtsdt.setText("");
		combogender.setSelectedIndex(-1);
		birthday.setDate(null);
	}
    public void Init(){
    	setLayout(new GridLayout(1,2));
    	JPanel left = new JPanel();
		left.setBackground(new Color(250, 238, 232));
    	left.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(80,10,0,30)));
    	left.setLayout(new BorderLayout(0,10));
    	JPanel northleft = new JPanel();
		northleft.setBackground(new Color(250, 238, 232));
    	northleft.setPreferredSize(new Dimension(0,200));
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


        storelb.add(lbma);
        storelb.add(lbname);
        storelb.add(lbgender);
        storelb.add(lbbirth);
		storelb.add(lbdiachi);
		storelb.add(lbsdt);

        
    	
    	
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

    	
    	storetext.add(txtma);
    	storetext.add(txtname);
    	storetext.add(combogender);
    	storetext.add(birthday);
		storetext.add(txtdiachi);
		storetext.add(txtsdt);

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
				   if(txtma.getText().trim().equals("") || txtname.getText().trim().equals("")|| txtdiachi.getText().trim().equals("") || txtsdt.getText().trim().equals("")||birthday.getDate()==null || combogender.getSelectedIndex()==-1){
					  JOptionPane.showMessageDialog(null, "Vui lòng chọn đầy đủ thông tin");
				   }else{
					    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
						if (BUS.KhachHang_BUS.getInstance().isExists(txtma.getText().trim())){
							JOptionPane.showMessageDialog(null, "Đã tồn tại khách hàng");
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
					    if(!BUS.KhachHang_BUS.getInstance().insert(new KhachHang_DTO(txtma.getText().trim(),txtname.getText().trim(),f.format(birthday.getDate()),txtdiachi.getText().trim(),txtsdt.getText().trim(),combogender.getSelectedItem().toString()))){
							JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại");
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
				if(txtma.getText().trim().equals("") || txtname.getText().trim().equals("")|| txtdiachi.getText().trim().equals("") || txtsdt.getText().trim().equals("")||birthday.getDate()==null || combogender.getSelectedIndex()==-1){
					JOptionPane.showMessageDialog(null, "Vui lòng chọn đầy đủ thông tin");
				 }else{
					  SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
					  if (!BUS.KhachHang_BUS.getInstance().isExists(txtma.getText().trim())){
						  JOptionPane.showMessageDialog(null,"Không tồn tại khách hàng");
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
					  if(!BUS.KhachHang_BUS.getInstance().update(new KhachHang_DTO(txtma.getText().trim(),txtname.getText().trim(),f.format(birthday.getDate()),txtdiachi.getText().trim(),txtsdt.getText().trim(),combogender.getSelectedItem().toString()))){
						  JOptionPane.showMessageDialog(null, "Cập nhật khách hàng thất bại");
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
					JOptionPane.showMessageDialog(null, "Vui lòng nhập mã khách hàng cần xóa");
				}else{
					if (!BUS.KhachHang_BUS.getInstance().isExists(txtma.getText().trim())){
						JOptionPane.showMessageDialog(null,"Không tồn tại khách hàng");
						return;
					}

					if(!BUS.KhachHang_BUS.getInstance().remove(txtma.getText().trim())){
						JOptionPane.showMessageDialog(null, "Xóa khách hàng thất bại");
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
    	southfind.setBorder(BorderFactory.createTitledBorder("Tìm khách hàng"));


    	txtfind = new JTextField();
		txtfind.getDocument().addDocumentListener(new DocumentListener() {
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
		String column[] = {"Mã khách hàng","Họ tên","Giới tính","Ngày sinh","Địa chỉ","SDT"};

		tablemodel.setColumnIdentifiers(column);
		JTable table = new JTable(tablemodel);

		table.addMouseListener(
				new MouseAdapter() {

					@Override
					public void mouseClicked(java.awt.event.MouseEvent e
					) {
						int row = table.getSelectedRow();
						if(row!=-1){
							KhachHang_DTO dto = BUS.KhachHang_BUS.getInstance().getKhachHangByID(table.getValueAt(row,0).toString());
							txtma.setText(dto.getMaKH());
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
